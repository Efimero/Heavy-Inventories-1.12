package superscary.heavyinventories.client.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import superscary.heavyinventories.common.capability.offsets.IOffset;
import superscary.heavyinventories.common.capability.offsets.OffsetProvider;
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
		/*IWeighable weighable = player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		weighable.setMaxWeight(weighable.getMaxWeight() + HeavyInventoriesConfig.pumpingIronWeightIncrease);*/
		IOffset offset = player.getCapability(OffsetProvider.OFFSET_CAPABILITY, null);

		offset.setOffset(offset.getOffset() + HeavyInventoriesConfig.pumpingIronWeightIncrease);
	}

	@SubscribeEvent
	public void handleToolCraft(PlayerEvent.ItemCraftedEvent event)
	{

	}

	@SubscribeEvent
	public void handleArmorCraft(PlayerEvent.ItemCraftedEvent event)
	{

	}

}
