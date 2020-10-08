package com.youngbj.choongang.vo;

public class AuctionInfoVo {
	private String auc_idx;	//경매글번호
	private String mbr_idx;	//회원번호
	private String auc_pnm;	//경매품명
	private String auc_pexp;//경매품설명
	private String auc_lcst;//최저가
	private String auc_hcst;//최고가
	private String auc_rdat;//등록일시
	private String auc_sdat;//경매시작일시
	private String auc_edat;//종료일시
	//private String auc_iurl;//이미지url
	private String auc_rcnt;//조회수
	//생성자
	public AuctionInfoVo() {
		super();
	}
	public AuctionInfoVo(String auc_idx, String mbr_idx, String auc_pnm, String auc_pexp, String auc_lcst, String auc_hcst,
			String auc_rdat,String auc_sdat, String auc_edat, String auc_rcnt) {
		super();
		this.auc_idx = auc_idx;
		this.mbr_idx = mbr_idx;
		this.auc_pnm = auc_pnm;
		this.auc_pexp = auc_pexp;
		this.auc_lcst = auc_lcst;
		this.auc_hcst = auc_hcst;
		this.auc_rdat = auc_rdat;
		this.auc_sdat = auc_sdat;
		this.auc_edat = auc_edat;
		this.auc_rcnt = auc_rcnt;
	}
	
	//게터 세터
	public String getAuc_idx() {
		return auc_idx;
	}
	public void setAuc_idx(String auc_idx) {
		this.auc_idx = auc_idx;
	}
	public String getMbr_idx() {
		return mbr_idx;
	}
	public void setMbr_idx(String mbr_idx) {
		this.mbr_idx = mbr_idx;
	}
	public String getAuc_pnm() {
		return auc_pnm;
	}
	public void setAuc_pnm(String auc_pnm) {
		this.auc_pnm = auc_pnm;
	}
	public String getAuc_pexp() {
		return auc_pexp;
	}
	public void setAuc_pexp(String auc_pexp) {
		this.auc_pexp = auc_pexp;
	}
	public String getAuc_lcst() {
		return auc_lcst;
	}
	public void setAuc_lcst(String auc_lcst) {
		this.auc_lcst = auc_lcst;
	}
	public String getAuc_hcst() {
		return auc_hcst;
	}
	public void setAuc_hcst(String auc_hcst) {
		this.auc_hcst = auc_hcst;
	}
	public String getAuc_rdat() {
		return auc_rdat;
	}
	public void setAuc_rdat(String auc_rdat) {
		this.auc_rdat = auc_rdat;
	}
	public String getAuc_edat() {
		return auc_edat;
	}
	public void setAuc_edat(String auc_edat) {
		this.auc_edat = auc_edat;
	}
	public String getAuc_rcnt() {
		return auc_rcnt;
	}
	public void setAuc_rcnt(String auc_rcnt) {
		this.auc_rcnt = auc_rcnt;
	}
	public String getAuc_sdat() {
		return auc_sdat;
	}
	public void setAuc_sdat(String auc_sdat) {
		this.auc_sdat = auc_sdat;
	}
	
	
}
