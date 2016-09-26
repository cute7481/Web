package yoon.reply.model;

import java.sql.*;

public class ReplyDTO {
	private int num;
	private int bnum;
	private String id;
	private String reply;
	private Date rdate;
	private String nick;
	private String ctime;
	private String pic;
	public ReplyDTO(int num, int bnum, String id, String reply, Date rdate, String nick, String pic) {
		super();
		this.num = num;
		this.bnum = bnum;
		this.id = id;
		this.reply = reply;
		this.rdate = rdate;
		this.nick = nick;
		this.pic = pic;
	}
	public ReplyDTO(String ctime, int num, int bnum, String id, String reply, String nick , String pic) {
		super();
		this.ctime = ctime;
		this.num = num;
		this.bnum = bnum;
		this.id = id;
		this.reply = reply;
		this.nick = nick;
		this.pic = pic;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCtime(){
		return ctime;
	}
	public void setCtime(){
		this.ctime = ctime;
	}
	public String getPic(){
		return pic;
	}
	public void setPic(String pic){
		this.pic = pic;
	}
}
