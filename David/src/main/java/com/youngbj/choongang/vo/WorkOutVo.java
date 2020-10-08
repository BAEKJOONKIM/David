package com.youngbj.choongang.vo;

public class WorkOutVo {

	private String wrk_idx;
	private String wrk_cat;
	private String mbr_idx;
	private String wrk_ttl;
	private String wrk_con;
	private String wrk_wdat;
	private String wrk_surl;
	private String wrk_vurl;
	private String wrk_lcnt;
	private String wrk_rcnt;
	
	public WorkOutVo() {
		super();
	}

	public WorkOutVo(String wrk_idx, String wrk_cat, String mbr_idx, String wrk_ttl, String wrk_con, String wrk_wdat,
			String wrk_surl, String wrk_vurl, String wrk_lcnt, String wrk_rcnt) {
		super();
		this.wrk_idx = wrk_idx;
		this.wrk_cat = wrk_cat;
		this.mbr_idx = mbr_idx;
		this.wrk_ttl = wrk_ttl;
		this.wrk_con = wrk_con;
		this.wrk_wdat = wrk_wdat;
		this.wrk_surl = wrk_surl;
		this.wrk_vurl = wrk_vurl;
		this.wrk_lcnt = wrk_lcnt;
		this.wrk_rcnt = wrk_rcnt;
	}

	public String getWrk_idx() {
		return wrk_idx;
	}

	public void setWrk_idx(String wrk_idx) {
		this.wrk_idx = wrk_idx;
	}

	public String getWrk_cat() {
		return wrk_cat;
	}

	public void setWrk_cat(String wrk_cat) {
		this.wrk_cat = wrk_cat;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getWrk_ttl() {
		return wrk_ttl;
	}

	public void setWrk_ttl(String wrk_ttl) {
		this.wrk_ttl = wrk_ttl;
	}

	public String getWrk_con() {
		return wrk_con;
	}

	public void setWrk_con(String wrk_con) {
		this.wrk_con = wrk_con;
	}

	public String getWrk_wdat() {
		return wrk_wdat;
	}

	public void setWrk_wdat(String wrk_wdat) {
		this.wrk_wdat = wrk_wdat;
	}

	public String getWrk_surl() {
		return wrk_surl;
	}

	public void setWrk_surl(String wrk_surl) {
		this.wrk_surl = wrk_surl;
	}

	public String getWrk_vurl() {
		return wrk_vurl;
	}

	public void setWrk_vurl(String wrk_vurl) {
		this.wrk_vurl = wrk_vurl;
	}

	public String getWrk_lcnt() {
		return wrk_lcnt;
	}

	public void setWrk_lcnt(String wrk_lcnt) {
		this.wrk_lcnt = wrk_lcnt;
	}

	public String getWrk_rcnt() {
		return wrk_rcnt;
	}

	public void setWrk_rcnt(String wrk_rcnt) {
		this.wrk_rcnt = wrk_rcnt;
	}
	
}
