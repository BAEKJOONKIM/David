package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.My_flex_replyService;
import com.youngbj.choongang.vo.MemberVo;

import com.youngbj.choongang.vo.My_flex_replyVo;

@Service
public class My_flex_replyServiceImpl implements My_flex_replyService {

	@Autowired
	private com.youngbj.choongang.mapper.My_flex_replySQLMapper My_flex_replySQLMapper;
	@Autowired
	private MemberSQLMapper memberSQLMapper;

	@Override
	public List<HashMap<String, Object>> getContentList(String flx_idx) {
		// TODO Auto-generated method stub

		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String, Object>>();

		List<My_flex_replyVo> contentList = My_flex_replySQLMapper.selectByFLXIDX(flx_idx);

		for (My_flex_replyVo my_flex_replyVo : contentList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(my_flex_replyVo.getMbr_idx());

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("my_flex_replyVo", my_flex_replyVo);

			resultDataList.add(map);

		}
		return resultDataList;
	}

	@Override
	public void replywriteContent(My_flex_replyVo vo) {
		// TODO Auto-generated method stub
		My_flex_replySQLMapper.insert(vo);

	}

	@Override
	public HashMap<String, Object> getreplyContent(String frpl_idx) {
		// TODO Auto-generated method stub
		My_flex_replyVo my_flex_replyVo = My_flex_replySQLMapper.selectByIdx(frpl_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(my_flex_replyVo.getMbr_idx());

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVo", memberVo);
		map.put("my_flex_replyVo", my_flex_replyVo);

		return map;
	}

	@Override
	public void replyupdateContent(String frpl_idx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replydeleteContent(String frpl_idx) {
		// TODO Auto-generated method stub
		My_flex_replySQLMapper.deleteByIdx(frpl_idx);

	}

	@Override
	public String replycount(My_flex_replyVo vo) {
		// TODO Auto-generated method stub
		String likecount = My_flex_replySQLMapper.replycount(vo);

		return likecount;
	}

}
