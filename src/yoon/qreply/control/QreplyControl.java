package yoon.qreply.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yoon.qreply.model.QreplyDTO;
import yoon.qreply.model.QreplyManager;

public class QreplyControl extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		String m = req.getParameter("method");
		if(m !=null){
			m = m.trim();
			if(m.equals("reply")){
				//질문 리스트
				qinsert(req, res);
			}else if(m.equals("delete")){
				//질문 클릭 후 댓글 리스트
				qdelete(req, res);
			}else{
				res.sendRedirect("main.do");
			}
		}else{
			res.sendRedirect("main.do");
		}	
	}

	//질문 리스트
	private void qdelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {	
		QreplyManager manager = QreplyManager.getInstance();
		String pnum = req.getParameter("pnum");
		String noStr = req.getParameter("num");
		String memChk = req.getParameter("memchk");
		int num = -1;
		if(noStr !=null)noStr = noStr.trim();
		num = Integer.parseInt(noStr);
		int delchk = -1;
		if(memChk.equals("none")){
			String pwd = req.getParameter("pwd");
			String cmpPwd = manager.getPwdB(num);
			if(cmpPwd==null)cmpPwd="";
			if(cmpPwd.equals(pwd)){
				manager.qrdelB(num);
				delchk=0;//0=>삭제 성공;
				System.out.println("pws del suc");
			}
			else{
				delchk=1;//삭제 실패
				System.out.println("pws flase");
			}
		}else if(memChk.equals("member")){
			manager.qrdelB(num);
			delchk=0;
		}
		req.setAttribute("delchk", delchk);
		String view = "main.do?method=qinfo&num="+pnum;
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
	}
	private void qinsert(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		QreplyManager manager = QreplyManager.getInstance();
		String memChk = req.getParameter("mcheck");
		System.out.println(memChk);
		memChk=memChk.trim();
		String strNum = req.getParameter("num");//질문 번호
		int qnum = 0;
		if(strNum != null)strNum=strNum.trim();
		qnum = Integer.parseInt(strNum);	
		if(memChk.equals("member")){//회원일때

			String id = req.getParameter("id");
			String replyContent = req.getParameter("reply");
			String nick = req.getParameter("nick");
			QreplyDTO dto = new QreplyDTO(-1, qnum, id, null , null, replyContent, null, null);
			manager.qrinsertB(dto);
		}else if(memChk.endsWith("none")){ //비회원일때
			String name = req.getParameter("name");
			String replyContent = req.getParameter("reply");
			String pwd = req.getParameter("pwd");
			System.out.println(qnum+ name + replyContent + pwd);
			QreplyDTO dto = new QreplyDTO(-1, qnum, null, pwd, name, replyContent, null, null);
			manager.qrinsertB(dto);
			
		}
		String view = "main.do?method=qinfo&num="+qnum;
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
		/*
		QreplyDTO newDto = manager.qrinsertB(dto);

		res.setContentType("text/html;charset=UTF-8");
		JSONObject obj = new JSONObject();
		PrintWriter pw = res.getWriter();
		obj.put("num" , newDto.getNum());
		obj.put("qnum", newDto.getPnum());
		obj.put("name", newDto.getName());
		obj.put("reply", newDto.getReply());
		obj.put("rdate", newDto.getRdate().toString());
		System.out.println(newDto.getNum() + " : " + newDto.getPnum() + " :  " + newDto.getReply());
		String json = obj.toString();
		pw.print(json);
		*/
		/*
		req.setAttribute("qdto", dto);
		
		ArrayList<QreplyDTO> rdto = null;
		QreplyManager rmanager =QreplyManager.getInstance();
		rdto = rmanager.qrlistB(qnum);
		req.setAttribute("qrlist", rdto);

		HashMap<String , String> picList = new HashMap<String , String>();
		if(r_dto != null){
			MbManager m_manager = MbManager.getInstance();
			for(PReDTO p_dto : r_dto){
				String userid = p_dto.getUserid();
				if(userid != null){
					String pic = m_manager.userPicS(userid);
					//System.out.println("pic : " + pic);
					picList.put(userid, pic);
				}
			}
			req.setAttribute("userPic", picList);
		}
		
		String view = "project/q_selected.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, res);
		*/
	}
}
