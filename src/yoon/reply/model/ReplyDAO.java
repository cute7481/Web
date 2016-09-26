package yoon.reply.model;

import java.util.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import yoon.reply.model.ReplyDTO;

class ReplyDAO {

	//for DB pool
	private DataSource ds;
	ReplyDAO(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne){
			System.out.println("ne : "+ne);
		}
	}
	//府敲 格废
	ArrayList<ReplyDTO> list(int bnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReplyDTO> list = new ArrayList<ReplyDTO>();
		try{
			con = ds.getConnection();
			// "select * from R_BOA_REPLY where BNUM=? order by NUM desc";
			pstmt = con.prepareStatement(ReplySQL.LIST);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			

			while(rs.next()){
				//NUM,BNUM,ID,REPLY,DATE
				int num = rs.getInt(1);
				//int b_num = rs.getInt(2);
				String id = rs.getString(3);
				String reply = rs.getString(4);
				String ctime = rs.getString(5);
				String nick = rs.getString(6);
				String pic = rs.getString(7);
				list.add(new ReplyDTO(ctime, num, bnum, id, reply, nick, pic));
			}
			return list;
		}catch(SQLException se){
			System.out.println("se on reply list: " + se);
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
 
	void delete(ReplyDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			//府敲 昏力 孽府
			// "delete from R_BOA_REPLY where NUM=?"
			pstmt = con.prepareStatement(ReplySQL.DEL);
			pstmt.setInt(1, dto.getNum());

			int i = pstmt.executeUpdate();

		}catch(SQLException se){
			System.out.println("se on reply delete  : " +se);
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se){
			}
		}
	}

	
	ReplyDTO reply(ReplyDTO dto){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			//                                NUM   				   ,BNUM, ID,REPLY,DATE 
			//"insert into R_BOA_REPLY values(R_BOA_REPLY_NUM.nextval, ?  ,  ?, ?   , SYSDATE)";
			pstmt = con.prepareStatement(ReplySQL.REPLY,new String[]{"num","rdate"} );
			pstmt.setInt(1, dto.getBnum());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3,  dto.getReply());
			int i = pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			int rnum = -1;
			java.sql.Date rdate = null;
			if(rs.next()){
				rnum = rs.getInt(1);
				rdate = rs.getDate(2);
			}
			pstmt = con.prepareStatement(ReplySQL.GET_NICK);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			rs.next();
			dto.setNum(rnum);
			dto.setRdate(rdate);
			dto.setNick(rs.getString(1));
			return dto;
 
		}catch(SQLException se){
			System.out.println("se on reply : " +se);
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

}
