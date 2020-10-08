package com.youngbj.choongang.service;

import com.youngbj.choongang.vo.WorkOutPickMemberVo;

public interface WorkOutPickMemberService {
	public WorkOutPickMemberVo isPickWorkOut(WorkOutPickMemberVo vo);

	public void pickWorkOut(WorkOutPickMemberVo vo);

	public void deletepickWorkOut(WorkOutPickMemberVo vo);
}
