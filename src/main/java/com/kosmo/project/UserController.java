package com.kosmo.project;

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

import org.codehaus.jackson.map.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class UserController{
	private static final int blockCount=4;
	private static final int blockPage=5;
	@Autowired
    private UserService u;
	@Autowired
	private PointService p;

    //로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(0);//유효시간 0초
		session.invalidate();//
		return "pro/user_main";
	}

	//로그인
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idVal = request.getParameter("userid");
		String pwVal = request.getParameter("password");
		UserVO vo = u.serviceFuncLogin(idVal, pwVal);
		HttpSession session = request.getSession();
		session.setAttribute("USER", vo);
		return "pro/user_main";
	}
	
	
	@RequestMapping(value="/checklog")
	public @ResponseBody int checklog(HttpServletRequest request, HttpServletResponse response, Model model){
		return u.serviceFuncChecklog(request.getParameter("userid"),request.getParameter("password"));
	}
	
	@RequestMapping(value="/checkid")
	public @ResponseBody int checkid(HttpServletRequest request, HttpServletResponse response, Model model){
		return u.serviceFuncCheckid(request.getParameter("userid"));
	}
	
	@RequestMapping(value="/androidcheckid")
	public @ResponseBody Map<String, String> androidcheckid(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		System.out.println(userid);
		int res = u.serviceFuncSelect(userid);
		Map<String, String> result = new HashMap<String, String>();
		int point=0;
		if(res==0){
			result.put("sendData", "no");
		}else{
			point = p.serviceFuncgetPoint(userid);
			result.put("sendData", ""+point);
		}
		return result;
	}
	@RequestMapping(value="/androidlogin")
	public @ResponseBody Map<String, String> androidlogin(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		UserVO uvo = u.serviceFuncLogin(userid, password);
		Map<String, String> result = new HashMap<String, String>();
		if(uvo==null){
			result.put("sendData", "no");
		}else{
			result.put("sendData", uvo.getAccount());
		}
		
		return result;
	}
	
	@RequestMapping(value="/androidreg")
	public @ResponseBody Map<String, String> androidreg(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String useremail = request.getParameter("useremail");
		int res = u.serviceFuncCheckid(request.getParameter("userid"));
		Map<String, String> result = new HashMap<String, String>();
		if(res>0){
			result.put("sendData", "no");
		}else{
			UserVO vo = new UserVO();
			vo.setId(userid);
			vo.setPw(password);
			vo.setName(username);
			vo.setEmail(useremail);
			res = u.serviceFuncInsert(vo);
			if(res>0){
				result.put("sendData", "ok");
			}else{
				result.put("sendData", "no");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/androidmemlist")
	public @ResponseBody JSONObject androidmemlist(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		
		JSONArray jarray = new JSONArray();
		try{
			UserVO vo = new UserVO();
			String idVal = request.getParameter("userid");
			String pwVal = request.getParameter("password");
			String nameVal = request.getParameter("name");
			String emailVal = request.getParameter("email");
			String accountVal = request.getParameter("account");
			int seq = Integer.parseInt(request.getParameter("seq").toString());
			vo.setSeq(seq);
			vo.setId(idVal);
			vo.setAccount(accountVal);
			vo.setEmail(emailVal);
			vo.setName(nameVal);
			vo.setPw(pwVal);
			u.serviceFuncUpdate(vo);
			json.put("update", "succ");
		}catch(Exception e){
			json.put("update", "fail");
		}
		ArrayList<UserVO> list = u.serviceFuncSelect();
		for(int i=0;i<list.size();i++){
			UserVO uvo = list.get(i);
			JSONObject row = new JSONObject();
			row.put("users_account", uvo.getAccount());
			row.put("users_id",uvo.getId() );
			row.put("users_email", uvo.getEmail());
			row.put("users_name", uvo.getName());
			row.put("users_pw", uvo.getPw());
			row.put("users_seq", uvo.getSeq());
			jarray.add(i,row);
		}
		json.put("sendData", jarray);
		return json;
	}
	
	@RequestMapping(value="/androidmemadd")
	public @ResponseBody JSONObject androidmemadd(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		
		try{
			UserVO vo = new UserVO();
			String idVal = request.getParameter("userid");
			String pwVal = request.getParameter("password");
			String nameVal = request.getParameter("name");
			String emailVal = request.getParameter("email");
			String accountVal = request.getParameter("account");
			vo.setId(idVal);
			vo.setEmail(emailVal);
			vo.setName(nameVal);
			vo.setPw(pwVal);
			u.serviceFuncInsert(vo,accountVal);
			json.put("add", "succ");
		}catch(Exception e){
			json.put("add", "fail");
		}
		return json;
	}
	
	@RequestMapping(value="/androidmemdel")
	public @ResponseBody JSONObject androidmemdel(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		try{
			int seqVal = Integer.parseInt(request.getParameter("seq").toString());
			u.serviceFuncDelete(seqVal);
			json.put("del", "succ");
		}catch(Exception e){
			json.put("del", "fail");
		}
		return json;
	}
	
	//회원가입
	@RequestMapping(value="/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String idVal = request.getParameter("userid");
		String pwVal = request.getParameter("password");
		String nameVal = request.getParameter("username");
		String emailVal = request.getParameter("email");
		UserVO vo = new UserVO();
		int res =0;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		//새로운 유저일 때
		vo.setId(idVal);
		vo.setPw(pwVal);
		vo.setName(nameVal);
		vo.setEmail(emailVal);
		res = u.serviceFuncInsert(vo);
		if(res <= 0){
			//db입력 실패
			out.println("alert('가입 실패하였습니다.');");
			out.println("location.href='/pro/user_register.jsp'"); 
		}else {//가입성공
			out.println("alert('가입 성공하였습니다.');");
			out.println("location.href='/pro/user_login.jsp'"); 
		}
		out.println("</script>"); 
		out.close();
	}
	
	//유저목록(관리자)
	@RequestMapping(value="/userlist")
	public ModelAndView userlist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//현재 페이지
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int totRecord=0;
		PagingUtil page;
		String pagingHtml="";
		ArrayList<UserVO> list = new ArrayList<UserVO>();
		try{
			String column = request.getParameter("search_param");
			if(column.equals("id")){
				column = "users_id";
			}else if(column.equals("name")){
				column = "users_name";
			}
			String key = request.getParameter("sk");
			totRecord = u.serviceFuncSearchCount(column, key);
			page = new PagingUtil("userlist?search_param="+column+"&sk="+key,currentPage,totRecord,blockCount,blockPage);
			pagingHtml = page.getPagingHtml().toString();
			list = u.serviceFuncSearch(column, key, page.getStartSeq(), page.getEndSeq());
		}catch(Exception e){
			totRecord = u.serviceFunclistCount();
			page = new PagingUtil("/userlist?",currentPage,totRecord,blockCount,blockPage);
			pagingHtml = page.getPagingHtml().toString();
			list = u.serviceFuncSelect(page.getStartSeq(), page.getEndSeq());
		}
		
		mav.addObject("PAGING", pagingHtml);
		mav.addObject("LIST", list);
		mav.setViewName("pro/user_list");
		return mav;
	}

	//유저삭제(관리자)
	@RequestMapping(value="/userdelete")
	public ModelAndView userdelete(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		try{
			//선택한 유저목록 삭제
			String[] check = request.getParameterValues("check_user");
			for(int i=0;i<check.length;i++){
				u.serviceFuncDelete(Integer.parseInt(check[i]));
			}
		}catch(Exception e){}
		finally{
			//예외처리해도 유저리스트로 돌아가야 함
			mav.setView(new RedirectView("userlist?currentPage=1"));
			return mav;
		}
	}
	
	//회원 추가(관리자)
	@RequestMapping(value="/userinsert")
	public void userinsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String idVal = request.getParameter("id");
		System.out.println(idVal);
		String pwVal = request.getParameter("psw");
		String nameVal = request.getParameter("name");
		String emailVal = request.getParameter("email");
		String account = request.getParameter("account");
		UserVO vo = new UserVO();
		int res = u.serviceFuncSelect(idVal);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		if(res==1){
			//입력한 정보(아이디)가 이미 있을 때
			out.println("<script language='javascript'>");
			out.println("alert('중복된 아이디 입니다.');");
		}else{
			//새로운 유저일 때
			vo.setId(idVal);
			vo.setPw(pwVal);
			vo.setName(nameVal);
			vo.setEmail(emailVal);
			res = u.serviceFuncInsert(vo,account);
			
			if(res <= 0){
				//db입력 실패
				out.println("alert('회원추가 실패하였습니다.');");		
			}else {//가입성공
				out.println("alert('회원추가 성공하였습니다.');");
			}
		}
		out.println("location.href='/userlist?currentPage=1'"); 
		out.println("</script>"); 
		out.close();
	}
	
	//로그인한 유저 정보 페이지
	@RequestMapping(value="/userinfo")
	public String userinfo(HttpServletRequest request, HttpServletResponse response){
		return "/pro/user_info";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//로그인한 유저 정보 수정
	@RequestMapping(value="/userupdate")
	public void userupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");
		vo.setPw(request.getParameter("pw"));
		vo.setEmail(request.getParameter("email"));
		vo.setName(request.getParameter("uname"));
		u.serviceFuncUpdate(vo);
		//session의 유저정보를 수정된 유저정보로 교체
		session.setAttribute("USER", vo);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		out.println("alert('정보 수정이 완료되었습니다.');");
		out.println("location.href='/userinfo'"); 
		out.println("</script>"); 
		out.close();
	}
	
	//상세 유저정보(관리자)
	@RequestMapping(value="/userdetail")
	public ModelAndView userdetail(HttpServletRequest request, HttpServletResponse response){
		int user_seq = Integer.parseInt(request.getParameter("seq"));
		UserVO vo = u.serviceFuncSelect(user_seq);
		ModelAndView mav = new ModelAndView();
		mav.addObject("USERVO", vo);
		mav.setViewName("pro/user_modify");
		return mav;
	}

	//유저 수정(관리자)
	@RequestMapping(value="/usermodify")
	public ModelAndView usermodify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		UserVO vo = new UserVO();
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setId(request.getParameter("id"));
		vo.setPw(request.getParameter("pw"));
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setAccount(request.getParameter("account"));
		u.serviceFuncUpdate(vo);
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("userlist?currentPage=1"));
		return mav;
	}
	
}
