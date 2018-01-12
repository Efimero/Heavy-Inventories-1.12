package superscary.heavyinventories.compat.mods.theoneprobe;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import superscary.heavyinventories.calc.PlayerWeightCalculator;
import superscary.heavyinventories.util.Toolkit;

import static superscary.heavyinventories.util.Constants.MODID;

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
public class HITOPInfoProvider implements IProbeInfoProvider
{

	@Override
	public String getID()
	{
		return MODID + "Weight";
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo info, EntityPlayer player, World world, IBlockState state, IProbeHitData data)
	{
		Block block = state.getBlock();
		double blockWeight = Toolkit.roundDouble(PlayerWeightCalculator.getWeight(new ItemStack(Item.getItemFromBlock(block))), 1);
		info.horizontal().text("" + blockWeight + " stone", info.defaultTextStyle());
	}

}
