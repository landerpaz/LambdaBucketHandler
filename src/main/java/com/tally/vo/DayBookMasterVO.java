package com.tally.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DayBookMaster")
public class DayBookMasterVO {

	private String voucherType;
	private String voucherAction;
	private String voucherDate;
	private String voucherTypeName;
	private String voucherNumber;
	private String partyLedgerName;
	private String voucherKey;
	private String effectiveDate;
	private String persistedView;
	private String alterId;
	private String masterId;
	private String ledgerName;
	private String checkFlag;
	
	private List<LedgerEntryVO> ledgerEntryVOs;
	private List<InventoryEntryVO> inventoryEntryVOs;
	
	public List<LedgerEntryVO> getLedgerEntryVOs() {
		return ledgerEntryVOs;
	}
	public void setLedgerEntryVOs(List<LedgerEntryVO> ledgerEntryVOs) {
		this.ledgerEntryVOs = ledgerEntryVOs;
	}
	public List<InventoryEntryVO> getInventoryEntryVOs() {
		return inventoryEntryVOs;
	}
	public void setInventoryEntryVOs(List<InventoryEntryVO> inventoryEntryVOs) {
		this.inventoryEntryVOs = inventoryEntryVOs;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getVoucherAction() {
		return voucherAction;
	}
	public void setVoucherAction(String voucherAction) {
		this.voucherAction = voucherAction;
	}
	public String getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}
	public String getVoucherTypeName() {
		return voucherTypeName;
	}
	public void setVoucherTypeName(String voucherTypeName) {
		this.voucherTypeName = voucherTypeName;
	}
	public String getVoucherNumber() {
		return voucherNumber;
	}
	public void setVoucherNumber(String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
	public String getPartyLedgerName() {
		return partyLedgerName;
	}
	public void setPartyLedgerName(String partyLedgerName) {
		this.partyLedgerName = partyLedgerName;
	}
	public String getVoucherKey() {
		return voucherKey;
	}
	public void setVoucherKey(String voucherKey) {
		this.voucherKey = voucherKey;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
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
	public String getLedgerName() {
		return ledgerName;
	}
	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}
	public String getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	
}
