package com.kosmo.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PointController{
	private static final int blockCount = 4;
	private static final int blockPage = 5;
	//Point p;
	@Autowired
	PointService p;
	@Autowired
	UserService u;
	@Autowired
	GradeService g;
   
    /*public PointController(Point p) {
        this.p = p;
        // TODO Auto-generated constructor stub
    }
*/
    //포인트 목록
	@RequestMapping(value = "/pointlist")
	public ModelAndView pointlist(HttpServletRequest request, HttpServletResponse response) {
		//현재 페이지
		if(request.getParameter("mon")==null)
		{	
			System.out.println("null임?");
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("포인트페이지"+currentPage);
			HttpSession session = request.getSession();
			UserVO vo = (UserVO)session.getAttribute("USER");
	
			int point = p.serviceFuncgetPoint(vo.getId());
			int totRecord = p.serviceFunclistCount(vo.getId());
			//페이징
			PagingUtil page = new PagingUtil("/pointlist?",currentPage,totRecord,blockCount,blockPage);
			String pagingHtml = page.getPagingHtml().toString();
			//포인트 구간 목록
			ArrayList<PointVO> list = p.serviceFuncSelect(page.getStartSeq(), page.getEndSeq(),vo.getId());
			ModelAndView mav = new ModelAndView();
			mav.addObject("PAGING", pagingHtml);
			mav.addObject("POINT", point);
			mav.addObject("LIST", list);
			mav.setViewName("pro/point_list");
			return mav;
		}else{
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int mon = Integer.parseInt(request.getParameter("mon"));
			System.out.println(mon+"mon나옴??");
			System.out.println("포인트페이지"+currentPage);
			HttpSession session = request.getSession();
			UserVO vo = (UserVO)session.getAttribute("USER");

			int point = p.serviceFuncgetMonthPoint(vo.getId(),mon);
			int totRecord = p.serviceFunclistCount(vo.getId(),mon);
			//페이징
			PagingUtil page = new PagingUtil("/pointlist?mon="+mon+"&",currentPage,totRecord,blockCount,blockPage);
			String pagingHtml = page.getPagingHtml().toString();
			//포인트 구간 목록
			ArrayList<PointVO> list = p.serviceFuncMonthSelect(page.getStartSeq(), page.getEndSeq(),vo.getId(),mon);
			ModelAndView mav = new ModelAndView();
			mav.addObject("PAGING", pagingHtml);
			mav.addObject("POINT", point);
			mav.addObject("LIST", list);
			mav.addObject("MON",mon);
			mav.setViewName("pro/point_list");
			return mav;
		}
	}
	
	@RequestMapping(value = "/pointspend")
	public ModelAndView pointspend(HttpServletRequest request, HttpServletResponse response) {
		//현재 페이지
		if(request.getParameter("mon")==null)
		{	
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");

		int point = p.serviceFuncgetSpend(vo.getId());
		int totRecord = p.serviceFunclistSpendCount(vo.getId());
		//페이징
		PagingUtil page = new PagingUtil("/pointspend?",currentPage,totRecord,blockCount,blockPage);
		String pagingHtml = page.getPagingHtml().toString();
		//포인트 구간 목록
		ArrayList<PointVO> list = p.serviceFuncSpend(page.getStartSeq(), page.getEndSeq(),vo.getId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("PAGING", pagingHtml);
		mav.addObject("SPENDPOINT", point);
		mav.addObject("LIST", list);
		mav.setViewName("pro/point_list");
		return mav;
		}else{
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int mon = Integer.parseInt(request.getParameter("mon"));
			System.out.println(mon+"mon나옴??");
			System.out.println("포인트페이지"+currentPage);
			HttpSession session = request.getSession();
			UserVO vo = (UserVO)session.getAttribute("USER");

			int point = p.serviceFuncgetMonthSpendPoint(vo.getId(),mon);
			int totRecord = p.serviceFunclistSpendCount(vo.getId(),mon);
			//페이징
			PagingUtil page = new PagingUtil("/pointspend?mon="+mon+"&",currentPage,totRecord,blockCount,blockPage);
			String pagingHtml = page.getPagingHtml().toString();
			//포인트 구간 목록
			ArrayList<PointVO> list = p.serviceFuncMonthSpendSelect(page.getStartSeq(), page.getEndSeq(),vo.getId(),mon);
			ModelAndView mav = new ModelAndView();
			mav.addObject("PAGING", pagingHtml);
			mav.addObject("SPENDPOINT", point);
			mav.addObject("LIST", list);
			mav.addObject("MON",mon);
			mav.setViewName("pro/point_list");
			return mav;
		}
	}
	
	@RequestMapping(value = "/pointstack")
	public ModelAndView pointstack(HttpServletRequest request, HttpServletResponse response) {
		//현재 페이지
		if(request.getParameter("mon")==null)
		{	
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");

		int point = p.serviceFuncgetStack(vo.getId());
		int totRecord = p.serviceFunclistStackCount(vo.getId());
		//페이징
		PagingUtil page = new PagingUtil("/pointstack?",currentPage,totRecord,blockCount,blockPage);
		String pagingHtml = page.getPagingHtml().toString();
		//포인트 구간 목록
		ArrayList<PointVO> list = p.serviceFuncStack(page.getStartSeq(), page.getEndSeq(),vo.getId());
		ModelAndView mav = new ModelAndView();
		mav.addObject("PAGING", pagingHtml);
		mav.addObject("STACKPOINT", point);
		mav.addObject("LIST", list);
		mav.setViewName("pro/point_list");
		return mav;
		}else{
			int currentPage = Integer.parseInt(request.getParameter("currentPage"));
			int mon = Integer.parseInt(request.getParameter("mon"));
			System.out.println(mon+"mon나옴??");
			System.out.println("포인트페이지"+currentPage);
			HttpSession session = request.getSession();
			UserVO vo = (UserVO)session.getAttribute("USER");

			int point = p.serviceFuncgetMonthStackPoint(vo.getId(),mon);
			int totRecord = p.serviceFunclistStackCount(vo.getId(),mon);
			//페이징
			PagingUtil page = new PagingUtil("/pointstack?mon="+mon+"&",currentPage,totRecord,blockCount,blockPage);
			String pagingHtml = page.getPagingHtml().toString();
			//포인트 구간 목록
			ArrayList<PointVO> list = p.serviceFuncMonthStackSelect(page.getStartSeq(), page.getEndSeq(),vo.getId(),mon);
			ModelAndView mav = new ModelAndView();
			mav.addObject("PAGING", pagingHtml);
			mav.addObject("STACKPOINT", point);
			mav.addObject("LIST", list);
			mav.addObject("MON",mon);
			mav.setViewName("pro/point_list");
			return mav;
		}
	}	
	
	
	@RequestMapping(value = "/monthlist")
	public ModelAndView monthlist(HttpServletRequest request, HttpServletResponse response) {
		//현재 페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");
		System.out.println(vo.getId());
		PointVO po= new PointVO();
		ArrayList<PointVO> mList = p.serviceFuncMonthSpendSelect(vo.getId());
		ArrayList<PointVO> mList2 = p.serviceFuncMonthStackSelect(vo.getId());
		ModelAndView mav = new ModelAndView();	
		mav.addObject("MONTH_LIST",mList);
		mav.addObject("MONTH_LIST2",mList2);
		mav.setViewName("pro/month_list");
		return mav;
	}		
	
	@RequestMapping(value="/pointsum")
	public @ResponseBody Map<String, String> pointsum(HttpServletRequest request, HttpServletResponse response, Model model){
		int point = Integer.parseInt(request.getParameter("point"));
		String userid = request.getParameter("userid");
		
		PointVO vo = new PointVO();
		vo.setUid(userid);
		vo.setPval(point);
		p.serviceFuncInsert(vo);
		UserVO uvo = u.serviceFuncSelectId(userid);
		int gubun = p.serviceFuncsumPoint(userid);
		
		ArrayList<GradeVO> list = g.serviceFuncSelectAll();
		for(int i=0;i<list.size();i++){
			if(gubun>=list.get(i).getRequestpoint()){
				uvo.setAccount(list.get(i).getUsers_account());
				break;
			}
		}
		u.serviceFuncUpdate(uvo);
		
		point = p.serviceFuncgetPoint(userid);
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("sendData", ""+point);
		return result;
	}
}
