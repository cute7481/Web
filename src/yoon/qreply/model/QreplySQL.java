package yoon.qreply.model;

class QreplySQL {
	//사진에 대한 댓글 리스트
	final static String LIST = "select A.*, B.NICK, B.PIC from R_PIC_REPLY A,R_MEMBER B where A.PNUM=? and A.ID=B.ID(+) order by A.NUM desc";
	
	//사진 댓글 업데이트
	final static String QREPLY = "insert into R_PIC_REPLY values(R_PIC_REPLY_NUM.nextval, ?, ?, ? , ? ,? , SYSDATE)";
	
	//댓글 삭제
	final static String QR_DEL = "delete from R_PIC_REPLY where NUM=?";
	
	//댓글 패스워드 얻기
	final static String QR_PWD = "select PWD from R_PIC_REPLY where NUM=?";
	
	//final static String PR_LIST = "select NUM, USERID, NAME, REPLY, RDATE from R_PIC_REPLY where PNUM=? order by NUM desc";
	//final static String PR_INSERT = "insert into R_PIC_REPLY values(R_PIC_REPLY_NUM.nextval, ?, ?, ?, ?, ?, SYSDATE)";
	//final static String PR_DELETE = "delete from R_PIC_REPLY where num=?";
	//final static String PR_PWD = "select PWD from R_PIC_REPLY where NUM=?";
}
