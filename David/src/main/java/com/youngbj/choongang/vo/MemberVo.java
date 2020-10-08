package com.youngbj.choongang.vo;

public class MemberVo {
	
	private String mbr_idx;
	private String mbr_id;
	private String mbr_pw;
	private String mbr_name;
	private String mbr_nmbr;
	private String mbr_emil;
	private String mbr_nick;
	private String mbr_sex;
	private String mbr_bth;
	private String mbr_trnr;
	private String mbr_mngr;
	private String mbr_joindate;
	
	public MemberVo() {
		super();
	}

	public MemberVo(String mbr_idx, String mbr_id, String mbr_pw, String mbr_name, String mbr_nmbr, String mbr_emil,
			String mbr_nick, String mbr_sex, String mbr_bth, String mbr_trnr, String mbr_mngr, String mbr_joindate) {
		super();
		this.mbr_idx = mbr_idx;
		this.mbr_id = mbr_id;
		this.mbr_pw = mbr_pw;
		this.mbr_name = mbr_name;
		this.mbr_nmbr = mbr_nmbr;
		this.mbr_emil = mbr_emil;
		this.mbr_nick = mbr_nick;
		this.mbr_sex = mbr_sex;
		this.mbr_bth = mbr_bth;
		this.mbr_trnr = mbr_trnr;
		this.mbr_mngr = mbr_mngr;
		this.mbr_joindate = mbr_joindate;
	}

	public String getMbr_idx() {
		return mbr_idx;
	}

	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}

	public String getMbr_id() {
		return mbr_id;
	}

	public void setMbr_id(String mbr_id) {
		this.mbr_id = mbr_id;
	}

	public String getMbr_pw() {
		return mbr_pw;
	}

	public void setMbr_pw(String mbr_pw) {
		this.mbr_pw = mbr_pw;
	}

	public String getMbr_name() {
		return mbr_name;
	}

	public void setMbr_name(String mbr_name) {
		this.mbr_name = mbr_name;
	}

	public String getMbr_nmbr() {
		return mbr_nmbr;
	}

	public void setMbr_nmbr(String mbr_nmbr) {
		this.mbr_nmbr = mbr_nmbr;
	}

	public String getMbr_emil() {
		return mbr_emil;
	}

	public void setMbr_emil(String mbr_emil) {
		this.mbr_emil = mbr_emil;
	}

	public String getMbr_nick() {
		return mbr_nick;
	}

	public void setMbr_nick(String mbr_nick) {
		this.mbr_nick = mbr_nick;
	}

	public String getMbr_sex() {
		return mbr_sex;
	}

	public void setMbr_sex(String mbr_sex) {
		this.mbr_sex = mbr_sex;
	}

	public String getMbr_bth() {
		return mbr_bth;
	}

	public void setMbr_bth(String mbr_bth) {
		this.mbr_bth = mbr_bth;
	}

	public String getMbr_trnr() {
		return mbr_trnr;
	}

	public void setMbr_trnr(String mbr_trnr) {
		this.mbr_trnr = mbr_trnr;
	}

	public String getMbr_mngr() {
		return mbr_mngr;
	}

	public void setMbr_mngr(String mbr_mngr) {
		this.mbr_mngr = mbr_mngr;
	}

	public String getMbr_joindate() {
		return mbr_joindate;
	}

	public void setMbr_joindate(String mbr_joindate) {
		this.mbr_joindate = mbr_joindate;
	}



}
