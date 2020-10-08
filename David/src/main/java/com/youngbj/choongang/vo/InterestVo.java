package com.youngbj.choongang.vo;

public class InterestVo {
	
	private String mbr_idx;
	private String wrk_cat;
	
	public InterestVo() {
		super();
	}

	public InterestVo(String mbr_idx, String wrk_cat) {
		super();
		this.mbr_idx = mbr_idx;
		this.wrk_cat = wrk_cat;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getWrk_cat() {
		return wrk_cat;
	}

	public void setWrk_cat(String wrk_cat) {
		this.wrk_cat = wrk_cat;
	}
	
	

}
