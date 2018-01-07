package superscary.heavyinventories.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import superscary.heavyinventories.configs.HeavyInventoriesConfig;

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
public class HeavyInventoriesGuiConfig extends GuiConfig
{
	public HeavyInventoriesGuiConfig(GuiScreen parent)
	{
		super(parent, (new ConfigElement(HeavyInventoriesConfig.getConfig().getCategory("Setup"))).getChildElements(),
				"HeavyInventories", false, true, "Heavy Inventories Config");
	}
}
