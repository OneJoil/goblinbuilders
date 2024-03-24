package net.anessan.goblinbuilders.screen;

import net.anessan.goblinbuilders.entity.custom.GoblinBuilderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class GoblinBuilderMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final static HashMap<String, Object> guistate = new HashMap<>();
    private final GoblinBuilderEntity goblinBuilder;
    public final Level world;
    public int x, y, z;

    private final Map<Integer, Slot> customSlots = new HashMap<>();
    private IItemHandler internal;
    private ContainerLevelAccess access = ContainerLevelAccess.NULL;
    private Supplier<Boolean> boundItemMatcher = null;
    private BlockEntity boundBlockEntity = null;
    private Entity boundEntity = null;
    private boolean bound = false;

    protected GoblinBuilderMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf extraData, final GoblinBuilderEntity pGoblinBuilder) {
        super(ModMenuTypes.GOBLIN_BUILDER_GUI.get(), pContainerId);
        this.goblinBuilder = pGoblinBuilder;
        this.world = pPlayerInventory.player.level();
        this.internal = new ItemStackHandler(0);
        BlockPos pos = null;
        if (extraData != null) {
            pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
            access = ContainerLevelAccess.create(world, pos);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.bound) {
            if (this.boundItemMatcher != null)
                return this.boundItemMatcher.get();
            else if (this.boundBlockEntity != null)
                return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
            else if (this.boundEntity != null)
                return this.boundEntity.isAlive();
        }
        return true;
    }

    @Override
    public Map<Integer, Slot> get() {
        return customSlots;
    }
}