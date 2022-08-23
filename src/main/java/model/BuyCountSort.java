package model;

import java.util.Comparator;

public class BuyCountSort implements Comparator<Item>{
	 public int compare(Item i1, Item i2) {
		 if(i1.getBuyCount()<i2.getBuyCount()) {
			 return 1; //大きい順：１　小さい順：-1
		 }else if(i1.getBuyCount()>i2.getBuyCount()) {
			 return -1;
		 }else {
			 String[] split1=i1.getIID().split("_");
			 String[] split2=i2.getIID().split("_");
			 
			 return split1[1].compareTo(split2[1]);
		 }
	 }

}
