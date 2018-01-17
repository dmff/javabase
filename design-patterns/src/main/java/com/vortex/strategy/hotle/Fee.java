package com.vortex.strategy.hotle;

import java.util.Calendar;

public interface Fee {

	public int fee(Calendar start, Calendar end, boolean isVip);
}
