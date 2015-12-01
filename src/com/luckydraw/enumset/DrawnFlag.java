package com.luckydraw.enumset;

import com.luckydraw.exception.FlagNullException;

public enum DrawnFlag {
	
	UNDRAWN(0),
	
	DRAWN(1),
	
	CANCELLED(2);
	
	int value;
	
	DrawnFlag(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static DrawnFlag valueOf(int i) {
		for (DrawnFlag drawnFlag : values()) {
			if (drawnFlag.getValue() == i) {
				return drawnFlag;
			}
		}
		throw new FlagNullException();
	}

}
