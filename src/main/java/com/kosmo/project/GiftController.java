package com.kosmo.project;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;


@Controller
public class GiftController {
	
	@Autowired
	private GiftService gift;
	@Autowired
	private ProductService pro;
	@Autowired
	private PointService point;
	@Autowired
	private BuyService buy;
 
	@RequestMapping(value="/giftlist")
	public ModelAndView giftlist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		UserVO u = (UserVO)session.getAttribute("USER");
		String users_id = u.getId();
		//현재 페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		//전체 게시물 수 가져오기
		int totalCnt = gift.serviceFuncListCount(users_id);
		//페이징
		int blockCount = 5;   //한 블럭의 게시물 수
		int blockPage = 5;    //한화면에 보여질 블럭 수
		PagingUtil page  = new PagingUtil("/giftlist?", currentPage, totalCnt, blockCount, blockPage);
		String pagingHtml =page.getPagingHtml().toString();
		//장바구니 리스트 저장
		ArrayList<GiftVO> list = gift.serviceFuncSelect(page.getStartSeq(), page.getEndSeq(), users_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("TOTAL_CNT", totalCnt);
		mav.addObject("PAGING",  pagingHtml);
		mav.addObject("LIST", list);
		mav.setViewName("pro/gift_list");
		return mav;
	}
	
	@RequestMapping(value="/kakaogift")
	public @ResponseBody String kakaogift(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String barcode = request.getParameter("barcode");
		int pseq = Integer.parseInt(request.getParameter("ps"));
		ProductVO pvo = pro.serviceFuncSelect(pseq);
		String usersid = request.getParameter("usersid");
		int price = Integer.parseInt(request.getParameter("price"));
		
		int sum = point.serviceFuncgetPoint(usersid);
		if(sum>=price&&pvo.getStock()>0){
			String uploaddir = request.getSession().getServletContext().getRealPath("/img/gift")+"/";
			Barcode bar = BarcodeFactory.createCode128(barcode);
			File file = new File(uploaddir + barcode + ".jpg");
			GiftVO vo = new GiftVO();
			vo.setBarcode(barcode);
			vo.setBarcodeimg("../img/gift/"+barcode+".jpg");
			vo.setProductimg(pvo.getUrl());
			vo.setUsersid(usersid);
			vo.setProductname(pvo.getPname());
			vo.setProductprice(price);
			gift.serviceFuncInsert(vo);
			BarcodeImageHandler.saveJPEG(bar, file);
			
			PointVO pointvo = new PointVO();
			pointvo.setPval(-price);
			pointvo.setUid(usersid);
			point.serviceFuncInsert(pointvo);
			
			pvo.setStock(pvo.getStock()-1);
			pro.serviceFuncUpdate(pvo);
		}else if(pvo.getStock()<1){
			barcode="-1";
		}else {
			barcode="0";
		}
		return barcode;
	}
	
	@RequestMapping(value="/giftpage")
	public ModelAndView giftpage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String barcode = request.getParameter("barcode");
		ModelAndView mav = new ModelAndView();
		GiftVO vo = new GiftVO();
		vo.setBarcode(barcode); 
		vo = gift.serviceFuncSelect(vo);
		mav.addObject("giftvo", vo);
		mav.setViewName("/pro/giftpage");
		return mav;
	}
	
	@RequestMapping(value="/giftuse")
	public @ResponseBody Map<String, String> giftuse(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String barcode = request.getParameter("barcode");
		GiftVO vo = new GiftVO();
		vo.setBarcode(barcode);
		vo = gift.serviceFuncSelect(vo);
		Map<String, String> result = new HashMap<String, String>();
		String url = request.getRequestURL().toString().replace(request.getRequestURI(),"");
		if(vo==null){
			BuyVO bvo = new BuyVO();
			bvo = buy.serviceFuncSelect(barcode);
			if(bvo.getUsed().equals("false")){
				result.put("sendData", bvo.getPname()+" "+bvo.getPrice());
				result.put("url", url+bvo.getProduct_img().replace("..", ""));
				
				bvo.setUsed("true");
				buy.serviceFuncUpdate(bvo);
			}else{
				result.put("sendData", "no");
			}
		}else{	
			if(vo.getUsing().equals("false")){
				result.put("sendData", vo.getProductname()+" "+vo.getProductprice());
				result.put("url", url+vo.getProductimg().replace("..", ""));
				vo.setUsing("true");
				gift.serviceFuncUpdate(vo);
			}else{
				result.put("sendData", "no");
			}
		}
		
		return result;
	}
}
