package yoon.board.model;

class BoardSQL {
	//게시판 목록 보기
	//final static String LIST = "select * FROM R_BOARD order by NUM desc";
	//select a.num, (select count(*) from r_boa_reply b where b.bnum=a.num) from r_board a
	//페이징을 위한 쿼리문
	final static String LIST = "select * from ("
									+ "select ROWNUM as RNUM, A.* from ("
										+ "select B.*, (select COUNT(*) as CNT from R_BOA_REPLY C where C.BNUM=B.NUM), D.NICK "
									+ "from R_BOARD B, R_MEMBER D where B.ID=D.ID order by B.NUM desc"
									+ ") A where ROWNUM < ?"
								+ ") where RNUM >= ?";
	//검색 쿼리문들
	//제목
	final static String L_SUBJ = "select * from ("
			+ "select ROWNUM as RNUM, A.* from ("
			+ "select B.*, (select COUNT(*) as CNT from R_BOA_REPLY C where C.BNUM=B.NUM), D.NICK"
			+ " from R_BOARD B, R_MEMBER D where B.ID=D.ID and B.SUBJECT like ? order by B.NUM desc"
			//+ "select * from R_BOARD where SUBJECT like ? order by NUM desc" //where subject like '%가나다%' 
		+ ") A where ROWNUM < ?"
	+ ") where RNUM >= ?";
	//내용
	final static String L_CONT = "select * from ("
			+ "select ROWNUM as RNUM, A.* from ("
			+"select B.*, (select COUNT(*) as CNT from R_BOA_REPLY C where C.BNUM=B.NUM), D.NICK"
			+ " from R_BOARD B, R_MEMBER D where B.ID=D.ID and B.CONTENT like ? order by B.NUM desc"
			//+ "select * from R_BOARD where CONTENT like ? order by NUM desc"
		+ ") A where ROWNUM < ?"
	+ ") where RNUM >= ?";
	//제목+내용
	final static String L_CONT_SUBJ = "select * from ("
			+ "select ROWNUM as RNUM, A.* from ("
			+ "select B.*, (select COUNT(*) as CNT from R_BOA_REPLY C where C.BNUM=B.NUM), D.NICK" 
			+ " from R_BOARD B, R_MEMBER D where B.ID=D.ID and (CONTENT like ? or SUBJECT like ?) order by B.NUM desc"
			//+ "select * from R_BOARD where CONTENT like ? or SUBJECT like ? order by NUM desc"
		+ ") A where ROWNUM < ?"
	+ ") where RNUM >= ?";
	//글쓴이
	final static String L_ID = "select * from ("
			+ "select ROWNUM as RNUM, A.* from ("
			+ "select B.*, (select COUNT(*) as CNT from R_BOA_REPLY C where C.BNUM=B.NUM), D.NICK" 
			+ " from R_BOARD B, R_MEMBER D where B.ID=D.ID and D.NICK like ? order by B.NUM desc"
			//+ "select * from R_BOARD where ID like ? order by NUM desc"
		+ ") A where ROWNUM < ?"
	+ ") where RNUM >= ?";
	//전체 게시글 수
	final static String TOTALNUM = "select COUNT(*) as TOTALCOUNT FROM R_BOARD";
	//검색 게시글 수
	final static String T_CONT = "select COUNT(*) as TOTALCOUNT FROM R_BOARD where CONTENT like ?";
	final static String T_SUBJ = "select COUNT(*) as TOTALCOUNT FROM R_BOARD where SUBJECT like ?";
	final static String T_CONT_SUBJ = "select COUNT(*) as TOTALCOUNT FROM R_BOARD where CONTENT like ? or SUBJECT like ?";
	final static String T_ID = "select COUNT(*) as TOTALCOUNT FROM R_BOARD where ID like ?";
	//내용 보기
	final static String CONTENT = "select B.* , A.NAME from R_BOARD B, R_MEMBER A where B.NUM=? and B.ID=A.ID";
	//조회수 증가
	final static String READNUMBER = "update R_BOARD set READNUM=READNUM+1 where NUM=?";
	
	//글 쓰기(쓰기버튼 클릭 후)											NUM			   ,ID, SUBJECT, CONTENT,READNUM, DATE
	final static String WRITE = "insert into R_BOARD values(R_BOARD_NUM.nextval,? ,		  ?,      ?,       ?, SYSDATE)";
		
	//글 수정(수정버튼 클릭후 에디트 모드 들어갈때)
	final static String EDIT = "select B.SUBJECT, B.CONTENT, B.ID, A.NAME from R_BOARD B, R_MEMBER A where B.NUM=? and B.ID=A.ID ";
		
	//업데이트 (수정 버튼 클릭 후)
	final static String UPDATE = "update R_BOARD set SUBJECT=?, CONTENT=? where NUM=?";
		
	//글 삭제
	final static String DEL = "delete from R_BOARD where NUM=?";
		
	
}
