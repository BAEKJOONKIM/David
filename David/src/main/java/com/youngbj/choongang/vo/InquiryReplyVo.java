package com.youngbj.choongang.vo;

public class InquiryReplyVo {
	
	private String irpl_idx;
	private String inq_idx;
	private String mbr_idx;
	private String irpl_con;
	private String irpl_rdat;
	
	public InquiryReplyVo() {
		super();
	}

	public InquiryReplyVo(String irpl_idx, String inq_idx, String mbr_idx, String irpl_con, String irpl_rdat) {
		super();
		this.irpl_idx = irpl_idx;
		this.inq_idx = inq_idx;
		this.mbr_idx = mbr_idx;
		this.irpl_con = irpl_con;
		this.irpl_rdat = irpl_rdat;
	}

	public String getIrpl_idx() {
		return irpl_idx;
	}

	public void setIrpl_idx(String irpl_idx) {
		this.irpl_idx = irpl_idx;
	}

	public String getInq_idx() {
		return inq_idx;
	}

	public void setInq_idx(String inq_idx) {
		this.inq_idx = inq_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getIrpl_con() {
		return irpl_con;
	}

	public void setIrpl_con(String irpl_con) {
		this.irpl_con = irpl_con;
	}

	public String getIrpl_rdat() {
		return irpl_rdat;
	}

	public void setIrpl_rdat(String irpl_rdat) {
		this.irpl_rdat = irpl_rdat;
	}
	
	

}
