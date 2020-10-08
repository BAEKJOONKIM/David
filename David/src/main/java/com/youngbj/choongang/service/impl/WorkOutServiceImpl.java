package com.youngbj.choongang.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.WorkOutLikeMemberSQLMapper;
import com.youngbj.choongang.mapper.WorkOutPickMemberSQLMapper;
import com.youngbj.choongang.mapper.WorkOutReplySQLMapper;
import com.youngbj.choongang.mapper.WorkoutSQLMapper;
import com.youngbj.choongang.service.WorkoutService;
import com.youngbj.choongang.vo.MemberImgVo;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.WorkOutLikeMemberVo;
import com.youngbj.choongang.vo.WorkOutPickMemberVo;
import com.youngbj.choongang.vo.WorkOutReplyVo;
import com.youngbj.choongang.vo.WorkOutVo;

@Service
public class WorkOutServiceImpl implements WorkoutService {
	@Autowired
	WorkoutSQLMapper workOutSQLMapper;
	@Autowired
	MemberSQLMapper memberSQLMapper;
	@Autowired
	WorkOutReplySQLMapper workOutReplySQLMapper;
	@Autowired
	WorkOutLikeMemberSQLMapper workOutLikeMemberSQLMapper;
	@Autowired
	WorkOutPickMemberSQLMapper workOutPickMemberSQLMapper;

	@Override
	public void upLoad(WorkOutVo vo) {
		// TODO Auto-generated method stub

		workOutSQLMapper.insertWorkOut(vo);
	}

	@Override
	public HashMap<String, Object> getWorkOutx(WorkOutVo vo) {
		// TODO Auto-generated method stub
		WorkOutVo workOutVo = workOutSQLMapper.selectByIdx(vo);
		MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());
		ArrayList<WorkOutReplyVo> workOutReplyList = workOutReplySQLMapper.selectByReply(workOutVo.getWrk_idx());
		MemberImgVo memberImgVo = memberSQLMapper.selectMemberImgByIdx(workOutVo.getMbr_idx());

		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("workOutVo", workOutVo);
		map.put("memberVo", memberVo);
		map.put("workOutReplyList", workOutReplyList);
		map.put("memberImgVo", memberImgVo);


		return map;
	}

	public HashMap<String, Object> getWorkOut(WorkOutVo vo) {
		// TODO Auto-generated method stub
		WorkOutVo workOutVo = workOutSQLMapper.selectByIdx(vo);
		MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());
		ArrayList<WorkOutReplyVo> workOutReplyList = workOutReplySQLMapper.selectByReply(workOutVo.getWrk_idx());
		// ArrayList<WorkOutLikeMemberVo> workOutLikeMemberList =
		// workOutLikeMemberSQLMapper.selectWorkOutLikeByIdx(workOutVo.getWrk_idx());
		MemberImgVo memberImgVo = memberSQLMapper.selectMemberImgByIdx(workOutVo.getMbr_idx());

		WorkOutPickMemberVo ttt = new WorkOutPickMemberVo();
		WorkOutLikeMemberVo tt = new WorkOutLikeMemberVo();

		System.out.println("vo.getMbr_idx() : " + vo.getMbr_idx());
		System.out.println("vo.getWrk_idx() : " + vo.getWrk_idx());

		tt.setMbr_idx(vo.getMbr_idx());
		tt.setWrk_idx(vo.getWrk_idx());
		ttt.setMbr_idx(vo.getMbr_idx());
		ttt.setWrk_idx(vo.getWrk_idx());
		WorkOutLikeMemberVo workOutLike = workOutLikeMemberSQLMapper.selectWorkOutLikeMember(tt);
		WorkOutPickMemberVo workOutPick = workOutPickMemberSQLMapper.selectWorkOutPickMember(ttt);
		System.out.println("sssss : " + workOutLike);
		System.out.println("sssss : " + workOutPick);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("workOutVo", workOutVo);
		map.put("memberVo", memberVo);
		map.put("workOutReplyList", workOutReplyList);
		map.put("memberImgVo", memberImgVo);

		// map.put("workOutLikeMemberList", workOutLikeMemberList);
		if (workOutLike != null) {
			map.put("isLike", "xxxx");
		}
		if (workOutPick != null) {
			map.put("isPick", "isPick");
		}

		return map;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutList() {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutDataList = new ArrayList<HashMap<String, Object>>();

		List<WorkOutVo> workOutList = workOutSQLMapper.selectAll();

		for (WorkOutVo workOutVo : workOutList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("workOutVo", workOutVo);
			workOutDataList.add(map);
		}

		return workOutDataList;
	}

	@Override
	public void deleteWorkOut(String wrk_idx) {
		// TODO Auto-generated method stub
		workOutSQLMapper.deleteByIdx(wrk_idx);
	}

	@Override
	public void updateReadcount(String wrk_idx) {
		// TODO Auto-generated method stub
		workOutSQLMapper.updateReadCount(wrk_idx);
	}

	@Override
	public void updateWorkOut(WorkOutVo vo) {
		// TODO Auto-generated method stub
		workOutSQLMapper.updateWorkOut(vo);
	}

	@Override
	public List<HashMap<String, Object>> getSearchWorkOutList(String result, String searchType) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutDataList = new ArrayList<HashMap<String, Object>>();
		System.out.println("result" + result);
		System.out.println("searchType" + searchType);

		if (searchType.equals("t")) {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectTitleSearch(result);
			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		} else {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectContentSearch(result);
			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		}

	}

	@Override
	public List<HashMap<String, Object>> getWorkOutPageList(String wrk_cp) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutDataList = new ArrayList<HashMap<String, Object>>();
		System.out.println(wrk_cp);
		List<WorkOutVo> workOutList = workOutSQLMapper.selectPageNumber(wrk_cp);
		System.out.println("ssss");
		for (WorkOutVo workOutVo : workOutList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("workOutVo", workOutVo);
			workOutDataList.add(map);

		}
		return workOutDataList;

	}

	@Override
	public List<HashMap<String, Object>> getSearchWorkOutPageList(String wrk_cp, String result, String searchType) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutDataList = new ArrayList<HashMap<String, Object>>();

		System.out.println("result" + result);
		System.out.println("searchType" + searchType);
		System.out.println("wrk_cp" + wrk_cp);

		if (searchType.equals("t")) {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectSearchTitlePageNumber(result, wrk_cp);
			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		} else if (searchType.equals("c")) {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectSearchContentPageNumber(result, wrk_cp);
			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		} else {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectSearchTitleAndContentPageNumber(result, wrk_cp);
			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		}
	}
	
	@Override
	public List<HashMap<String, Object>> getSearchWriterWorkOutPageList(String wrk_cp, String result, String searchType) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutUserList = workOutSQLMapper.selectSearchUserPageNumber(result, wrk_cp);

		return workOutUserList;
	}

	@Override
	public List<HashMap<String, Object>> getWorkOutCategoryList(String wrk_cat) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutDataList = new ArrayList<HashMap<String, Object>>();
		System.out.println("카테고리번호" + wrk_cat);

		if (wrk_cat.equals("0")) {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectAll();
			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		} else {
			List<WorkOutVo> workOutList = workOutSQLMapper.selectByCat(wrk_cat);

			for (WorkOutVo workOutVo : workOutList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("workOutVo", workOutVo);
				workOutDataList.add(map);

			}
			return workOutDataList;
		}

	}

	@Override
	public List<HashMap<String, Object>> getWorkOutCategoryPageList(String wrk_cat, String wrk_cp) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> workOutDataList = new ArrayList<HashMap<String, Object>>();
		List<WorkOutVo> workOutList = workOutSQLMapper.selectByCatPage(wrk_cat, wrk_cp);
		for (WorkOutVo workOutVo : workOutList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(workOutVo.getMbr_idx());

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberVo", memberVo);
			map.put("workOutVo", workOutVo);
			workOutDataList.add(map);

		}
		return workOutDataList;

	}

	@Override
	public String getWorkOutCount() {
		// TODO Auto-generated method stub
		String workOutCount = workOutSQLMapper.countWorkOut();
		return workOutCount;
	}

	@Override
	public HashMap<String, Object> getWorkOuty(String wrk_idx, String mbr_idx) {
		// TODO Auto-generated method stub
		HashMap<String, Object> readWorkOutPage = workOutSQLMapper.readWorkOutPage(wrk_idx, mbr_idx);
		
		
		return readWorkOutPage;
	}

	

}
