package com.tally.vo;

public class LedgerEntryVO {

	private String ledgerName;
	private String amount;
	private String voucherKey;
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLedgerName() {
		return ledgerName;
	}
	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getVoucherKey() {
		return voucherKey;
	}
	public void setVoucherKey(String voucherKey) {
		this.voucherKey = voucherKey;
	}
	
	
}
