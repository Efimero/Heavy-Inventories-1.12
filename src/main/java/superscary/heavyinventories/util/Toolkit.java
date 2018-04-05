package superscary.heavyinventories.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.WeightProvider;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;

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
		String find = block.getRegistryName().getResourceDomain();
		for (ModContainer mod : Loader.instance().getModList())
		{
			if (mod.getModId().equalsIgnoreCase(find))
			{
				return mod.getName().replaceAll("\\s+", "");
			}
		}
		return null;
	}

	public static String getModNameFromItem(Item item)
	{
		String find = item.getRegistryName().getResourceDomain();
		for (ModContainer mod : Loader.instance().getModList())
		{
			if (mod.getModId().equalsIgnoreCase(find))
			{
				return mod.getName().replaceAll("\\s+", "");
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

	public static void increasePlayerMaxWeight(EntityPlayer player, double value)
	{
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

		player.getEntityData().setDouble("HIWeight", weighable.getMaxWeight() + value);
	}

	public static void decreasePlayerMaxWeight(EntityPlayer player, double value)
	{
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);

		player.getEntityData().setDouble("HIWeight", weighable.getMaxWeight() - value >= 0 ? weighable.getMaxWeight() - value : 0);
	}

	public static void setPlayerMaxCarryWeight(EntityPlayer player, double value)
	{
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		player.getEntityData().setDouble("HIWeight", value);
	}

	/**
	 * Plays a sound at the player (reference: http://www.minecraftforum.net/forums/mapping-and-modding-java-edition/mapping-and-modding-tutorials/2567682-all-playsound-names-1-9-1-11-updated)
	 * @param player
	 * @param soundID
	 */
	public static void playSoundAtPlayer(EntityPlayer player, int soundID, float volume, float pitch)
	{
		player.playSound(SoundEvent.REGISTRY.getObjectById(soundID), volume, pitch);
	}

	public static double roundDouble(double value, int places)
	{
		if (places == 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value *= factor;
		long temp = Math.round(value);
		return (double) temp / factor;
	}

	public static double roundDouble(double value)
	{
		return roundDouble(value, 1);
	}

}
