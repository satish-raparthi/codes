package shopping;

import java.util.Queue;

public class Consumer extends Thread{
	Queue<Order> shoppingcart;

	public Consumer(int id) throws InterruptedException {
		Publisher pub = new Publisher(id);
		shoppingcart = pub.orders;
		synchronized (this) {
			Order ord = shoppingcart.peek();
			int id1 = ord.getOrderid();
			Thread mail = new Thread() {
				public void run() {
					System.out.println("Mail sent for the order" + id1);
					System.out.println("Order Details: ");
					System.out.println("Order Id" + ord.getOrderid());
					System.out.println("Ordered Date: " + ord.getOrderedDate());
					System.out.println("Total Amount" + ord.getTotalAmount());
				}
			};
			mail.start();
			mail.join();
			Thread whatsapp = new Thread() {
				public void run() {
					System.out.println("Whatsapp msg sent for the order" + id1);
					System.out.println("Order Details: ");
					System.out.println("Order Id" + ord.getOrderid());
					System.out.println("Ordered Date: " + ord.getOrderedDate());
					System.out.println("Total Amount" + ord.getTotalAmount());
				}
			};
			whatsapp.start();
			wait(300);
			notify();
		}
	}

}
