package com.unforgeable.foc.blocks;

import com.unforgeable.foc.Foc;
import com.unforgeable.foc.items.FocItems;
import com.unforgeable.foc.tabs.FocTabs;
import com.unforgeable.foc.world.gen.PohutukawaTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class FocBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Foc.MODID);


    //FIRST TESTING BLOCK//
    public static final RegistryObject<Block> POHUTUKAWA_LOG = registerBlock("pohutukawa_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD) .strength(0.5F, 2.0F).sound(SoundType.WOOD)), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_LOG_STRIPPED = registerBlock("pohutukawa_log_stripped",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.0F).sound(SoundType.WOOD)), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_WOOD = registerBlock("pohutukawa_wood",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.0F).sound(SoundType.WOOD)), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_WOOD_STRIPPED = registerBlock("pohutukawa_wood_stripped",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.0F).sound(SoundType.WOOD)), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_LEAVES = registerBlock("pohutukawa_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F, 1.0F).sound(SoundType.GRASS).noOcclusion()), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_FLOWERING_LEAVES = registerBlock("pohutukawa_flowering_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F, 1.0F).sound(SoundType.GRASS).noOcclusion()), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_PLANKS = registerBlock("pohutukawa_planks",
            () -> new Block(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.0F).sound(SoundType.WOOD)), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_BOOKSHELF = registerBlock("pohutukawa_bookshelf",
            () -> new FocBookshelfBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.0F).lightLevel((p_50828_) -> { return 6; }).sound(SoundType.WOOD)), FocTabs.FOC_TAB);



    public static final RegistryObject<Block> POHUTUKAWA_DOOR = registerBlock("pohutukawa_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).strength(0.5F, 2.0F)),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_TRAPDOOR = registerBlock("pohutukawa_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).strength(0.5F, 2.0F)),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_STAIRS = registerBlock( "pohutukawa_stairs",
            () -> new StairBlock(() -> Blocks.OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(0.5F, 2.0F)), FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_SLAB = registerBlock("pohutukawa_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(0.5F, 2.0F)),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_FENCE = registerBlock("pohutukawa_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).noOcclusion()),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_FENCE_GATE = registerBlock("pohutukawa_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5F, 2.5F).noOcclusion()),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_PRESSURE_PLATE = registerBlock("pohutukawa_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.of
                    (Material.WOOD).strength(0.3F, 2.5F).noOcclusion()),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_BUTTON = registerBlock("pohutukawa_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.3F, 2.5F).noOcclusion()),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_SAPLING = registerBlock("pohutukawa_sapling",
            () ->  new SaplingBlock(new PohutukawaTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission()
                    .randomTicks().instabreak().sound(SoundType.GRASS)),FocTabs.FOC_TAB);

    public static final RegistryObject<Block> POHUTUKAWA_SAPLING_POTTED = BLOCKS.register("pohutukawa_sapling_potted",
            () -> new FlowerPotBlock(POHUTUKAWA_SAPLING.get(),BlockBehaviour.Properties.of(Material.CLAY).strength(2.0F).noOcclusion().sound(SoundType.BONE_BLOCK)));



    public static final RegistryObject<Block> POHUTUKAWA_STANDING_SIGN = BLOCKS.register("pohutukawa_sign",
            () -> new FocStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD), FocWoodTypes.POHUTUKAWA));

    public static final RegistryObject<Block> POHUTUKAWA_WALL_SIGN = BLOCKS.register("pohutukawa_wall_sign",
            () -> new FocWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD), FocWoodTypes.POHUTUKAWA));

    //Registering Block Item of porridge bowl is done in FocItems manually
    //But it may be able to be moved to here
    public static final RegistryObject<Block> PORRIDGE_BOWL = BLOCKS.register("porridge_bowl",
            () -> new FocPorridgeBowl(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOD)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        FocItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}