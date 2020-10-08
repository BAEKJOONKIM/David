package com.youngbj.choongang.vo;

public class BoardReplyVo {

	private String brpl_idx;
	private String brd_idx;
	private String mbr_idx;
	private String brpl_con;
	private String brpl_rdat;
	
	public BoardReplyVo() {
		super();
	}

	public BoardReplyVo(String brpl_idx, String brd_idx, String mbr_idx, String brpl_con, String brpl_rdat) {
		super();
		this.brpl_idx = brpl_idx;
		this.brd_idx = brd_idx;
		this.mbr_idx = mbr_idx;
		this.brpl_con = brpl_con;
		this.brpl_rdat = brpl_rdat;
	}

	public String getBrpl_idx() {
		return brpl_idx;
	}

	public void setBrpl_idx(String brpl_idx) {
		this.brpl_idx = brpl_idx;
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

	public String getBrpl_con() {
		return brpl_con;
	}

	public void setBrpl_con(String brpl_con) {
		this.brpl_con = brpl_con;
	}

	public String getBrpl_rdat() {
		return brpl_rdat;
	}

	public void setBrpl_rdat(String brpl_rdat) {
		this.brpl_rdat = brpl_rdat;
	}
	
	
	
}
