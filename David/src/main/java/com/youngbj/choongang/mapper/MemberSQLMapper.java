package com.youngbj.choongang.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.MemberImgVo;
import com.youngbj.choongang.vo.MemberVo;

public interface MemberSQLMapper { 

	//로그인
	@Select("SELECT * FROM Member_info WHERE mbr_id=#{mbr_id} AND mbr_pw=#{mbr_pw}")
	public MemberVo selectIdAndPw(MemberVo vo);

	//회원가입 
	//****mbr_vrf뺐음****
	@Insert("INSERT INTO Member_info VALUES (seq_member_info.nextval, #{mbr_id}, #{mbr_pw}, #{mbr_name}, #{mbr_nmbr}, #{mbr_emil}, #{mbr_nick}, #{mbr_sex}, #{mbr_bth}, 'F', 'F', SYSDATE)")
	public void insertMemberInfo(MemberVo vo);

	@Update("UPDATE Member_info SET mbr_nick=#{mbr_nick}, mbr_emil=#{mbr_emil} WHERE mbr_idx=#{mbr_idx}")
	public void modifyInfo(MemberVo vo);

	@Update("UPDATE Member_info SET mbr_pw=#{mbr_pw} WHERE mbr_idx=#{mbr_idx}")
	public void changePw(MemberVo vo);

	@Select("SELECT * FROM Member_info WHERE mbr_idx=#{mbr_idx} AND mbr_pw=#{mbr_pw}")
	public MemberVo selectIdxAndPw(MemberVo vo);



	//사진
	@Insert("INSERT INTO Member_img VALUES(#{mbr_idx}, #{mbri_url})")
	public void insertMemberImg(MemberImgVo vo);

	@Update("UPDATE Member_img SET mbri_url = #{mbri_url}")
	public void changeMemberImg(MemberImgVo vo);
	
	@Delete("DELETE FROM Member_img WHERE mbr_idx = ${mbr_idx}")
	public void deleteMemberImg(MemberImgVo vo);
	
	@Select("SELECT * FROM Member_img WHERE mbr_idx = #{mbr_idx}")
	public MemberImgVo selectMemberImgByIdx(String mbr_idx);
	
	@Select("SELECT * FROM Member_img WHERE mbri_url = #{mbri_url}")
	public MemberImgVo selectMemberImgByUrl(String mbri_url);
	

	
	//임소청
	//read_report_detail에서 필요
	@Select("SELECT * FROM Member_info WHERE mbr_idx=#{mbr_idx}")
	public MemberVo selectIdx(String mbr_idx);

	
	//양성민
		@Select("SELECT * FROM Member_info WHERE mbr_id=#{mbr_id}")
		public MemberVo selectConfirmId (String mbr_id); 

}
