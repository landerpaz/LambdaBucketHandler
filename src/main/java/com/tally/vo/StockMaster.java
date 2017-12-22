package com.tally.vo;

import java.util.List;

public class StockMaster {

	private String  vchType;
	private String  action;
	private String  alterdon;
	private String  voucherDate;
	private String  voucherType;
	private String  voucherNumber;
	private String  voucherTypeName;
	private String  voucherKey;
	private String  voucherEffectiveDate;
	private String  persistedView;
	private String  alterId;
	private String  masterId;
	
	private String oprDate;
	private String realWeight;
	private String startTime;
	private String rewindStart;
	private String rewindEnd;
	private String operatedBy;
	private String foreman1;
	private String foreman2;
	
	public String getOprDate() {
		return oprDate;
	}
	public void setOprDate(String oprDate) {
		this.oprDate = oprDate;
	}
	public String getRealWeight() {
		return realWeight;
	}
	public void setRealWeight(String realWeight) {
		this.realWeight = realWeight;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getRewindStart() {
		return rewindStart;
	}
	public void setRewindStart(String rewindStart) {
		this.rewindStart = rewindStart;
	}
	
	public String getRewindEnd() {
		return rewindEnd;
	}
	public void setRewindEnd(String rewindEnd) {
		this.rewindEnd = rewindEnd;
	}
	public String getOperatedBy() {
		return operatedBy;
	}
	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}
	public String getForeman1() {
		return foreman1;
	}
	public void setForeman1(String foreman1) {
		this.foreman1 = foreman1;
	}
	public String getForeman2() {
		return foreman2;
	}
	public void setForeman2(String foreman2) {
		this.foreman2 = foreman2;
	}
	public String getVoucherTypeName() {
		return voucherTypeName;
	}
	public void setVoucherTypeName(String voucherTypeName) {
		this.voucherTypeName = voucherTypeName;
	}
	private List<StockDetail> stockDetails;
	
	public String getVchType() {
		return vchType;
	}
	public void setVchType(String vchType) {
		this.vchType = vchType;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAlterdon() {
		return alterdon;
	}
	public void setAlterdon(String alterdon) {
		this.alterdon = alterdon;
	}
	public String getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getVoucherKey() {
		return voucherKey;
	}
	public void setVoucherKey(String voucherKey) {
		this.voucherKey = voucherKey;
	}
	public String getVoucherEffectiveDate() {
		return voucherEffectiveDate;
	}
	public void setVoucherEffectiveDate(String voucherEffectiveDate) {
		this.voucherEffectiveDate = voucherEffectiveDate;
	}
	public String getPersistedView() {
		return persistedView;
	}
	public void setPersistedView(String persistedView) {
		this.persistedView = persistedView;
	}
	public String getAlterId() {
		return alterId;
	}
	public void setAlterId(String alterId) {
		this.alterId = alterId;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public List<StockDetail> getStockDetails() {
		return stockDetails;
	}
	public void setStockDetails(List<StockDetail> stockDetails) {
		this.stockDetails = stockDetails;
	}
	
	
}
