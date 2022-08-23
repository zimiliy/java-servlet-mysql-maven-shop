package model;

public class PagingLogic {
	public int pagingLogic(int p,int size,int i) {
		int r=0;
		//1：表示 2:... 3:非表示
		if(p<=4) {
			if(i<=5 || i==size) {
				r=1;
			}else if(i==6) {
				r=2;
			}else {
				r=3;
			}
		}else if(p>=size-3){
			if(i>=size-4 || i==1) {
				r=1;
			}else if(i==size-5) {
				r=2;
			}else {
				r=3;
			}
		}else {
			if((i>=p-2 && i<=p+2) || i==1 || i==size) {
				r=1;
			}else if(i==p-3 || i==p+3) {
				r=2;
			}else {
				r=3;
			}
		}
		
		return r;
	}
}
