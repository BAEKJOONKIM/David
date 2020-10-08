package com.youngbj.choongang.service;

import java.util.ArrayList;

import com.youngbj.choongang.vo.WorkOutLikeMemberVo;

public interface WorkOutLikeMemberService {

	public WorkOutLikeMemberVo isLikeWorkOut(WorkOutLikeMemberVo vo);
	
	public void likeWorkOut(WorkOutLikeMemberVo vo);
	
	public void deleteLikeWorkOut(WorkOutLikeMemberVo vo);
	
	public String countLikeWorkOut(String wrk_idx);
	
	public void updateLikePuls(String wrk_idx);
	public void updateLikeMinus(String wrk_idx);
}
