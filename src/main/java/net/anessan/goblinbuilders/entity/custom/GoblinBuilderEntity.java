package net.anessan.goblinbuilders.entity.custom;

import io.netty.buffer.Unpooled;
import net.anessan.goblinbuilders.entity.ModEntities;
import net.anessan.goblinbuilders.screen.GoblinBuilderMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GoblinBuilderEntity extends TamableAnimal implements InventoryCarrier {
    private final SimpleContainer inventory = new SimpleContainer(8);

    public GoblinBuilderEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setCanPickUpLoot(true);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
    }

    @Nullable
    public GoblinBuilderEntity getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        GoblinBuilderEntity goblinBuilderEntity = ModEntities.GOBLINBUILDER.get().create(pLevel);
        if (goblinBuilderEntity != null) {
            UUID uuid = this.getOwnerUUID();
            if (uuid != null) {
                goblinBuilderEntity.setOwnerUUID(uuid);
                goblinBuilderEntity.setTame(true);
            }
        }

        return goblinBuilderEntity;
    }

    // INVENTORY CARRIER

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.writeInventoryToTag(pCompound);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readInventoryFromTag(pCompound);
    }

    protected boolean canAddToInventory(ItemStack pStack) {
        return this.inventory.canAddItem(pStack);
    }

    protected void pickUpItem(ItemEntity pItemEntity) {
        this.onItemPickup(pItemEntity);
        GoblinBuilderEntityAi.pickUpItem(this, pItemEntity);
    }

    public boolean wantsToPickUp(ItemStack pStack) {
        return ForgeEventFactory.getMobGriefingEvent(this.level(), this) && this.canPickUpLoot() && GoblinBuilderEntityAi.wantsToPickup(this, pStack);
    }

    // INVENTORY CARRIER

    // MOB INTERACTION

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
        if (pPlayer instanceof ServerPlayer serverPlayer && this.isOwnedBy(pPlayer)) {
            NetworkHooks.openScreen(serverPlayer, new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return Component.literal("Goblin Builder");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
                    FriendlyByteBuf packetBuffer = new FriendlyByteBuf(Unpooled.buffer());
                    packetBuffer.writeBlockPos(pPlayer.blockPosition());
                    packetBuffer.writeByte(0);
                    packetBuffer.writeVarInt(GoblinBuilderEntity.this.getId());
                    return new GoblinBuilderMenu(id, inventory, packetBuffer);
                }
            }, buf -> {
                buf.writeBlockPos(pPlayer.blockPosition());
                buf.writeByte(0);
                buf.writeVarInt(this.getId());
            });
        }

        else if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(pPlayer) || this.isTame() || itemstack.is(Items.EMERALD) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (itemstack.is(Items.EMERALD)) {
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                this.tame(pPlayer);
                this.level().broadcastEntityEvent(this, (byte) 7);
            } else {
                this.level().broadcastEntityEvent(this, (byte) 6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
        super.mobInteract(pPlayer, pHand);
        return retval;
    }

    // MOB INTERACTION

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Override
    public SimpleContainer getInventory() {
        return this.inventory;
    }

}