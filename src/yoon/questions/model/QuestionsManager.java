package yoon.questions.model;

import java.util.ArrayList;

public class QuestionsManager {
	private QuestionsDAO dao;
	
	private static QuestionsManager instance = new QuestionsManager();
	
	private QuestionsManager(){
		dao = new QuestionsDAO();
	}
	public static QuestionsManager getInstance(){
		return instance;
	}
	public QuestionsDTO mPicB(int n){
		return dao.mPic(n);
	}
	public ArrayList<QuestionsDTO> listB(){
		return dao.list();
	}
	public QuestionsDTO qselectedB(int qnum){
		return dao.qselected(qnum);
	}
}
