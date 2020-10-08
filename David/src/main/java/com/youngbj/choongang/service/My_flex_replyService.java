package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import com.youngbj.choongang.vo.My_flex_replyVo;

public interface My_flex_replyService {
public List<HashMap<String,Object>> getContentList(String flx_idx);
	
	public void replywriteContent(My_flex_replyVo vo);
	
	public void replydeleteContent(String frpl_idx);
	
	public HashMap<String,Object> getreplyContent(String frpl_idx);
	
	public String replycount(My_flex_replyVo replyvo);
	
	public void replyupdateContent(String frpl_idx);

}
