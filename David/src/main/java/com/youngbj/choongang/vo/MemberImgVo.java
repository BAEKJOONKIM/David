package com.youngbj.choongang.vo;

public class MemberImgVo {
	
	private String mbr_idx;
	private String mbri_url;
	
	public MemberImgVo() {
		super();
	}

	public MemberImgVo(String mbr_idx, String mbri_url) {
		super();
		this.mbr_idx = mbr_idx;
		this.mbri_url = mbri_url;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getMbri_url() {
		return mbri_url;
	}

	public void setMbri_url(String mbri_url) {
		this.mbri_url = mbri_url;
	}
	
	

}
