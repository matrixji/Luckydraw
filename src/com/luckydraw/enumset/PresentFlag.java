package com.luckydraw.enumset;

import java.io.Serializable;

import com.luckydraw.exception.FlagNullException;

public enum PresentFlag implements Serializable{
	
    SHOULD_BE_PRESENT(0,"应到场","请补抽"),
	
	IN_LEAVE(1,"未到场","请补发"),
	
	STAFF(2,"工作人员","请补发");
		
    int value;
    
    String description;
    
    String note;
	
    PresentFlag(int value,String description,String note){
		this.value = value;
		this.description = description;
		this.note = note;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getNote(){
		return note;
	}
	
	public static PresentFlag valueOf(int i) {
		for (PresentFlag presentFlag : values()) {
			if (presentFlag.getValue() == i) {
				return presentFlag;
			}
		}
		throw new FlagNullException();
	}

}
