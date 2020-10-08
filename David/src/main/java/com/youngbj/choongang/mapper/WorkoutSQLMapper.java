package com.youngbj.choongang.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.WorkOutVo;



public interface WorkoutSQLMapper {	
	
	@Insert("INSERT INTO Work_out VALUES(seq_work_out.nextval,#{wrk_cat},#{mbr_idx},#{wrk_ttl},#{wrk_con},SYSDATE,#{wrk_surl},#{wrk_vurl},0,0)")
	public void insertWorkOut(WorkOutVo vo);
	
	@Select("SELECT * FROM Work_out ORDER BY wrk_idx DESC")
	public ArrayList<WorkOutVo> selectAll();
	
	@Select("SELECT COUNT(*) FROM(SELECT * FROM Work_out)")
	public String countWorkOut();
	
	@Select("SELECT * FROM Work_out WHERE wrk_idx=#{wrk_idx}")
	public WorkOutVo selectByIdx(WorkOutVo vo);
	
	@Select("SELECT * FROM Work_out WHERE wrk_ttl LIKE '%' || #{wrk_ttl} || '%'")
	public ArrayList<WorkOutVo> selectTitleSearch(String result); 
	
	@Select("SELECT * FROM Work_out WHERE wrk_con LIKE '%' || #{wrk_con} || '%'")
	public ArrayList<WorkOutVo> selectContentSearch(String result); 
	
	@Delete("DELETE FROM Work_out WHERE wrk_idx=#{wrk_idx}")
	public void deleteByIdx(String wrk_idx);
	
	@Update("UPDATE Work_out SET wrk_cat=#{wrk_cat}, wrk_ttl=#{wrk_ttl}, wrk_con=#{wrk_con}, wrk_surl=#{wrk_surl}, wrk_vurl=#{wrk_vurl} WHERE wrk_idx=#{wrk_idx}")
	public void updateWorkOut(WorkOutVo vo);
	
	@Update("UPDATE Work_out SET wrk_rcnt = wrk_rcnt+1 WHERE wrk_idx=#{wrk_idx}")
	public void updateReadCount(String wrk_idx);
	
	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM Work_out ORDER BY wrk_idx DESC) t1 ) t2 WHERE ((#{wrk_cp}-1)*6)+1 <=t2.rnum AND t2.rnum<=(#{wrk_cp}*6)")
	public ArrayList<WorkOutVo> selectPageNumber(String wrk_cp);
	
	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM Work_out WHERE wrk_ttl LIKE '%' || #{wrk_ttl} || '%'ORDER BY wrk_idx DESC) t1 ) t2 WHERE ((#{wrk_cp}-1)*6)+1 <=t2.rnum AND t2.rnum<=(#{wrk_cp}*6)")
	public ArrayList<WorkOutVo> selectSearchTitlePageNumber(@Param("wrk_ttl") String result, @Param("wrk_cp") String wrk_cp);
	
	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM Work_out WHERE wrk_con LIKE '%' || #{wrk_con} || '%'ORDER BY wrk_idx DESC) t1 ) t2 WHERE ((#{wrk_cp}-1)*6)+1 <=t2.rnum AND t2.rnum<=(#{wrk_cp}*6)")
	public ArrayList<WorkOutVo> selectSearchContentPageNumber(@Param("wrk_con") String result, @Param("wrk_cp") String wrk_cp);
	
	@Select("SELECT * FROM (SELECT T1.*,ROWNUM RNUM FROM(SELECT MEMBER_INFO.MBR_NICK, WORK_OUT.* FROM WORK_OUT, MEMBER_INFO WHERE WORK_OUT.MBR_IDX = MEMBER_INFO.MBR_IDX AND MBR_NICK LIKE '%' || #{MBR_NICK} || '%'ORDER BY WRK_IDX DESC) T1 ) T2 WHERE ((#{WRK_CP}-1)*6)+1 <=T2.RNUM AND T2.RNUM<=(#{WRK_CP}*6)")
	public List<HashMap<String, Object>> selectSearchUserPageNumber(@Param("MBR_NICK") String result, @Param("WRK_CP") String wrk_cp);
	
	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM Work_out WHERE wrk_con LIKE '%' || #{result} || '%' OR wrk_ttl LIKE '%' || #{result} || '%' ORDER BY wrk_idx DESC) t1 ) t2 WHERE ((#{wrk_cp}-1)*6)+1 <=t2.rnum AND t2.rnum<=(#{wrk_cp}*6)")
	public ArrayList<WorkOutVo> selectSearchTitleAndContentPageNumber(@Param("result") String result, @Param("wrk_cp") String wrk_cp);
	
	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM Work_out WHERE wrk_cat=#{wrk_cat} ORDER BY wrk_idx DESC) t1 ) t2 WHERE ((#{wrk_cp}-1)*6)+1 <=t2.rnum AND t2.rnum<=(#{wrk_cp}*6)")
	public ArrayList<WorkOutVo> selectByCatPage(@Param("wrk_cat") String wrk_cat, @Param("wrk_cp") String wrk_cp);
	
	@Select("SELECT * FROM Work_out WHERE wrk_cat=#{wrk_cat}")
	public ArrayList<WorkOutVo> selectByCat(String wrk_cat);
	
	@Select("SELECT * FROM work_out, member_info, work_out_reply WHERE work_out.mbr_idx = member_info.mbr_idx AND work_out.wrk_idx = work_out_reply.wrk_idx AND work_out_reply.wrk_idx =#{wrk_idx} AND work_out_reply.mbr_idx=#{mbr_idx}")
	public HashMap<String, Object> readWorkOutPage(@Param("wrk_idx") String wrk_idx, @Param("mbr_idx") String mbr_idx);
	

	
	//민은지 추가
		@Select("SELECT * FROM Work_out WHERE wrk_idx=#{wrk_idx}")
		public WorkOutVo selectWorkOutByIdx(String wrk_idx);
	
		
		
	
	
	
}
