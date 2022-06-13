package com.poseidon.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardCotroller {

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	
	//상세보기 화면 /detail?b_no=154
	@GetMapping(value = "/detail")
	public ModelAndView detail(@RequestParam("b_no") int b_no)  { // HttpServletRequest , BoardDTO dto , 총 세가지 방법이 있음
		ModelAndView mv = new ModelAndView("detail"); //jsp
//		System.out.println("들어오는 b_no : " + b_no );
		//DB로 보내서 값이 있는지 확인하기
		BoardDTO dto = boardService.detail(b_no);
		mv.addObject("detail", dto); // 붙이기
//		System.out.println(dto);
		return mv;
	}

	//글쓰기 화면 나오게 하기
	//화면만 이동하려면 String으로 하시면 편합니다.
	
	/*
	 * @RequestMapping(value = "/write") //값 없이 붙혀 넣을땐 이렇게 해도 됩니다.
	 */	
		@GetMapping(value = "/write")
		public String write(HttpSession session) {
			if (session.getAttribute("id") != null) {
				return "write";
			} else {
				return "redirect:/login";
			}
	}
		@PostMapping(value = "write")
		public String write(HttpServletRequest request) throws Exception {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
		
			
//			System.out.println(title);
//			System.out.println(content);
			
			BoardDTO write = new BoardDTO();
			write.setB_title(request.getParameter("title"));
			write.setB_content(request.getParameter("content"));
			write.setU_id((String) session.getAttribute("id"));
			
			//세션은?
			write.setU_id((String) session.getAttribute("id"));
			//각 데이터가 들어오지 않는다면 모두 failure로 보내기 작업만들어 주세요.
			if(session.getAttribute("id") != null && request.getParameter("title").equals("") && request.getParameter("content")!= null) {
				
			}
			//데이터 베이스로 보내기 
			int result = boardService.write(write);
//			System.out.println("처리결과? " + result);
				if (result == 1 ) {
					return "redirect:/success"; //글쓰기 성공
				} else {
					return "redirect:/failure"; //실패했습니다. 다시 시도하세요.
				}				
			}
			
		
		@GetMapping(value = "/success")
		public String success() {
			return "success";
		}
		
		@GetMapping(value = "/failure")
		public String faulure() {
			return "failure";
		}

	@RequestMapping(value = "/board")
	public ModelAndView board(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("board"); // jsp 이동
		// 값 보내기
		//mv.addObject("test", "테스트"); // 이름, 값 (키 , 밸류)
		// service에게 일 시키기
		
		//전자정부페이징 사용하기
		int pageNo = 1;
		if(request.getParameter("pageNo") != null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		
		//recordCountPageNo 한 페이지당 게시되는 게시물 수 yes
		int listScale = 10;
		//pageSize = 페이지 리스트에 게시되는 페이지 수 yes
		int pageScale = 10;
		//totalRecordCount 전체 게시물 건수
		int totalCount = boardService.totalCount();
		
		System.out.println("totalCount : " + totalCount);
		
		//전자정부페이징 호출
		PaginationInfo paginationInfo = new PaginationInfo();
		//값 대입
		paginationInfo.setCurrentPageNo(pageNo);
		paginationInfo.setRecordCountPerPage(listScale);
		paginationInfo.setPageSize(pageScale);
		paginationInfo.setTotalRecordCount(totalCount);
		//전자정부 계산하기
		int startPage = paginationInfo.getFirstRecordIndex();
		int lastpage = paginationInfo.getRecordCountPerPage();
		
		//서버로 보내기
		PageDTO page = new PageDTO();
		page.setStartPage(startPage);
		page.setLastPage(lastpage);
		
		
		
		List<BoardDTO> boardList = boardService.boardList(page);
		mv.addObject("boardList", boardList);
		mv.addObject("paginationInfo", paginationInfo);
		mv.addObject("pageNo", pageNo);
		return mv;
	}
	
}
