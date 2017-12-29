package com.kosmo.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {
	@Autowired
	private BoardService b;
	
	@Autowired ServletContext ctx;
	
	@RequestMapping(value="/boardadd")
	public void boardadd(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/boardlist?currentPage=1"));
		int maxSize = 10 * 1024 * 1024;
		//저장될 이미지 절대 경로
		//String uploadDir2 = "C:/Users/kosmo_21/Desktop/workspaceJava/EBSpring/src/main/webapp/img/product";
		String uploadDir2 = request.getSession().getServletContext().getRealPath("/img/product");
		try{
			HttpSession session = request.getSession();
			UserVO vo = (UserVO)session.getAttribute("USER");
			String format = "UTF-8";
			MultipartRequest mRequest = new MultipartRequest(request, uploadDir2, maxSize, format, new DefaultFileRenamePolicy());
			//renamepolicy - 같은이름오면 rename해줌
			
			String titleVal = mRequest.getParameter("title");
			String contentVal = mRequest.getParameter("contents");
			String fileVal = mRequest.getFilesystemName("file");//맨마지막경로를 가져옴
			String oriVal = mRequest.getOriginalFileName("file");
			String userid = vo.getId();
			//String filePath2 = "..\\"+uploadDir2 + "\\" + ufileVal;//상대경로쓴경우
			//String filePath2 = uploadDir2 + "/" + ufileVal;//절대경로쓴경우
			//System.out.println(filePath);
			String filePath2 = "../img/product/" + fileVal;//상대경로쓴경우
			
			//File file = new File(filePath);
			File file2 = new File(filePath2);
			
			//JDBC  컬럼 - 원본파일명, 시스템파일명, 파일사이즈, 확장자
			
			BoardVO bo = new BoardVO();
			bo.setTitle(titleVal);
			bo.setContents(contentVal);
			bo.setSysFname(fileVal);
			bo.setOrigFname(oriVal);
			bo.setUserid(userid);
			
			int r = b.serviceFuncInsert(bo);
			if(r<=0){
				System.out.println("실패");
			}else{
				System.out.println("성공");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("location.href='/boardlist?currentPage=1'"); 
				out.println("</script>"); 
				out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}	
	@RequestMapping(value="/boardlist")
	public ModelAndView boardlist(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		//현재페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int totalCnt=0;
		ArrayList<BoardVO> bList;
		PagingUtil page;
		String pagingHtml;
		int blockCount=4;
		int blockPage=5;
		HttpSession session = request.getSession();
		UserVO vo = null;
		try{
			vo = (UserVO)session.getAttribute("USER");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			String column = request.getParameter("search_param");
			if(column.equals("title")){
				column = "title";
			}else if(column.equals("id")){
				column = "users_id";
			}
			String key = request.getParameter("sk");
			System.out.println(column+","+key);
			totalCnt = b.serviceFuncSearchCount(column, key);
			page = new PagingUtil("boardlist?search_param="+column+"&sk="+key,currentPage,totalCnt,blockCount,blockPage);
			pagingHtml = page.getPagingHtml().toString();
			bList = b.serviceBoardSearch(column, key, page.getStartSeq(), page.getEndSeq());
		}catch(Exception e){
			totalCnt = b.serviceFuncListCount();
			page = new PagingUtil("boardlist?",currentPage,totalCnt,blockCount,blockPage);
			pagingHtml = page.getPagingHtml().toString();
			bList = b.serviceBoardSelect(page.getStartSeq(), page.getEndSeq());
		}
		
		mav.addObject("TOTAL_CNT", totalCnt);
		mav.addObject("BOARD_PAGING", pagingHtml);
		mav.addObject("BOARD_LIST", bList);
		mav.setViewName("pro/board_list");
		/*
		if(vo==null){
			//비로그인회원
			mav.setViewName("pro/board_list");
		}else{
			if(vo.getAccount().equals("admin")){
				//관리자
				mav.setViewName("pro/board_list");
			}else{
				//관리자 외의 유저
				mav.setViewName("pro/board_list");
			}
		}
		*/
		return mav;
		
	}
	
	@RequestMapping(value="/boarddetail")
	public String productdetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int bseq = Integer.parseInt(request.getParameter("bseq"));
		
		BoardVO bo = new BoardVO();
		bo.setBseq(bseq);
		int rr = b.serviceFuncHitsUpdate(bo);
		bo= b.serviceFuncSelect(bseq);
		//상품정보를 session에 담아서 넘겨줌
		session.setAttribute("BOARD_DETAIL", bo);
		return "pro/board_detail";
	}
	@RequestMapping(value="/replyinsert")
	public ModelAndView replyinsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");
		int bseq = Integer.parseInt(request.getParameter("bseq"));
		String reply = request.getParameter("replyy");
//		BoardVO bo = new BoardVO();
//		bo= b.serviceFuncSelect(bseq);
		BoardReplyVO bro = new BoardReplyVO();
		bro.setBseq(bseq);
		bro.setReply(reply);
		System.out.println(vo.getId());
		bro.setUserid(vo.getId());
		int r =b.serviceFuncReplyInsert(bro);
		ModelAndView mav = new ModelAndView();

		mav.setView(new RedirectView("/boarddetail?bseq="+bseq));
		return mav;
	}
	@RequestMapping(value="/boardfiledown")
	public void filedown(HttpServletRequest request, HttpServletResponse response) {

		int bseq = Integer.parseInt(request.getParameter("bseq"));
		String filename = request.getParameter("sname");
		String oname = request.getParameter("oname");
		System.out.println(bseq);
		System.out.println(filename);
		System.out.println(oname);
		
		String uploadDir = request.getSession().getServletContext().getRealPath("/img/product");
		String filePath = uploadDir + "/" + filename;
		System.out.println(uploadDir);
		System.out.println(filePath);
		//MIME type of the file  브라우저에서 출력
		//ServletContext ctx = this.getServletContext();
		
		String mimeType = ctx.getMimeType(filePath);
	
		if (mimeType == null) {        
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		try{
		//modifies HTTP header response
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", oname);
		response.setHeader(headerKey, headerValue);

		
		//send http response using output stream
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];//4kb씩 끊어서보냄
		int bytesRead = -1;
		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inStream.close();
		outStream.close();  
	
		

		
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	@RequestMapping(value="/boardupdate")
	public String productupdate(HttpServletRequest request, HttpServletResponse response) {
		int bseq = Integer.parseInt(request.getParameter("bseq"));
		BoardVO po = new BoardVO();
		po = b.serviceFuncSelect(bseq);
		HttpSession session =request.getSession();
		session.setAttribute("BOARD_UPDATE", po);
		return "pro/board_update";
	}	
	@RequestMapping(value="/boardupdatecomplete")
	public void boardupdate(HttpServletRequest request, HttpServletResponse response) {
		int bseq = Integer.parseInt(request.getParameter("bseq"));
		int maxSize = 10 * 1024 * 1024;
		//저장될 이미지 절대 경로
		//String uploadDir2 = "C:/Users/kosmo_21/Desktop/workspaceJava/EBSpring/src/main/webapp/img/product";
		String uploadDir2 = request.getSession().getServletContext().getRealPath("/img/product");
		try{
			HttpSession session = request.getSession();
			String format = "UTF-8";
			MultipartRequest mRequest = new MultipartRequest(request, uploadDir2, maxSize, format, new DefaultFileRenamePolicy());
			//renamepolicy - 같은이름오면 rename해줌
			
			String titleVal = mRequest.getParameter("title");
			String contentVal = mRequest.getParameter("contents");
			String fileVal = mRequest.getFilesystemName("file");//맨마지막경로를 가져옴
			String oriVal = mRequest.getOriginalFileName("file");
	
			//String filePath2 = "..\\"+uploadDir2 + "\\" + ufileVal;//상대경로쓴경우
			//String filePath2 = uploadDir2 + "/" + ufileVal;//절대경로쓴경우
			//System.out.println(filePath);
			String filePath2 = "../img/product/" + fileVal;//상대경로쓴경우
			
			//File file = new File(filePath);
			File file2 = new File(filePath2);
			
			//JDBC  컬럼 - 원본파일명, 시스템파일명, 파일사이즈, 확장자
			
			BoardVO bo = new BoardVO();
			bo.setTitle(titleVal);
			bo.setContents(contentVal);
			bo.setSysFname(fileVal);
			bo.setOrigFname(oriVal);
			bo.setBseq(bseq);
			int r = b.serviceFuncUpdate(bo);
			if(r<=0){
				System.out.println("실패");
			}else{
				System.out.println("성공");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("location.href='/boardlist?currentPage=1'"); 
				out.println("</script>"); 
				out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	@RequestMapping(value="/boarddelete")
	public void productdelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seqVal = Integer.parseInt(request.getParameter("bseq"));
		System.out.println(seqVal);
		
		int r = b.serviceFuncDelete(seqVal);
		if(r<=1){
			System.out.println("실패");
		}else{
			System.out.println("성공");
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");

		out.println("location.href='/boardlist?currentPage=1'"); 
		out.println("</script>");
		out.close();
	}	
	@RequestMapping(value="/replydelete")
	public void replydelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seqVal = Integer.parseInt(request.getParameter("rseq"));
		int bseq= Integer.parseInt(request.getParameter("bseq"));
		System.out.println(seqVal+"리플번호");
		System.out.println(bseq+"글번호");
		BoardReplyVO brv = new BoardReplyVO();
		brv.setRseq(seqVal);
		int r = b.serviceFuncReplyDelete(brv);

		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");

		out.println("location.href='/boarddetail?bseq="+bseq+"'");
		out.println("</script>");
		out.close();
	}
	
	@RequestMapping(value="/replyupdate")
	public @ResponseBody String replyupdate(HttpServletRequest request, HttpServletResponse response) {
		String seq = request.getParameter("rseq");
		int seqVal = Integer.parseInt(seq.substring(4));
		System.out.println(seqVal);
		String reply= request.getParameter("con");
		BoardReplyVO brv = new BoardReplyVO();
		brv.setRseq(seqVal);
		brv.setReply(reply);
		System.out.println(seqVal);
		System.out.println(reply);
		
		b.serviceFuncReplyUpdate(brv);
		
		brv = b.serviceFuncReplySelect(seqVal);
		System.out.println("내용"+brv.getReply());
		System.out.println("날짜 찍히냐구요"+brv.getRegDate());
		
		return brv.getRegDate();
	}		
}
