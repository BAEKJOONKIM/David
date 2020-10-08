package com.youngbj.choongang.vo;

public class BoardVo {

	private String brd_idx;
	private String mbr_idx;
	private String brd_ttl;
	private String brd_con;
	private String brd_bdat;
	private String brd_imgurl;
	private String brd_rcnt;
	private String brd_lcnt;

	public BoardVo() {
		super();
	}

	public BoardVo(String brd_idx, String mbr_idx, String brd_ttl, String brd_con, String brd_bdat, String brd_imgurl,
			String brd_rcnt, String brd_lcnt) {
		super();
		this.brd_idx = brd_idx;
		this.mbr_idx = mbr_idx;
		this.brd_ttl = brd_ttl;
		this.brd_con = brd_con;
		this.brd_bdat = brd_bdat;
		this.brd_imgurl = brd_imgurl;
		this.brd_rcnt = brd_rcnt;
		this.brd_lcnt = brd_lcnt;
	}

	public String getBrd_idx() {
		return brd_idx;
	}

	public void setBrd_idx(String brd_idx) {
		this.brd_idx = brd_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getBrd_ttl() {
		return brd_ttl;
	}

	public void setBrd_ttl(String brd_ttl) {
		this.brd_ttl = brd_ttl;
	}

	public String getBrd_con() {
		return brd_con;
	}

	public void setBrd_con(String brd_con) {
		this.brd_con = brd_con;
	}

	public String getBrd_bdat() {
		return brd_bdat;
	}

	public void setBrd_bdat(String brd_bdat) {
		this.brd_bdat = brd_bdat;
	}

	public String getBrd_imgurl() {
		return brd_imgurl;
	}

	public void setBrd_imgurl(String brd_imgurl) {
		this.brd_imgurl = brd_imgurl;
	}

	public String getBrd_rcnt() {
		return brd_rcnt;
	}

	public void setBrd_rcnt(String brd_rcnt) {
		this.brd_rcnt = brd_rcnt;
	}

	public String getBrd_lcnt() {
		return brd_lcnt;
	}

	public void setBrd_lcnt(String brd_lcnt) {
		this.brd_lcnt = brd_lcnt;
	}

}
