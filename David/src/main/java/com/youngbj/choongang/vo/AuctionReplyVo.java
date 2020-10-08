package com.youngbj.choongang.vo;

public class AuctionReplyVo {
	private String arpl_idx;	//경매댓글번호
	private String auc_idx;		//경매글번호
	private String mbr_idx;		//회원번호
	private String arpl_con;	//경매 댓글 내용
	private String arpl_rdat;	//경매 댓글 등록일시
	//생성자
	public AuctionReplyVo() {
		super();
	}
	public AuctionReplyVo(String arpl_idx, String auc_idx, String mbr_idx, String arpl_con, String arpl_rdat) {
		super();
		this.arpl_idx = arpl_idx;
		this.auc_idx = auc_idx;
		this.mbr_idx = mbr_idx;
		this.arpl_con = arpl_con;
		this.arpl_rdat = arpl_rdat;
	}
	//게터세터
	public String getArpl_idx() {
		return arpl_idx;
	}
	public void setArpl_idx(String arpl_idx) {
		this.arpl_idx = arpl_idx;
	}
	public String getAuc_idx() {
		return auc_idx;
	}
	public void setAuc_idx(String auc_idx) {
		this.auc_idx = auc_idx;
	}
	public String getMbr_idx() {
		return mbr_idx;
	}
	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	public String getArpl_con() {
		return arpl_con;
	}
	public void setArpl_con(String arpl_con) {
		this.arpl_con = arpl_con;
	}
	public String getArpl_rdat() {
		return arpl_rdat;
	}
	public void setArpl_rdat(String arpl_rdat) {
		this.arpl_rdat = arpl_rdat;
	}
	
	
}
