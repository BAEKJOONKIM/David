package com.youngbj.choongang.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.BoardLikeSQLMapper;
import com.youngbj.choongang.service.BoardLikeService;
import com.youngbj.choongang.vo.BoardLikeVo;

@Service
public class BoardLikeServiceImpl implements BoardLikeService{
	
	@Autowired
	BoardLikeSQLMapper boardLikeSQLMapper;

	@Override
	public void insertBoardLike(BoardLikeVo vo) {
		// TODO Auto-generated method stub
		boardLikeSQLMapper.insertBoardLike(vo);
	}

	@Override
	public HashMap<String, Object> getBoardLike(String brd_idx) {
		// TODO Auto-generated method stub	
		return null;
	}

	@Override
	public BoardLikeVo selectByMbrANDBrdidx(BoardLikeVo vo) {
		// TODO Auto-generated method stub
		
		BoardLikeVo result = boardLikeSQLMapper.selectByMbrANDBrdidx(vo);
		
		return result;
	}

	@Override
	public void deleteByMbrANDBrdidx(BoardLikeVo vo) {
		// TODO Auto-generated method stub
		boardLikeSQLMapper.deleteByMbrANDBrdidx(vo);
		
	}

	@Override
	public String countBoardLike(BoardLikeVo vo) {
		// TODO Auto-generated method stub
		String count = boardLikeSQLMapper.countBoardLike(vo);
		
		return count;
		
	}

}
