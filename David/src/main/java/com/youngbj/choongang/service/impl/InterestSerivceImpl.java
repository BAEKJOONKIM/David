package com.youngbj.choongang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.InterestSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.InterestService;
import com.youngbj.choongang.vo.InterestVo;

@Service
public class InterestSerivceImpl implements InterestService {

	@Autowired
	InterestSQLMapper interestSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	
	@Override
	public InterestVo getInterest(InterestVo vo) {
		// TODO Auto-generated method stub
		InterestVo interestVo = new InterestVo();
		
		System.out.println("ȸ��" + vo.getMbr_idx());
		System.out.println("��ī" + vo.getWrk_cat());
		
		interestVo.setMbr_idx(vo.getMbr_idx());
		interestVo.setWrk_cat(vo.getWrk_cat());
		
		InterestVo interest = interestSQLMapper.selectIdx(interestVo);
		
		return interest;
	}

	@Override
	public void insertInterest(InterestVo vo) {
		// TODO Auto-generated method stub
		interestSQLMapper.insertInterest(vo);
	}

	@Override
	public void updateInterest(String mbr_idx) {
		// TODO Auto-generated method stub
		interestSQLMapper.updateInterest(mbr_idx);
	}

	@Override
	public void deleteInterest(InterestVo vo) {
		// TODO Auto-generated method stub
		interestSQLMapper.deleteInterest(vo);
	}

	@Override
	public List<InterestVo> listInterest(String mbr_idx) {
		// TODO Auto-generated method stub
		List<InterestVo> result = interestSQLMapper.listByIdx(mbr_idx);
		return result;
	}


}
