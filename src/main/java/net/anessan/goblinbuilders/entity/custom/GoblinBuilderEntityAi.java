package net.anessan.goblinbuilders.entity.custom;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class GoblinBuilderEntityAi {
    protected static void pickUpItem(GoblinBuilderEntity pGoblinBuilderEntity, ItemEntity pItemEntity) {
        ItemStack itemstack;
        if (pItemEntity.getItem().is(Items.EMERALD)) {
            pGoblinBuilderEntity.take(pItemEntity, pItemEntity.getItem().getCount());
            itemstack = pItemEntity.getItem();
            pItemEntity.discard();
        } else {
            pGoblinBuilderEntity.take(pItemEntity, 1);
            itemstack = removeOneItemFromItemEntity(pItemEntity);
        }
    }

    protected static boolean wantsToPickup(GoblinBuilderEntity pGoblinBuilderEntity, ItemStack pStack) {
        boolean flag = pGoblinBuilderEntity.canAddToInventory(pStack);
        if (pStack.is(Items.EMERALD)) {
            return flag;
        } else {
            return false;
        }
    }

    private static ItemStack removeOneItemFromItemEntity(ItemEntity pItemEntity) {
        ItemStack itemstack = pItemEntity.getItem();
        ItemStack itemstack1 = itemstack.split(1);
        if (itemstack.isEmpty()) {
            pItemEntity.discard();
        } else {
            pItemEntity.setItem(itemstack);
        }
        return itemstack1;
    }

}
