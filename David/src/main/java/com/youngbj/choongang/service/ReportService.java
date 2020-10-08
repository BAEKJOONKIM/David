package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public interface ReportService {
	
	//신고글번호  + 카테고리 + 신고자 가져오기 (신고글 모든 info)
	public List<HashMap<String, Object>> getReportList(String keyword, String searchType);

	public HashMap<String, Object> getDetailReport(String rpt_idx);
	
	public void deleteBasedOnReport(String b_idx);
		

	//추가
	public void addIntoWarningList(String mbr_idx);
}
