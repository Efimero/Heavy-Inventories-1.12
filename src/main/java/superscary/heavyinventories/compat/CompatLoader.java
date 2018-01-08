package superscary.heavyinventories.compat;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import superscary.heavyinventories.compat.mods.HIBaubles;
import superscary.heavyinventories.compat.mods.HIJei;
import superscary.heavyinventories.compat.mods.theoneprobe.HITheOneProbe;
import superscary.heavyinventories.compat.mods.HIWaila;
import superscary.heavyinventories.util.Logger;

import java.util.ArrayList;

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
public class CompatLoader
{

	// TODO: FINISH COMPATABILITY LOADER

	/**
	 * This class will be used for default implemented mods to calculate weights of inventories
	 * such as Tinkers' Construct, Applied Energistics 2, and such.
	 */

	private static ArrayList<String> loadableMods = new ArrayList<>();

	/**
	 * Will build all compatability files
	 */
	public static void build()
	{
		Logger.info("Finding compatible mods...");

		for (ModContainer container : Loader.instance().getActiveModList())
		{
			String modid = container.getModId();
			for (EnumCompatMods mods : EnumCompatMods.values())
			{
				if (modid.equals(mods.getCompatModid()))
				{
					Logger.info("Found %s!", modid);
					loadableMods.add(modid);
				}
			}
		}

		finish();
	}

	/**
	 * Finishes the implementation of the compatability
	 */
	protected static void finish()
	{
		ArrayList<String> unloaded = new ArrayList<>();

		for (EnumCompatMods mod : EnumCompatMods.values()) unloaded.add(mod.getCompatModid());

		for (String s : loadableMods)
		{
			switch (s)
			{
				case "baubles": HIBaubles.register();
								unloaded.remove(s);
								break;
				case "jei": new HIJei();
								unloaded.remove(s);
								break;
				case "theoneprobe": new HITheOneProbe();
								unloaded.remove(s);
								break;
				case "waila": new HIWaila();
								unloaded.remove(s);
								break;
				default: break;
			}
		}

		for (String s : unloaded)
		{
			Logger.info("Disabled compatability for %s", s);
		}
	}

}
