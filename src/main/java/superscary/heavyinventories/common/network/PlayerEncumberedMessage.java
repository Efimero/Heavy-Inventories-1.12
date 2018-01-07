package superscary.heavyinventories.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
public class PlayerEncumberedMessage implements IMessage
{

	private boolean isEncumbered;

	public PlayerEncumberedMessage()
	{
	}

	public PlayerEncumberedMessage(boolean isEncumbered)
	{

		this.isEncumbered = isEncumbered;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(isEncumbered);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		isEncumbered = buf.readBoolean();
	}

	public boolean isEncumbered()
	{
		return isEncumbered;
	}

}
