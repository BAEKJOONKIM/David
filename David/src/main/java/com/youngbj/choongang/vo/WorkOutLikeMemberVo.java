package com.youngbj.choongang.vo;

public class WorkOutLikeMemberVo {

	private String wrk_idx;
	private String mbr_idx;
	private String wolm_like;
	
	public WorkOutLikeMemberVo() {
		super();
	}

	public WorkOutLikeMemberVo(String wrk_idx, String mbr_idx, String wolm_like) {
		super();
		this.wrk_idx = wrk_idx;
		this.mbr_idx = mbr_idx;
		this.wolm_like = wolm_like;
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
	
	public String getWolm_like() {
		return wolm_like;
	}

	public void setWolm_like(String wolm_like) {
		this.wolm_like = wolm_like;
	}
	
	
}
