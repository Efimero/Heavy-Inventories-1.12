package superscary.heavyinventories.common.capability.weight;

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
public interface IWeighable
{

	double getWeight();
	double getMaxWeight();
	boolean isEncumbered();
	boolean isOverEncumbered();
	double getRelativeWeight();
	double getStamina();
	double getMaxStamina();
	double getSpeed();
	double getMaxSpeed();

	void increaseMaxWeight(double weight);
	void decreaseMaxWeight(double weight);
	void setMaxWeight(double weight);

	void increaseWeight(double weight);
	void decreaseWeight(double weight);
	void setWeight(double weight);

	void setEncumbered(boolean bool);
	void setOverEncumbered(boolean bool);

}
