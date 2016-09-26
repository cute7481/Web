package yoon.questions.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;

class QuestionsDAO {
	
	private DataSource ds;
	
	QuestionsDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("ne : " + ne);
		}
	}
	QuestionsDTO mPic(int n){
		Connection con=null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//전체 갯수 받아오고
			con = ds.getConnection();
			stmt =  con.createStatement();
			rs = stmt.executeQuery(QuestionsSQL.TOTALQ);
			if(rs.next()){
				int cnt = rs.getInt(1);
				int rnum=-1;
				if(n == 0){
					Random r = new Random();
					rnum = r.nextInt(cnt)+1;
				}else{
					rnum = n+1;
					if(rnum>cnt)rnum=1;
				}
				pstmt = con.prepareStatement(QuestionsSQL.MAINQ);
				pstmt.setInt(1, rnum);
				if(rs != null) rs.close();
				rs = pstmt.executeQuery();
				if(rs.next()){
					int num = rs.getInt(1);
					String pic = rs.getString(2);
					String subject = rs.getString(3);
					String auth = rs.getString(4);
					java.sql.Date rdate = rs.getDate(5);
					QuestionsDTO dto = new QuestionsDTO(num, pic, subject, auth, rdate);
					return dto;
				}else return null;
			}else return null;
			
		}catch(SQLException se){
			System.out.println("se : " + se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	ArrayList<QuestionsDTO> list(){
		Connection con = null;
		Statement stmt =null;
		ResultSet rs = null;
		ArrayList<QuestionsDTO> list = new ArrayList<QuestionsDTO>();
		try{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(QuestionsSQL.LIST);
			while(rs.next()){
				int num = rs.getInt(1);
				String pic = rs.getString(2);
				String subject =  rs.getString(3);
				String auth = rs.getString(4);
				java.sql.Date rdate = rs.getDate(5);
				list.add(new QuestionsDTO(num, pic, subject, auth, rdate));
			}
			return list;
		}catch(SQLException se){
			System.out.println("se : " + se);
			return null;
		}finally{
			try{
				if(rs != null)rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();				
			}catch(SQLException se){
			}
		}
		
	}
	QuestionsDTO qselected(int qnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QuestionsSQL.MAINQ);
			pstmt.setInt(1, qnum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				int num = rs.getInt(1);
				String pic = rs.getString(2);
				String subject = rs.getString(3);
				String author = rs.getString(4);
				java.sql.Date rdate = rs.getDate(5);
				QuestionsDTO dto = new QuestionsDTO(num, pic, subject, author, rdate);
				return dto;
			}else return null;
		}catch(SQLException se){
			System.out.println("se : " + se);
			return null;
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(SQLException se){
			}
		}
	}

}
