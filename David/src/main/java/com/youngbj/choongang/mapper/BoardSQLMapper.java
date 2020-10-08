package com.youngbj.choongang.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.BoardVo;

public interface BoardSQLMapper {

	@Select("SELECT * FROM Board ORDER BY brd_idx DESC")
	public List<BoardVo> selectAll();

	@Select("SELECT * FROM Board WHERE brd_idx=#{brd_idx}")
	public BoardVo selectByIdx(String brd_idx);

	@Insert("INSERT INTO Board VALUES(seq_board.nextval,#{mbr_idx},#{brd_ttl},#{brd_con},SYSDATE,'sd',0,0)")
	public void insertContent(BoardVo vo);

	@Update("UPDATE Board SET brd_rcnt=brd_rcnt+1 WHERE brd_idx=#{brd_idx}")
	public void updateReadCount(String brd_idx);
	
	@Update("UPDATE Board SET brd_lcnt=brd_lcnt+1 WHERE brd_idx=#{brd_idx}")
	public void updateLikeCount(String brd_idx);
	
	@Update("UPDATE Board SET brd_ttl=#{brd_ttl}, brd_con=#{brd_con} WHERE brd_idx=#{brd_idx}")
	public void updateContent(BoardVo vo);
	
	@Delete("DELETE FROM Board WHERE brd_idx=#{brd_idx}")
	public void deleteContent(BoardVo vo);
	
	@Select("SELECT * FROM Board WHERE brd_ttl LIKE '%' || #{search} || '%' OR brd_con LIKE '%' || #{search} || '%' ORDER BY brd_idx ASC")
	public ArrayList<BoardVo> selectBoardSearch(String search);
	
	//@Select("SELECT * FROM Board B, member_info MI WHERE B.mbr_idx=MI.mbr_idx AND b.brd_ttl LIKE '%'||#{search}||'%' OR B.brd_con LIKE '%'||#{search}||'%' OR MI.mbr_nick LIKE '%'||#{search}||'%' ORDER BY B.brd_idx DESC")
	//public List<HashMap<String, Object>> selectBoardSearch(String search);

}
