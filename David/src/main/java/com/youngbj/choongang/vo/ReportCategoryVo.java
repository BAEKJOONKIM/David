package com.youngbj.choongang.vo;

public class ReportCategoryVo {
	
	
	// 신고카테고리
		private String rpt_bcd;
		private String rpt_bcdn;
		
		public ReportCategoryVo() {
			super();
		}

		public ReportCategoryVo(String rpt_bcd, String rpt_bcdn) {
			super();
			this.rpt_bcd = rpt_bcd;
			this.rpt_bcdn = rpt_bcdn;
		}

		public String getRpt_bcd() {
			return rpt_bcd;
		}

		public void setRpt_bcd(String rpt_bcd) {
			this.rpt_bcd = rpt_bcd;
		}

		public String getRpt_bcdn() {
			return rpt_bcdn;
		}

		public void setRpt_bcdn(String rpt_bcdn) {
			this.rpt_bcdn = rpt_bcdn;
		}
		
		
		
		

}
