package com.youngbj.choongang.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.WorkOutPickMemberVo;

public interface WorkOutPickMemberSQLMapper {

	@Select("SELECT * FROM Work_out_pick_member WHERE wrk_idx=#{wrk_idx} AND mbr_idx=#{mbr_idx}")
	public WorkOutPickMemberVo selectWorkOutPickMember(WorkOutPickMemberVo vo);
	
	@Insert("INSERT INTO Work_out_pick_member VALUES(#{wrk_idx},#{mbr_idx})")
	public void insertWorkOutPickMember(WorkOutPickMemberVo vo);
	
	@Delete("DELETE FROM Work_out_pick_member WHERE wrk_idx=#{wrk_idx} AND mbr_idx=#{mbr_idx}")
	public void deleteWorkOutPickMember(WorkOutPickMemberVo vo);
}
