package com.youngbj.choongang.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.BoardReplyVo;

public interface BoardReplySQLMapper {
	
	@Select("SELECT * FROM Board_reply WHERE brd_idx=#{brd_idx} ORDER BY brpl_idx ASC")
	public List<BoardReplyVo> selectReplyAll(String brd_idx);
	
	@Select("SELECT * FROM Board_reply WHERE brpl_idx=#{brpl_idx}")
	public BoardReplyVo selectByBrdIdx(String brpl_idx);
	
	@Insert("INSERT INTO Board_reply VALUES(seq_board_reply.nextval,#{brd_idx},#{mbr_idx},#{brpl_con},SYSDATE)")
	public void  boardWriteReply(BoardReplyVo vo);
	
	@Delete("DELETE FROM Board_reply WHERE brpl_idx=#{brpl_idx}")
	public void  boardDeleteReply(BoardReplyVo vo);
	
	@Update("UPDATE Board_reply SET  brpl_con=#{brpl_con} WHERE brpl_idx=#{brpl_idx}")
	public void boardUpdateReply(BoardReplyVo vo);
	
	
	

}
