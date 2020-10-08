package com.youngbj.choongang.vo;

public class BodyVo {
	
	private String mbr_idx;
	private String bd_height;
	private String bd_weight;
	private String bd_age;
	
	public BodyVo() {
		super();
	}

	public BodyVo(String mbr_idx, String bd_height, String bd_weight, String bd_age) {
		super();
		this.mbr_idx = mbr_idx;
		this.bd_height = bd_height;
		this.bd_weight = bd_weight;
		this.bd_age = bd_age;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getBd_height() {
		return bd_height;
	}

	public void setBd_height(String bd_height) {
		this.bd_height = bd_height;
	}

	public String getBd_weight() {
		return bd_weight;
	}

	public void setBd_weight(String bd_weight) {
		this.bd_weight = bd_weight;
	}

	public String getBd_age() {
		return bd_age;
	}

	public void setBd_age(String bd_age) {
		this.bd_age = bd_age;
	}

}
