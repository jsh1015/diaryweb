package model;

public class Member {
	private String id;
	private String pass;
	private String name;
	private String email;
	private String picture;
	private String diaryname;
	private String color;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDiaryname() {
		return diaryname;
	}
	public void setDiaryname(String diaryname) {
		this.diaryname = diaryname;
	}	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email + ", picture=" + picture
				+ ", diaryname=" + diaryname + ", color=" + color + "]";
	}
}
