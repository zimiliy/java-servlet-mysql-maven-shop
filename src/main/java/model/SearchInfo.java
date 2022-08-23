package model;

public class SearchInfo {
	private String main;
	private String sub;
	private String free;
	
	public SearchInfo() {}
	public SearchInfo(String main, String sub, String free) {
		this.main = main;
		this.sub = sub;
		this.free = free;
	}

	//Main
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}

	//Sub
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}

	//Free
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	
	
}
