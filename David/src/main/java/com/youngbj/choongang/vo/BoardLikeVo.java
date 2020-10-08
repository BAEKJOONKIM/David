package com.youngbj.choongang.vo;

public class BoardLikeVo {
	
	private String brd_idx;
	private String mbr_idx;
	
	public BoardLikeVo() {
		super();
	}

	public BoardLikeVo(String brd_idx, String mbr_idx) {
		super();
		this.brd_idx = brd_idx;
		this.mbr_idx = mbr_idx;
	}

	public String getBrd_idx() {
		return brd_idx;
	}

	public void setBrd_idx(String brd_idx) {
		this.brd_idx = brd_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	
}
