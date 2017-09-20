package superscary.supercore;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import superscary.supercore.info.Generator;
import superscary.supercore.logging.Logger;
import superscary.supercore.proxy.IProxy;
import superscary.supercore.resources.ModResources;

import static superscary.supercore.resources.ModResources.*;

@Mod(modid = MODID, version = VERSION, name = NAME, acceptedMinecraftVersions = "1.12")
public class SuperCore
{

    public static final String SET_REQUIRED_AFTER = "required-after:supercore@[1.0,)";
    public static final String SET_REQUIRED_AFTER_CUSTOM = "required-after:supercore@";

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Logger.setLogger(event.getModLog());
        Generator.Info.create(ModResources.class, event);

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
    }

}
