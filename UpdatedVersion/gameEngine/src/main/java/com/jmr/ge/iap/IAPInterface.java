package com.jmr.ge.iap;

import com.badlogic.gdx.utils.Array;

public interface IAPInterface {

	public Array<Item> items = new Array<Item>();
   
    static final int RC_REQUEST = 10001;
	
	public boolean isLoaded();
	
	public void processPurchases();  
	
	public boolean alreadyPurchased(String sku);
	
	public void buy(String sku);
	
	public Item getItem(String sku);
	
	public void donePurchase(Item item);
	
}
