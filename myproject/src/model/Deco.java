package model;

public class Deco {
	private int num;
	private String id;
	private int deconum;
	private String img;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getDeconum() {
		return deconum;
	}
	public void setDeconum(int deconum) {
		this.deconum = deconum;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return "Deco [num=" + num + ", id=" + id + ", deconum=" + deconum + ", img=" + img + "]";
	}
}
