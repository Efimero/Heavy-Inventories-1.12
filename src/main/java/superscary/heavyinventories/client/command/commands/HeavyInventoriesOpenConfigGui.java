package superscary.heavyinventories.client.command.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import superscary.heavyinventories.client.gui.HeavyInventoriesGuiConfig;
import superscary.supercore.tools.gui.DisplayDelayedGui;

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
public class HeavyInventoriesOpenConfigGui extends CommandBase
{

	@Override
	public String getName()
	{
		return "heavyinventories";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "hi.commands.usage";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		try
		{
			new DisplayDelayedGui(2, new HeavyInventoriesGuiConfig(null));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
