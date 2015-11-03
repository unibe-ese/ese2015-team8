package ch.unibe.ese.admin;

import java.util.Arrays;
import java.util.List;

public class AdminData {
	private static final double CONTACT_PRICE = 5.0;
	
	private static final String[] temp = {"Master","Visa"};
	private static final List<String> CREDIT_CARDS = Arrays.asList(temp);

	public static double getContactprice() {
		return CONTACT_PRICE;
	}
	
	public static List<String> getCreditCards() {
		return CREDIT_CARDS;
	}
}
