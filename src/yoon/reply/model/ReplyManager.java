package yoon.reply.model;

import java.util.ArrayList;

public class ReplyManager {

	private ReplyDAO dao;
	public static ReplyManager instance = new ReplyManager();
	private ReplyManager(){
		dao = new ReplyDAO();
	}
	public static ReplyManager getInstance(){
		return instance;
	}
	public ArrayList<ReplyDTO> listR(int bnum){
		return dao.list(bnum);
	}
	public ReplyDTO replyR(ReplyDTO dto){
		return dao.reply(dto);
	}
	public void deleteR(ReplyDTO dto){
		dao.delete(dto);
	}

}