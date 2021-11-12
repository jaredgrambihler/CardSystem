package cardsystem.rewards;

import java.util.ArrayList;
import java.util.Arrays;

public enum MerchantCategory {
	RESTAURANT("Restaurant"),
	CLOTHING_STORE("Clothing store"),
	GROCERY_STORE("Grocery store"),
	AIRLINE("Airline"),
	GENERAL("General");

    private String merchantType;
    private static ArrayList<String> restaurants = new ArrayList<>(Arrays.asList("McDonald's", "Burger King", "Pizza Hut", "Domino's"));
    private static ArrayList<String> clothingStores = new ArrayList<>(Arrays.asList("River Island", "Mango", "Zara", "H&M"));
    private static ArrayList<String> groceryStores = new ArrayList<>(Arrays.asList("Tesco", "Lidl", "Aldi", "Spar"));
    private static ArrayList<String> airlines = new ArrayList<>(Arrays.asList("Ryanair", "British Airways", "SAS", "American Airlines"));
    

    MerchantCategory(String merchantType) {
        this.merchantType = merchantType;
    }

    @Override
    public String toString() {
        return merchantType;
    }
    
    public static MerchantCategory getCategory(String merchant) {
    	if (restaurants.contains(merchant)) {
    		return RESTAURANT;
    	} else if (clothingStores.contains(merchant)) {
    		return CLOTHING_STORE;
    	} else if (groceryStores.contains(merchant)) {
    		return GROCERY_STORE;
    	} else if (airlines.contains(merchant)) {
    		return AIRLINE;
    	}
    	return GENERAL;
    }
}
