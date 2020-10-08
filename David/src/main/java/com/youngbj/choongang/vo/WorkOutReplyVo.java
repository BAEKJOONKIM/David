package com.youngbj.choongang.vo;

public class WorkOutReplyVo {

	private String wrpl_idx;
	private String wrk_idx;
	private String mbr_idx;
	private String mbr_nick;
	private String wrpl_con;
	private String wrpl_wdat;

	public WorkOutReplyVo() {
		super();
	}

	public WorkOutReplyVo(String wrpl_idx, String wrk_idx, String mbr_idx, String mbr_nick, String wrpl_con, String wrpl_wdat) {
		super();
		this.wrpl_idx = wrpl_idx;
		this.wrk_idx = wrk_idx;
		this.mbr_idx = mbr_idx;
		this.mbr_nick = mbr_nick;
		this.wrpl_con = wrpl_con;
		this.wrpl_wdat = wrpl_wdat;
	}

	public String getWrpl_idx() {
		return wrpl_idx;
	}

	public void setWrpl_idx(String wrpl_idx) {
		this.wrpl_idx = wrpl_idx;
	}

	public String getWrk_idx() {
		return wrk_idx;
	}

	public void setWrk_idx(String wrk_idx) {
		this.wrk_idx = wrk_idx;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	
	public String getMbr_nick() {
		return mbr_nick;
	}

	public void setMbr_nick(String mbr_nick) {
		this.mbr_nick = mbr_nick;
	}

	public String getWrpl_con() {
		return wrpl_con;
	}

	public void setWrpl_con(String wrpl_con) {
		this.wrpl_con = wrpl_con;
	}

	public String getWrpl_wdat() {
		return wrpl_wdat;
	}

	public void setWrpl_wdat(String wrpl_wdat) {
		this.wrpl_wdat = wrpl_wdat;
	}

}
