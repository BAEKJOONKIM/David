package com.youngbj.choongang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.youngbj.choongang.vo.MemberVo;

public interface ManagerSQLMapper {

	// ---------------------manager기능---------------------

	// 최근 가입 멤버 리스트
	@Select("SELECT t1.*,ROWNUM FROM (SELECT * FROM member_info WHERE mbr_joindate > SYSDATE-1 ORDER BY mbr_idx DESC) t1 WHERE ROWNUM <=3")
	public List<MemberVo> selectRecentMemberList();

	// member_info에서 mbr_mngr속성에 true인 사람을 뽑아오기
	@Select("SELECT * FROM member_info WHERE mbr_mngr='T' AND mbr_id=#{mbr_id} AND mbr_pw=#{mbr_pw}")
	public MemberVo selectManager(MemberVo vo);

	// manager_page에 멤버에 관한 성별 정보 불러오기
	@Select("SELECT COUNT(*) FROM member_info")
	public int selectAllMember();

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_sex='F'")
	public int selectAllFemaleMember();

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_sex='M'")
	public int selectAllMaleMember();

	// manager_page에 멤버에 관한 신분 정보 불러오기
	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_sex='F' AND mbr_trnr='F' ")
	public int selectAllFemaleNormalMember();

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_sex='M' AND mbr_trnr='F' ")
	public int selectAllMaleNormalMember();

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_trnr='F' ")
	public int selectAllNormalMember();

	
	// put into blacklist
	@Insert("INSERT INTO blacklist VALUES(seq_blacklist.nextval, #{mbr_idx}, SYSDATE, SYSDATE+5)")
	public void putIntoBlacklist(String mbr_idx);
	
	//select from blacklist
	@Select("SELECT MI.mbr_id, MI.mbr_nick, MI.mbr_trnr, BL.bl_date, BL.bl_idx FROM Member_info MI, blacklist BL WHERE BL.mbr_idx= MI.mbr_idx")
	public List<HashMap<String, Object>> getListFromBlacklistByMbrIdx();

}




















