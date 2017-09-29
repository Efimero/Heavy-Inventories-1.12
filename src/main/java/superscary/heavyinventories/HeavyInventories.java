package superscary.heavyinventories;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import superscary.heavyinventories.client.command.HeavyInventoriesCommandRegistry;
import superscary.heavyinventories.common.CommonProxy;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
import superscary.heavyinventories.configs.reader.ConfigReader;
import superscary.heavyinventories.configs.weights.MinecraftConfig;
import superscary.heavyinventories.util.Constants;
import superscary.heavyinventories.util.Logger;
import superscary.supercore.info.Generator;

import java.io.File;

import static superscary.heavyinventories.util.Constants.*;

@SuppressWarnings("unused")
@Mod(modid = MODID, version = VERSION, name = NAME, guiFactory = "superscary.heavyinventories.client.gui.ModGuiFactory", acceptedMinecraftVersions = "1.12")
public class HeavyInventories
{

    private static File readerDirectory;

    @SidedProxy(serverSide = PROXY_SERVER, clientSide = PROXY_CLIENT)
    public static CommonProxy proxy;

    @Mod.Instance
    public static HeavyInventories instance;

    public static SimpleNetworkWrapper networkWrapper;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Logger.setLogger(event.getModLog());

        Generator.Info.create(Constants.class, event);
        MinecraftConfig.init(event.getModConfigurationDirectory());
        HeavyInventoriesConfig.init(event.getModConfigurationDirectory());

        readerDirectory = event.getModConfigurationDirectory();

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
        ConfigReader.handshake();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        new HeavyInventoriesCommandRegistry(event);
    }

    public static SimpleNetworkWrapper getNetwork()
    {
        return networkWrapper;
    }

    public static File getReaderDirectory()
    {
        return readerDirectory;
    }

}
