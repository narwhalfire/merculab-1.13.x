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
import net.scottnotfound.merculab.block.chemical.BlockChemical;
import net.scottnotfound.merculab.block.labware.*;
import net.scottnotfound.merculab.chemical.Chemical;
import net.scottnotfound.merculab.item.chemical.ItemChemical;
import net.scottnotfound.merculab.tileentity.labware.TileEntityBeaker;
import net.scottnotfound.merculab.tileentity.labware.TileEntityFlask;
import net.scottnotfound.merculab.tileentity.labware.TileEntityJar;
import net.scottnotfound.merculab.tileentity.labware.TileEntityVial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Supplier;

@Mod(MercuLab.MODID)
@SuppressWarnings("unused")
public final class MercuLab {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "merculab";

    public MercuLab() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        MinecraftForge.EVENT_BUS.register(this);
        init();
    }

    /**
     * Initializing these before the registry events are fired allows the registering of blocks and items
     * depending on what chemicals are being registered.
     */
    private void init() {
        priorityInit();
        TileEntityTypes.init();
    }

    /**
     * Chemicals must be initialized first, then blocks, then items, then whatever
     */
    private void priorityInit() {
        Chemicals.init();
        Blocks.init();
        Items.init();
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
        public static void onRegistries(final RegistryEvent.NewRegistry event) {
            Registry.CHEMICALS = new RegistryBuilder<Chemical>()
                    .setName(new ResourceLocation(MODID, "chemicals"))
                    .setDefaultKey(new ResourceLocation(MODID, "nil"))
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
        public static void onPotions(final RegistryEvent.Register<Potion> event) {

        }
        @SubscribeEvent
        public static void onBiomes(final RegistryEvent.Register<Biome> event) {

        }
        @SubscribeEvent
        public static void onSounds(final RegistryEvent.Register<SoundEvent> event) {

        }
        @SubscribeEvent
        public static void onPotionTypes(final RegistryEvent.Register<PotionType> event) {

        }
        @SubscribeEvent
        public static void onEnchantments(final RegistryEvent.Register<Enchantment> event) {

        }
        @SubscribeEvent
        public static void onVillagers(final RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {

        }
        @SubscribeEvent
        public static void onEntities(final RegistryEvent.Register<EntityType<?>> event) {

        }
        @SubscribeEvent
        public static void onTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            TileEntityTypes.registerFor(event.getRegistry());
        }
        @SubscribeEvent
        public static void onDims(final RegistryEvent.Register<ModDimension> event) {

        }
        @SubscribeEvent
        public static void onDataSerializers(final RegistryEvent.Register<DataSerializerEntry> event) {

        }
        @SubscribeEvent
        public static void onChemicals(final RegistryEvent.Register<Chemical> event) {
            Chemicals.registerFor(event.getRegistry());
        }
    }

    public static final class Registry {

        public static IForgeRegistry<Chemical> CHEMICALS = null;

    }

    @ObjectHolder(MODID)
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
            list.add(block.setRegistryName(MODID, id));
        }
        static void init() {
            make(new BlockBeaker(Block.Properties.create(Material.GLASS)), "beaker", insts);
            make(new BlockFlask(Block.Properties.create(Material.GLASS)), "flask", insts);
            make(new BlockJar(Block.Properties.create(Material.GLASS)), "jar", insts);
            make(new BlockVial(Block.Properties.create(Material.GLASS)), "vial", insts);
            make(new BlockLabBench(Block.Properties.create(Material.ROCK)), "lab_bench", insts);
            Chemicals.blockInsts.forEach(chemical -> make(
                    new BlockChemical(chemical, Block.Properties.create(Material.ROCK)),
                    chemical.getRegistryName().getPath() + "_block", insts));
        }
    }

    @ObjectHolder(MODID)
    public static final class Items {

        private static List<Item> insts = new ArrayList<>();
        static void registerFor(IForgeRegistry<Item> registry) {
            insts.forEach(registry::register);
        }
        private static void make(Item item, String id, List<Item> list) {
            list.add(item.setRegistryName(MODID, id));
        }
        static void init() {
            Blocks.insts.forEach(block -> make(new ItemBlock(block, new Item.Properties()),
                                               block.getRegistryName().getPath(), insts));
            Chemicals.itemInsts.forEach(chemical -> make(new ItemChemical(chemical, new Item.Properties()),
                                                         chemical.getRegistryName().getPath(), insts));
            Chemicals.blockInsts.forEach(chemical -> make(new ItemChemical(chemical, new Item.Properties()),
                                                          chemical.getRegistryName().getPath(), insts));
        }
    }

    @ObjectHolder(MODID)
    public static final class Potions {

    }

    @ObjectHolder(MODID)
    public static final class Biomes {

    }

    @ObjectHolder(MODID)
    public static final class SoundEvents {

    }

    @ObjectHolder(MODID)
    public static final class PotionTypes {

    }

    @ObjectHolder(MODID)
    public static final class Enchantments {

    }

    @ObjectHolder(MODID)
    public static final class VillagerProfessions {

    }

    @ObjectHolder(MODID)
    public static final class EntityTypes {

    }

    @ObjectHolder(MODID)
    public static final class TileEntityTypes {

        public static final TileEntityType BEAKER = null;
        public static final TileEntityType FLASK = null;
        public static final TileEntityType JAR = null;
        public static final TileEntityType VIAL = null;

        private static List<TileEntityType<?>> insts = new ArrayList<>();
        static void registerFor(IForgeRegistry<TileEntityType<?>> registry) {
            insts.forEach(registry::register);
        }
        private static void make(Supplier<? extends TileEntity> s, String id, List<TileEntityType<?>> list) {
            list.add(TileEntityType.Builder.create(s)
                                            .build(null)
                                            .setRegistryName(MODID, id));
        }
        static void init() {
            make(TileEntityBeaker::new, "beaker", insts);
            make(TileEntityFlask::new, "flask", insts);
            make(TileEntityJar::new, "jar", insts);
            make(TileEntityVial::new, "vial", insts);
        }
    }

    @ObjectHolder(MODID)
    public static final class ModDimensions {

    }

    @ObjectHolder(MODID)
    public static final class DataSerializers {

    }

    @ObjectHolder(MODID)
    public static final class Chemicals {

        public static final Chemical SODIUM_CHLORIDE = null;
        public static final Chemical WATER = null;
        public static final Chemical METHANE = null;
        public static final Chemical FERRIC_OXIDE = null;
        public static final Chemical TUNGSTEN_CARBIDE = null;

        private static List<Chemical> normInsts = new ArrayList<>();
        private static List<Chemical> blockInsts = new ArrayList<>();
        private static List<Chemical> fluidInsts = new ArrayList<>();
        private static List<Chemical> itemInsts = new ArrayList<>();
        static void registerFor(IForgeRegistry<Chemical> registry) {
            normInsts.forEach(registry::register);
            blockInsts.forEach(registry::register);
            fluidInsts.forEach(registry::register);
            itemInsts.forEach(registry::register);
        }
        private static void make(Chemical chemical, String id, List<Chemical> list) {
            list.add(chemical.setRegistryName(MODID, id));
        }
        static void init() {
            make(new Chemical(Chemical.Properties.SODIUM_CHLORIDE), "sodium_chloride", itemInsts);
            make(new Chemical(Chemical.Properties.WATER), "water", normInsts);
            make(new Chemical(Chemical.Properties.METHANE), "methane", fluidInsts);
            make(new Chemical(Chemical.Properties.FERRIC_OXIDE), "ferric_oxide", itemInsts);
            make(new Chemical(Chemical.Properties.TUNGSTEN_CARBIDE), "tungsten_carbide", blockInsts);
        }

    }

}
