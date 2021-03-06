package superscary.heavyinventories.configs;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import superscary.heavyinventories.util.Toolkit;

import java.io.File;
import java.util.ArrayList;

import static superscary.heavyinventories.util.Constants.MODID;

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
public class HeavyInventoriesConfig
{

	public static Configuration config;

	public static final String GENERAL = Configuration.CATEGORY_GENERAL;
	public static final String SETUP = "Setup";

	public static boolean isEnabled;
	public static boolean allowInCreative;
	public static float encumberedSpeed;
	public static float overEncumberedSpeed;
	public static boolean canSleepWhileEncumbered;
	public static boolean canSleepWhileOverEncumbered;
	public static boolean autoGenerateWeightConfigFiles;
	public static boolean pumpingIron;
	public static float pumpingIronWeightIncrease;
	public static boolean canEnderPearlTeleport;
	public static boolean canTeleportToEnd;
	public static boolean canTeleportToNether;
	public static boolean canTeleport;
	public static boolean canFly;

	public static double maxCarryWeight;

	public static ArrayList<String> loadableFiles = new ArrayList<>();

	public static void init(File file)
	{
		File configFile = new File(file + "/Heavy Inventories/", "Heavy Inventories.cfg");
		if (config == null)
		{
			config = new Configuration(configFile);
		}
		loadConfig();
	}

	private static void loadConfig()
	{
		config.load();

		isEnabled = Toolkit.getBoolean("enabled", true);
		allowInCreative = Toolkit.getBoolean("allowInCreative", false);
		encumberedSpeed = Toolkit.getFloat("encumberedSpeed", (float) 0.2, 0, 1);
		overEncumberedSpeed = Toolkit.getFloat("overEncumberedSpeed", (float) 0, 0, 1);
		canSleepWhileEncumbered = Toolkit.getBoolean("canSleepWhileEncumbered", true);
		canSleepWhileOverEncumbered = Toolkit.getBoolean("canSleepWhileOverEncumbered", false);
		maxCarryWeight = Toolkit.getFloat("maxCarryWeight", 700, 0, Float.MAX_VALUE);
		autoGenerateWeightConfigFiles = Toolkit.getBoolean("autoGenerateWeightConfigFiles", false);
		pumpingIron = Toolkit.getBoolean("pumpingIron", true);
		pumpingIronWeightIncrease = Toolkit.getFloat("pumpingIronWeightIncrease", (float) 0.5, 0, 10);
		canEnderPearlTeleport = Toolkit.getBoolean("canEnderPearlTeleport", false);
		canTeleportToEnd = Toolkit.getBoolean("canTeleportToEnd", false);
		canTeleportToNether = Toolkit.getBoolean("canTeleportToNether", false);
		canTeleport = Toolkit.getBoolean("canTeleport", false);
		canFly = Toolkit.getBoolean("canFly", false);

		String[] files = config.getStringList("Files", "Files", new String[] {"Minecraft.cfg"}, "The custom weight files for other mods. Seperate each with a comma.");

		for (String s : files)
		{
			loadableFiles.add(s);
		}

		config.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(MODID))
		{
			syncConfig(config);
		}
	}

	private void syncConfig(Configuration config)
	{
		loadConfig();
		if (config.hasChanged())
		{
			config.save();
		}
	}

	public static Configuration getConfig()
	{
		return config;
	}

}
