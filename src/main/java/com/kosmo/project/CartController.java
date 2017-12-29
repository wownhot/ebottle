package com.kosmo.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CartController{
	int users_Seq;
	
	@Autowired
	private CartService c;
	@Autowired
	private ProductService p;

    //장바구니 목록
	@RequestMapping(value="/cartlist")
	public ModelAndView cartlist(HttpServletRequest request, HttpServletResponse response){
		//cart 페이징
		HttpSession session = request.getSession();
		UserVO u = (UserVO)session.getAttribute("USER");
		users_Seq = u.getSeq();
		//현재 페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//전체 게시물 수 가져오기
		int totalCnt = c.serviceFuncListCount(users_Seq);
		//페이징
		int blockCount = 5;   //한 블럭의 게시물 수
		int blockPage = 5;    //한화면에 보여질 블럭 수
		PagingUtil page  = new PagingUtil("/cartlist?", currentPage, totalCnt, blockCount, blockPage);
		String pagingHtml =page.getPagingHtml().toString();
		//장바구니 리스트 저장
		ArrayList<CartVO> list = c.serviceFuncList(users_Seq, page.getStartSeq(), page.getEndSeq());
		ModelAndView mav = new ModelAndView();
		mav.addObject("TOTAL_CNT", totalCnt);
		mav.addObject("PAGING",  pagingHtml);
		mav.addObject("ALL_LIST", list);
		mav.setViewName("pro/cart");
		return mav;
	}
	
	//장바구니 삭제
	@RequestMapping(value="/cartdelete")
	public ModelAndView cartdelete(HttpServletRequest request, HttpServletResponse response){
		//선택한 장바구니 목록들 번호
		String[] cSeq = request.getParameterValues("cSeq");
		//해당상품 재고목록 갱신, 장바구니 목록에서 삭제
		for (int i = 0; i < cSeq.length; i++) {
			//ProductVO pvo = c.proSelect(Integer.parseInt(cSeq[i]));
			ProductVO pvo = p.serviceFuncSelect(c.serviceFuncSelect(Integer.parseInt(cSeq[i])).getProduct_seq());
			//pvo = p.productSelect(pvo.getPseq());
			pvo.setStock(pvo.getStock()+1);
			p.serviceFuncUpdate(pvo);
			c.serviceFuncDelete(Integer.parseInt(cSeq[i]));
		}
		ModelAndView mav = new ModelAndView();
		//컨트롤러에서 컨트롤러로 넘어가야하기 때문에 RedirectView 사용
		mav.setView(new RedirectView("cartlist?currentPage=1"));
		return mav;
	}
	
	//장바구니 추가
	@RequestMapping(value="/cartinsert")
	public void cartinsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//상품번호
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		//유저정보
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("USER");
		int useq = vo.getSeq();
		CartVO cvo = new CartVO();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		cvo.setProduct_seq(pseq);
		cvo.setUsers_seq(useq);
		int r = c.serviceFuncInsert(cvo);

		out.println("location.href='/productdetail?pseq="+pseq+"'");
		out.println("</script>"); 
		out.close();
	}
	
	//장바구니 수정
	@RequestMapping(value="/cartUpdate")
	public ModelAndView cartUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		int i = Integer.parseInt(request.getParameter("index"));
		System.out.println("index="+i);
		String cSeqStr = request.getParameter("cSeqForCartUpdate"+i);
		String cQuantityStr = request.getParameter("cQuantity"+i);
		CartVO cvo = new CartVO();
		cvo.setCart_seq(Integer.parseInt(cSeqStr));  
		cvo.setCart_quantity(Integer.parseInt(cQuantityStr));
		c.serviceFuncUpdate(cvo);
		mav.setView(new RedirectView("/cartlist?currentPage=1"));
		return mav;
	}

}
