package com.youngbj.choongang.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.ReportSQLMapper;
import com.youngbj.choongang.service.ReportService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.ReportVo;

@Service
public class ReportServiceImpl implements ReportService {

	// 1.memberVo와 reportVo 갖고오기
	@Autowired
	private ReportSQLMapper reportSQLMapper;

	@Autowired
	private MemberSQLMapper memberSQLMapper;

	// 2.hashmap으로 reportvo와 membervo를 엮어서 list에 담기
	@Override
	public List<HashMap<String, Object>> getReportList(String keyword, String searchType) {


//		// 1)reportvo객체 가져오기
//		List<ReportVo> reportInfoList = reportSQLMapper.selectAll();
//
//		// 4)세가지 객체를 담은 HashMap를 하나의 List에 담기
//		List<HashMap<String, Object>> resultReportList = new ArrayList<HashMap<String, Object>>();
//
//		// 2)reportvo객체 개수만큼 memberVo객체를 가져와서 mbr_idx붙여주고; reportInfoVo객체의 rpt_bcd를 붙여주기
//		for (ReportVo reportVo : reportInfoList) {
//			MemberVo memberVo = memberSQLMapper.selectIdx(reportVo.getMbr_idx());
//			ReportCategoryVo reportBcdVo = reportSQLMapper.selectByBcd(reportVo.getRpt_bcd());
//
//			// 3)한바퀴 돌때마다 세가지 객체를 가지고 hashmap생성하고 키지정해주기
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("reportVo", reportVo);
//			map.put("memberVo", memberVo);
//			map.put("reportBcd", reportBcdVo);
//
//			// hashmap를 하나의 list에 담기
//			resultReportList.add(map);
//			
//		}
//		
//		return resultReportList;

		List<HashMap<String, Object>> result = null;
		// 검색
		if (keyword != null) {
			if (searchType.equals("mbr_nick")) {
				result = reportSQLMapper.selectAllReportInfoSearchMbrNick(keyword);

			} else if (searchType.equals("rpt_bcdn")) {

				result = reportSQLMapper.selectAllReportInfoSearchRptBcdn(keyword);

			}
		}else {
			result = reportSQLMapper.selectAllReportInfo();
		}
		return result;
	}

	// 신고글 자세히보기
	@Override
	public HashMap<String, Object> getDetailReport(String rpt_idx) {
		// TODO Auto-generated method stub
//		HashMap<String, Object> map = new HashMap<String, Object>();
//
//		ReportVo reportcontent = reportSQLMapper.selectReportByIdx(rpt_idx);
//		MemberVo memberReportVo = memberSQLMapper.selectIdx(reportcontent.getMbr_idx()); // 신고자 정보 추출
//		ReportCategoryVo reportBcdVo = reportSQLMapper.selectByBcd(reportcontent.getRpt_bcd()); // 신고당한 게시판 이름
//
//		map.put("reportVo", reportcontent);
//		map.put("memberVo", memberReportVo);
//		map.put("reportBcd", reportBcdVo);

		HashMap<String, Object> SingleReportInfo = reportSQLMapper.selectSingleReportInfo(rpt_idx);

		String reportTarget = null;
		int reportCount = 0;

		if (SingleReportInfo.get("RPT_BCD").toString().equals("1")) {

			// 자유게시판에서 b_idx검색하고 brd_idx를 통해 mbr_idx를 가져오기

			reportTarget = reportSQLMapper.selectFromBoardByIdx(SingleReportInfo.get("B_IDX").toString()).getMbr_idx();
			reportCount = reportSQLMapper.selectBoardByReportCount(SingleReportInfo.get("B_IDX").toString());

		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("2")) {

			reportTarget = reportSQLMapper.selectFromBoardReplyByIdx(SingleReportInfo.get("B_IDX").toString())
					.getMbr_idx();
			reportCount = reportSQLMapper.selectBoardReplyByReportCount(SingleReportInfo.get("B_IDX").toString());

		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("3")) {

			reportTarget = reportSQLMapper.selectFromMyFlexByIdx(SingleReportInfo.get("B_IDX").toString()).getMbr_idx();
			reportCount = reportSQLMapper.selectMyFlexByReportCount(SingleReportInfo.get("B_IDX").toString());

		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("4")) {

			reportTarget = reportSQLMapper.selectFromMyFlexReplyByIdx(SingleReportInfo.get("B_IDX").toString())
					.getMbr_idx();
			reportCount = reportSQLMapper.selectMyFlexReplyByReportCount(SingleReportInfo.get("B_IDX").toString());

		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("5")) {

			reportTarget = reportSQLMapper.selectFromWorkOutByIdx(SingleReportInfo.get("B_IDX").toString())
					.getMbr_idx();
			reportCount = reportSQLMapper.selectWorkOutByReportCount(SingleReportInfo.get("B_IDX").toString());
		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("6")) {

			reportTarget = reportSQLMapper.selectFromWorkOutReplyByIdx(SingleReportInfo.get("B_IDX").toString())
					.getMbr_idx();
			reportCount = reportSQLMapper.selectWorkOutReplyByReportCount(SingleReportInfo.get("B_IDX").toString());
		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("7")) {

			reportTarget = reportSQLMapper.selectFromAuctionInfoByIdx(SingleReportInfo.get("B_IDX").toString())
					.getMbr_idx();
			reportCount = reportSQLMapper.selectAuctionInfoByReportCount(SingleReportInfo.get("B_IDX").toString());

		} else if (SingleReportInfo.get("RPT_BCD").toString().equals("8")) {

			reportTarget = reportSQLMapper.selectFromAuctionReplyByIdx(SingleReportInfo.get("B_IDX").toString())
					.getMbr_idx();
			reportCount = reportSQLMapper.selectAuctionReplyByReportCount(SingleReportInfo.get("B_IDX").toString());

		}

		MemberVo memberReportTargetVo = memberSQLMapper.selectIdx(reportTarget);
		SingleReportInfo.put("memberReportTarget", memberReportTargetVo);
		SingleReportInfo.put("reportCount", reportCount);

		return SingleReportInfo;
	}

	@Override
	public void deleteBasedOnReport(String rpt_idx) {

		ReportVo contentToBoDeleted = reportSQLMapper.selectReportByIdx(rpt_idx);
		// MemberVo memberReported =
		// memberSQLMapper.selectByIdx(contentToBoDeleted.getMbr_idx());

		switch (contentToBoDeleted.getRpt_bcd()) {
		case "1":
			// 자유게시판에서 b_idx검색하고 brd_idx를 통해 mbr_idx를 가져오기
			reportSQLMapper.deleteFromBoardByIdx(contentToBoDeleted.getB_idx());
			break;

		case "2":
			reportSQLMapper.deleteFromBoardReplyByIdx(contentToBoDeleted.getB_idx());
			break;

		case "3":
			reportSQLMapper.deleteFromMyFlexByIdx(contentToBoDeleted.getB_idx());
			break;

		case "4":
			reportSQLMapper.deleteFromMyFlexReplyByIdx(contentToBoDeleted.getB_idx());
			break;

		case "5":
			reportSQLMapper.deleteFromWorkOutByIdx(contentToBoDeleted.getB_idx());
			break;

		case "6":
			reportSQLMapper.deleteFromWorkOutReplyByIdx(contentToBoDeleted.getB_idx());
			break;

		case "7":
			reportSQLMapper.deleteFromAuctionInfoByIdx(contentToBoDeleted.getB_idx());
			break;

		case "8":
			reportSQLMapper.deleteFromAuctionReplyByIdx(contentToBoDeleted.getB_idx());
			break;

		}

		reportSQLMapper.deleteReportAfterDeleteReportedContent(contentToBoDeleted);

	}

	@Override
	public void addIntoWarningList(String mbr_idx) {
		reportSQLMapper.insertIntoWarningTable(mbr_idx);

	}

}
