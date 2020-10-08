package com.youngbj.choongang.vo;

public class AuctionPickMemberVo {
	private String auc_idx;		//경매글번호
	private String mbr_idx;		//회원 번호
	//생성자
	public AuctionPickMemberVo() {
		super();
	}
	public AuctionPickMemberVo(String auc_idx, String mbr_idx) {
		super();
		this.auc_idx = auc_idx;
		this.mbr_idx = mbr_idx;
	}
	//게터세터
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
	
	
}
