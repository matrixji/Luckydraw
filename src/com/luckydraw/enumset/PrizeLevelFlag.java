package com.luckydraw.enumset;

import com.luckydraw.exception.FlagNullException;

public enum PrizeLevelFlag {

	DEFAULT(-1),

	TOP_PRIZE(0),

	FIRST_PRIZE(1),

	SECOND_PRIZE(2),

	THIRD_PRIZE(3),

	FORTH_PRIZE(4),

	CONSOLATION_PRIZE(7);

	int value;

	private PrizeLevelFlag(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static PrizeLevelFlag valueOf(int i) {
		for (PrizeLevelFlag prizeLevelFlag : values()) {
			if (prizeLevelFlag.getValue() == i) {
				return prizeLevelFlag;
			}
		}
		throw new FlagNullException();
	}

}
