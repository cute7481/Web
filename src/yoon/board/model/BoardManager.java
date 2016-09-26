package yoon.board.model;

import java.util.ArrayList;

public class BoardManager {
	private BoardDAO dao;
	public static BoardManager instance = new BoardManager();
	private BoardManager(){
		dao = new BoardDAO();
	}
	public static BoardManager getInstance(){
		return instance;
	}
	public ArrayList<BoardDTO> listB(int pg, int sidx, String str){
		System.out.println("show list");
		return dao.list(pg, sidx, str);
	}
	public int getTotalB(int sidx,String str){
		return dao.getTotal(sidx,str);
	}
	public void writeB(BoardDTO dto){
		System.out.println("write content");
		dao.write(dto);
	}
	public void updateB(BoardDTO dto){
		System.out.println("update board");
		dao.update(dto);
	}
	public void deleteB(BoardDTO dto){
		System.out.println("delete");
		dao.delete( dto);
	}
	public BoardDTO contentB(int tmpNo, boolean Cchk){
		System.out.println("show content");
		return dao.content(tmpNo, Cchk);
	}
	public BoardDTO editModeB(int tmpNo){
		System.out.println("show editMode" + tmpNo);
		return dao.editMode(tmpNo);
	}
}
