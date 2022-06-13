package com.poseidon.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	private BoardDAO boardDAO;

	public List<BoardDTO> boardList(PageDTO page) {
		return boardDAO.boardList(page);
		
	}

	public BoardDTO detail(int b_no) {
		return boardDAO.detail(b_no);
	}

	public int write(BoardDTO write) {
		return boardDAO.write(write);
	}

	public int totalCount() {
		return boardDAO.totalCount();
	}
	
}
