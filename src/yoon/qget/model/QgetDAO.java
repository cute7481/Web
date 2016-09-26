package yoon.qget.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

class QgetDAO {
	private DataSource ds;
	QgetDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("ne : " + ne);
		}
	}
	int getTotal(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		int total = 0;
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(QgetSQL.QG_TOTAL);
			if(rs.next()){
				total = rs.getInt(1);
			}
		}catch(SQLException se){
			System.out.println("se : " + se);
			return -1;
		}finally{
			try{
				if(rs != null)rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
		return total;
	}
	ArrayList<QgetDTO> list(int pg){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<QgetDTO> list = new ArrayList<QgetDTO>();
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QgetSQL.QG_LIST);
			int min = pg-10;
			pstmt.setInt(1, pg);
			pstmt.setInt(2, min);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int num = rs.getInt(1);
				String id = rs.getString(2);
				String nick = rs.getString(3);
				String content = rs.getString(4);
				int good = rs.getInt(5);
				int bad = rs.getInt(6);
				String ctime = rs.getString(7);
				System.out.println("list: " +num + ":"+nick);
				list.add(new QgetDTO(ctime, num, id, content, good, bad, null, nick));
			}
			return list;
		}catch(SQLException se){
			System.out.println("se : " + se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	ArrayList<QgetDTO> bestList(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<QgetDTO> bestlist = new ArrayList<QgetDTO>();
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(QgetSQL.QG_BEST);
			while(rs.next()){
				int num = rs.getInt(1);
				String id = rs.getString(2);
				String nick = rs.getString(3);
				String content = rs.getString(4);
				int good = rs.getInt(5);
				int bad = rs.getInt(6);
				String ctime = rs.getString(7);
				System.out.println(num + ":"+nick);
				bestlist.add(new QgetDTO(ctime, num, id, content, good, bad,null, nick));
			}
			return bestlist;
		}catch(SQLException se){
			System.out.println("se : " +se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	int insert(QgetDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QgetSQL.QG_INSERT);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getContent());
			int i = pstmt.executeUpdate();
			if(i == 1) return 1;
			else return 0;
		}catch(SQLException se){
			return -1;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	int delete(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QgetSQL.QG_DELETE);
			pstmt.setInt(1, num);
			int i = pstmt.executeUpdate();
			return i;
		}catch(SQLException se){
			return -1;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	
	//good bad history check
	String qgcheck(int qnum, String userid){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = "";
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QgetSQL.QG_GB_CHECK);
			pstmt.setInt(1, qnum);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			while(rs.next()){  //좋아요 싫어요 전적있으면
				check = rs.getString(1);
			}
			if(check == null || check.equals("")){ //값이 없거나 공백인경우=>좋아요 싫어요 전적 없는경우
				return "3";
			}else{
				check = check.trim();
				return check; //좋아요 실허요 전적 있으면 기록 반환
			}
		}catch(SQLException se){
			return "-1";
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	
	//good bad update  num->질문번호, what->"1"(like),"2"(hate), userid
	void updateGD(int num, String what, String userid){
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try{
			con = ds.getConnection();
			con.setAutoCommit(false); 
			if(what.equals("1")){//좋아요일경우
				pstmt = con.prepareStatement(QgetSQL.QG_GOOD_UP);
			}else{
				pstmt = con.prepareStatement(QgetSQL.QG_DOWN_UP);				
			}
			pstmt.setInt(1, num);
			pstmt2 = con.prepareStatement(QgetSQL.QG_GB_INSERT);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, what);
			pstmt2.setString(3, userid);
			int i = pstmt.executeUpdate(); //질문 테이블의 좋아요 업데이트
			int j = pstmt2.executeUpdate();
			
			con.commit();
		}catch(SQLException se){
			System.out.println("se : " + se);
			if(con!=null){
				try{
					con.rollback();
				}catch(SQLException se2){
				}
			}
		}finally{
			try{
				con.setAutoCommit(true); 
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	
	//좋아요 실어요 전적 테이블
	/*
	int insertGB(int num, String what, String userid){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QgetSQL.QG_GB_INSERT);
			pstmt.setInt(1, num);
			pstmt.setString(2, what);
			pstmt.setString(3, userid);
			int i = pstmt.executeUpdate();
			return i;  //성공하면 1리턴
		}catch(SQLException se){
			return -1; //실패하면 -1리턴
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
	*/
}
