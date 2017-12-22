package com.tally.vo;

public class InventoryEntryVO {

	private String voucherKey;
	private String ledgerName;
	private String stockItemName;
	private String rate;
	private String amount;
	private String billedQuantity;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVoucherKey() {
		return voucherKey;
	}
	public void setVoucherKey(String voucherKey) {
		this.voucherKey = voucherKey;
	}
	public String getLedgerName() {
		return ledgerName;
	}
	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public String getStockItemName() {
		return stockItemName;
	}
	public void setStockItemName(String stockItemName) {
		this.stockItemName = stockItemName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBilledQuantity() {
		return billedQuantity;
	}
	public void setBilledQuantity(String billedQuantity) {
		this.billedQuantity = billedQuantity;
	}
	
	
}
