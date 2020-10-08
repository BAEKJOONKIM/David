package com.youngbj.choongang.vo;

public class WarningEmailVo {
	
	private String wrn_idx;
	private String mbr_idx;
	private String wrn_dat;
	
	public WarningEmailVo() {
		super();
	}

	public WarningEmailVo(String wrn_idx, String mbr_idx, String wrn_dat) {
		super();
		this.wrn_idx = wrn_idx;
		this.mbr_idx = mbr_idx;
		this.wrn_dat = wrn_dat;
	}

	public String getWrn_idx() {
		return wrn_idx;
	}

	public void setWrn_idx(String wrn_idx) {
		this.wrn_idx = wrn_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getWrn_dat() {
		return wrn_dat;
	}

	public void setWrn_dat(String wrn_dat) {
		this.wrn_dat = wrn_dat;
	}


}
