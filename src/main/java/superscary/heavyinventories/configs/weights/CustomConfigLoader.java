package superscary.heavyinventories.configs.weights;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import superscary.heavyinventories.configs.reader.ConfigReader;

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
public class CustomConfigLoader
{

	/**
	 * Returns the weight of the item from the custom configs
	 * @param modid
	 * @param item
	 * @return
	 */
	public static double getItemWeight(String modid, Item item)
	{
		try
		{
			return ConfigReader.getConfig(modid + ".cfg").get(Configuration.CATEGORY_GENERAL, item.getRegistryName().getResourcePath(), 0.1).getDouble();
		}
		catch (NullPointerException e)
		{}

		return 0.1;
	}

}
