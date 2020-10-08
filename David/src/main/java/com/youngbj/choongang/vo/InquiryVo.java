package com.youngbj.choongang.vo;

public class InquiryVo {
	
	private String inq_idx;
	private String mbr_idx;
	private String inq_ttl;
	private String inq_con;
	private String inq_vrf;
	private String inq_idat;
	
	
	public InquiryVo() {
		super();
	}

	public InquiryVo(String inq_idx, String mbr_idx, String inq_ttl, String inq_con, String inq_vrf, String inq_idat) {
		super();
		this.inq_idx = inq_idx;
		this.mbr_idx = mbr_idx;
		this.inq_ttl = inq_ttl;
		this.inq_con = inq_con;
		this.inq_vrf = inq_vrf;
		this.inq_idat = inq_idat;
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

	public String getInq_ttl() {
		return inq_ttl;
	}

	public void setInq_ttl(String inq_ttl) {
		this.inq_ttl = inq_ttl;
	}

	public String getInq_con() {
		return inq_con;
	}

	public void setInq_con(String inq_con) {
		this.inq_con = inq_con;
	}
	
	public String getInq_vrf() {
		return inq_vrf;
	}

	public void setInq_vrf(String inq_vrf) {
		this.inq_vrf = inq_vrf;
	}
	
	public String getInq_idat() {
		return inq_idat;
	}

	public void setInq_idat(String inq_idat) {
		this.inq_idat = inq_idat;
	}


}
