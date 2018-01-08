package superscary.heavyinventories.compat.event;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;

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

@SuppressWarnings("unused")
public interface IPlayerCompatEvent
{

	void register();

	@Mod.EventHandler
	void playerTickEvent(TickEvent.PlayerTickEvent event);

}
