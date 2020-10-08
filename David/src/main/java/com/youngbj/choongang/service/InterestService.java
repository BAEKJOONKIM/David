package com.youngbj.choongang.service;

import java.util.List;

import com.youngbj.choongang.vo.InterestVo;

public interface InterestService {
	
	public InterestVo getInterest(InterestVo vo);
	public void insertInterest(InterestVo vo);
	public void updateInterest(String mbr_Idx);
	
	//추가
	public void deleteInterest(InterestVo vo);
	public List<InterestVo> listInterest(String mbr_idx);
	

}
