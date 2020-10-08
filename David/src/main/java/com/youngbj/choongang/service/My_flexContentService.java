package com.youngbj.choongang.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_replyVo;

@Service
public interface My_flexContentService {

	public List<HashMap<String, Object>> getContentList();

	public List<HashMap<String, Object>> getsearchList(String searchType, String searchData);

	public void writeContent(My_flexContentVo vo);

	public void deleteContent(String flx_idx);

	public HashMap<String, Object> getContent(String flx_idx);

	public void updateReadCount(String flx_idx);

	public void updateContent(My_flexContentVo vo);

	public void replywriteContent(My_flex_replyVo vo);

	public void replydeleteContent(String frpl_idx);

	public List<HashMap<String, Object>> page(String flx_page);

	public HashMap<String, Object> getreplyContent(String frpl_idx);

	public void replyupdateContent(My_flex_replyVo vo);

}
