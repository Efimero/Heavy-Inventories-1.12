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

	// TODO: Honestly I cant remember what I was going for here other than to get the weight of a custom config item

	public static double getItemWeight(String modid, Item item)
	{
		return ConfigReader.getConfig(modid).get(Configuration.CATEGORY_GENERAL, item.getUnlocalizedName().substring(5), 0.5).getDouble();
	}

}
