package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.ManagerSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.TrainerSQLMapper;
import com.youngbj.choongang.service.ManagerService;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class ManagerServiceImpl implements ManagerService {

	
	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Autowired
	TrainerSQLMapper trainerSQLMapper;
	
	@Autowired
	ManagerSQLMapper managerSQLMapper;
	
	
	// 임소청
		@Override
		public MemberVo ManagerLogin(MemberVo vo) {
			
			MemberVo result = managerSQLMapper.selectManager(vo);
			
			return result;
		}

	//*********MAPPER에서 List<HashMap<String, Object>>로 바꾸기*******
		@Override
		public List<HashMap<String, Object>> getMemberListLatest() {

			List<MemberVo> memberList = managerSQLMapper.selectRecentMemberList();

			List<HashMap<String, Object>> newMemberList = new ArrayList<HashMap<String, Object>>();

			for (MemberVo membervo : memberList) {
				String newMemberNick = memberSQLMapper.selectIdx(membervo.getMbr_idx()).getMbr_nick();
				MemberVo memberVo = memberSQLMapper.selectIdx(membervo.getMbr_idx());
				
				String newMemberIdentity = null;

				if(membervo.getMbr_trnr().equals("T")) {
					newMemberIdentity = "트레이너";
				}else {
					newMemberIdentity = "일반회원";
				}
				
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newMemberNick", newMemberNick);
				map.put("newMemberIdentity", newMemberIdentity);
				map.put("memberVo", memberVo);

				newMemberList.add(map);

			}
			return newMemberList;
		}

		
		@Override
		public HashMap<String, Object> getMemberNumList(MemberVo vo) {
			
			HashMap<String, Object> NumMember = new HashMap<String, Object>();
			
			int memberAllNum = managerSQLMapper.selectAllMember();
			int memberFNum = managerSQLMapper.selectAllFemaleMember();
			int memberMNum = managerSQLMapper.selectAllMaleMember();
			
			NumMember.put("memberAllNum", memberAllNum);
			NumMember.put("memberFNum", memberFNum);
			NumMember.put("memberMNum", memberMNum);
			
			int memberNormalAll = managerSQLMapper.selectAllNormalMember();
			int memberNormalF = managerSQLMapper.selectAllFemaleNormalMember();
			int memberNormalM = managerSQLMapper.selectAllMaleNormalMember();
			NumMember.put("memberNF", memberNormalF);
			NumMember.put("memberNM", memberNormalM);
			NumMember.put("memberNAll", memberNormalAll);
			
			int memberTrainerAll = trainerSQLMapper.selectAllTrainer();
			int memberTrainerF = trainerSQLMapper.selectAllFemaleTrainer();
			int memberTrainerM = trainerSQLMapper.selectAllMaleTrainer();
			NumMember.put("memberTF", memberTrainerF);
			NumMember.put("memberTM", memberTrainerM);
			NumMember.put("memberTAll", memberTrainerAll);
		
			return NumMember;
		}


		@Override
		public void putIntoBlacklist(String mbr_idx) {
			managerSQLMapper.putIntoBlacklist(mbr_idx);

		}

		//추가
		@Override
		public List<HashMap<String, Object>> getListFromBlacklist() {
			
			return managerSQLMapper.getListFromBlacklistByMbrIdx();
		}

	
	
	
}
