package yoon.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import yoon.questions.model.QuestionsDTO;
import yoon.questions.model.QuestionsManager;

import java.io.*;


public class IndexControl extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		QuestionsManager manager = QuestionsManager.getInstance();
		String nStr = req.getParameter("num");
		int n = 0;
		if(nStr != null){
			nStr.trim();
			n = Integer.parseInt(nStr);
		}
		QuestionsDTO dto = manager.mPicB(n);
		req.setAttribute("pic", dto);
		
		String view = "index.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
	}
}
