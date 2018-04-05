package superscary.heavyinventories;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import superscary.heavyinventories.client.command.HeavyInventoriesCommandRegistry;
import superscary.heavyinventories.common.CommonProxy;
import superscary.heavyinventories.compat.CompatLoader;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
import superscary.heavyinventories.configs.reader.ConfigReader;
import superscary.heavyinventories.configs.weights.MinecraftConfig;
import superscary.heavyinventories.util.Constants;
import superscary.heavyinventories.util.Logger;
import superscary.supercore.SuperCore;
import superscary.supercore.info.Generator;

import java.io.File;

import static superscary.heavyinventories.util.Constants.*;

/**
 * Copyright (c) 2018 by SuperScary(ERBF) http://codesynced.com
 * <p>
 * All rights reserved. No part of this software may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods,
 * without the prior written permission of the publisher, except in
 * the case of brief quotations embodied in critical reviews and
 * certain other noncommercial uses permitted by copyright law.
 */

@SuppressWarnings("unused")
@Mod(modid = MODID, version = VERSION, name = NAME, guiFactory = "superscary.heavyinventories.client.gui.ModGuiFactory", acceptedMinecraftVersions = "1.12", dependencies = SuperCore.SET_REQUIRED_AFTER)
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

        Logger.info("PreInit...");

        Generator.Info.create(Constants.class, event);

        readerDirectory = event.getModConfigurationDirectory();

        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "superscary.heavyinventories.compat.mods.theoneprobe.HITOPInfoProvider");

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Logger.info("Init...");

        HeavyInventoriesConfig.init(readerDirectory);
        MinecraftConfig.init(readerDirectory);

        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Logger.info("PostInit...");

        proxy.postInit();
        ConfigReader.handshake();
        CompatLoader.build();
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
