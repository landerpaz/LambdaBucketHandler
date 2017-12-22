package com.tally.vo;

import java.util.List;

public class StockDetail {

	private String stockDetailsId;
	private String stockItemName;
	private String rate;
	private String amount;
	private String billedQty;
	private String actualQty;
	private String status;
	private String voucherKey;
	private List<StockBatchUDF> batchUDF;
	
	public String getVoucherKey() {
		return voucherKey;
	}
	public void setVoucherKey(String voucherKey) {
		this.voucherKey = voucherKey;
	}
	public String getStockDetailsId() {
		return stockDetailsId;
	}
	public void setStockDetailsId(String stockDetailsId) {
		this.stockDetailsId = stockDetailsId;
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
	public String getBilledQty() {
		return billedQty;
	}
	public void setBilledQty(String billedQty) {
		this.billedQty = billedQty;
	}
	public String getActualQty() {
		return actualQty;
	}
	public void setActualQty(String actualQty) {
		this.actualQty = actualQty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<StockBatchUDF> getBatchUDF() {
		return batchUDF;
	}
	public void setBatchUDF(List<StockBatchUDF> batchUDF) {
		this.batchUDF = batchUDF;
	}
	
}
