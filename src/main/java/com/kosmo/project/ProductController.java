package com.kosmo.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class ProductController {
	@Autowired
	private ProductService p;
	@Autowired
	private GradeService g;

	// 상품목록
	@RequestMapping(value = "/productlist")
	public ModelAndView productlist(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		UserVO vo = null;
		try {
			vo = (UserVO) session.getAttribute("USER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 현재페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String add = request.getParameter("add");
		int blockCount = 9;
		int blockPage = 5;
		int totalCnt = 0;
		ArrayList<ProductVO> pList = new ArrayList<ProductVO>();
		String pagingHtml = "";
		PagingUtil page;
		try {
			String column = request.getParameter("search_param");
			if (column.equals("title")) {
				column = "product_name";
			} else if (column.equals("content")) {
				column = "product_desc";
			}
			String key = request.getParameter("sk");
			totalCnt = p.serviceFuncSearchCount(column, key);
			page = new PagingUtil("productlist?search_param=" + column + "&sk=" + key, currentPage, totalCnt,
					blockCount, blockPage);
			pagingHtml = page.getPagingHtml().toString();
			pList = p.serviceFuncSearch(column, key, page.getStartSeq(), page.getEndSeq());
		} catch (Exception e) {
			totalCnt = p.serviceFuncListCount();
			page = new PagingUtil("productlist?", currentPage, totalCnt, blockCount, blockPage);
			pagingHtml = page.getPagingHtml().toString();
			pList = p.serviceFuncList(page.getStartSeq(), page.getEndSeq());
		}
		mav.addObject("TOTAL_CNT", totalCnt);
		mav.addObject("ADD", add);
		System.out.println(add+"add");
		mav.addObject("PRODUCT_PAGING", pagingHtml);
		mav.addObject("PRODUCT_LIST", pList);
		if (vo == null) {
			// 비로그인회원
			mav.setViewName("pro/product_list_user");
		} else {
			if (vo.getAccount().equals("admin")) {
				// 관리자
				mav.setViewName("pro/product_list");
			} else {
				// 관리자 외의 유저
				mav.setViewName("pro/product_list_user");
			}
		}
		return mav;

	}

	// 상품추가
	@RequestMapping(value = "/productadd")
	public ModelAndView productadd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setView(new RedirectView("/productlist?currentPage=1"));
		int maxSize = 10 * 1024 * 1024;
		// 저장될 이미지 절대 경로
		// String uploadDir2 =
		// "C:/Users/kosmo_21/Desktop/workspaceJava/EBSpring/src/main/webapp/img/product";
		String uploadDir2 = request.getSession().getServletContext().getRealPath("/img/product");
		System.out.println(uploadDir2);
		try {
			String format = "UTF-8";
			MultipartRequest mRequest = new MultipartRequest(request, uploadDir2, maxSize, format,
					new DefaultFileRenamePolicy());
			// renamepolicy - 같은이름오면 rename해줌

			String nameVal = mRequest.getParameter("pname");
			int priceVal = Integer.parseInt(mRequest.getParameter("price"));
			int stockVal = Integer.parseInt(mRequest.getParameter("stock"));
			String descVal = mRequest.getParameter("desc");
			String ufileVal = mRequest.getFilesystemName("img");// 맨마지막경로를 가져옴

			// String filePath2 = "..\\"+uploadDir2 + "\\" + ufileVal;//상대경로쓴경우
			// String filePath2 = uploadDir2 + "/" + ufileVal;//절대경로쓴경우
			// System.out.println(filePath);
			String filePath2 = "../img/product/" + ufileVal;// 상대경로쓴경우

			// File file = new File(filePath);
			File file2 = new File(filePath2);

			// JDBC 컬럼 - 원본파일명, 시스템파일명, 파일사이즈, 확장자

			// 저장될 상품정보
			ProductVO po = new ProductVO();
			po.setPname(nameVal);
			po.setPrice(priceVal);
			po.setStock(stockVal);
			po.setUrl(filePath2);
			po.setP_uname(ufileVal);
			po.setDesc(descVal);

			int r = p.serviceFuncInsert(po);
			if (r <= 0) {
				System.out.println("실패");
			} else {
				System.out.println("성공");

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return mav;
		}
	}

	@RequestMapping(value = "/product_addAll")
	public void productExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductVO po = new ProductVO();
		
		// xlsx
		int count=0;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		try {
			// FileInputStream fis = new
			// FileInputStream("C:\\Users\\sehyu\\Desktop\\abc.xlsx");
			String path = request.getSession().getServletContext().getRealPath("/excel")+"/abc.xlsx";
			
			FileInputStream fis = new FileInputStream(path);
			 
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int rowindex = 0;
			int columnindex = 0;
			// 시트 수 (첫번째에만 존재하므로 0을 준다)
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// 행의 수
			int rows = sheet.getPhysicalNumberOfRows();
			

			
			// 행을읽는다
			XSSFRow row = sheet.getRow(rowindex);
			
			if (row != null) {
				// 셀의 수
				
				int cells = row.getPhysicalNumberOfCells();

				// 셀값을 읽는다
				XSSFCell cell = row.getCell(columnindex);
				String value = "";
				
				// 셀이 빈값일경우를 위한 널체크
				if (cell == null) {
					System.out.println("셀이 null임");

				} else {
					
					for (int rownum = 0; rownum < rows; rownum++) {
						XSSFRow xlsxRow = sheet.getRow(rownum); // 셀정보

						String pnameVal = xlsxRow.getCell(0).toString();
						int priceVal = (int) Double.parseDouble(xlsxRow.getCell(1).toString().trim());
						String descVal = xlsxRow.getCell(2).toString();
						String urlVal = xlsxRow.getCell(3).toString();
						int stockVal = (int) Double.parseDouble(xlsxRow.getCell(4).toString().trim());
						String unameVal = xlsxRow.getCell(5).toString();
						System.out.println(priceVal + pnameVal + descVal + urlVal + stockVal + unameVal);

						po.setPname(pnameVal);
						po.setPrice(priceVal);
						po.setDesc(descVal);
						po.setUrl(urlVal);
						po.setStock(stockVal);
						po.setP_uname(unameVal);

						int r = p.serviceFuncInsert(po);
						if (r <= 0) {
							System.out.println("실패");
						} else {
							System.out.println("성공");
							count++;
							
						}
					}
					

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.println("alert('상품"+count+"개 업로드 성공.');");
			out.println("location.href='/productlist?currentPage=1'");
			out.println("</script>");
			out.close();
		}
	}

	// 상품 상세정보
	@RequestMapping(value = "/productdetail")
	public String productdetail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		double rate = 1;
		try {
			UserVO uvo = (UserVO) session.getAttribute("USER");
			rate = 1 - ((double) g.serviceFuncSelectAccount(uvo) / 100);
		} catch (Exception e) {
		}
		ProductVO po = new ProductVO();
		po = p.serviceFuncSelect(pseq);
		// 상품정보를 session에 담아서 넘겨줌
		session.setAttribute("PRODUCT_DETAIL", po);
		session.setAttribute("DISCOUNT", rate);
		return "pro/product_Detail";
	}

	// 상품수정 페이지로갈때
	@RequestMapping(value = "/productupdate")
	public String productupdate(HttpServletRequest request, HttpServletResponse response) {
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		ProductVO po = new ProductVO();
		po = p.serviceFuncSelect(pseq);
		HttpSession session = request.getSession();
		session.setAttribute("PRODUCT_UPDATE", po);
		return "pro/product_update";
	}

	// 상품삭제
	@RequestMapping(value = "/productdelete")
	public void productdelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int seqVal = Integer.parseInt(request.getParameter("pseq"));

		int r = p.serviceFuncDelete(seqVal);
		if (r <= 1) {
			System.out.println("실패");
		} else {
			System.out.println("성공");
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");
		out.println("location.href='/productlist?currentPage=1'");
		out.println("</script>");
		out.close();
	}

	// 상품을 수정했을 때
	@RequestMapping(value = "/productmodify")
	public void productmodify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		System.out.println(pseq);
		ProductVO po = new ProductVO();
		String nameVal = request.getParameter("pname");
		int priceVal = Integer.parseInt(request.getParameter("price"));
		int stockVal = Integer.parseInt(request.getParameter("stock"));
		String descVal = request.getParameter("desc");
		po.setDesc(descVal);
		po.setPname(nameVal);
		po.setPrice(priceVal);
		po.setStock(stockVal);
		po.setPseq(pseq);
		int r = p.serviceFuncUpdate(po);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>");

		out.println("location.href='/productlist?currentPage=1'");
		out.println("</script>");
		out.close();
	}
	
	//안드로이드
	@RequestMapping(value="/androidproductlist")
	public @ResponseBody JSONObject androidproductlist(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		request.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
		ArrayList<ProductVO> list = p.serviceFuncList();
		for(int i=0;i<list.size();i++){
			ProductVO pvo = list.get(i);
			JSONObject row = new JSONObject();
			row.put("product_seq", pvo.getPseq());
			row.put("product_name",pvo.getPname());
			row.put("product_price", pvo.getPrice());
			row.put("product_url", pvo.getUrl());
			row.put("product_stock", pvo.getStock());
			row.put("product_dsec", pvo.getDesc());
			row.put("product_uname", pvo.getP_uname());
			jarray.add(i,row);
		}
		json.put("sendData", jarray);
		return json;
	}
	
	// 안드로이드상품추가
		@RequestMapping(value = "/androidproductadd")
		public @ResponseBody JSONObject androidproductadd(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
			request.setCharacterEncoding("utf-8");
			int maxSize = 10 * 1024 * 1024;
			JSONObject json = new JSONObject();
			// 저장될 이미지 절대 경로
			// String uploadDir2 =
			// "C:/Users/kosmo_21/Desktop/workspaceJava/EBSpring/src/main/webapp/img/product";
			String uploadDir2 = request.getSession().getServletContext().getRealPath("/img/product");
			try {
				String format = "UTF-8";
				MultipartRequest mRequest = new MultipartRequest(request, uploadDir2, maxSize, format,
						new DefaultFileRenamePolicy());
				// renamepolicy - 같은이름오면 rename해줌

				String nameVal = mRequest.getParameter("pName");
				int priceVal = Integer.parseInt(mRequest.getParameter("pPrice"));
				int stockVal = Integer.parseInt(mRequest.getParameter("pStock"));
				String descVal = mRequest.getParameter("pDesc");
				String ufileVal = mRequest.getFilesystemName("pImg");// 맨마지막경로를 가져옴
				System.out.println(descVal);
				// String filePath2 = "..\\"+uploadDir2 + "\\" + ufileVal;//상대경로쓴경우
				// String filePath2 = uploadDir2 + "/" + ufileVal;//절대경로쓴경우
				// System.out.println(filePath);
				String filePath2 = "../img/product/" + ufileVal;// 상대경로쓴경우

				// File file = new File(filePath);
				File file2 = new File(filePath2);

				// JDBC 컬럼 - 원본파일명, 시스템파일명, 파일사이즈, 확장자

				// 저장될 상품정보
				ProductVO po = new ProductVO();
				po.setPname(nameVal);
				po.setPrice(priceVal);
				po.setStock(stockVal);
				po.setUrl(filePath2);
				po.setP_uname(ufileVal);
				po.setDesc(descVal);

				int r = p.serviceFuncInsert(po);
				if (r <= 0) {
					System.out.println("실패");
					json.put("add", "fail");
				} else {
					System.out.println("성공");
					json.put("add", "success");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.put("add", "fail");
			} finally {
				return json;
			}
		}
}
