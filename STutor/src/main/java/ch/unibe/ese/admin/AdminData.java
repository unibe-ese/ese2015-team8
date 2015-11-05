package ch.unibe.ese.admin;

import java.util.Arrays;
import java.util.List;

/**
 * This class contains Data that is valid for the whole Project.
 * <p>
 * <b>Note:</b> The class could be deleted the Data inserted in the respective classes.
 * 
 * @author Christian Zürcher
 * @version 1.0
 * @since 04.11.2015
 */
public class AdminData {
	
	private static final double CONTACT_PRICE = 5.0;
	
	private static final String[] cards = {"Master","Visa"};
	private static final List<String> CREDIT_CARDS = Arrays.asList(cards);
	
	private static final String[] days = 
		{"Monday","Thuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	private static final List<String> DAYS = Arrays.asList(days);

	public static double getContactprice() {
		return CONTACT_PRICE;
	}
	
	public static List<String> getCreditCards() {
		return CREDIT_CARDS;
	}
	
	public static List<String> getDays() {
		return DAYS;
	}
}
