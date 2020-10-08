package com.youngbj.choongang.vo;

public class TrainerVerificationVo {

	private String trn_idx;
	private String mbr_idx;
	private String mbr_vrf;
	private String trn_vdat;
	private String trn_turl;
	
	public TrainerVerificationVo() {
		super();
	}

	public TrainerVerificationVo(String trn_idx, String mbr_idx, String mbr_vrf, String trn_vdat, String trn_turl) {
		super();
		this.trn_idx = trn_idx;
		this.mbr_idx = mbr_idx;
		this.mbr_vrf = mbr_vrf;
		this.trn_vdat = trn_vdat;
		this.trn_turl = trn_turl;
	}

	public String getTrn_idx() {
		return trn_idx;
	}

	public void setTrn_idx(String trn_idx) {
		this.trn_idx = trn_idx;
	}
	
	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	

	public String getMbr_vrf() {
		return mbr_vrf;
	}

	public void setMbr_vrf(String mbr_vrf) {
		this.mbr_vrf = mbr_vrf;
	}

	
	public String getTrn_vdat() {
		return trn_vdat;
	}

	public void setTrn_vdat(String trn_vdat) {
		this.trn_vdat = trn_vdat;
	}


	public String getTrn_turl() {
		return trn_turl;
	}

	public void setTrn_turl(String trn_turl) {
		this.trn_turl = trn_turl;
	}
	
	
	
}
