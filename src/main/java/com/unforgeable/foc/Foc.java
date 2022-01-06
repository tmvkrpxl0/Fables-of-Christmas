package com.unforgeable.foc;

import com.unforgeable.foc.blocks.FocBlocks;
import com.unforgeable.foc.blocks.FocWoodTypes;
import com.unforgeable.foc.client.renderers.RenderLayers;
import com.unforgeable.foc.entities.FocEntityTypes;
import com.unforgeable.foc.entities.goal.FocPOI;
import com.unforgeable.foc.items.FocItems;
import com.unforgeable.foc.tileentities.FocBlockEntities;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Foc.MODID)
public class Foc {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "foc";
    public static final IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
    public static final IEventBus FORGE_BUS = MinecraftForge.EVENT_BUS;

    public Foc() {
        // Register the setup method for modloading
        MOD_BUS.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        MOD_BUS.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        MOD_BUS.addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        FORGE_BUS.register(this);
        FocEntityTypes.register(MOD_BUS);
        FocBlocks.register(MOD_BUS);
        FocItems.register(MOD_BUS);
        FocPOI.register(MOD_BUS);
        FocBlockEntities.register(MOD_BUS);
        RenderLayers.safeRunClient();

    }

    private void setup(final FMLCommonSetupEvent event) {

        // some preinit code

        WoodType.register(FocWoodTypes.POHUTUKAWA);
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("foc", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {


            BlockEntityRenderers.register(FocBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
            Sheets.addWoodType(FocWoodTypes.POHUTUKAWA);






        });


       // RenderingRegistry.registerEntityRenderingHandler(FocBlockEntityTypes.POHUTUKAWA_BOAT.get(), FocBoatRenderer::new);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
