package superscary.heavyinventories.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import superscary.heavyinventories.common.capability.CapabilityHandler;
import superscary.heavyinventories.common.capability.offsets.IOffset;
import superscary.heavyinventories.common.capability.offsets.OffsetStorage;
import superscary.heavyinventories.common.capability.offsets.WeightOffset;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.Weight;
import superscary.heavyinventories.common.capability.weight.WeightStorage;
import superscary.heavyinventories.common.event.CommonEventHandler;
import superscary.heavyinventories.common.network.PlayerEncumberedMessage;
import superscary.heavyinventories.common.network.PlayerNotEncumberedMessage;
import superscary.heavyinventories.common.network.PlayerOverEncumberedMessage;
import superscary.heavyinventories.common.network.handlers.PlayerEncumberedMessageHandler;
import superscary.heavyinventories.common.network.handlers.PlayerNotEncumberedMessageHandler;
import superscary.heavyinventories.common.network.handlers.PlayerOverEncumberedMessageHandler;

import static superscary.heavyinventories.HeavyInventories.networkWrapper;
import static superscary.heavyinventories.util.Constants.MODID;

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
public abstract class CommonProxy
{

	public void preInit()
	{
		registerCapability();
		registerNetwork();
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}

	public void init()
	{

	}

	public void postInit()
	{

	}

	public void registerCapability()
	{
		CapabilityManager.INSTANCE.register(IWeighable.class, new WeightStorage(), Weight::new);
		CapabilityManager.INSTANCE.register(IOffset.class, new OffsetStorage(), WeightOffset::new);
	}

	public void registerNetwork()
	{
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		int id = 0;

		networkWrapper.registerMessage(PlayerEncumberedMessageHandler.class, PlayerEncumberedMessage.class, id++, Side.SERVER);
		networkWrapper.registerMessage(PlayerOverEncumberedMessageHandler.class, PlayerOverEncumberedMessage.class, id++, Side.SERVER);
		networkWrapper.registerMessage(PlayerNotEncumberedMessageHandler.class, PlayerNotEncumberedMessage.class, id++, Side.SERVER);
	}

}
