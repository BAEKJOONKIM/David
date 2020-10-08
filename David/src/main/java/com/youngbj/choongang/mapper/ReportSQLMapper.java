package com.youngbj.choongang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.AuctionInfoVo;
import com.youngbj.choongang.vo.AuctionReplyVo;
import com.youngbj.choongang.vo.BoardReplyVo;
import com.youngbj.choongang.vo.BoardVo;
import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_replyVo;
import com.youngbj.choongang.vo.ReportCategoryVo;
import com.youngbj.choongang.vo.ReportVo;
import com.youngbj.choongang.vo.WarningEmailVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

public interface ReportSQLMapper {

	// 누가 어떤 내용의 어떤 카테고리로 신고했는지
	@Select("SELECT * FROM report, report_category,member_info WHERE report.rpt_bcd = report_category.rpt_bcd AND member_info.mbr_idx = report.mbr_idx")
	public List<HashMap<String, Object>> selectAllReportInfo();

	@Select("SELECT * FROM report, report_category,member_info WHERE report.rpt_bcd = report_category.rpt_bcd AND member_info.mbr_idx = report.mbr_idx AND rpt_idx=#{rpt_idx}")
	public HashMap<String, Object> selectSingleReportInfo(String rpt_idx);

	
	// 1. report내용 가져오기
//	@Select("SELECT * FROM report")
//	public List<ReportVo> selectAll();
//	
	// 2. 회원정보 가져오기 (memberSQLMapper에서 selectByIdx 인터페이스)
	// 3. report당한 게시판 코드를 가져오기
//	@Select("SELECT *  FROM report_category  WHERE rpt_bcd = #{rpt_bcd}")
//	public ReportCategoryVo selectByBcd(String rpt_bcd);
	// 4. controller에서 mbr_idx와 rpt_bcd를 조건으로 3 객체를 엮이기;

	//----------------------------------------------- 검색 ------------------------------------------------
	// mbr_nick으로 검색하면
	@Select("SELECT * FROM report RP, report_category RPC, member_info MI WHERE MI.mbr_idx = RP.mbr_idx AND MI.mbr_idx = RPC.mbr_idx AND MI.mbr_nick LIKE '%'||#{keyword}||'%' ORDER BY RP.rpt_idx DESC")
	public List<HashMap<String, Object>> selectAllReportInfoSearchMbrNick(String keyword);

	// mbr_vrf으로 검색하면
	@Select("SELECT * FROM report RP, report_category RPC, member_info MI WHERE  MI.mbr_idx = RP.mbr_idx AND MI.mbr_idx = RPC.mbr_idx AND RPC.rpt_bcdn LIKE '%'||#{keyword}||'%' ORDER BY RP.rpt_idx DESC")
	public List<HashMap<String, Object>> selectAllReportInfoSearchRptBcdn(String keyword);

	
	
	
	//신고글 가져오기
	@Select("SELECT * FROM report WHERE rpt_idx=#{rpt_idx}")
	public ReportVo selectReportByIdx(String rpt_idx);
	
	//각 게시판에 글 가져오기
	@Select("SELECT * FROM board WHERE brd_idx=#{brd_idx}")
	public BoardVo selectFromBoardByIdx(String brd_idx);
	@Select("SELECT * FROM board_reply WHERE brpl_idx=#{brpl_idx}")
	public BoardReplyVo selectFromBoardReplyByIdx(String brpl_idx);
	@Select("SELECT * FROM My_flex WHERE flx_idx=#{flx_idx}")
	public My_flexContentVo selectFromMyFlexByIdx(String flx_idx);
	@Select("SELECT * FROM My_flex_reply WHERE frpl_idx=#{frpl_idx}")
	public My_flex_replyVo selectFromMyFlexReplyByIdx(String frpl_idx);
	@Select("SELECT * FROM Work_out WHERE wrk_idx=#{wrk_idx}")
	public WorkOutVo selectFromWorkOutByIdx(String wrk_idx);
	@Select("SELECT * FROM Work_out_reply WHERE wrpl_idx=#{wrpl_idx}")
	public WorkOutReplyVo selectFromWorkOutReplyByIdx(String wrpl_idx);
	@Select("SELECT * FROM Auction_info WHERE auc_idx=#{auc_idx}")
	public AuctionInfoVo selectFromAuctionInfoByIdx(String auc_idx);
	@Select("SELECT * FROM Auction_reply WHERE arpl_idx=#{arpl_idx}")
	public AuctionReplyVo selectFromAuctionReplyByIdx(String arpl_idx);
	

	//쓴 신고글을 저장하기
	@Insert("INSERT INTO report VALUES(seq_report.nextval,#{rpt_bcd},#{mbr_idx},#{b_idx},#{rpt_con},SYSDATE)")
	public void insertReport(ReportVo vo);
	
	
	//모든 게시판에서 중복신고당한 글을 count세기
	@Select("SELECT COUNT(*) FROM Report, Board WHERE report.b_idx = Board.BRD_IDX AND report.b_idx = #{b_idx}")
	public int selectBoardByReportCount(String b_idx);
	@Select("SELECT COUNT(*) FROM Report, Board_reply WHERE report.b_idx = Board_reply.Brpl_IDX AND report.b_idx = #{b_idx}")
	public int selectBoardReplyByReportCount(String brd_idx);
	@Select("SELECT COUNT(*) FROM Report, my_flex WHERE report.b_idx = my_flex.flx_IDX AND report.b_idx = #{b_idx}")
	public int selectMyFlexByReportCount(String brd_idx);
	@Select("SELECT COUNT(*) FROM Report, my_flex_reply WHERE report.b_idx = my_flex_reply.frpl_IDX AND report.b_idx = #{b_idx}")
	public int selectMyFlexReplyByReportCount(String brd_idx);
	@Select("SELECT COUNT(*) FROM Report, work_out WHERE report.b_idx = work_out.wrk_IDX AND report.b_idx = #{b_idx}")
	public int selectWorkOutByReportCount(String brd_idx);
	@Select("SELECT COUNT(*) FROM Report, work_out_reply WHERE report.b_idx = work_out_reply.frpl_IDX AND report.b_idx = #{b_idx}")
	public int selectWorkOutReplyByReportCount(String brd_idx);
	@Select("SELECT COUNT(*) FROM Report, auction_info WHERE report.b_idx = auction_info.auc_IDX AND report.b_idx = #{b_idx}")
	public int selectAuctionInfoByReportCount(String brd_idx);
	@Select("SELECT COUNT(*) FROM Report, auction_reply WHERE report.b_idx = auction_reply.arpl_IDX AND report.b_idx = #{b_idx}")
	public int selectAuctionReplyByReportCount(String brd_idx);
	

	
	//신고 당한 글 삭제
	@Delete("DELETE FROM Board WHERE brd_idx=#{b_idx}")
	public void deleteFromBoardByIdx(String b_idx);
	@Delete("DELETE FROM Board_reply WHERE brpl_idx=#{b_idx}")
	public void deleteFromBoardReplyByIdx(String b_idx);
	@Delete("DELETE FROM my_flex WHERE flx_idx=#{b_idx}")
	public void deleteFromMyFlexByIdx(String b_idx);
	@Delete("DELETE FROM my_flex_reply WHERE frpl_idx=#{b_idx}")
	public void deleteFromMyFlexReplyByIdx(String b_idx);
	@Delete("DELETE FROM work_out WHERE wrk_idx=#{b_idx}")
	public void deleteFromWorkOutByIdx(String b_idx);
	@Delete("DELETE FROM work_out_reply WHERE wrpl_idx=#{b_idx}")
	public void deleteFromWorkOutReplyByIdx(String b_idx);
	@Delete("DELETE FROM Auction_info WHERE auc_idx=#{b_idx}")
	public void deleteFromAuctionInfoByIdx(String b_idx);
	@Delete("DELETE FROM Auction_reply WHERE arpl_idx=#{b_idx}")
	public void deleteFromAuctionReplyByIdx(String b_idx);
	
	
	// 삭제된 글의 해당 신고글를 같이 지우기
	@Delete("DELETE FROM report WHERE rpt_bcd=#{rpt_bcd} AND b_idx=#{b_idx}")
	public void deleteReportAfterDeleteReportedContent(ReportVo vo);
	
	
	//신고이메일 보내기
	@Insert("INSERT INTO Warning_list VALUES(seq_warning_list.nextval, #{mbr_idx}, SYSDATE)")
	public void insertIntoWarningTable(String mbr_idx);
	
	@Delete("DELETE FROM Warning_list WHERE mbr_idx = #{mbr_idx}")
	public void deleteFromWarningTable(String mbr_idx);
		
}
