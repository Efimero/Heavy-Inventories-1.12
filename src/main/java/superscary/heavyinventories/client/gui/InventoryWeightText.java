package superscary.heavyinventories.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import superscary.heavyinventories.client.event.ClientEventHandler;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.WeightProvider;
import superscary.heavyinventories.util.Toolkit;

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
public class InventoryWeightText extends Gui
{

	private static String label = " stone";

	public static void renderText(Minecraft minecraft)
	{
		IWeighable weighable = minecraft.player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		ScaledResolution scaledResolution = new ScaledResolution(minecraft);
		int attackIndicator = minecraft.gameSettings.attackIndicator;

		if (attackIndicator == EnumAttackIndicator.CROSSHAIR.getAttackIndicator())
		{
			renderTextToScreen(minecraft, weighable, scaledResolution, 15, ClientEventHandler.getPlayerWeight());
		}
		else if (attackIndicator == EnumAttackIndicator.BAR.getAttackIndicator())
		{
			renderTextToScreen(minecraft, weighable, scaledResolution, 30, ClientEventHandler.getPlayerWeight());
		}
	}

	public static void renderTextToScreen(Minecraft minecraft, IWeighable weighable, ScaledResolution scaledResolution, int scaledResolutionOffset, double display)
	{
		minecraft.fontRenderer.drawString("" + Toolkit.roundDouble(weighable.getWeight()) + "/" + display + label, scaledResolution.getScaledWidth() / 2 + 97, scaledResolution.getScaledHeight() - scaledResolutionOffset, Integer.parseInt("FFFFFF", 16), true);
	}

	public enum EnumAttackIndicator
	{
		CROSSHAIR(1),
		BAR(2);

		private int pos;
		EnumAttackIndicator(int pos)
		{
			this.pos = pos;
		}

		public int getAttackIndicator()
		{
			return pos;
		}
	}

}
