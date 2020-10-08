package com.youngbj.choongang.vo;

public class TrainerVerificationFileVo {

	
	private String f_idx;
	private String trn_idx;
	private String f_filename;
	private String f_upload_date;
	
	public TrainerVerificationFileVo() {
		super();
	}

	public TrainerVerificationFileVo(String f_idx, String trn_idx, String f_filename, String f_upload_date) {
		super();
		this.f_idx = f_idx;
		this.trn_idx = trn_idx;
		this.f_filename = f_filename;
		this.f_upload_date = f_upload_date;
	}

	public String getF_idx() {
		return f_idx;
	}

	public void setF_idx(String f_idx) {
		this.f_idx = f_idx;
	}

	public String getTrn_idx() {
		return trn_idx;
	}

	public void setTrn_idx(String trn_idx) {
		this.trn_idx = trn_idx;
	}

	public String getF_filename() {
		return f_filename;
	}

	public void setF_filename(String f_filename) {
		this.f_filename = f_filename;
	}

	public String getF_upload_date() {
		return f_upload_date;
	}

	public void setF_upload_date(String f_upload_date) {
		this.f_upload_date = f_upload_date;
	}
	
	
	
	
	
}
