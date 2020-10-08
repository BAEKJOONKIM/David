package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.WorkOutReplyVo;

public interface WorkOutReplyService {

	
	public void register(WorkOutReplyVo vo);
	
	public void deleteWorkOutReply(String wrpl_idx);
	
	public List<HashMap<String, Object>> replyAndImg(String wrk_idx);

}
