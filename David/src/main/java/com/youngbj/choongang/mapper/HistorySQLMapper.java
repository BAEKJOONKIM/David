package com.youngbj.choongang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.BoardReplyVo;
import com.youngbj.choongang.vo.BoardVo;
import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_replyVo;
import com.youngbj.choongang.vo.WorkOutPickMemberVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

public interface HistorySQLMapper {
	//게시글 2개씩보이게
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM Board WHERE mbr_idx = #{mbr_idx} ORDER BY brd_idx DESC) t1) t2 WHERE 1 <= t2.rnum AND t2.rnum <=2")
	public List<BoardVo> selectBoardByIdx(String mbr_idx);
	
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM My_flex WHERE mbr_idx = #{mbr_idx} ORDER BY flx_idx DESC) t1) t2 WHERE 1 <= t2.rnum AND t2.rnum <=2")
	public  List<My_flexContentVo> selectMyFlexByIdx(String mbr_idx);

	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM Work_out WHERE mbr_idx = #{mbr_idx} ORDER BY wrk_idx DESC) t1) t2 WHERE 1 <= t2.rnum AND t2.rnum <=2")
	public List<WorkOutVo> selectWorkOutByIdx(String mbr_idx);
	

	//댓글 2개씩보이게
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM Board_reply WHERE mbr_idx = #{mbr_idx} ORDER BY brpl_idx DESC) t1) t2 WHERE 1 <= t2.rnum AND t2.rnum <=2")
	public List<BoardReplyVo> selectBoardReplyByIdx(String mbr_idx);

	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM My_flex_reply WHERE mbr_idx = #{mbr_idx} ORDER BY frpl_idx DESC) t1) t2 WHERE 1 <= t2.rnum AND t2.rnum <=2")
	public List<My_flex_replyVo> selectMyFlexReplyByIdx(String mbr_idx);

	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM Work_out_reply WHERE mbr_idx = #{mbr_idx} ORDER BY wrpl_idx DESC) t1) t2 WHERE 1 <= t2.rnum AND t2.rnum <=2")
	public List<WorkOutReplyVo> selectWorkOutReplyByIdx(String mbr_idx);

	//찜 가져오기
	@Select("SELECT * FROM Member_info, Work_out, Work_out_pick_member WHERE Member_info.mbr_idx = Work_out_pick_member.mbr_idx AND Work_out.wrk_idx = Work_out_pick_member.wrk_idx AND Work_out_pick_member.mbr_idx=#{mbr_idx}")
	public List<WorkOutPickMemberVo> selectWorkOutPickByIdx(String mbr_idx);
	
	//페이징추가
	//자게
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM board WHERE mbr_idx=#{mbr_idx} ORDER BY brd_idx DESC) t1) t2 WHERE ((#{n_p}-1)*10)+1 <= t2.rnum AND t2.rnum <=(#{n_p})*10")
	public List<BoardVo> selectBoardByPaging(@Param("mbr_idx") String mbr_idx, @Param("n_p") String n_p);
	@Select("SELECT COUNT(*) FROM board WHERE mbr_idx=#{mbr_idx}")
	public String myBoardCount(String mbr_idx);
	
	//my_flex
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM My_flex WHERE mbr_idx=#{mbr_idx} ORDER BY flx_idx DESC) t1) t2 WHERE ((#{n_p}-1)*10)+1 <= t2.rnum AND t2.rnum <=(#{n_p})*10")
	public List<My_flexContentVo> selectMyFlexByPaging(@Param("mbr_idx") String mbr_idx, @Param("n_p") String n_p);
	@Select("SELECT COUNT(*) FROM My_flex WHERE mbr_idx=#{mbr_idx}")
	public String myMyFlexCount(String mbr_idx);
	
	//운동합시다
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM Work_out WHERE mbr_idx=#{mbr_idx} ORDER BY wrk_idx DESC) t1) t2 WHERE ((#{n_p}-1)*10)+1 <= t2.rnum AND t2.rnum <=(#{n_p})*10")
	public List<WorkOutVo> selectWorkOutByPaging(@Param("mbr_idx") String mbr_idx, @Param("n_p") String n_p);
	@Select("SELECT COUNT(*) FROM Work_out WHERE mbr_idx=#{mbr_idx}")
	public String myWorkOutCount(String mbr_idx);
	
	//자게 댓글
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM BOARD_REPLY WHERE mbr_idx=#{mbr_idx} ORDER BY brpl_idx DESC) t1) t2 WHERE ((#{n_p}-1)*10)+1 <= t2.rnum AND t2.rnum <=(#{n_p})*10")
	public List<BoardReplyVo> selectBoardReplyByPaging(@Param("mbr_idx") String mbr_idx, @Param("n_p") String n_p);
	@Select("SELECT COUNT(*) FROM BOARD_REPLY WHERE mbr_idx=#{mbr_idx}")
	public String myBoardReplyCount(String mbr_idx);
	
	//마이플렉스
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM MY_FLEX_REPLY WHERE mbr_idx = #{mbr_idx} ORDER BY frpl_idx DESC) t1) t2 WHERE ((#{n_p}-1)*10)+1 <= t2.rnum AND t2.rnum <=(#{n_p})*10")
	public List<My_flex_replyVo> selectMyFlexReplyByPaging(@Param("mbr_idx") String mbr_idx, @Param("n_p") String n_p);
	@Select("SELECT COUNT(*) FROM MY_FLEX_REPLY WHERE mbr_idx = #{mbr_idx}")
	public String myFlexReplyCount(String mbr_idx);
	
	//운동합시다
	@Select("SELECT * FROM (SELECT t1.*, ROWNUM rnum FROM (SELECT * FROM WORK_OUT_REPLY WHERE mbr_idx = #{mbr_idx} ORDER BY wrpl_idx DESC) t1) t2 WHERE ((#{n_p}-1)*10)+1 <= t2.rnum AND t2.rnum <= (#{n_p})*10")
	public List<WorkOutReplyVo> selectWorkOutReplyByPagin(@Param("mbr_idx") String mbr_idx, @Param("n_p") String n_p);
	@Select("SELECT COUNT(*) FROM WORK_OUT_REPLY WHERE mbr_idx = #{mbr_idx}")
	public String myWorkOutReplyCount(String mbr_idx);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
