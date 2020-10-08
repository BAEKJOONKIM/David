package com.youngbj.choongang.vo;

public class My_flex_replyVo {
	
	private String frpl_idx;
	private String flx_idx;
	private String mbr_idx;
	private String frpl_con;
	private String frpl_fdat;
	
	public My_flex_replyVo() {
		super();
	}

	public My_flex_replyVo(String frpl_idx, String flx_idx, String mbr_idx, String frpl_con, String frpl_fdat) {
		super();
		this.frpl_idx = frpl_idx;
		this.flx_idx = flx_idx;
		this.mbr_idx = mbr_idx;
		this.frpl_con = frpl_con;
		this.frpl_fdat = frpl_fdat;
	}

	public String getFrpl_idx() {
		return frpl_idx;
	}

	public void setFrpl_idx(String frpl_idx) {
		this.frpl_idx = frpl_idx;
	}

	public String getFlx_idx() {
		return flx_idx;
	}

	public void setFlx_idx(String flx_idx) {
		this.flx_idx = flx_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getFrpl_con() {
		return frpl_con;
	}

	public void setFrpl_con(String frpl_con) {
		this.frpl_con = frpl_con;
	}

	public String getFrpl_fdat() {
		return frpl_fdat;
	}

	public void setFrpl_fdat(String frpl_fdat) {
		this.frpl_fdat = frpl_fdat;
	}

	public static void insert(My_flex_replyVo vo) {
		// TODO Auto-generated method stub
		
	}
	
	

}
