package superscary.heavyinventories.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;

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
public class Toolkit
{

	public static final String SETUP = HeavyInventoriesConfig.SETUP;

	public static final String DATA_MAXWEIGHT = "maxWeight";

	public static void saveDataToPlayer(EntityPlayer player, String dataName, float data)
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setFloat(dataName, data);
		player.writeToNBT(compound);
	}

	public static float getDataFromPlayer(EntityPlayer player, String dataName)
	{
		NBTTagCompound compound = player.getEntityData();
		return compound.getFloat(dataName);
	}

	public static String getModNameFromBlock(Block block)
	{
		String find = block.getUnlocalizedName().substring(5);
		for (ModContainer mod : Loader.instance().getModList())
		{
			if (mod.getModId().equalsIgnoreCase(find))
			{
				return mod.getModId();
			}
		}
		return null;
	}

	public static String getModNameFromItem(Item item)
	{
		String find = item.getUnlocalizedName().substring(5);
		for (ModContainer mod : Loader.instance().getModList())
		{
			if (mod.getModId().equalsIgnoreCase(find))
			{
				return mod.getModId();
			}
		}

		return null;
	}

	public static String translate(String par1Str)
	{
		return net.minecraft.client.resources.I18n.format(par1Str);
	}

	public static float getFloat(String name, String category, float defaultValue, float minValue, float maxValue)
	{
		return HeavyInventoriesConfig.getConfig().getFloat(translate(name + ".name"), category, defaultValue, minValue, maxValue, translate(name + ".comment"));
	}

	public static float getFloat(String name, float defaultValue, float minValue, float maxValue)
	{
		return getFloat(name, SETUP, defaultValue, minValue, maxValue);
	}

	public static boolean getBoolean(String name, String category, boolean defaultValue)
	{
		return HeavyInventoriesConfig.getConfig().getBoolean(translate(name + ".name"), category, defaultValue, translate(name + ".comment"));
	}

	public static boolean getBoolean(String name, boolean defaultValue)
	{
		return getBoolean(name, SETUP, defaultValue);
	}

}
