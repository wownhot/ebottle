package com.kosmo.project;

public class PagingUtil {

	private int startSeq;				// 현재 페이지 처음 글번호
	private int endSeq;					// 현재 페이지 끝 글번호
	private int maxPage;				// 최대 페이지 수
	private int startPage;  			// 페이지 시작번호
	private int endPage;				// 페이지 끝번호
	private StringBuffer pagingHtml; 	// 페이징 관련 HTML

	/**
	 * @param url 			: 페이징 적용 대상 주소  (예) /board/BoardServlet
	 * @param currentPage 	: 현재 페이지
	 * @param totRecord 	: 젠체 게시물수
	 * @param blockCount 	: 한 블럭의 게시물 수
	 * @param blockPage  	: 한화면에 보여질 블럭 수
	 **/
	public PagingUtil(String url, int currentPage, int totRecord, int blockCount, int blockPage) {
		// (1) 최대 페이지 수 구하기 (Math.ceil 올림)
		maxPage = (int) Math.ceil((double) totRecord / blockCount);
		if (maxPage == 0) {
			maxPage = 1;
		}

		// 예외처리 : 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > maxPage) {
			currentPage = maxPage;
		}

		// (2) 현재 페이지의 처음과  끝 글번호 가져오기
		startSeq = (currentPage - 1) * blockCount + 1;
		endSeq = currentPage * blockCount;

		// (3) 시작페이지와 끝페이지 값 구하기  
		if ( currentPage % blockPage == 0 ) {
			startPage = currentPage - ( blockPage - 1 );
		} else {
			startPage = (int)(currentPage / blockPage) * blockPage +1;
		}
		endPage = startPage + blockPage - 1;

		// 예외처리 : 마지막페이지가 전체페이지수보다 크면 전체페이지 수로 설정하기
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		//################## HTML 만들기 ###################
		// [이전] HTML
		pagingHtml = new StringBuffer();
		if (currentPage > blockPage) {
			pagingHtml.append("<li><a href='"+ url +"currentPage="  + (startPage - 1) + "'>");
			pagingHtml.append("<");
			pagingHtml.append("</a></li>");
		}

		//pagingHtml.append(" | ");
		// |1|2|3|4|5|  HTML (현재 페이지는 빨간색으로 강조하고 링크 제거)
		for (int i = startPage; i <= endPage; i++) {
			if (i > maxPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("<li><a href='" + url +"&currentPage=");
				pagingHtml.append(i);
				pagingHtml.append("'>");
				pagingHtml.append(i);
				pagingHtml.append("</a></li>");
			} else {
				pagingHtml.append("<li><a href='" + url +"&currentPage=");
				pagingHtml.append(i);
				pagingHtml.append("'>");
				pagingHtml.append(i);
				pagingHtml.append("</a></li>");
			}

			pagingHtml.append(" ");
		}
		//pagingHtml.append("  |  ");


		// [다음] HTML
		if (maxPage - startPage >= blockPage) {
			pagingHtml.append("<li><a href='" + url +"currentPage="  + (endPage + 1) + "'>");
			pagingHtml.append(">");
			pagingHtml.append("</a></li>");
		}
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public int getStartSeq() {
		return this.startSeq;
	}

	public int getEndSeq() {
		return this.endSeq;
	}



}
