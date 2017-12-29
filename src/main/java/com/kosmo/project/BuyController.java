package com.kosmo.project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

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
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

@Controller
public class BuyController{
       
	/*private Buy b;
	private Point pt;
	private Product p;
	private Cart c;
	*/
	@Autowired
	private BuyService b;
	@Autowired
	private PointService pt;
	@Autowired
	private ProductService p;
	@Autowired
	private CartService c;
	@Autowired
	private GiftService gift;
	@Autowired
	private GradeService g;
    //구매목록
	@RequestMapping(value="/buylist")
	public ModelAndView buylist(HttpServletRequest request, HttpServletResponse response){
		//현재 페이지 가져옴
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//세션에서 유저 정보 가져옴
		HttpSession session = request.getSession();
		UserVO u = (UserVO)session.getAttribute("USER");
		//유저의 구매목록 리스트 카운트 저장
		int totalCnt = b.serviceFuncListCount(u.getSeq());
		
		int blockCount=5;
		int blockPage=5;
		//페이징
		PagingUtil page = new PagingUtil("buylist?", currentPage,totalCnt,blockCount,blockPage);
		String pagingHtml = page.getPagingHtml().toString();
		//해당 페이지 구간 구매목록
		ArrayList<BuyVO> pList = b.serviceFuncList(u.getSeq(),page.getStartSeq(), page.getEndSeq());
		//ModelAndView로 리스트 페이지에 넘길 정보들 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("PAGING", pagingHtml);
		mav.addObject("TOTAL_CNT", totalCnt);
		mav.addObject("BUY_LIST", pList);
		mav.setViewName("pro/buy_list");
		return mav;
	}
	
	//상품구매
	@RequestMapping(value="/buyproduct")
	public void buyproduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//유저 정보
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO vo=(UserVO)session.getAttribute("USER");
		int useq1 = vo.getSeq();
		String uid =vo.getId();
		//상품이름, 가격, 번호
		String pname1 = request.getParameter("pname");
		String purl = request.getParameter("purl");
		int price1 =(Integer.parseInt(request.getParameter("price")));
		int pseq1 = (Integer.parseInt(request.getParameter("pseq")));
		//바코드생성
		String timestamp = String.valueOf(Calendar.getInstance().getTime().getTime());
		System.out.println(timestamp);
		Barcode bar = BarcodeFactory.createCode128(timestamp);
		String uploaddir = request.getSession().getServletContext().getRealPath("/img/gift")+"/";
		File file = new File(uploaddir + timestamp + ".jpg");
	
		BarcodeImageHandler.saveJPEG(bar, file);
		System.out.println("바코드생성완?");

		
		//포인트 정보
		PointVO pto = new PointVO();
		//유저 포인트
		int sum= pt.serviceFuncgetPoint(vo.getId());
		//해당 상품 정보
		ProductVO po = new ProductVO();
		po = p.serviceFuncSelect(pseq1);
		
		//한글처리
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");

		if(po.getStock()<1){
			//상품 재고가 부족할 때
			out.println("alert('재고가 부족합니다.');");
			out.println("history.back()");

		}else if(sum>=price1){
			//유저포인트로 구매가능한 가격일 때
			//포인트 기록
			pto.setPval(-price1);
			pto.setUid(vo.getId());
			pt.serviceFuncInsert(pto);
			
			session.setAttribute("POINT", pto);
			session.setAttribute("PRODUCT_DETAIL", po);
			//구매목록 정보
			BuyVO bo = new BuyVO();
			bo.setProduct_img(po.getUrl());
			bo.setBarcode(timestamp);
			//bo.setBarcode_img(gvo.getBarcodeimg());
			bo.setBarcode_img("../img/gift/"+timestamp+".jpg");
			bo.setPname(pname1);
			bo.setPrice(price1); 
			bo.setUseq(useq1);
			bo.setPseq(pseq1);
			//상품 정보 갱신
			po.setStock(po.getStock()-1);
			p.serviceFuncUpdate(po);
			int res = b.serviceFuncInsert(bo);
			out.println("location.href='/productdetail?pseq="+pseq1+"'");
		}else if(sum<price1){
			//유저포인트가 부족할 때
			out.println("alert('포인트가 부족합니다.');");
			out.println("location.href='/productlist?currentPage=1'"); 
		}
		out.println("</script>"); 
		out.close();
	}
	
	//장바구니에서 상품구매
	@RequestMapping(value="/buycart")
	public void buycart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//유저 정보
		Boolean sf=true;
		HttpSession session = request.getSession();
		UserVO uvo = (UserVO)session.getAttribute("USER");
		
		//장바구니에서 선택한 장바구니 목록들
		String[] cSeq = request.getParameterValues("cSeq");
	
		CartVO co = new CartVO();
		ProductVO pvo = new ProductVO();
		
		ArrayList<ProductVO> plist = new ArrayList<ProductVO>();
		
		
		/*RatingSale rs = new RatingSale();
		double sale = rs.getDiscount(uvo.getAccount());
		*/
		int rate = g.serviceFuncSelectAccount(uvo);
		double sale = 1-((double)rate/100);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		int result = pt.serviceFuncgetPoint(uvo.getId());
		//장바구니 번호로 상품리스트 저장
		try{
		for (int i = 0; i < cSeq.length; i++) {
			//ProductVO pvo = c.proSelect(Integer.parseInt(cSeq[i]));
			//ProductVO pvo = p.serviceFuncSelect(c.serviceFuncSelect(Integer.parseInt(cSeq[i])).getCart_seq());
			co = c.serviceFuncSelect(Integer.parseInt(cSeq[i]));
			int pnum = co.getProduct_seq();
			for(int j=0;j<co.getCart_quantity();j++){
				pvo=p.serviceFuncSelect(pnum);	
				//plist.add(pvo);
				
				if(result>=(int)(pvo.getPrice()*sale)&&pvo.getStock()>0){
					BuyVO bvo = new BuyVO();
					PointVO pointvo = new PointVO();
					pvo = p.serviceFuncSelect(pvo.getPseq());
					//c.serviceFuncDelete(Integer.parseInt(cSeq[i]));
					pointvo.setUid(uvo.getId());
					pointvo.setPval(-(int)(pvo.getPrice()*sale));
					pt.serviceFuncInsert(pointvo);
					result-=(int)(pvo.getPrice()*sale);
					
					String timestamp = String.valueOf(Calendar.getInstance().getTime().getTime());
					Barcode bar = BarcodeFactory.createCode128(timestamp);
					String uploaddir = request.getSession().getServletContext().getRealPath("/img/gift")+"/";
					File file = new File(uploaddir + timestamp + ".jpg");
					BarcodeImageHandler.saveJPEG(bar, file);
					
					bvo.setPname(pvo.getPname());
					bvo.setPrice((int)(pvo.getPrice()*sale));
					bvo.setPseq(pvo.getPseq());
					bvo.setUseq(uvo.getSeq());
					bvo.setProduct_img(pvo.getUrl());
					bvo.setBarcode(timestamp);
					bvo.setBarcode_img("../img/gift/"+timestamp+".jpg");
					b.serviceFuncInsert(bvo);
					
					pvo.setStock(pvo.getStock()-1);
					p.serviceFuncUpdate(pvo);
					
				}else{
					sf=false;
					throw new Exception();
				}
			}
		}
		}catch(Exception e){
			sf=false;
			throw new Exception();
		}finally{
			if(!sf){

			}
			else{
				for (int i = 0; i < cSeq.length; i++) {
					c.serviceFuncDelete(Integer.parseInt(cSeq[i]));
				}
		
			}
			out.println("location.href='/cartlist?currentPage=1'"); 
			out.println("</script>"); 
			out.close();
		}
		
	}
	
	@RequestMapping(value="/buypage")
	public ModelAndView buypage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String barcode = request.getParameter("barcode");
		ModelAndView mav = new ModelAndView();
		BuyVO vo = new BuyVO();
		vo = b.serviceFuncSelect(barcode);
		mav.addObject("buyvo", vo);
		mav.setViewName("/pro/buypage");
		return mav;
	}
}