package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.BoardReplySQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.BoardReplyService;
import com.youngbj.choongang.vo.BoardReplyVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {

	@Autowired
	BoardReplySQLMapper boardReplySQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;

	@Override
	public List<HashMap<String, Object>> getBoardReplyList(String brd_idx, String mbr_idx) {
		// TODO Auto-generated method stub

		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String, Object>>();
		List<BoardReplyVo> boardReplyList = boardReplySQLMapper.selectReplyAll(brd_idx);
		// MemberVo vo=(MemberVo)session.getAttribute("sessionUserData");

		for (BoardReplyVo boardReplyVo : boardReplyList) {

			MemberVo memberVo = memberSQLMapper.selectIdx(boardReplyVo.getMbr_idx());

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("boardReplyVo", boardReplyVo);

			if (mbr_idx != null) {
				if (mbr_idx.equals(boardReplyVo.getMbr_idx())) {

					map.put("sessionUserData", "true");
				}else {
					map.put("sessionUserData", "false");
				}
			}else {
				map.put("sessionUserData", "false");
			}

			resultDataList.add(map);
		}
		return resultDataList;
	}

	@Override
	public HashMap<String, Object> getBoardReply(String brpl_idx) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();

		BoardReplyVo boardReplyVo = boardReplySQLMapper.selectByBrdIdx(brpl_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(boardReplyVo.getBrd_idx());

		map.put("memberVo", memberVo);
		map.put("boardReplyVo", boardReplyVo);

		return map;
	}

	@Override
	public void boardWriteReply(BoardReplyVo vo) {
		// TODO Auto-generated method stub
		boardReplySQLMapper.boardWriteReply(vo);
	}

	@Override
	public void boardDeleteReply(BoardReplyVo vo) {
		// TODO Auto-generated method stub
		boardReplySQLMapper.boardDeleteReply(vo);
	}

	@Override
	public void boardUpdateReply(BoardReplyVo vo) {
		// TODO Auto-generated method stub
		boardReplySQLMapper.boardUpdateReply(vo);

	}

}
