package yoon.member.model;

class MemberSQL {
	//멤버 리스트
//	final static LIST = "select * from R_MEMBER";
//	final static String M_SLIST = "select NAME, ID, PWD from R_MEMBER";
	
	//비밀번호 가져오기
	final static String MB_PWD = "select PWD from R_MEMBER where ID=?";
	//ID 가져오기
	final static String MB_ID2 = "select ID from R_MEMBER where NICK=?";
	
	//멤버 추가
	final static String MB_JOIN = "insert into R_MEMBER values(R_MEMBER_NUM.nextval, ?, ?,?, ?,?, null, ?, SYSDATE)";

	//멤버 정보 가져오기
	final static String MB_INFO = "select ID, PWD, NAME, NICK, EMAIL, PIC, BIRTH, RDATE from R_MEMBER where ID=?";
	
	//아이디 가져오기(아이디 찾기 위해)
	final static String MB_ID = "select ID from R_MEMBER where NAME=? and EMAIL=?";
	
	//비밀번호 변경
	final static String MB_PWD_UP = "update R_MEMBER set PWD=? where ID=?";
	
	//멤버 정보 업데이트
	final static String MB_UPDATE = "update R_MEMBER set PWD=?, NICK=?, EMAIL=? where ID=?";
	//프로필사진 업데이트
	final static String PIC_UPDATE = "update R_MEMBER set PIC=? where ID=?";
	//프로필 사진 주소 가져오기
//	final static String MB_PIC = "select PIC from R_MEMBER where ID=?";
//	final static String ID_CHECK = "select pwd from R_MEMBER where ID=?";
//	final static String M_INSERT = "insert into R_MEMBER values(R_MEMBER_NUM.nextval, ?, ?, ?, ?, null, -1, SYSDATE)";
//	final static String M_PIC = "select PIC from R_MEMBER where ID =?";
//	final static String M_ID = "select ID from R_MEMBER where NAME=? and EMAIL=?";
//	final static String M_PWD_UP = "update R_MEMBER set PWD=? where ID=?";

}
