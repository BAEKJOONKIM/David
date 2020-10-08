package com.youngbj.choongang.vo;

public class ReportVo {

	// 신고글쓰기
	private String rpt_idx;
	private String rpt_bcd;
	private String rpt_rdat;
	private String rpt_con;
	private String b_idx;
	//회원번호
	private String mbr_idx;
	
	
	public ReportVo() {
		super();
	}


	public ReportVo(String rpt_idx, String rpt_rdat, String rpt_con, String b_idx, String rpt_bcd, 
			String mbr_idx) {
		super();
		this.rpt_idx = rpt_idx;
		this.rpt_rdat = rpt_rdat;
		this.rpt_con = rpt_con;
		this.b_idx = b_idx;
		this.rpt_bcd = rpt_bcd;
		this.mbr_idx = mbr_idx;
	}


	public String getRpt_idx() {
		return rpt_idx;
	}


	public void setRpt_idx(String rpt_idx) {
		this.rpt_idx = rpt_idx;
	}


	public String getRpt_rdat() {
		return rpt_rdat;
	}


	public void setRpt_rdat(String rpt_rdat) {
		this.rpt_rdat = rpt_rdat;
	}


	public String getRpt_con() {
		return rpt_con;
	}


	public void setRpt_con(String rpt_con) {
		this.rpt_con = rpt_con;
	}


	public String getB_idx() {
		return b_idx;
	}


	public void setB_idx(String b_idx) {
		this.b_idx = b_idx;
	}


	public String getRpt_bcd() {
		return rpt_bcd;
	}


	public void setRpt_bcd(String rpt_bcd) {
		this.rpt_bcd = rpt_bcd;
	}


	public String getMbr_idx() {
		return mbr_idx;
	}


	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	


	
	
	
}
