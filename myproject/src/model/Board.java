package model;

import java.util.Date;

public class Board {
	private int num;
	private int boardnum;
	private String id;
	private String name;
	private String subject;
	private String content;
	private String file1;
	private Date regdate;
	private Date setdate;
	private int cnt;
	private int likenum;
	private int setpublic;
	private String img;
	private int likechk;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getSetdate() {
		return setdate;
	}
	public void setSetdate(Date setdate) {
		this.setdate = setdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getLikenum() {
		return likenum;
	}
	public void setLikenum(int likenum) {
		this.likenum = likenum;
	}
	public int getSetpublic() {
		return setpublic;
	}
	public void setSetpublic(int setpublic) {
		this.setpublic = setpublic;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getLikechk() {
		return likechk;
	}
	public void setLikechk(int likechk) {
		this.likechk = likechk;
	}
	@Override
	public String toString() {
		return "Board [num=" + num + ", boardnum=" + boardnum + ", id=" + id + ", name=" + name + ", subject=" + subject
				+ ", content=" + content + ", file1=" + file1 + ", regdate=" + regdate + ", setdate=" + setdate
				+ ", cnt=" + cnt + ", likenum=" + likenum + ", setpublic=" + setpublic + ", img=" + img + ", likechk="
				+ likechk + "]";
	}

}
