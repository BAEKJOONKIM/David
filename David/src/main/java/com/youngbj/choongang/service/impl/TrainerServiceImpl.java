package com.youngbj.choongang.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.TrainerSQLMapper;
import com.youngbj.choongang.service.TrainerService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.TrainerVerificationVo;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Autowired
	TrainerSQLMapper trainerSQLMapper;

	// 임소청

	// 크레이너 신청정보 자세히 보기
	@Override
	public HashMap<String, Object> getTrainerApplicationDetail(String trn_idx) {

		//trainer정보 가져오기
		HashMap<String, Object> SingleTrnrAplFile = trainerSQLMapper.selectSingleTrnrAplFile(trn_idx);
		
		return SingleTrnrAplFile;
	}

	// 매니저패이지에 트레이너신청리스트 확인하기
	@Override
	public List<HashMap<String, Object>> getTrnrApplicationList(String keyword, String SearchType,String r_num) {

		if(r_num == null) {
			r_num="1";
		}
		
		// 한사람이 몇번때 신청하는 지 가져오기
		List<HashMap<String, Object>> result = null;

		//검색
		if (keyword != null) {
			if (SearchType.equals("mbr_nick")) {
				result = trainerSQLMapper.selectAllApplicationInfoSearchMbrIdx(keyword);

			} else if (SearchType.equals("mbr_vrf")) {

				//수정
				// 검색이 지금 완전한 단어를 쳐야한다.....
				//keyword = keyword.replace("승인", "C");
				//keyword = keyword.replace("거절", "R");
				//keyword = keyword.replace("대기", "W");
				// keyword = keyword.replace("재신청", "RA");

				result = trainerSQLMapper.selectAllApplicationInfoSearchMbrVrf(keyword);
			}
		} else {
			result = trainerSQLMapper.selectAllApplicationInfo(r_num);
		}

		//몇 번째 재신청인지 확인하기 위한 count세기
		for (HashMap<String, Object> vo : result) {
			int mbr_idx = ((BigDecimal) (vo.get("MBR_IDX"))).intValue();
			int count = trainerSQLMapper.AplCountByMbrIdx(mbr_idx + "");
			vo.put("APL_COUNT", count);
		}
		return result;
	}

	// trainer신청 거절
	@Override
	public void rejectTrnrApl(String mbr_idx) {
		trainerSQLMapper.updateTrainerReject(mbr_idx);
	}

	// trainer신청승인
	@Override
	public void ConfirmTrnrApl(String mbr_idx) {
		trainerSQLMapper.updateTrainerConfirm(mbr_idx);
	}
	
	@Override
	public void updateTrainer(String mbr_idx) {
		trainerSQLMapper.updateTrainer(mbr_idx);	
	}

	// trainer신청 정보 저장
	@Override
	public void insertTrainerinfo(TrainerVerificationVo vo) {
		trainerSQLMapper.insertTrainerInfo(vo);
	}

	// trainer신청 처리대기
	@Override
	public void putIntoWaiting(String mbr_idx) {
		trainerSQLMapper.updateTrainerWaiting(mbr_idx);
	}

	// 거절 당한 신청자한테 재신청시키기?????????
//	@Override
//	public void reapplyTrainerApl(String mbr_idx) {
//		trainerSQLMapper.updateTrainerReapply(mbr_idx);
//	}

	// 신청내역 삭제
	@Override
	public void deleteTrnrApl(String trn_idx) {
		trainerSQLMapper.deleteTrnrApl(trn_idx);
	}

	// 일반회원으로 전환할때
	@Override
	public void returnNormalMember(String trn_idx) {
		trainerSQLMapper.updateTrainerToNormalMember(trn_idx);
	}

	//신청취소할때
//	@Override
//	public void defaultTrainerApl(String mbr_idx) {
//		trainerSQLMapper.updateTrainerDefault(mbr_idx);
//	}

	
	@Override
	public TrainerVerificationVo getAplByMbrIdx(String mbr_idx) {
		TrainerVerificationVo vo = trainerSQLMapper.selectTrainerApplicationFileByMbrIdx(mbr_idx);
	return vo;
	}

	//
	@Override
	public void deleteTrainerAplAfterReturnToNormalMember(String mbr_idx) {
		trainerSQLMapper.deleteTrainerAplAfterReturnToNormalMember(mbr_idx);
		
	}



}
