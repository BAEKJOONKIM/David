package com.youngbj.choongang.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_replyVo;
import com.youngbj.choongang.vo.WorkOutVo;

public interface My_flexContentSQLMapper {
	@Select("SELECT * FROM My_flex WHERE flx_ttl LIKE '%' || #{searchData} || '%'")
	public ArrayList<My_flexContentVo> searchselecttitleAll(String searchData);

	@Select("SELECT * FROM My_flex WHERE flx_con LIKE '%' || #{searchData} || '%'")
	public ArrayList<My_flexContentVo> searchselectcontentAll(String searchData);

	@Select("SELECT * FROM My_flex, member_info WHERE member_info.mbr_idx = My_flex.mbr_idx AND mbr_nick LIKE '%' || #{searchData} || '%'")
	public ArrayList<My_flexContentVo> searchselectnickAll(String searchData);

	@Select("SELECT * FROM My_flex ORDER BY flx_idx DESC")
	public ArrayList<My_flexContentVo> selectAll();

	@Select("SELECT * FROM My_flex WHERE flx_idx=#{flx_idx}")
	public My_flexContentVo selectByIdx(String flx_idx);

	@Update("UPDATE My_flex SET flx_rcnt=flx_rcnt+1 WHERE flx_idx=#{flx_idx}")
	public void updateReadCount(String flx_idx);

	@Insert("INSERT INTO My_flex VALUES(seq_my_flex.nextval,#{mbr_idx},#{flx_ttl},#{flx_con},SYSDATE,#{flx_iurl},#{flx_vurl},1,1)")
	public void insert(My_flexContentVo vo);

	@Delete("DELETE FROM My_flex WHERE flx_idx=#{flx_idx}")
	public void deleteByIdx(String flx_idx);

	@Update("UPDATE My_flex SET flx_ttl=#{flx_ttl}, flx_con=#{flx_con}, flx_iurl=#{flx_iurl}, flx_vurl=#{flx_vurl} WHERE flx_idx=#{flx_idx}")
	public void updateContent(My_flexContentVo vo);

	@Select("SELECT t2.flx_idx FROM (SELECT t1.*,ROWNUM rnum FROM (SELECT * FROM My_flex ORDER BY flx_idx DESC) t1) t2 WHERE t2.rnum = (SELECT rnum FROM (SELECT t1.*,ROWNUM rnum FROM (SELECT * FROM My_flex ORDER BY flx_idx DESC) t1) t2 WHERE flx_idx=#{flx_idx})+1")
	public String prnum(String flx_idx);

	@Select("SELECT t2.flx_idx FROM (SELECT t1.*,ROWNUM rnum FROM (SELECT * FROM My_flex ORDER BY flx_idx DESC) t1) t2 WHERE t2.rnum = (SELECT rnum FROM (SELECT t1.*,ROWNUM rnum FROM (SELECT * FROM My_flex ORDER BY flx_idx DESC) t1) t2 WHERE flx_idx=#{flx_idx})-1")
	public String mrnum(String flx_idx);

	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM (SELECT * FROM My_flex ORDER BY flx_idx DESC) t1) t2 WHERE ((#{flx_page}-1)*8)+1 <= t2.rnum AND t2.rnum <= (#{flx_page}*8)")
	public ArrayList<My_flexContentVo> page(String flx_page);
	
	//--------------------------------------------------------------------------------------------
//	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM My_flex WHERE flx_ttl LIKE '%' || #{flx_ttl} || '%'ORDER BY flx_idx DESC) t1 ) t2 WHERE ((#{flx_page}-1)*8)+1 <=t2.rnum AND t2.rnum<=(#{flx_page}*8)")
//	public ArrayList<My_flexContentVo> SearchTitlePage(@Param("flx_ttl") String flx_idx, @Param("flx_page") String flx_page);
//	
//	@Select("SELECT * FROM (SELECT t1.*,ROWNUM rnum FROM(SELECT * FROM My_flex WHERE flx_con LIKE '%' || #{flx_con} || '%'ORDER BY flx_idx DESC) t1 ) t2 WHERE ((#{flx_page}-1)*8)+1 <=t2.rnum AND t2.rnum<=(#{flx_page}*8)")
//	public ArrayList<My_flexContentVo> SearchContentPage(@Param("flx_con") String flx_idx, @Param("flx_page") String flx_page);
//	
//	@Select("SELECT * FROM (SELECT T1.*,ROWNUM RNUM FROM(SELECT MEMBER_INFO.MBR_NICK, My_flex.* FROM My_flex, MEMBER_INFO WHERE My_flex.MBR_IDX = MEMBER_INFO.MBR_IDX AND MBR_NICK LIKE '%' || #{MBR_NICK} || '%'ORDER BY WRK_IDX DESC) T1 ) T2 WHERE ((#{flx_page}-1)*8)+1 <=T2.RNUM AND T2.RNUM<=(#{flx_page}*8)")
//	public List<HashMap<String, Object>> SearchUserPage(@Param("MBR_NICK") String flx_idx, @Param("flx_page") String flx_page);
	//---------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	

}
