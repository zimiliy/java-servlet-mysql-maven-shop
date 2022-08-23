package model;

import java.util.Comparator;

public class NewItemSort implements Comparator<Item> {
	 public int compare(Item i1, Item i2) {
		 String[] split1=i1.getIID().split("_");
		 String[] split2=i2.getIID().split("_");
		 
		 if(Long.parseLong(split1[1])<Long.parseLong(split2[1])) {
			 return 1;
		 }else if(Long.parseLong(split1[1])>Long.parseLong(split2[1])) {
			 return -1;
		 }else {
			 return split1[1].compareTo(split2[1]);
		 }
	 }
}
