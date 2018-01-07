package superscary.supercore.resources.properties;

import net.minecraftforge.common.property.IUnlistedProperty;

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
public class StringProperty implements IUnlistedProperty<String>
{

	private String name;

	public StringProperty(String name)
	{
		this.name = name;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public boolean isValid(String value)
	{
		return value != null;
	}

	@Override
	public Class<String> getType()
	{
		return String.class;
	}

	@Override
	public String valueToString(String value)
	{
		return value;
	}

}
