package com.youngbj.choongang.vo;

public class BidInfoVo {
	private String bid_idx;	//낙찰번호
	private String bdng_idx;//입찰번호
	private String auc_idx; //경매번호
	private String mbr_idx;	//회원번호
	private String bid_cst;	//낙찰가격
	private String bid_dat;	//낙찰일시
	
	//생성자
	public BidInfoVo() {
		super();
	}
	public BidInfoVo(String bid_idx, String bdng_idx, String auc_idx, String mbr_idx, String bid_cst, String bid_dat) {
		super();
		this.bid_idx = bid_idx;
		this.bdng_idx = bdng_idx;
		this.auc_idx = auc_idx;
		this.mbr_idx = mbr_idx;
		this.bid_cst = bid_cst;
		this.bid_dat = bid_dat;
		
	}
	//게터세터
	public String getBid_idx() {
		return bid_idx;
	}
	public void setBid_idx(String bid_idx) {
		this.bid_idx = bid_idx;
	}
	public String getBdng_idx() {
		return bdng_idx;
	}
	public void setBdng_idx(String bdng_idx) {
		this.bdng_idx = bdng_idx;
	}
	public String getMbr_idx() {
		return mbr_idx;
	}
	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	public String getBid_cst() {
		return bid_cst;
	}
	public void setBid_cst(String bid_cst) {
		this.bid_cst = bid_cst;
	}
	public String getBid_dat() {
		return bid_dat;
	}
	public void setBid_dat(String bid_dat) {
		this.bid_dat = bid_dat;
	}
	public String getAuc_idx() {
		return auc_idx;
	}
	public void setAuc_idx(String auc_idx) {
		this.auc_idx = auc_idx;
	}
	
	
}
