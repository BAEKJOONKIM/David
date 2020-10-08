package com.youngbj.choongang.mapper;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import com.youngbj.choongang.vo.My_flex_like_memberVo;

public interface My_flex_like_memberSQLMapper {
	
	
	@Select("SELECT * FROM My_flex_like_member WHERE flx_idx=#{flx_idx} AND mbr_idx=#{mbr_idx}")
	public My_flex_like_memberVo likeselectByIdx(My_flex_like_memberVo vo);
	
	@Update("UPDATE My_flex_like_member SET flx_rcnt=flx_rcnt+1 WHERE flx_idx=#{flx_idx}")
	public void likeReadCount(String flx_idx);
	
	@Insert("INSERT INTO My_flex_like_member VALUES(#{flx_idx},#{mbr_idx})")
	public void insert(My_flex_like_memberVo vo);
	
	@Delete("DELETE FROM My_flex_like_member WHERE flx_idx=#{flx_idx}")
	public void likedeleteByIdx(String flx_idx);
	
	@Select("SELECT COUNT(*) FROM My_flex_like_member WHERE flx_idx=#{flx_idx} ")
	public String likecount(My_flex_like_memberVo vo);
	
	
	//@Update("UPDATE My_flex_like_member SET mbr_idx=mbr_idx+1 WHERE flx_idx=#{flx_idx}")
	//public void likeupdateReadCount(String flx_idx);
	
	
}
