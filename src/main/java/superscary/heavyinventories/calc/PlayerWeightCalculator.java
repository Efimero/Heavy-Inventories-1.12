package superscary.heavyinventories.calc;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import superscary.heavyinventories.configs.weights.CustomConfigLoader;
import superscary.heavyinventories.configs.weights.MinecraftConfig;
import superscary.heavyinventories.util.Toolkit;

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
public class PlayerWeightCalculator
{

	/**
	 * Calculates the weight of the player's entire inventory
	 * @param player the player to weigh
	 * @return the weight of the player
	 */
	public static double calculateWeight(EntityPlayer player)
	{
		double weight = 0;
		for (int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			Item item = stack.getItem();
			if (stack != null)
			{
				if (Toolkit.getModNameFromItem(stack.getItem()).equalsIgnoreCase("minecraft"))
				{
					weight += (getWeight(stack) * stack.getCount());
				}
				else
				{
					weight += (getWeight(Toolkit.getModNameFromItem(stack.getItem()), stack.getItem()) * stack.getCount());
				}
			}
		}
		return weight;
	}

	/**
	 * Gets the weight for {@link net.minecraft.client.Minecraft} items
	 * @param stack
	 * @return
	 */
	public static double getWeight(ItemStack stack)
	{
		return MinecraftConfig.getConfig().get(Configuration.CATEGORY_GENERAL, stack.getItem().getUnlocalizedName().substring(5), 0.5).getDouble();
	}

	/**
	 * Gets the weight for custom items
	 * @param modid the modid of the item
	 * @param item the item to be weighed
	 * @return
	 */
	public static double getWeight(String modid, Item item)
	{
		return CustomConfigLoader.getItemWeight(modid, item);
	}

	/**
	 * Gets the weight for custom blocks
	 * @param modid the modid of the item
	 * @param block the item to be weighed
	 * @return
	 */
	public static double getWeight(String modid, Block block)
	{
		return getWeight(modid, Item.getItemFromBlock(block));
	}

}
