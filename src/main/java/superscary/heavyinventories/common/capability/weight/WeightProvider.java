package superscary.heavyinventories.common.capability.weight;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

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
public class WeightProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IWeighable.class)
	public static final Capability<IWeighable> WEIGHABLE_CAPABILITY = null;

	private IWeighable instance = WEIGHABLE_CAPABILITY.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == WEIGHABLE_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == WEIGHABLE_CAPABILITY ? WEIGHABLE_CAPABILITY.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return WEIGHABLE_CAPABILITY.getStorage().writeNBT(WEIGHABLE_CAPABILITY, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase base)
	{
		WEIGHABLE_CAPABILITY.getStorage().readNBT(WEIGHABLE_CAPABILITY, this.instance, null, base);
	}

}
