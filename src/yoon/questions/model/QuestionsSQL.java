package yoon.questions.model;

class QuestionsSQL {
	//질문 리스트  
	final static String LIST = "select * from R_PICTURE order by NUM desc";
	//질문_댓글 리스트 
	final static String COMMENTLIST = "select * from R_PIC_REPLY order by NUM desc";
	//사진 하나 셀렉트
	final static String MAINQ = "select * from R_PICTURE where num=?";
	//질문 갯수 쿼리
	final static String TOTALQ = "select count(*) as totalcount from R_PICTURE";
	
	
	//final static String P_LIST = "select NUM, SUBJECT, RDATE from R_PICTURE order by NUM desc";
	//final static String P_SLIST = "select PIC, SUBJECT, AUTHOR, RDATE from R_PICTURE where NUM=?";

}
