package com.youngbj.choongang.vo;

public class WorkOutPickMemberVo {

	private String wrk_idx;
	private String mbr_idx;
	
	public WorkOutPickMemberVo() {
		super();
	}

	public WorkOutPickMemberVo(String wrk_idx, String mbr_idx) {
		super();
		this.wrk_idx = wrk_idx;
		this.mbr_idx = mbr_idx;
	}

	public String getWrk_idx() {
		return wrk_idx;
	}

	public void setWrk_idx(String wrk_idx) {
		this.wrk_idx = wrk_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	
	
}
