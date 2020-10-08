package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.etc.CharacterEncoding;
import com.youngbj.choongang.mapper.BoardLikeSQLMapper;
import com.youngbj.choongang.mapper.BoardSQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.service.BoardService;
import com.youngbj.choongang.vo.BoardLikeVo;
import com.youngbj.choongang.vo.BoardVo;
import com.youngbj.choongang.vo.MemberVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardSQLMapper boardSQLMapper;
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	@Autowired
	private BoardLikeSQLMapper boardLikeSQLMapper;

	@Override
	public List<HashMap<String, Object>> getBoardList() {
		// TODO Auto-generated method stub

		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String, Object>>();

		List<BoardVo> boardList = boardSQLMapper.selectAll();

		for (BoardVo boardVo : boardList) {

			MemberVo memberVo = memberSQLMapper.selectIdx(boardVo.getMbr_idx());
			HashMap<String, Object> map = new HashMap<String, Object>();

			BoardLikeVo vo = new BoardLikeVo();
			vo.setBrd_idx(boardVo.getBrd_idx());
			String voLike = boardLikeSQLMapper.countBoardLike(vo);

			map.put("memberVo", memberVo);
			map.put("boardVo", boardVo);
			map.put("voLike", voLike);

			resultDataList.add(map);
		}

		return resultDataList;
	}

	@Override
	public HashMap<String, Object> getContent(String brd_idx) {
		// TODO Auto-generated method stub

		HashMap<String, Object> map = new HashMap<String, Object>();
		BoardVo boardVo = boardSQLMapper.selectByIdx(brd_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(boardVo.getMbr_idx());

		String text = CharacterEncoding.encode(boardVo.getBrd_con());
		boardVo.setBrd_con(text);

		map.put("memberVo", memberVo);
		map.put("boardVo", boardVo);

		return map;

	}

	@Override
	public void insertContent(BoardVo vo) {
		// TODO Auto-generated method stub
		boardSQLMapper.insertContent(vo);

	}

	@Override
	public void updateReadCount(String brd_idx) {
		// TODO Auto-generated method stub
		boardSQLMapper.updateReadCount(brd_idx);
	}

	@Override
	public void updateLikeCount(String brd_idx) {
		// TODO Auto-generated method stub
		boardSQLMapper.updateLikeCount(brd_idx);

	}

	@Override
	public void deleteContent(BoardVo vo) {
		// TODO Auto-generated method stub
		boardSQLMapper.deleteContent(vo);

	}

	@Override
	public void updateContent(BoardVo vo) {
		// TODO Auto-generated method stub
		boardSQLMapper.updateContent(vo);

	}

	@Override
	public List<HashMap<String, Object>> getBoardListBySearch(String search) {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> listBySearch = new ArrayList<HashMap<String, Object>>();
		ArrayList<BoardVo> boardList = boardSQLMapper.selectBoardSearch(search);

		for (BoardVo board : boardList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			MemberVo memberVo = memberSQLMapper.selectIdx(board.getBrd_idx());

			BoardLikeVo vo = new BoardLikeVo();
			vo.setBrd_idx(board.getBrd_idx());
			String voLike = boardLikeSQLMapper.countBoardLike(vo);

			map.put("memberVo", memberVo);
			map.put("boardVo", board);
			map.put("voLike", voLike);

			listBySearch.add(map);

			// List<HashMap<String, Object>> listBySearch =
			// boardSQLMapper.selectBoardSearch(search);

		}
		return listBySearch;
	}
}
