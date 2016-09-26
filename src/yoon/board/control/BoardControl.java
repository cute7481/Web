package yoon.board.control;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import yoon.board.model.BoardManager;
import yoon.reply.model.ReplyManager;
import yoon.board.model.BoardDTO;
import yoon.reply.model.ReplyDTO;

public class BoardControl extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		String m = req.getParameter("method");
		System.out.println(m);
		if(m !=null){
			m = m.trim();
			if(m.equals("edit")){			//에디트창 글수정모드		
				editMode(req,res);
			}else if(m.equals("update")){	//디비업데이트(글수정)
				update(req,res);
			}else if(m.equals("del")){	//디비삭제(글삭제)
				delete(req,res);
			}else if(m.equals("content")){	//글 내용 보기
				content(req,res);
			}else if(m.equals("write")){	//글쓰기 창
				writeMode(req, res);
			}else if(m.equals("insert")){	//글 다쓰고 디비 인서트
				insert(req, res);
			}else if(m.equals("reply")){
				reply(req, res);
			}else if(m.equals("redel")){
				redel(req, res);
			}else{
				list(req,res);				//글 목록 보기
			}
		}else {
			list(req,res);				//글 목록 보기
		}	
	}
	//게시판 글 목록 보여주기
	private void list(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		BoardManager manager = BoardManager.getInstance();
		String strPg= req.getParameter("pg");
		if(strPg != null)strPg.trim();
		else strPg="1";//pg에 널값일 경우 디폴트로 1페이지 보이도록
		int pg = Integer.parseInt(strPg);
		String tok = req.getParameter("stoken");
		String sval = req.getParameter("search");
		ArrayList<BoardDTO> list = null;
		int totalNum = -1;
		if(tok==null)tok="";
		switch(tok){
			case "con":
				list = manager.listB(pg, 1, sval);
				totalNum = manager.getTotalB(1,sval);
				break;
			case "sub":
				list = manager.listB(pg, 2, sval);
				totalNum = manager.getTotalB(2,sval);
				break;
			case "consub":
				list = manager.listB(pg, 3, sval);
				totalNum = manager.getTotalB(3,sval);
				break;
			case "writer":
				list = manager.listB(pg, 4, sval);
				totalNum = manager.getTotalB(4,sval);
				break;
			default:
				list = manager.listB(pg, 0, "");
				totalNum = manager.getTotalB(0,"");
				break;
 		}

		String view = "project/list.jsp";
		req.setAttribute("list", list);
		req.setAttribute("total", totalNum);
		req.setAttribute("pg", pg);
		req.setAttribute("tok", tok);
		if(sval!=null)req.setAttribute("keyword", sval);
		else req.setAttribute("keyword","");
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
	}
	
	//수정 완료 후 디비 업데이트
	private void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//req.setCharacterEncoding("UTF-8");
		BoardManager manager = BoardManager.getInstance();
		String noStr = req.getParameter("num");
		if(noStr != null)noStr=noStr.trim();
		int num = Integer.parseInt(noStr);
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		BoardDTO dto = new BoardDTO(num, id, subject, content, -1, null, -1, null);
		manager.updateB(dto);
		res.sendRedirect("board.do?method=content&num=" + num);
	}
	//글 다쓰고 디비 인서트
	private void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//req.setCharacterEncoding("UTF-8");
		BoardManager manager = BoardManager.getInstance();
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		System.out.println("id : " +id + " , sub : " + subject + " , cont : " + content);
		BoardDTO dto = new BoardDTO(-1, id, subject, content, 0, null, 0, null);
		manager.writeB(dto);
		res.sendRedirect("board.do?method=list");
	}
	
	//디비 삭제
	private void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		BoardManager manager = BoardManager.getInstance();
		String noStr = req.getParameter("num");
		if(noStr != null)noStr=noStr.trim();
		int num = Integer.parseInt(noStr);
		BoardDTO dto = new BoardDTO(num, null, null, null, -1 , null, 0 , null);
		System.out.println("delnum : " +num);
		manager.deleteB(dto);
		res.sendRedirect("board.do?method=list&tableIdx=");
	}
	
	//글 내용 보여주기
	private void content(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		BoardManager manager = BoardManager.getInstance();
		String noStr = req.getParameter("num");
		if(noStr != null)noStr=noStr.trim();
		int num= Integer.parseInt(noStr);

		ReplyManager rmanager = ReplyManager.getInstance();
		ArrayList<ReplyDTO> relist = rmanager.listR(num);
		boolean Cchk = false;//쿠키없음을 일단 디폴트
		//쿠키체크
		Cookie[] cookies = req.getCookies();
		Cookie viewCookie = null;
		if(cookies !=null && cookies.length > 0){//쿠키에 값이 들어있고 쿠키 배열이 존재할때
			for(Cookie c : cookies){
				if(c.getName().equals("VIEWS"))viewCookie = c;
			}
		}
		if(viewCookie==null){//즉 위의 조건문 실행안됬을때
			Cookie newCookie = new Cookie("VIEWS","bnum:"+num);
			res.addCookie(newCookie);
		}else{//위의 조건문이 실행됬을때->일단 쿠키는있음
			String value = viewCookie.getValue();
			if(value.indexOf("bnum:"+num)<0){//밸류의 인덱스가0보다작으면 즉,그값의 밸류가없단소리
				value = "bnum:"+num;
				viewCookie.setValue(value);
				res.addCookie(viewCookie);
			}else{
				Cchk=true;
			}
		}
		BoardDTO dto = manager.contentB(num,Cchk);

		req.setAttribute("contDto", dto);
		req.setAttribute("replylist", relist);
		String view = "project/content.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);

		rd.forward(req, res);
	}
	
	//글쓰기 창 보여줌
	private void writeMode(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		String id = req.getParameter("id");
		req.setAttribute("userid", id);
		String view = "project/write.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);		
	}
	//편집창 보여주기
	private void editMode(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		BoardManager manager = BoardManager.getInstance();
		String noStr = req.getParameter("num");
		if(noStr != null)noStr=noStr.trim();
		int num = Integer.parseInt(noStr);

		BoardDTO dto = manager.editModeB(num);
		req.setAttribute("upDto", dto);
		String view = "project/edit.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);		
	}	
	//리플 달기
	private void reply(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//req.setCharacterEncoding("UTF-8");
		
		ReplyManager manager = ReplyManager.getInstance();
		String noStr = req.getParameter("num");
		if(noStr != null)noStr=noStr.trim();
		int bnum = Integer.parseInt(noStr);
		
		String id = req.getParameter("replyid");
		String replyContent = req.getParameter("reply");
		ReplyDTO dto = new ReplyDTO(-1, bnum, id, replyContent, null, null , null);
		
		ReplyDTO newDto = manager.replyR(dto);

		res.sendRedirect("board.do?method=content&num="+bnum);
		/*
		res.setContentType("text/html;charset=UTF-8");
		JSONObject obj = new JSONObject();
		PrintWriter pw = res.getWriter();
		obj.put("num" , newDto.getNum());
		obj.put("id", newDto.getId());
		obj.put("reply", newDto.getReply());
		obj.put("bnum", newDto.getBnum());
		obj.put("rdate", newDto.getRdate().toString());
		obj.put("nick", newDto.getNick());
		String json = obj.toString();
		pw.print(json);
		*/
	}

	//리플 삭제
	private void redel(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		ReplyManager manager = ReplyManager.getInstance();
		String noStr = req.getParameter("bnum");
		if(noStr != null)noStr=noStr.trim();
		int bnum = Integer.parseInt(noStr);
		String noStr2 = req.getParameter("num");
		if(noStr2 != null)noStr=noStr.trim();
		int num = Integer.parseInt(noStr2);
		System.out.println("num :" + num + ", bnum : " +bnum);
		ReplyDTO dto = new ReplyDTO(num, bnum, null, null, null, null,null);
		manager.deleteR(dto);
		res.sendRedirect("board.do?method=content&num="+bnum);
	}
}
