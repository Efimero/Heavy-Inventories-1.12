package superscary.heavyinventories.common.capability.weight;

import superscary.heavyinventories.configs.HeavyInventoriesConfig;

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
public class Weight implements IWeighable
{

	private double weight = 0, maxWeight = HeavyInventoriesConfig.maxCarryWeight;
	private double speed = 0, maxSpeed = 1;
	private double stamina = 100, maxStamina = 100;
	private boolean isEncumbered = false, isOverEncumbered = false;

	@Override
	public double getWeight()
	{
		return this.weight;
	}

	@Override
	public double getMaxWeight()
	{
		return this.maxWeight;
	}

	@Override
	public boolean isEncumbered()
	{
		return getRelativeWeight() >= 0.85;
	}

	@Override
	public boolean isOverEncumbered()
	{
		return getRelativeWeight() >= 1.0;
	}

	@Override
	public double getRelativeWeight()
	{
		return getWeight() / getMaxWeight();
	}

	@Override
	public double getStamina()
	{
		return this.stamina;
	}

	@Override
	public double getMaxStamina()
	{
		return this.maxStamina;
	}

	@Override
	public double getSpeed()
	{
		return this.speed;
	}

	@Override
	public double getMaxSpeed()
	{
		return this.maxSpeed;
	}

	@Override
	public void increaseMaxWeight(double weight)
	{
		this.maxWeight += weight;
	}

	@Override
	public void decreaseMaxWeight(double weight)
	{
		this.maxWeight = (getMaxWeight() - weight > 0 ? maxWeight -= weight : 0);
	}

	@Override
	public void setMaxWeight(double weight)
	{
		this.maxWeight = weight;
	}

	@Override
	public void increaseWeight(double weight)
	{
		this.weight += weight;
	}

	@Override
	public void decreaseWeight(double weight)
	{
		this.weight = (getWeight() - weight > 0 ? this.weight -= weight : 0);
	}

	@Override
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	@Override
	public void setEncumbered(boolean bool)
	{
		this.isEncumbered = bool;
	}

	@Override
	public void setOverEncumbered(boolean bool)
	{
		this.isOverEncumbered = bool;
	}
}
