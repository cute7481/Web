package yoon.qget.model;

import java.util.ArrayList;

public class QgetManager {
	QgetDAO dao;
	private static QgetManager instance = new QgetManager();
	private QgetManager(){
		dao = new QgetDAO();
	}
	public static QgetManager getInstance(){
		return instance;
	}
	public int getTotalB(){
		return dao.getTotal();
	}
	public ArrayList<QgetDTO> listB(int pg) {
		return dao.list(pg);
	}
	public ArrayList<QgetDTO> bestListB() {
		return dao.bestList();
	}
	public int insertB(QgetDTO dto){
		return dao.insert(dto);
	}
	public int deleteB(int num){
		return dao.delete(num);
	}
	public String qgcheckB(int qnum, String userid){
		return dao.qgcheck(qnum, userid);
	}
	public void updateGDB(int num, String what, String userid){
		dao.updateGD(num, what, userid);
	}
}


