package net.scottnotfound.merculab.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryBuilder;
import net.scottnotfound.merculab.block.labware.*;
import net.scottnotfound.merculab.chemical.Chemical;
import net.scottnotfound.merculab.tileentity.labware.TileEntityBeaker;
import net.scottnotfound.merculab.tileentity.labware.TileEntityFlask;
import net.scottnotfound.merculab.tileentity.labware.TileEntityJar;
import net.scottnotfound.merculab.tileentity.labware.TileEntityVial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod("merculab")
@SuppressWarnings("ConstantConditions")
public final class MercuLab {

    private static final Logger LOGGER = LogManager.getLogger();

    public MercuLab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.register(this);
        init();
    }

    private void init() {
        Blocks.init();
        Items.init();
        TileEntityTypes.init();
        Chemicals.init();
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
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onRegistries(RegistryEvent.NewRegistry event) {
            Registry.CHEMICALS = new RegistryBuilder<Chemical>()
                    .setName(new ResourceLocation("merculab:chemicals"))
                    .setDefaultKey(new ResourceLocation("merculab:nil"))
                    .setType(Chemical.class)
                    .create();
        }
        @SubscribeEvent
        public static void onBlocks(final RegistryEvent.Register<Block> event) {
            Blocks.registerFor(event.getRegistry());
        }
        @SubscribeEvent
        public static void onItems(final RegistryEvent.Register<Item> event) {
            Items.registerFor(event.getRegistry());
        }
        @SubscribeEvent
        public static void onPotions(RegistryEvent.Register<Potion> event) {

        }
        @SubscribeEvent
        public static void onBiomes(RegistryEvent.Register<Biome> event) {

        }
        @SubscribeEvent
        public static void onSounds(RegistryEvent.Register<SoundEvent> event) {

        }
        @SubscribeEvent
        public static void onPotionTypes(RegistryEvent.Register<PotionType> event) {

        }
        @SubscribeEvent
        public static void onEnchantments(RegistryEvent.Register<Enchantment> event) {

        }
        @SubscribeEvent
        public static void onVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {

        }
        @SubscribeEvent
        public static void onEntities(RegistryEvent.Register<EntityType<?>> event) {

        }
        @SubscribeEvent
        public static void onTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            TileEntityTypes.registerFor(event.getRegistry());
        }
        @SubscribeEvent
        public static void onDims(RegistryEvent.Register<ModDimension> event) {

        }
        @SubscribeEvent
        public static void onDataSerializers(RegistryEvent.Register<DataSerializerEntry> event) {

        }
        @SubscribeEvent
        public static void onChemicals(RegistryEvent.Register<Chemical> event) {
            Chemicals.registerFor(event.getRegistry());
        }
    }

    public static final class Registry {

        public static IForgeRegistry<Chemical> CHEMICALS = null;

    }

    @ObjectHolder("merculab")
    public static final class Blocks {

        public static final Block LAB_BENCH = null;

        public static final Block BEAKER = null;
        public static final Block FLASK = null;
        public static final Block JAR = null;
        public static final Block VIAL = null;

        private static List<Block> insts = new ArrayList<>();
        private static List<Block> special = new ArrayList<>();
        static void registerFor(IForgeRegistry<Block> registry) {
            insts.forEach(registry::register);
        }
        private static void make(Block block, String id, List<Block> list) {
            list.add(block.setRegistryName("merculab", id));
        }
        static void init() {
            make(new BlockBeaker(Block.Properties.create(Material.GLASS)), "beaker", insts);
            make(new BlockFlask(Block.Properties.create(Material.GLASS)), "flask", insts);
            make(new BlockJar(Block.Properties.create(Material.GLASS)), "jar", insts);
            make(new BlockVial(Block.Properties.create(Material.GLASS)), "vial", insts);
            make(new BlockLabBench(Block.Properties.create(Material.ROCK)), "lab_bench", insts);
        }
    }

    @ObjectHolder("merculab")
    public static final class Items {

        private static List<Item> insts = new ArrayList<>();
        static void registerFor(IForgeRegistry<Item> registry) {
            insts.forEach(registry::register);
        }
        private static void make(Item item, String id) {
            insts.add(item.setRegistryName("merculab", id));
        }
        static void init() {
            Blocks.insts.forEach((block) -> make(new ItemBlock(block, new Item.Properties()),
                                                 block.getRegistryName().getPath()));
        }
    }

    @ObjectHolder("merculab")
    public static final class Potions {

    }

    @ObjectHolder("merculab")
    public static final class Biomes {

    }

    @ObjectHolder("merculab")
    public static final class SoundEvents {

    }

    @ObjectHolder("merculab")
    public static final class PotionTypes {

    }

    @ObjectHolder("merculab")
    public static final class Enchantments {

    }

    @ObjectHolder("merculab")
    public static final class VillagerProfessions {

    }

    @ObjectHolder("merculab")
    public static final class EntityTypes {

    }

    @ObjectHolder("merculab")
    public static final class TileEntityTypes {

        public static final TileEntityType BEAKER = null;
        public static final TileEntityType FLASK = null;
        public static final TileEntityType JAR = null;
        public static final TileEntityType VIAL = null;

        private static List<TileEntityType<?>> insts = new ArrayList<>();
        static void registerFor(IForgeRegistry<TileEntityType<?>> registry) {
            insts.forEach(registry::register);
        }
        private static void make(Supplier<? extends TileEntity> s, String id) {
            insts.add(TileEntityType.Builder.create(s)
                                            .build(null)
                                            .setRegistryName("merculab", id));
        }
        static void init() {
            make(TileEntityBeaker::new, "beaker");
            make(TileEntityFlask::new, "flask");
            make(TileEntityJar::new, "jar");
            make(TileEntityVial::new, "vial");
        }
    }

    @ObjectHolder("merculab")
    public static final class ModDimensions {

    }

    @ObjectHolder("merculab")
    public static final class DataSerializers {

    }

    @ObjectHolder("merculab")
    public static final class Chemicals {

        public static final Chemical SODIUM_CHLORIDE = null;
        public static final Chemical WATER = null;
        public static final Chemical METHANE = null;
        public static final Chemical FERRIC_OXIDE = null;

        private static List<Chemical> insts = new ArrayList<>();
        static void registerFor(IForgeRegistry<Chemical> registry) {
            insts.forEach(registry::register);
        }
        private static void make(Chemical chemical, String id) {
            insts.add(chemical.setRegistryName("merculab", id));
        }
        static void init() {
            make(new Chemical(Chemical.Properties.SODIUM_CHLORIDE), "sodium_chloride");
            make(new Chemical(Chemical.Properties.WATER), "water");
            make(new Chemical(Chemical.Properties.METHANE), "methane");
            make(new Chemical(Chemical.Properties.FERRIC_OXIDE), "ferric_oxide");
        }

    }

}
