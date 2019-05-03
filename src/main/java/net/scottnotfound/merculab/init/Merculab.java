package net.scottnotfound.merculab.init;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataSerializerEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("merculab")
public class Merculab {

    private static final Logger LOGGER = LogManager.getLogger();

    public Merculab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLCommonSetupEvent event) {

    }

    private void clientSetup(FMLClientSetupEvent event) {

    }

    private void enqueueIMC(InterModEnqueueEvent event) {

    }

    private void processIMC(InterModProcessEvent event) {

    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public class RegistryEvents {
        @SubscribeEvent
        public void onBlocks(RegistryEvent.Register<Block> event) {

        }
        @SubscribeEvent
        public void onItems(RegistryEvent.Register<Item> event) {

        }
        @SubscribeEvent
        public void onPotions(RegistryEvent.Register<Potion> event) {

        }
        @SubscribeEvent
        public void onBiomes(RegistryEvent.Register<Biome> event) {

        }
        @SubscribeEvent
        public void onSounds(RegistryEvent.Register<SoundEvent> event) {

        }
        @SubscribeEvent
        public void onPotionTypes(RegistryEvent.Register<PotionType> event) {

        }
        @SubscribeEvent
        public void onEnchantments(RegistryEvent.Register<Enchantment> event) {

        }
        @SubscribeEvent
        public void onVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {

        }
        @SubscribeEvent
        public void onEntities(RegistryEvent.Register<EntityType<?>> event) {

        }
        @SubscribeEvent
        public void onTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {

        }
        @SubscribeEvent
        public void onDims(RegistryEvent.Register<ModDimension> event) {

        }
        @SubscribeEvent
        public void onDataSerializers(RegistryEvent.Register<DataSerializerEntry> event) {

        }
    }

}
