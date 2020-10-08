package com.youngbj.choongang.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.WorkOutLikeMemberVo;

public interface WorkOutLikeMemberSQLMapper {
	
	@Select("SELECT * FROM Work_out_like_member WHERE wrk_idx=#{wrk_idx} AND mbr_idx=#{mbr_idx}")
	public WorkOutLikeMemberVo selectWorkOutLikeMember(WorkOutLikeMemberVo vo);
	
	@Select("SELECT * FROM Work_out_like_member WHERE wrk_idx=#{wrk_idx}")
	public ArrayList<WorkOutLikeMemberVo> selectWorkOutLikeByIdx(String wrk_idx);
	
	@Select("SELECT COUNT(*) FROM(SELECT * FROM work_out_like_member WHERE wrk_idx=#{wrk_idx})")
	public String selectCountWorkOutLike (String wrk_idx);
	
	@Insert("INSERT INTO Work_out_like_member VALUES(#{wrk_idx},#{mbr_idx})")
	public void insertWorkOutLikeMember(WorkOutLikeMemberVo vo);
	
	@Delete("DELETE FROM Work_out_like_member WHERE wrk_idx=#{wrk_idx} AND mbr_idx=#{mbr_idx}")
	public void deleteWorkOutLikeMember(WorkOutLikeMemberVo vo);
	
	@Update("UPDATE Work_out SET wrk_lcnt = wrk_lcnt+1 WHERE wrk_idx=#{wrk_idx}")
	public void updateLikeCountPlus(String wrk_idx);
	
	@Update("UPDATE Work_out SET wrk_lcnt = wrk_lcnt-1 WHERE wrk_idx=#{wrk_idx}")
	public void updateLikeCountMinus(String wrk_idx);
}
