package com.luckydraw.dto;

public class Employee {
	
	private int id;
	private int nsnId;
	private String name;
	private int drawnFlag;
	private int prizeLevel;
	private int sequenceId;
	private int presentFlag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNsnId() {
		return nsnId;
	}
	public void setNsnId(int nsnId) {
		this.nsnId = nsnId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public int getPrizeLevel() {
		return prizeLevel;
	}
	public void setPrizeLevel(int prizeLevel) {
		this.prizeLevel = prizeLevel;
	}
	public int getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
	
	public int getDrawnFlag() {
		return drawnFlag;
	}
	public void setDrawnFlag(int drawnFlag) {
		this.drawnFlag = drawnFlag;
	}
	public int getPresentFlag() {
		return presentFlag;
	}
	public void setPresentFlag(int presentFlag) {
		this.presentFlag = presentFlag;
	}
	@Override
	public String toString() {
		String output = "";
		output += "Employee:[";
		output += "id="+id+",nsnId="+nsnId+",name="+name+",drawnFlag="+drawnFlag+",prizeLevel="+prizeLevel
			      +",sequenceId="+sequenceId+",presentFlag="+presentFlag;
		output += "]";
		return output;
	}

}
