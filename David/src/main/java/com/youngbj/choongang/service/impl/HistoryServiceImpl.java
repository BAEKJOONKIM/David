package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.BoardSQLMapper;
import com.youngbj.choongang.mapper.HistorySQLMapper;
import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.My_flexContentSQLMapper;
import com.youngbj.choongang.mapper.WorkoutSQLMapper;
import com.youngbj.choongang.service.HistoryService;
import com.youngbj.choongang.vo.BoardReplyVo;
import com.youngbj.choongang.vo.BoardVo;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_replyVo;
import com.youngbj.choongang.vo.WorkOutPickMemberVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

@Service
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	HistorySQLMapper historySQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	BoardSQLMapper boardSQLMapper;
	@Autowired
	My_flexContentSQLMapper my_flexContentSQLMapper;
	@Autowired
	WorkoutSQLMapper workoutSQLMapper;

	// 글 목록 가져오기
	// Board 내가 쓴 글 목록
	@Override
	public List<HashMap<String, Object>> getBoardContentList(String mbr_idx) {
		// TODO Auto-generated method stub

		List<HashMap<String, Object>> boardMyWritingDataList = new ArrayList<HashMap<String, Object>>();

		List<BoardVo> boardMyList = historySQLMapper.selectBoardByIdx(mbr_idx);
		// List<BoardVo> boardMyList = historySQLMapper.selectBoardByIdx(mbr_idx,
		// r_num);

		for (BoardVo boardVo : boardMyList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(boardVo.getMbr_idx());

			HashMap<String, Object> boardMap = new HashMap<String, Object>();
			boardMap.put("memberVo", memberVo);
			boardMap.put("boardVo", boardVo);

			boardMyWritingDataList.add(boardMap);

		}

		return boardMyWritingDataList;
	}

	@Override
	public List<HashMap<String, Object>> getMyFlexContentList(String mbr_idx) {
		// TODO Auto-generated method stub

		List<HashMap<String, Object>> myFlexMyWritingDataList = new ArrayList<HashMap<String, Object>>();

		List<My_flexContentVo> myFlexMyList = historySQLMapper.selectMyFlexByIdx(mbr_idx);

		for (My_flexContentVo myFlexContentVo : myFlexMyList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(myFlexContentVo.getMbr_idx());

			HashMap<String, Object> myFlexMap = new HashMap<String, Object>();
			myFlexMap.put("memberVo", memberVo);
			myFlexMap.put("myFlexContentVo", myFlexContentVo);

			myFlexMyWritingDataList.add(myFlexMap);
		}
		return myFlexMyWritingDataList;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutContentList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutMyWritingDataList = new ArrayList<HashMap<String, Object>>();

		List<WorkOutVo> workOutList = historySQLMapper.selectWorkOutByIdx(mbr_idx);

		for (WorkOutVo workOutVo : workOutList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

			HashMap<String, Object> workOutMap = new HashMap<String, Object>();
			workOutMap.put("memberVo", memberVo);
			workOutMap.put("workOutVo", workOutVo);

			workOutMyWritingDataList.add(workOutMap);
		}
		return workOutMyWritingDataList;

	}

	// 댓글 가져오기
	@Override
	public List<HashMap<String, Object>> getBoardReplyList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> boardMyReplyDataList = new ArrayList<HashMap<String, Object>>();

		List<BoardReplyVo> boardReplyList = historySQLMapper.selectBoardReplyByIdx(mbr_idx);

		for (BoardReplyVo boardReplyVo : boardReplyList) {

			MemberVo memberVo = memberSQLMapper.selectIdx(boardReplyVo.getMbr_idx());

			HashMap<String, Object> boardReplyMap = new HashMap<String, Object>();
			boardReplyMap.put("memberVo", memberVo);
			boardReplyMap.put("boardReplyVo", boardReplyVo);

			boardMyReplyDataList.add(boardReplyMap);
		}
		return boardMyReplyDataList;
	}

	@Override
	public List<HashMap<String, Object>> getMyFlexReplyList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> myFlexMyReplyDataList = new ArrayList<HashMap<String, Object>>();

		List<My_flex_replyVo> myFlexReplyList = historySQLMapper.selectMyFlexReplyByIdx(mbr_idx);

		for (My_flex_replyVo myFlexReplyVo : myFlexReplyList) {

			MemberVo memberVo = memberSQLMapper.selectIdx(myFlexReplyVo.getMbr_idx());

			HashMap<String, Object> myFlexReplyMap = new HashMap<String, Object>();
			myFlexReplyMap.put("memberVo", memberVo);
			myFlexReplyMap.put("myFlexReplyVo", myFlexReplyVo);

			myFlexMyReplyDataList.add(myFlexReplyMap);
		}
		return myFlexMyReplyDataList;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutReplyList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutReplyDataList = new ArrayList<HashMap<String, Object>>();

		List<WorkOutReplyVo> workOutReplyList = historySQLMapper.selectWorkOutReplyByIdx(mbr_idx);

		for (WorkOutReplyVo workOutReplyVo : workOutReplyList) {

			MemberVo memberVo = memberSQLMapper.selectIdx(workOutReplyVo.getMbr_idx());

			HashMap<String, Object> workOutReplyMap = new HashMap<String, Object>();
			workOutReplyMap.put("memberVo", memberVo);
			workOutReplyMap.put("workOutReplyVo", workOutReplyVo);

			workOutReplyDataList.add(workOutReplyMap);
		}

		return workOutReplyDataList;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutPickList(String mbr_idx) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutPickDataList = new ArrayList<HashMap<String, Object>>();

		List<WorkOutPickMemberVo> workOutPickList = historySQLMapper.selectWorkOutPickByIdx(mbr_idx);

		for (WorkOutPickMemberVo workOutPickVo : workOutPickList) {

			WorkOutVo workOutVo = workoutSQLMapper.selectWorkOutByIdx(workOutPickVo.getWrk_idx());
			MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

			HashMap<String, Object> workOutPickMap = new HashMap<String, Object>();
			workOutPickMap.put("memberVo", memberVo);
			workOutPickMap.put("workOutVo", workOutVo);
			workOutPickMap.put("workOutPickVo", workOutPickVo);

			workOutPickDataList.add(workOutPickMap);

		}
		return workOutPickDataList;
	}

	@Override
	public List<HashMap<String, Object>> getBoardContentListByPaging(String mbr_idx, String n_p) {
		List<HashMap<String, Object>> boardList = new ArrayList<HashMap<String, Object>>();
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		List<BoardVo> boardMyList = historySQLMapper.selectBoardByPaging(mbr_idx, n_p);
		for (BoardVo boardVo : boardMyList) {
			HashMap<String, Object> boardMap = new HashMap<String, Object>();

			boardMap.put("memberVo", memberVo);
			boardMap.put("boardVo", boardVo);

			boardList.add(boardMap);
		}

		return boardList;
	}

	// 백준
	public String getMyBoardCount(String mbr_idx) {
		String count = historySQLMapper.myBoardCount(mbr_idx);
		return count;
	}

	@Override
	public List<HashMap<String, Object>> getMyFlexContentListByPaging(String mbr_idx, String n_p) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> myFlexList = new ArrayList<HashMap<String, Object>>();
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		List<My_flexContentVo> myFlexMyList = historySQLMapper.selectMyFlexByPaging(mbr_idx, n_p);
		for (My_flexContentVo myFlexVo : myFlexMyList) {
			HashMap<String, Object> myFlexMap = new HashMap<String, Object>();

			myFlexMap.put("memberVo", memberVo);
			myFlexMap.put("myFlexVo", myFlexVo);

			myFlexList.add(myFlexMap);

		}
		return myFlexList;
	}

	@Override
	public String getMyFlexCount(String mbr_idx) {
		// TODO Auto-generated method stub
		String count = historySQLMapper.myMyFlexCount(mbr_idx);
		return count;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutContentListByPaging(String mbr_idx, String n_p) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutList = new ArrayList<HashMap<String, Object>>();
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		List<WorkOutVo> workOutMyList = historySQLMapper.selectWorkOutByPaging(mbr_idx, n_p);
		for (WorkOutVo workOutVo : workOutMyList) {
			HashMap<String, Object> workOutMap = new HashMap<String, Object>();
			workOutMap.put("memberVo", memberVo);
			workOutMap.put("workOutVo", workOutVo);

			workOutList.add(workOutMap);

		}
		return workOutList;
	}

	@Override
	public String getMyWorkOutCount(String mbr_idx) {
		// TODO Auto-generated method stub
		String count = historySQLMapper.myWorkOutCount(mbr_idx);
		return count;
	}

	// 백준boardreply
	@Override
	public List<HashMap<String, Object>> getBoardReplyListByPaging(String mbr_idx, String n_p) {
		List<HashMap<String, Object>> boardReplyList = new ArrayList<HashMap<String, Object>>();
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		List<BoardReplyVo> boardReplyMyList = historySQLMapper.selectBoardReplyByPaging(mbr_idx, n_p);
		for (BoardReplyVo boardReplyVo : boardReplyMyList) {
			HashMap<String, Object> boardMap = new HashMap<String, Object>();
			BoardVo boardVo = boardSQLMapper.selectByIdx((boardReplyVo.getBrd_idx()));

			boardMap.put("memberVo", memberVo);
			boardMap.put("boardReplyVo", boardReplyVo);
			boardMap.put("boardVo", boardVo);

			boardReplyList.add(boardMap);
		}

		return boardReplyList;

	}

	// 백준boardreply
	@Override
	public String getMyBoardReplyCount(String mbr_idx) {
		String count = historySQLMapper.myBoardReplyCount(mbr_idx);
		return count;
	}

	@Override
	public List<HashMap<String, Object>> getMyFlexReplyListByPaging(String mbr_idx, String n_p) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> myFlexReplyList = new ArrayList<HashMap<String,Object>>();
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		List<My_flex_replyVo> myFlexReplyMyList = historySQLMapper.selectMyFlexReplyByPaging(mbr_idx, n_p);
		for(My_flex_replyVo myFlexReplyVo : myFlexReplyMyList) {
			HashMap<String, Object> myFlexMap = new HashMap<String, Object>();
			My_flexContentVo myFlexVo = my_flexContentSQLMapper.selectByIdx(myFlexReplyVo.getFlx_idx());
			
			myFlexMap.put("memberVo", memberVo);
			myFlexMap.put("myFlexVo", myFlexVo);
			myFlexMap.put("myFlexReplyVo", myFlexReplyVo);
			
			myFlexReplyList.add(myFlexMap);
		}
		return myFlexReplyList;
	}

	@Override
	public String getMyFelxReplyCount(String mbr_idx) {
		// TODO Auto-generated method stub
		String count = historySQLMapper.myFlexReplyCount(mbr_idx);
		return count;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutReplyListByPaging(String mbr_idx, String n_p) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutList = new ArrayList<HashMap<String,Object>>();
		MemberVo memberVo = memberSQLMapper.selectIdx(mbr_idx);
		List<WorkOutReplyVo> workOutReplyMyList = historySQLMapper.selectWorkOutReplyByPagin(mbr_idx, n_p);
		for(WorkOutReplyVo workOutReplyVo : workOutReplyMyList) {
			HashMap<String, Object> workOutMap = new HashMap<String, Object>();
			WorkOutVo workOutVo = workoutSQLMapper.selectWorkOutByIdx(workOutReplyVo.getWrk_idx());
			
			workOutMap.put("memberVo", memberVo);
			workOutMap.put("workOutVo", workOutVo);
			workOutMap.put("workOutReplyVo", workOutReplyVo);
			
			workOutList.add(workOutMap);
		}
		return workOutList;
	}

	@Override
	public String getMyWorkOutReplyCount(String mbr_Idx) {
		// TODO Auto-generated method stub
		String count = historySQLMapper.myWorkOutReplyCount(mbr_Idx);
		return count;
	}

}
