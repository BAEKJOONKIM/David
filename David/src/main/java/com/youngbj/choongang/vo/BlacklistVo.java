package com.youngbj.choongang.vo;

public class BlacklistVo {

	private String bl_idx;
	private String mbr_idx;
	private String bl_date;
	private String ubl_date;
	
	
	
	public BlacklistVo() {
		super();
	}



	public BlacklistVo(String bl_idx, String mbr_idx, String bl_date, String ubl_date) {
		super();
		this.bl_idx = bl_idx;
		this.mbr_idx = mbr_idx;
		this.bl_date = bl_date;
		this.ubl_date = ubl_date;
	}



	public String getBl_idx() {
		return bl_idx;
	}



	public void setBl_idx(String bl_idx) {
		this.bl_idx = bl_idx;
	}



	public String getMbr_idx() {
		return mbr_idx;
	}



	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}



	public String getBl_date() {
		return bl_date;
	}



	public void setBl_date(String bl_date) {
		this.bl_date = bl_date;
	}



	public String getUbl_date() {
		return ubl_date;
	}



	public void setUbl_date(String ubl_date) {
		this.ubl_date = ubl_date;
	}
	
	
}
