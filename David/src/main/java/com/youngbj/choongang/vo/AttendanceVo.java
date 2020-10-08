package com.youngbj.choongang.vo;

public class AttendanceVo {
 
	private String mbr_idx;
	private String att_date;
	
	public AttendanceVo() {
		super();
	}

	public AttendanceVo(String mbr_idx, String att_date) {
		super();
		this.mbr_idx = mbr_idx;
		this.att_date = att_date;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getAtt_date() {
		return att_date;
	}

	public void setAtt_date(String att_date) {
		this.att_date = att_date;
	}
	
	
}
