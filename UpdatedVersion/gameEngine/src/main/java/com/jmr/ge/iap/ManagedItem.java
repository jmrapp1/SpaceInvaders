package com.jmr.ge.iap;


public class ManagedItem extends Item {

	private boolean purchased;
	
	public ManagedItem(String sku) {
		super(sku);
	}
	
	public boolean isPurchased() {
		return purchased;
	}
	
	public void setPurchased(boolean b) {
		purchased = b;
	}

}
