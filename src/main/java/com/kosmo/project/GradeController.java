package com.kosmo.project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@Controller
public class GradeController{
	private static final int blockCount=4;
	private static final int blockPage=5;
	@Autowired
    private UserService u;
	@Autowired
	private PointService p;
	@Autowired
	private GradeService g;
	
	@RequestMapping(value="/gradelist")
	public ModelAndView gradelist(HttpServletRequest request, HttpServletResponse response){
		//현재 페이지 가져옴
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int totalCnt = g.serviceFunclistCount();
		
		int blockCount=5;
		int blockPage=5;
		//페이징
		PagingUtil page = new PagingUtil("gradelist?", currentPage,totalCnt,blockCount,blockPage);
		String pagingHtml = page.getPagingHtml().toString();
		//해당 페이지 구간 구매목록
		ArrayList<GradeVO> pList = g.serviceFuncSelect(page.getStartSeq(), page.getEndSeq());
		//ModelAndView로 리스트 페이지에 넘길 정보들 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("PAGING", pagingHtml);
		mav.addObject("TOTAL_CNT", totalCnt);
		mav.addObject("GRADE_LIST", pList);
		mav.setViewName("pro/grade_list");
		return mav;
	}

	@RequestMapping(value="/gradeadd")
	public ModelAndView gradeadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/gradelist?currentPage=1"));
		int maxSize = 10 * 1024 * 1024;
		String uploadDir2 = request.getSession().getServletContext().getRealPath("/img/grade");
		System.out.println(uploadDir2);

		
		
		try{
			String format = "UTF-8";
			MultipartRequest mRequest = new MultipartRequest(request, uploadDir2, maxSize, format, new DefaultFileRenamePolicy());
			//renamepolicy - 같은이름오면 rename해줌
			String account =mRequest.getParameter("account");

			int drate = Integer.parseInt(mRequest.getParameter("drate"));
			int rpoint = Integer.parseInt(mRequest.getParameter("rpoint"));
			String ufileVal = mRequest.getFilesystemName("img");//맨마지막경로를 가져옴		
			//String filePath2 = "..\\"+uploadDir2 + "\\" + ufileVal;//상대경로쓴경우
			//String filePath2 = uploadDir2 + "/" + ufileVal;//절대경로쓴경우
			//System.out.println(filePath);
			String filePath2 = "../img/grade/" + ufileVal;//상대경로쓴경우
			
			//File file = new File(filePath);
			File file2 = new File(filePath2);
			
			//JDBC  컬럼 - 원본파일명, 시스템파일명, 파일사이즈, 확장자
			

			GradeVO go = new GradeVO();
			int res =0;
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			//새로운 유저일 때
			go.setUsers_account(account);
			go.setRequestpoint(rpoint);
			go.setDiscountrate(drate);
			go.setGrade_url(filePath2);
			res = g.serviceFuncInsert(go);
			if(res<=0){
				System.out.println("실패");
			}else{
				System.out.println("성공");
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return mav;
		}
	}

    //포인트 목록
	@RequestMapping(value = "/gradeuserpoint")
	public ModelAndView gradeuserpoint(HttpServletRequest request, HttpServletResponse response) {
		//현재 페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("포인트페이지"+currentPage);
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");

		int point = p.serviceFuncsumPoint(vo.getId());
		int totRecord = g.serviceFunclistCount();
		//페이징
		PagingUtil page = new PagingUtil("/gradeuserpoint?",currentPage,totRecord,blockCount,blockPage);
		String pagingHtml = page.getPagingHtml().toString();
		//포인트 구간 목록
		ArrayList<GradeVO> pList = g.serviceFuncSelect(page.getStartSeq(), page.getEndSeq());
		ModelAndView mav = new ModelAndView();
		mav.addObject("PAGING", pagingHtml);
		mav.addObject("POINT", point);
		mav.addObject("LIST", pList);
		mav.setViewName("pro/grade_user");
		return mav;
	}	


	@RequestMapping(value="/gradedelete")
	public ModelAndView gradedelete(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		try{
			//선택한 유저목록 삭제
			String[] check = request.getParameterValues("check_grade");
			for(int i=0;i<check.length;i++){
				g.serviceFuncDelete(Integer.parseInt(check[i]));
			}
		}catch(Exception e){}
		finally{
			//예외처리해도 유저리스트로 돌아가야 함
			mav.setView(new RedirectView("gradelist?currentPage=1"));
			return mav;
		}
	}
	//상세 유저정보(관리자)
	@RequestMapping(value="/gradedetail")
	public ModelAndView gradedetail(HttpServletRequest request, HttpServletResponse response){
		int gseq = Integer.parseInt(request.getParameter("seq"));
		GradeVO vo = g.serviceFuncSelect(gseq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("GRADEVO", vo);
		mav.setViewName("pro/grade_modify");
		return mav;
	}

	@RequestMapping(value="/grademodify")
	public ModelAndView grademodify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		GradeVO vo = new GradeVO();
		vo.setGrade_seq(Integer.parseInt(request.getParameter("seq")));
		vo.setUsers_account(request.getParameter("account"));
		vo.setRequestpoint(Integer.parseInt(request.getParameter("rpoint")));
		vo.setDiscountrate(Integer.parseInt(request.getParameter("drate")));
		
		g.serviceFuncUpdate(vo);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("gradelist?currentPage=1"));
		return mav;
	}	
}
