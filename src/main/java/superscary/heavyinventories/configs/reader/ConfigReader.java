package superscary.heavyinventories.configs.reader;

import net.minecraftforge.common.config.Configuration;
import superscary.heavyinventories.HeavyInventories;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
import superscary.heavyinventories.util.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
public class ConfigReader
{

	/**
	 * This is used to read through this mods config directory to find any mods that have integration with this mod.
	 */

	private static ArrayList<String> loadedMods = new ArrayList<>();
	private static ArrayList<Configuration> configurations = new ArrayList<>();
	private static HashMap<String, Configuration> configs = new HashMap<>();

	private static final String DIR = HeavyInventories.getReaderDirectory() + File.separator + "Heavy Inventories" + File.separator + "Weights" + File.separator;

	public static void handshake()
	{
		if (HeavyInventoriesConfig.loadableFiles.size() > 0)
		{
			for (String s : HeavyInventoriesConfig.loadableFiles)
			{
				if (!s.equals("Minecraft.cfg"))
				{
					Logger.info("Found: " + s + " in the loading directory! Loading...");
					File file = new File(DIR + s);
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
			Configuration theConfig = new Configuration(new File(DIR + s));
			configurations.add(theConfig);
			configs.put(s, theConfig);
			Logger.info("Read: " + s + " into memory.");
		}
		addItems();
	}

	public static void addItems()
	{
		for (String mod : loadedMods)
		{
			configs.get(mod).load();
			Logger.info("Loaded: " + mod);
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
