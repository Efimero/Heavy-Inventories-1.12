package superscary.heavyinventories.util;

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
public enum EnumTagID
{

	WEIGHT("HIWeight");

	private String id;
	EnumTagID(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}

	public static String getTagID(EnumTagID enumTagID)
	{
		return enumTagID.getId();
	}

}
