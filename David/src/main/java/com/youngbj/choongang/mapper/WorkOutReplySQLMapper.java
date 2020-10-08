package com.youngbj.choongang.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

public interface WorkOutReplySQLMapper {

	@Insert("INSERT INTO Work_out_reply VALUES(seq_work_out_reply.nextval,#{wrk_idx},#{mbr_idx},#{mbr_nick},#{wrpl_con},SYSDATE)")
	public void insertWorkOutReply(WorkOutReplyVo vo);
	
	@Select("SELECT * FROM Work_out_reply WHERE wrk_idx=#{wrk_idx}")
	public ArrayList<WorkOutReplyVo> selectByReply(String wrk_idx);
	
	@Delete("DELETE FROM Work_out_reply WHERE wrpl_idx=#{wrpl_idx}")
	public void deleteByReply(String wrpl_idx);
	
	@Update("UPDATE Work_out_reply SET wrpl_con=#{wrpl_con} WHERE wrpl_idx=#{wrpl_idx}")
	public void updateWorkOutReply(WorkOutVo vo);
	
	@Select("select * from work_out_reply, member_img where work_out_reply.mbr_idx = member_img.mbr_idx and wrk_idx =#{wrk_idx}")
	public List<HashMap<String, Object>> selectByReplyAndImg(String wrk_idx);
}
