package superscary.heavyinventories.configs.reader;

import net.minecraftforge.common.config.Configuration;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
import superscary.heavyinventories.configs.weights.MinecraftConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Copyright (c) 2017 by SuperScary(ERBF) http://codesynced.com
 * <p>
 * All rights reserved. No part of this software may be reproduced,
 * distributed, or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods,
 * without the prior written permission of the publisher, except in
 * the case of brief quotations embodied in critical reviews and
 * certain other noncommercial uses permitted by copyright law.
 */
public class ConfigReader
{

	/**
	 * This is used to read through this mods config directory to find any mods that have integration with this mod.
	 */

	private static File directory = new File(MinecraftConfig.getConfig().getConfigFile().getAbsolutePath() + "/Weights");
	private static ArrayList<String> loadedMods = new ArrayList<>();
	private static ArrayList<Configuration> configurations = new ArrayList<>();
	private static HashMap<String, Configuration> configs = new HashMap<>();

	public static void handshake()
	{
		if (HeavyInventoriesConfig.loadableFiles.size() > 0)
		{
			for (String s : HeavyInventoriesConfig.loadableFiles)
			{
				if (!s.equals("Minecraft.cfg"))
				{
					System.out.println("Found: " + s + " in the loading directory! Loading...");
					File file = new File(directory.getAbsolutePath() + s);
					if (file != null) System.out.println("Found: " + file.getAbsolutePath());
					loadedMods.add(s);
				}
			}
		}
		readIntoMemory();
	}

	/**
	 * Reads the files into the memory
	 */
	public static void readIntoMemory()
	{
		for (String s : loadedMods)
		{
			Configuration theConfig = new Configuration(new File(directory.getAbsolutePath() + s));
			configurations.add(theConfig);
			configs.put(s, theConfig);
		}
		addItems();
	}

	public static void addItems()
	{
		for (String mod : loadedMods)
		{
			configs.get(mod).load();
		}
	}



	public static Configuration getConfig(String config)
	{
		return configs.containsKey(config) ? configs.get(config) : null;
	}

	public static ArrayList<String> getLoadedMods()
	{
		return loadedMods;
	}

}
