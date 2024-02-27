package shopping;

import java.util.Scanner;

public class ShoppingDemo {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter order id: ");
			int order = sc.nextInt();
			Publisher pub = new Publisher(order);
			Consumer con = new Consumer(order);

		}
	}
}