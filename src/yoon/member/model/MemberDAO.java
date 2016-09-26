package yoon.member.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

class MemberDAO {
	private DataSource ds;
	
	MemberDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("ne : " + ne);
		}
	}
	//로그인 성공 1, 아이디 없음=>0 비번 틀림=>0
	MemberDTO login(String id, String pwd){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			//"select ID, PWD, NAME, NICK, EMAIL, PIC, BIRTH, RDATE from R_MEMBER where ID=?";
			pstmt = con.prepareStatement(MemberSQL.MB_INFO);
			pstmt.setString(1, id);
			//조건문에 아이디사용하는 쿼리문으로 패스워드를 가져옴
			//로우가 없으면 아이디가 없다는 뜻
			//로우가 있는데 비밀번호가 틀리면 비밀번호가 틀리다는 뜻
			rs = pstmt.executeQuery();
			if(rs.next()){//아이디가 있음
				String mid = rs.getString(1);
				String cmpPwd = rs.getString(2);
				String name = rs.getString(3);
				String nick = rs.getString(4);
				String email = rs.getString(5);
				String pic = rs.getString(6);
				int birth = rs.getInt(7);
				java.sql.Date rdate = rs.getDate(8);
				if(pwd.equals(cmpPwd)){//비밀번호 맞으면
					MemberDTO dto = new MemberDTO(-1, mid, null, name, nick, email, pic, birth, rdate);
					return dto;
				}
				return null;//비밀번호 틀림
			}else{//아이디가 없음
				return null;
			}
		}catch(SQLException se){
			System.out.println("se on login : " +se);
			return null;
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	
	String pwdCheck(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			//"select PWD from R_MEMBER where ID=?";
			pstmt = con.prepareStatement(MemberSQL.MB_PWD);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			String pwd = "";
			if(rs.next()){
				pwd = rs.getString(1);
			}
			return pwd;
		}catch(SQLException se){
			System.out.println("se on pwdCheck : " +se);
			return "";
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	String idCheck2(String nick){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			//"select PWD from R_MEMBER where ID=?";
			pstmt = con.prepareStatement(MemberSQL.MB_ID2);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			String id = "";
			if(rs.next()){
				id = rs.getString(1);
			}
			return id;
		}catch(SQLException se){
			System.out.println("se on pwdCheck : " +se);
			return "";
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	boolean join(MemberDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			//"insert into R_MEMBER values(R_MEMBER_NUM.nextval, ?, ?,?, ?,?, null, ?, SYSDATE)";
			pstmt = con.prepareStatement(MemberSQL.MB_JOIN);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNick());
			pstmt.setString(5, dto.getEmail());
			pstmt.setInt(6, 19900608);
			int i = pstmt.executeUpdate();
			if(i == 1) return true;
			else return false;
		}catch(SQLException se){
			System.out.println("se on join : " + se);
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	String idSearch(String name, String email){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			//select ID from R_MEMBER where NAME=? and EMAIL=?"
			pstmt = con.prepareStatement(MemberSQL.MB_ID);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()){
				String userid = rs.getString(1);
				return userid;
			}
			return "";
		}catch(SQLException se){
			System.out.println("se on idsearch : " + se);
			return "";
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	boolean pwdUpdate(String pwd, String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			//"update R_MEMBER set PWD=? where ID=?";
			pstmt = con.prepareStatement(MemberSQL.MB_PWD_UP);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			int i = pstmt.executeUpdate();
			if(i == 1)return true;
			else return false;
		}catch(SQLException se){
			System.out.println("se on pw update : " + se);
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}
	boolean myinfo(MemberDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			//"update R_MEMBER set PWD=?, NICK=?, EMAIL=? where ID=?";
			pstmt = con.prepareStatement(MemberSQL.MB_UPDATE);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getNick());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getId());
			int i = pstmt.executeUpdate();
			System.out.println(dto.getPwd() + " : " + dto.getNick() + " : " + dto.getEmail() + " : " + dto.getId() + " : " + i);
			if(i == 1) return true;
			else return false;
		}catch(SQLException se){
			System.out.println("se on update : " + se);
			return false;
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}	
	void picupload(String id, String picpath){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(MemberSQL.PIC_UPDATE);
			pstmt.setString(1, picpath);
			pstmt.setString(2, id);
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
