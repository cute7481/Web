package yoon.qreply.model;

import java.util.ArrayList;

public class QreplyManager {
	QreplyDAO dao;
	private static QreplyManager instance = new QreplyManager();
	private QreplyManager(){
		dao =  new QreplyDAO();
	}
	public static QreplyManager getInstance(){
		return instance;
	}
	public ArrayList<QreplyDTO> qrlistB(int qnum){
		return dao.qrlist(qnum);
	}
	public void qrinsertB(QreplyDTO dto){
		dao.qrinsert(dto);
	}
	public String getPwdB(int num){
		return dao.getPwd(num);
	}
	public void qrdelB(int num){
		dao.qrdel(num);
	}

}
