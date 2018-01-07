package superscary.heavyinventories.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superscary.heavyinventories.client.event.ClientEventHandler;
import superscary.heavyinventories.client.event.PumpingIronHandler;
import superscary.heavyinventories.common.CommonProxy;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;
import superscary.heavyinventories.configs.weights.MinecraftConfig;

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
public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit()
	{
		super.preInit();
		MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
		MinecraftForge.EVENT_BUS.register(new MinecraftConfig());
		MinecraftForge.EVENT_BUS.register(new HeavyInventoriesConfig());
		MinecraftForge.EVENT_BUS.register(new PumpingIronHandler());
	}

	@Override
	public void init()
	{

	}

	@Override
	public void postInit()
	{

	}

	public static EntityPlayer getPlayerFromContext(MessageContext context)
	{
		return Minecraft.getMinecraft().player;
	}

}
