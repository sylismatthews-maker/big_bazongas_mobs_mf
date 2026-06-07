package com.dumbmovie.mod;

import com.dumbmovie.mod.entity.CustomWaifuMobEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DumbMovieMod.MODID)
public class DumbMovieMod {
    public static final String MODID = "dumbmovie";

    public static final net.minecraftforge.registries.DeferredRegister<net.minecraft.world.entity.EntityType<?>> ENTITIES = 
        net.minecraftforge.registries.DeferredRegister.create(net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES, MODID);

    public static final net.minecraftforge.registries.RegistryObject<net.minecraft.world.entity.EntityType<CustomWaifuMobEntity>> WAIFU_MOB = 
        ENTITIES.register("waifu_mob", () -> net.minecraft.world.entity.EntityType.Builder.of(CustomWaifuMobEntity::new, net.minecraft.world.entity.MobCategory.MONSTER)
                .sized(0.6F, 1.95F) 
                .build("waifu_mob"));

    public DumbMovieMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModModEvents {
        @net.minecraftforge.eventbus.api.SubscribeEvent
        public static void entityAttributes(net.minecraftforge.event.entity.EntityAttributeCreationEvent event) {
            event.put(WAIFU_MOB.get(), CustomWaifuMobEntity.createAttributes().build());
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEvents {
        @net.minecraftforge.eventbus.api.SubscribeEvent
        public static void onMobSpawn(net.minecraftforge.event.entity.living.MobSpawnEvent.FinalizeSpawn event) {
            if (event.getEntity() instanceof CustomWaifuMobEntity mob) {
                if (mob.level().random.nextInt(2) == 0) {
                    mob.addTag("is_dumbmovie_variant");
                }
            }
        }
    }
}
