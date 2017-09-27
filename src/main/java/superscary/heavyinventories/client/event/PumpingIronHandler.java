package superscary.heavyinventories.client.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.WeightProvider;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
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
public class PumpingIronHandler
{

	public static void register()
	{
		if (HeavyInventoriesConfig.pumpingIron)
		{
			MinecraftForge.EVENT_BUS.register(new PumpingIronHandler());
		}
	}

	@SubscribeEvent
	public void handleAnvilUse(AnvilRepairEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		weighable.setMaxWeight(weighable.getMaxWeight() + HeavyInventoriesConfig.pumpingIronWeightIncrease);
		Toolkit.saveDataToPlayer(player, Toolkit.DATA_MAXWEIGHT, (float) weighable.getMaxWeight());
	}

}
