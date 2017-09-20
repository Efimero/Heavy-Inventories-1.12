package superscary.heavyinventories.configs.weights;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import superscary.heavyinventories.configs.reader.ConfigReader;

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
public class CustomConfigLoader
{

	public static double getItemWeight(String modid, Item item)
	{
		return ConfigReader.getConfig(modid).get(Configuration.CATEGORY_GENERAL, item.getUnlocalizedName().substring(5), 0.5).getDouble();
	}

	/*public static double getBlockWeight(String modid, Block block)
	{
		*ArrayList<HashMap<Block, Double>> theList = blockWeightsList.get(modid);
		for (HashMap<Block, Double> map : theList)
		{
			if (map.containsKey(block))
			{
				return map.get(block).doubleValue();
			}
		}

		return ConfigReader.getConfig(modid).get(Configuration.CATEGORY_GENERAL, block.getUnlocalizedName().substring(5), 0.5).getDouble();

	}*/

	/*public static void putItem(String modid, Item item, double weight)
	{
		HashMap<Item, Double> hashMap = new HashMap<>();
		hashMap.put(item, weight);

		ArrayList<HashMap<Item, Double>> arrayList = new ArrayList<>();
		arrayList.add(hashMap);

		itemWeightsList.put(modid, arrayList);
	}

	public static void putBlock(String modid, Block block, double weight)
	{
		HashMap<Block, Double> hashMap = new HashMap<>();
		hashMap.put(block, weight);

		ArrayList<HashMap<Block, Double>> arrayList = new ArrayList<>();
		arrayList.add(hashMap);

		blockWeightsList.put(modid, arrayList);
	}

	public static void readItems()
	{

	}*/

}
