package yoon.qreply.model;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

class QreplyDAO {
	private DataSource ds;
	
	QreplyDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("ne : " + ne);
		}
	}
	
	ArrayList<QreplyDTO> qrlist(int qnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QreplySQL.LIST);
			pstmt.setInt(1, qnum);
			rs = pstmt.executeQuery();
			ArrayList<QreplyDTO> list = new ArrayList<QreplyDTO>();
			while(rs.next()){
				int num = rs.getInt(1);
				int pnum = rs.getInt(2);
				String userid = rs.getString(3);
				String name = rs.getString(5);
				String reply = rs.getString(6);
				java.sql.Date rdate = rs.getDate(7);
				if(name==null){
					String nick = rs.getString(8);
					String pic = rs.getString(9);
					list.add(new QreplyDTO(num, pnum, userid, "", nick, reply, rdate, pic));
				}else{
					list.add(new QreplyDTO(num, pnum, userid, "", name, reply, rdate, null));				
				}
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
	void qrinsert(QreplyDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QreplySQL.QREPLY);
			//"insert into R_PIC_REPLY values(R_PIC_REPLy.nextval, ?, ?, ? , ? ,? , SYSDATE)";
			pstmt.setInt(1, dto.getPnum());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getPwd());
			pstmt.setString(4,  dto.getName());
			pstmt.setString(5, dto.getReply());
			int i = pstmt.executeUpdate();
			System.out.println(i);
		}catch(SQLException se){
			System.out.println("se : " + se);
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	String getPwd(int num){
 		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QreplySQL.QR_PWD);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String pw = rs.getString(1);
				return pw;
			}
			return null;
		}catch(SQLException se){
			System.out.println("se : " + se);
			return null;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}		
	}
	void qrdel(int num){
 		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(QreplySQL.QR_DEL);
			pstmt.setInt(1,  num);
			pstmt.executeQuery();
		}catch(SQLException se){
			System.out.println("se : " + se);
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
		
	}
}

/*
 		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			
			
		}catch(SQLException se){
			System.out.println("se : " + se);
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
		
		*/
