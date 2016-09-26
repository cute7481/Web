package yoon.questions.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yoon.qreply.model.QreplyDTO;
import yoon.qreply.model.QreplyManager;
import yoon.questions.model.QuestionsDTO;
import yoon.questions.model.QuestionsManager;
import net.sf.json.JSONObject;

public class QuestionsControl extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		String m = req.getParameter("method");
		if(m !=null){
			m = m.trim();
			if(m.equals("list")){
				//질문 리스트
				qlist(req, res);
			}else if(m.equals("qinfo")){
				//질문 클릭 후 댓글 리스트
				qselected(req, res);
			}else if(m.equals("asyncpic")){
				asyncPic(req, res);
			}else{
				mainpic(req, res);
			}
		}else{
			mainpic(req,res);				//메인에서 질문 보기
		}	
	}
	private void mainpic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		QuestionsManager manager = QuestionsManager.getInstance();
		String nStr = req.getParameter("num");
		int n = 0;
		if(nStr != null){
			nStr.trim();
			n = Integer.parseInt(nStr);
		}
		QuestionsDTO dto = manager.mPicB(n);
		req.setAttribute("pic", dto);
		String view ="index.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
		
	}	
	//메인 질문사진 AJAX 처리를 위한 서비스 메소드
	private void asyncPic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		QuestionsManager manager = QuestionsManager.getInstance();
		String nStr = req.getParameter("num");
		int n = 0;
		if(nStr != null){
			nStr.trim();
			n = Integer.parseInt(nStr);
		}
		QuestionsDTO dto = manager.mPicB(n);
		req.setAttribute("pic", dto);
		JSONObject obj = new JSONObject();
		PrintWriter pw = res.getWriter();
		obj.put("num", dto.getNum());
		obj.put("pic", dto.getPic());
		obj.put("auth", dto.getAuthor());
		String json = obj.toString();
		pw.print(json);
		
	}
	//질문 리스트
	private void qlist(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {	
		ArrayList<QuestionsDTO> dto = null;
		QuestionsManager manager = QuestionsManager.getInstance();
		dto = manager.listB();
		
		req.setAttribute("qlist", dto);
		
		String view = "project/q_list.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
	}
	private void qselected(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		QuestionsDTO dto = null;
		QuestionsManager manager = QuestionsManager.getInstance();
		String strNum = req.getParameter("num");//질문 번호
		int qnum = 0;
		if(strNum != null) qnum = Integer.parseInt(strNum);

		dto = manager.qselectedB(qnum);
		
		req.setAttribute("qdto", dto);

		ArrayList<QreplyDTO> rdto = null;
		QreplyManager rmanager =QreplyManager.getInstance();
		rdto = rmanager.qrlistB(qnum);
		req.setAttribute("qrlist", rdto);

		String view = "project/q_selected.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
	}
}
