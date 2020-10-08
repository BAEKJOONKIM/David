package com.youngbj.choongang.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.TrainerVerificationVo;

public interface TrainerSQLMapper {

// --------------------------신청글---------------------------

	// trn_idx로 신청내역을 불러오기
	@Select("SELECT * FROM trainer_verification WHERE trn_idx=#{trn_idx}")
	public TrainerVerificationVo selectTrainerApplicationFileByTrnIdx(String trn_idx);

	// mbr_idx로 최신신청내역을 불러오기
	@Select("SELECT * FROM trainer_verification WHERE trn_idx = (SELECT MAX(trn_idx) FROM trainer_verification WHERE mbr_idx=#{mbr_idx})")
	public TrainerVerificationVo selectTrainerApplicationFileByMbrIdx(String mbr_idx);

	@Select("SELECT * FROM trainer_verification, member_info WHERE member_info.mbr_idx = trainer_verification.mbr_idx AND trn_idx=#{trn_idx}")
	public HashMap<String, Object> selectSingleTrnrAplFile(String trn_idx);

	// 동일 회원이 신청한 모든 내역
	@Select( "SELECT * FROM trainer_verification, member_info WHERE member_info.mbr_idx = trainer_verification.mbr_idx AND mbr_idx=#{mbr_idx}")
	public List<HashMap<String, Object>> selectSingleTrnrAplInfoAll(String mbr_idx);

	// 모든 신청글 불러오기
	@Select("SELECT T3.* FROM (SELECT T2.*, ROWNUM R_NUM FROM (SELECT TV.MBR_VRF, MI.MBR_NICK, TV.TRN_VDAT, TV.TRN_IDX, MI.MBR_IDX FROM trainer_verification TV, member_info MI WHERE MI.mbr_idx = TV.mbr_idx ORDER BY TV.trn_idx DESC) T2) T3 WHERE T3.R_NUM >=(#{r_num}-1)*5+1 AND T3.R_NUM<=(#{r_num}*5)")
	public List<HashMap<String, Object>> selectAllApplicationInfo(String r_num);

	// 트레이너 승급요청 파일 저장
	@Insert("INSERT INTO Trainer_verification VALUES(seq_trainer_verification.nextval, #{mbr_idx}, '대기', SYSDATE, #{trn_turl})")
	public void insertTrainerInfo(TrainerVerificationVo vo);

	//트레이너 탈탈퇴할때 회원의 모든 신청글 삭제
	@Delete("DELETE FROM Trainer_verification WHERE mbr_idx=#{mbr_idx}")
	public void deleteTrainerAplAfterReturnToNormalMember(String mbr_idx);
	
	//지정된 글 삭체
	@Delete("DELETE FROM Trainer_verification WHERE trn_idx=#{trn_idx}")
	public void deleteTrnrApl(String trn_idx);

	// 재신청할때 count가 2번넘어가면 재신청인 것이다
	@Select("SELECT COUNT(*) FROM Trainer_verification WHERE mbr_idx=#{mbr_idx}")
	public int AplCountByMbrIdx(String mbr_idx);

//------------------------------------------------ 검색 ------------------------------------------------
	// mbr_nick으로 검색하면
	@Select("SELECT * FROM trainer_verification TV, member_info MI WHERE MI.mbr_idx = TV.mbr_idx AND MI.mbr_nick LIKE '%'||#{keyword}||'%' ORDER BY TV.trn_idx DESC")
	public List<HashMap<String, Object>> selectAllApplicationInfoSearchMbrIdx(String keyword);

	// mbr_vrf으로 검색하면
	@Select("SELECT * FROM trainer_verification TV, member_info MI WHERE MI.mbr_idx = TV.mbr_idx AND TV.mbr_vrf LIKE '%'||#{keyword}||'%' ORDER BY TV.trn_idx DESC")
	public List<HashMap<String, Object>> selectAllApplicationInfoSearchMbrVrf(String keyword);

//------------------------------------------------트레이너회원관리--------------------------------------------

	// trainer인증하려하는 사람을 뽑아오기
//	@Select("SELECT * FROM member_info WHERE mbr_idx=#{mbr_idx} AND mbr_vrf='W'")
//	public MemberVo selectTrainerToBeByIdx(String mbr_idx);

//-------------------------------------------------- mbr_trnr 상태 바꾸기--------------------------------------------------
	@Update("UPDATE trainer_verification SET mbr_vrf='대기' WHERE mbr_idx=#{mbr_idx}")
	public void updateTrainerWaiting(String mbr_idx);

	@Update("UPDATE trainer_verification SET mbr_vrf='거절' WHERE mbr_idx=#{mbr_idx}")
	public void updateTrainerReject(String mbr_idx);

	@Update("UPDATE trainer_verification SET mbr_vrf='승인' WHERE trn_idx=#{trn_idx}")
	public void updateTrainerConfirm(String trn_idx);

	@Update("UPDATE member_info SET mbr_trnr='T' WHERE mbr_idx=#{mbr_idx}")
	public void updateTrainer(String mbr_idx);

	// 신청취소할때-- 신청내역을 지우기만 하면 끝
//	@Update("UPDATE trainer_verification SET mbr_vrf='N' WHERE mbr_idx=#{mbr_idx}")
//	public void updateTrainerDefault(String mbr_idx);

	// 일반회원으로 전환할때
	@Update("UPDATE member_info SET mbr_trnr='F' WHERE mbr_idx=#{mbr_idx}")
	public void updateTrainerToNormalMember(String mbr_idx);

//-------------------------- trainer뽑아오기--------------------------
	@Select("SELECT * FROM member_info WHERE mbr_id=#{mbr_id} AND mbr_pw=#{mbr_pw} AND mbr_trnr='T'")
	public MemberVo selectTrainer(MemberVo vo);

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_sex='F' AND mbr_trnr='T' ")
	public int selectAllFemaleTrainer();

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_sex='M' AND mbr_trnr='T' ")
	public int selectAllMaleTrainer();

	@Select("SELECT COUNT(*) FROM member_info WHERE mbr_trnr='T'")
	public int selectAllTrainer();

}
