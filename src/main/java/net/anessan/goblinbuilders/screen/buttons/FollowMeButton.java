package net.anessan.goblinbuilders.screen.buttons;

import net.minecraft.world.entity.Entity;

public class FollowMeButton {

    public static void execute(Entity entity) {
        if (entity == null)
            return;
        entity.setShiftKeyDown(true);
    }
}
