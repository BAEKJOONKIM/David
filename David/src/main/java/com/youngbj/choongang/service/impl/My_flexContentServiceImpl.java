package com.youngbj.choongang.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youngbj.choongang.mapper.MemberSQLMapper;
import com.youngbj.choongang.mapper.My_flex_like_memberSQLMapper;
import com.youngbj.choongang.mapper.My_flex_replySQLMapper;
import com.youngbj.choongang.service.My_flexContentService;
import com.youngbj.choongang.vo.MemberVo;
import com.youngbj.choongang.vo.My_flexContentVo;
import com.youngbj.choongang.vo.My_flex_like_memberVo;
import com.youngbj.choongang.vo.My_flex_replyVo;

@Service
public class My_flexContentServiceImpl implements My_flexContentService {
	@Autowired
	private My_flex_replySQLMapper My_flex_replySQLMapper;
	@Autowired
	private com.youngbj.choongang.mapper.My_flexContentSQLMapper My_flexContentSQLMapper;
	@Autowired
	private MemberSQLMapper memberSQLMapper;
	@Autowired
	private My_flex_like_memberSQLMapper My_flex_like_memberSQLMapper;

	@Override
	public List<HashMap<String, Object>> getContentList() {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String, Object>>();

		List<My_flexContentVo> contentList = My_flexContentSQLMapper.selectAll();
		
//		for(My_flexContentVo reIdxList : contentList) {
//			reIdxList.setFlx_idx(flx_idx);
//		}
			
			
			//-------------------------------------------
		for (My_flexContentVo my_flexcontentVo : contentList) {
			MemberVo memberVo = memberSQLMapper.selectIdx(my_flexcontentVo.getMbr_idx());
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			String tempFlxIdx = my_flexcontentVo.getFlx_idx();
			
			My_flex_like_memberVo vo = new My_flex_like_memberVo();
			vo.setFlx_idx(tempFlxIdx);
			String count = My_flex_like_memberSQLMapper.likecount(vo);
			
			My_flex_replyVo replyvo = new My_flex_replyVo();
			replyvo.setFlx_idx(tempFlxIdx);
			String replycount = My_flex_replySQLMapper.replycount(replyvo);
			
			map.put("memberVo", memberVo);
			map.put("my_flexcontentVo", my_flexcontentVo);
			map.put("likevo", count);
			map.put("replyvo", replycount);
			
			//글 등록후 6시간이 안 지났으면... 값을 추가한다..-------------------------------
			String fdat = my_flexcontentVo.getFlx_fdat();
			long currTime = System.currentTimeMillis();
			long fdatlong = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date d = sdf.parse(fdat);
				fdatlong = d.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			long time = (currTime - fdatlong)/1000/60/60;
			
			if(time < 6) {
				map.put("newContent","xxx");
			}
			//--------------------------------------------------------------
			resultDataList.add(map);

		}
		return resultDataList;
	}

	@Override
	public void writeContent(My_flexContentVo vo) {
		// TODO Auto-generated method stub
		My_flexContentSQLMapper.insert(vo);

	}

	@Override
	public void deleteContent(String flx_idx) {
		// TODO Auto-generated method stub
		My_flexContentSQLMapper.deleteByIdx(flx_idx);
	}

	@Override
	public HashMap<String, Object> getContent(String flx_idx) {
		// TODO Auto-generated method stub
		My_flexContentVo my_flexcontentVo = My_flexContentSQLMapper.selectByIdx(flx_idx);
		
		MemberVo memberVo = memberSQLMapper.selectIdx(my_flexcontentVo.getMbr_idx());
		

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVo", memberVo);
		map.put("my_flexcontentVo", my_flexcontentVo);
		
		String prnum = My_flexContentSQLMapper.prnum(flx_idx);
		String mrnum = My_flexContentSQLMapper.mrnum(flx_idx);
		map.put("prnum", prnum);
		map.put("mrnum", mrnum);

		return map;

	}

	@Override
	public void updateReadCount(String flx_idx) {
		// TODO Auto-generated method stub
		My_flexContentSQLMapper.updateReadCount(flx_idx);
	}

	@Override
	public void updateContent(My_flexContentVo vo) {
		// TODO Auto-generated method stub
		My_flexContentSQLMapper.updateContent(vo);
	}

	// ----------------------------------------------------------------------------------

	@Override
	public void replywriteContent(My_flex_replyVo vo) {
		// TODO Auto-generated method stub
		My_flex_replySQLMapper.insert(vo);

	}

	@Override
	public HashMap<String, Object> getreplyContent(String frpl_idx) {
		// TODO Auto-generated method stub
		My_flex_replyVo my_flexreplyVo = My_flex_replySQLMapper.selectByIdx(frpl_idx);
		MemberVo memberVo = memberSQLMapper.selectIdx(my_flexreplyVo.getMbr_idx());

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberVo", memberVo);
		map.put("my_flex_replyVo", my_flexreplyVo);

		return map;
	}

	@Override
	public void replydeleteContent(String frpl_idx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replyupdateContent(My_flex_replyVo vo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HashMap<String, Object>> getsearchList(String searchType, String searchData) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String, Object>>();

		if (searchType.equals("title")) {
			List<My_flexContentVo> contentList = My_flexContentSQLMapper.searchselecttitleAll(searchData);

			for (My_flexContentVo my_flexcontentVo : contentList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(my_flexcontentVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("my_flexcontentVo", my_flexcontentVo);

				resultDataList.add(map);

			}
			return resultDataList;

		} else if (searchType.equals("content")) {
			List<My_flexContentVo> contentList = My_flexContentSQLMapper.searchselectcontentAll(searchData);

			for (My_flexContentVo my_flexcontentVo : contentList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(my_flexcontentVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("my_flexcontentVo", my_flexcontentVo);

				resultDataList.add(map);

			}

			return resultDataList;
		} else if (searchType.equals("nick")) {
			List<My_flexContentVo> contentList = My_flexContentSQLMapper.searchselectnickAll(searchData);

			for (My_flexContentVo my_flexcontentVo : contentList) {
				MemberVo memberVo = memberSQLMapper.selectIdx(my_flexcontentVo.getMbr_idx());

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("memberVo", memberVo);
				map.put("my_flexcontentVo", my_flexcontentVo);

				resultDataList.add(map);

			}

			return resultDataList;
		} else{
			return null;
		}
	}

	@Override
	public List<HashMap<String, Object>> page(String flx_page) {
		// TODO Auto-generated method stub
		
		
		List<HashMap<String, Object>> resultDataList = new ArrayList<HashMap<String, Object>>();

		List<My_flexContentVo> page = My_flexContentSQLMapper.page(flx_page);
		
//		for(My_flexContentVo reIdxList : contentList) {
//			reIdxList.setFlx_idx(flx_idx);
//		}
			
			
			//-------------------------------------------
		for (My_flexContentVo my_flexcontentVo : page) {
			MemberVo memberVo = memberSQLMapper.selectIdx(my_flexcontentVo.getMbr_idx());
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			String tempFlxIdx = my_flexcontentVo.getFlx_idx();
			
			My_flex_like_memberVo vo = new My_flex_like_memberVo();
			vo.setFlx_idx(tempFlxIdx);
			String count = My_flex_like_memberSQLMapper.likecount(vo);
			
			My_flex_replyVo replyvo = new My_flex_replyVo();
			replyvo.setFlx_idx(tempFlxIdx);
			String replycount = My_flex_replySQLMapper.replycount(replyvo);
			
			map.put("memberVo", memberVo);
			map.put("my_flexcontentVo", my_flexcontentVo);
			map.put("likevo", count);
			map.put("replyvo", replycount);
			
			//글 등록후 6시간이 안 지났으면... 값을 추가한다..-------------------------------
			String fdat = my_flexcontentVo.getFlx_fdat();
			long currTime = System.currentTimeMillis();
			long fdatlong = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				Date d = sdf.parse(fdat);
				fdatlong = d.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			long time = (currTime - fdatlong)/1000/60/60;
			
			if(time < 6) {
				map.put("newContent","xxx");
			}
			//--------------------------------------------------------------
			resultDataList.add(map);

		}
		return resultDataList;
	}

	

//	@Override
//	public List<HashMap<String, Object>> getrnumList() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	//@Override
	//public void  replycount(String flx_idx) {
		// TODO Auto-generated method stub
		
		
	//}

}
