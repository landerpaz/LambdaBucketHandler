package com.tally.util;

import java.util.List;

public class TallyRequestContext {
	
	private List<String> keys;
	private List<String> values1;
	private List<String> values2;
	private String reportName;
	private int reportId;
	private boolean checkFlag;
	
	
	public boolean isCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(boolean checkFlag) {
		this.checkFlag = checkFlag;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public List<String> getKeys() {
		return keys;
	}
	public void setKeys(List<String> keys) {
		this.keys = keys;
	}
	public List<String> getValues1() {
		return values1;
	}
	public void setValues1(List<String> values1) {
		this.values1 = values1;
	}
	public List<String> getValues2() {
		return values2;
	}
	public void setValues2(List<String> values2) {
		this.values2 = values2;
	}
	
}
