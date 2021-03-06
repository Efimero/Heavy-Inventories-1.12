package superscary.heavyinventories.common.network.handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.WeightProvider;
import superscary.heavyinventories.common.network.PlayerNotEncumberedMessage;

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
public class PlayerNotEncumberedMessageHandler implements IMessageHandler<PlayerNotEncumberedMessage, IMessage>
{

	@Override
	public IMessage onMessage(PlayerNotEncumberedMessage message, MessageContext context)
	{
		EntityPlayerMP playerMP = context.getServerHandler().player;
		IWeighable weighable = playerMP.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		if (message.isNotEncumbered())
		{
			weighable.setOverEncumbered(message.isNotEncumbered());
		}

		return null;
	}

}
