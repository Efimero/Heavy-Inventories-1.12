package superscary.heavyinventories.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import superscary.heavyinventories.client.event.ClientEventHandler;
import superscary.heavyinventories.common.capability.offsets.IOffset;
import superscary.heavyinventories.common.capability.offsets.OffsetProvider;
import superscary.heavyinventories.common.capability.weight.IWeighable;
import superscary.heavyinventories.common.capability.weight.WeightProvider;

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
public class InventoryWeightText extends Gui
{

	private static String label = " stone";

	public static void renderText(Minecraft minecraft)
	{
		IWeighable weighable = minecraft.player.getCapability(WeightProvider.WEIGHABLE_CAPABILITY, null);
		IOffset offset = minecraft.player.getCapability(OffsetProvider.OFFSET_CAPABILITY, null);

		double display = ClientEventHandler.playerWeight;

		ScaledResolution scaledResolution = new ScaledResolution(minecraft);

		if (minecraft.gameSettings.attackIndicator == 1)
		{
			minecraft.fontRenderer.drawString("" + weighable.getWeight() + "/" + display + label, scaledResolution.getScaledWidth() / 2 + 97, scaledResolution.getScaledHeight() - 15, Integer.parseInt("FFFFFF", 16), true);
		}
		else if (minecraft.gameSettings.attackIndicator == 2)
		{
			minecraft.fontRenderer.drawString("" + weighable.getWeight() + "/" + display + label, scaledResolution.getScaledWidth() / 2 + 97, scaledResolution.getScaledHeight() - 30, Integer.parseInt("FFFFFF", 16), true);

		}
	}

}
