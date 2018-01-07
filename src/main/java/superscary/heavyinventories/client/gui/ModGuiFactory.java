package superscary.heavyinventories.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

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
public class ModGuiFactory implements IModGuiFactory
{

	public ModGuiFactory()
	{
	}

	@Override
	public void initialize(Minecraft minecraftInstance)
	{

	}

	@Override
	public boolean hasConfigGui()
	{
		return false;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen)
	{
		return new HeavyInventoriesGuiConfig(null);
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories()
	{
		return null;
	}

}
