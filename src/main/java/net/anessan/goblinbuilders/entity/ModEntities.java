package net.anessan.goblinbuilders.entity;

import net.anessan.goblinbuilders.GoblinBuilders;
import net.anessan.goblinbuilders.entity.custom.GoblinBuilderEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GoblinBuilders.MOD_ID);

    public static final RegistryObject<EntityType<GoblinBuilderEntity>> GOBLINBUILDER =
            ENTITY_TYPES.register("goblinbuilder", () -> EntityType.Builder.of(GoblinBuilderEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 1f).build("goblinbuilder"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }


}
