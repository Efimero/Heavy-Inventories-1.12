package superscary.heavyinventories.common.capability.offsets;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

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
public class OffsetStorage implements Capability.IStorage<IOffset>
{

	@Override
	public NBTBase writeNBT(Capability<IOffset> capability, IOffset instance, EnumFacing side)
	{
		return new NBTTagDouble(instance.getOffset());
	}

	@Override
	public void readNBT(Capability<IOffset> capability, IOffset instance, EnumFacing side, NBTBase base)
	{
		instance.setOffset(((NBTPrimitive) base).getDouble());
	}

}
