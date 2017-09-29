package superscary.heavyinventories.common.capability.offsets;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

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
public class OffsetProvider implements ICapabilitySerializable<NBTBase>
{

	@CapabilityInject(IOffset.class)
	public static final Capability<IOffset> OFFSET_CAPABILITY = null;

	private IOffset instance = OFFSET_CAPABILITY.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == OFFSET_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == OFFSET_CAPABILITY ? OFFSET_CAPABILITY.<T> cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return OFFSET_CAPABILITY.getStorage().writeNBT(OFFSET_CAPABILITY, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase base)
	{
		OFFSET_CAPABILITY.getStorage().readNBT(OFFSET_CAPABILITY, this.instance, null, base);
	}

}
