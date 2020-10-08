package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youngbj.choongang.vo.TrainerVerificationVo;

@Service
public interface TrainerService {

	// 목록페이지에 전체 리스트 갖고 오기
	public List<HashMap<String, Object>> getTrnrApplicationList(String keyword, String SearchType, String r_num);

	// 자세히 보기 페이지에서 내용 보기
	public HashMap<String, Object> getTrainerApplicationDetail(String trn_idx);

	public TrainerVerificationVo getAplByMbrIdx(String mbr_idx);

	public void insertTrainerinfo(TrainerVerificationVo vo);
	
	public void putIntoWaiting(String mbr_idx);
	
	public void ConfirmTrnrApl(String trn_idx);
	public void updateTrainer(String mbr_idx);

	public void rejectTrnrApl(String mbr_idx);
	
//	public void reapplyTrainerApl(String mbr_idx);
	
//	public void defaultTrainerApl(String mbr_idx);
	
	public void deleteTrnrApl(String trn_idx);
	
	public void deleteTrainerAplAfterReturnToNormalMember(String mbr_idx);
	
	public void returnNormalMember(String mbr_idx);


}
