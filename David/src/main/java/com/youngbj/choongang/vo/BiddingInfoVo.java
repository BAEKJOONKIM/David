package com.youngbj.choongang.vo;

public class BiddingInfoVo {
	private String bdng_idx;//입찰번호
	private String auc_idx;	//경매글번호
	private String mbr_idx;	//회원번호
	private String bdng_cst;//입찰가격
	private String bdng_bdat;//입찰일시
	//생성자
	public BiddingInfoVo() {
		super();
	}
	public BiddingInfoVo(String bdng_idx, String auc_idx, String mbr_idx, String bdng_cst, String bdng_bdat) {
		super();
		this.bdng_idx = bdng_idx;
		this.auc_idx = auc_idx;
		this.mbr_idx = mbr_idx;
		this.bdng_cst = bdng_cst;
		this.bdng_bdat = bdng_bdat;
	}
	//게터세터
	public String getBdng_idx() {
		return bdng_idx;
	}
	public void setBdng_idx(String bdng_idx) {
		this.bdng_idx = bdng_idx;
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
	public String getBdng_cst() {
		return bdng_cst;
	}
	public void setBdng_cst(String bdng_cst) {
		this.bdng_cst = bdng_cst;
	}
	public String getBdng_bdat() {
		return bdng_bdat;
	}
	public void setBdng_bdat(String bdng_bdat) {
		this.bdng_bdat = bdng_bdat;
	}
	

}
