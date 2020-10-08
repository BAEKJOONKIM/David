package com.youngbj.choongang.mapper;

import java.util.ArrayList;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.BoardLikeVo;
public interface BoardLikeSQLMapper {
	
	@Insert("INSERT INTO Board_like_count_member VALUES(#{brd_idx},#{mbr_idx})")
	public void insertBoardLike(BoardLikeVo vo);

	@Select("SELECT * FROM Board_like_count_member WHERE brd_idx=#{brd_idx}")
	public ArrayList<BoardLikeVo> selectLikeByBrdidx(String brd_idx);
	
	@Select("SELECT * FROM Board_like_count_member WHERE mbr_idx=#{mbr_idx} AND brd_idx=#{brd_idx}")
	public BoardLikeVo selectByMbrANDBrdidx(BoardLikeVo vo);
	
	@Delete("DELETE Board_like_count_member WHERE brd_idx=#{brd_idx} AND mbr_idx=#{mbr_idx}")
	public void deleteByMbrANDBrdidx(BoardLikeVo vo);
	
	@Select("SELECT COUNT(*) FROM(SELECT * FROM Board_like_count_member WHERE brd_idx=#{brd_idx})")
	public String countBoardLike(BoardLikeVo vo);
}
