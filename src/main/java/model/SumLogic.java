package model;

import java.util.List;

public class SumLogic {
	public int sumLogic(List<CartItem> cartList) {
		int sum=0;
		for(CartItem ci_:cartList) {
			sum=sum+(ci_.getBuyNum()*ci_.getItem().getPrice());
			}

		return sum;
	}
}
