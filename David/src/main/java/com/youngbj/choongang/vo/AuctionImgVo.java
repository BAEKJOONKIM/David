package com.youngbj.choongang.vo;

public class AuctionImgVo {
	private String i_idx;
	private String auc_idx;
	private String i_imgname;
	private String i_oriname;
	private String i_upload_date;
	//생성자
	public AuctionImgVo() {
		super();
	}
	public AuctionImgVo(String i_idx, String auc_idx, String i_imgname, String i_oriname, String i_upload_date) {
		super();
		this.i_idx = i_idx;
		this.auc_idx = auc_idx;
		this.i_imgname = i_imgname;
		this.i_oriname = i_oriname;
		this.i_upload_date = i_upload_date;
	}
	//게터세터
	public String getAuc_idx() {
		return auc_idx;
	}
	public void setAuc_idx(String auc_idx) {
		this.auc_idx = auc_idx;
	}
	public String getI_imgname() {
		return i_imgname;
	}
	public void setI_imgname(String i_imgname) {
		this.i_imgname = i_imgname;
	}
	public String getI_upload_date() {
		return i_upload_date;
	}
	public void setI_upload_date(String i_upload_date) {
		this.i_upload_date = i_upload_date;
	}
	public String getI_idx() {
		return i_idx;
	}
	public void setI_idx(String i_idx) {
		this.i_idx = i_idx;
	}
	public String getI_oriname() {
		return i_oriname;
	}
	public void setI_oriname(String i_oriname) {
		this.i_oriname = i_oriname;
	}
	
	
}
