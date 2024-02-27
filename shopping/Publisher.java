package shopping;

import java.util.LinkedList;
import java.util.Queue;

public class Publisher extends Thread {
	private int orderid;
	Queue<Order> orders;

	public Publisher(int orderid) {
		this.orderid = orderid;
		orders = new LinkedList<Order>();
		orders.add(new Order(orderid));
	}

}
