package vn.edu.tdt.it.dsa;

public class ProductRecord {
	private final int productID;
	private int quantity;

	public ProductRecord(final int Id, final int quantity) {
		this.productID = Id;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(final int quantity) {
		this.quantity = quantity;
	}

	public int getProductID() {
		return productID;
	}
	
	@Override
	public String toString(){
		return String.format("%03d%02d", productID, quantity);
	}
}
