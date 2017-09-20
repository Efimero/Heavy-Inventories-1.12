package superscary.heavyinventories.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
public class PlayerOverEncumberedMessage implements IMessage
{

	private boolean isOverEncumbered;

	public PlayerOverEncumberedMessage()
	{

	}

	public PlayerOverEncumberedMessage(boolean isOverEncumbered)
	{
		this.isOverEncumbered = isOverEncumbered;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(isOverEncumbered);
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		isOverEncumbered = buf.readBoolean();
	}

	public boolean isOverEncumbered()
	{
		return isOverEncumbered;
	}

}
