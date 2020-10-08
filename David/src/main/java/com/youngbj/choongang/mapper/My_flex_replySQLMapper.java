package com.youngbj.choongang.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.My_flex_like_memberVo;
import com.youngbj.choongang.vo.My_flex_replyVo;

public interface My_flex_replySQLMapper {
	@Select("SELECT * FROM My_flex_reply WHERE flx_idx=#{flx_idx} ORDER BY frpl_idx DESC")
	public ArrayList<My_flex_replyVo> selectByFLXIDX(String flx_idx);
	
	@Select("SELECT * FROM My_flex_reply WHERE frpl_idx=#{frpl_idx}")
	public My_flex_replyVo selectByIdx(String frpl_idx);
	
	@Insert("INSERT INTO My_flex_reply VALUES(seq_my_flex_reply.nextval,#{flx_idx},#{mbr_idx},#{frpl_con},SYSDATE)")
	public void insert(My_flex_replyVo vo);
	
	@Delete("DELETE FROM My_flex_reply WHERE frpl_idx=#{frpl_idx}")
	public void deleteByIdx(String frpl_idx);
	
	@Select("SELECT COUNT(*) FROM My_flex_reply WHERE flx_idx=#{flx_idx} ")
	public String replycount(My_flex_replyVo replyvo);
	

}
