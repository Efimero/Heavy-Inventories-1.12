package superscary.heavyinventories.compat.mods.theoneprobe;

import com.google.common.base.Function;
import mcjty.theoneprobe.api.ITheOneProbe;
import superscary.heavyinventories.util.Logger;

import javax.annotation.Nullable;

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
public class HITheOneProbe implements Function<ITheOneProbe, Void>
{

	private static final String NAME = "The One Probe";

	public HITheOneProbe()
	{
		Logger.info("Oh I see you, %s", NAME);
		Logger.info("Loading compat for %s...", NAME);
	}

	@Nullable
	@Override
	public Void apply(ITheOneProbe theOneProbe)
	{
		theOneProbe.registerProvider(new HITOPInfoProvider());
		return null;
	}

}
