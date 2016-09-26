package yoon.qreply.model;

import java.sql.Date;

public class QreplyDTO {
	private int num;
	private int pnum;
	private String userid;
	private String pwd;
	private String name;
	private String reply;
	private Date rdate;
	private String pic;
	
	public QreplyDTO(int num, int pnum, String userid, String pwd, String name, String reply, Date rdate, String pic) {
		super();
		this.num = num;
		this.pnum = pnum;
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
		this.reply = reply;
		this.rdate = rdate;
		this.pic = pic;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
}
