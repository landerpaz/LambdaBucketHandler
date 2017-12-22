package com.tally.dto;

import java.util.List;

import com.tally.vo.DayBookMasterVO;
import com.tally.vo.StockMaster;

public class TallyInputDTO {

	private String trackingID;
	private String dayBook;
	private List<DayBookMasterVO> dayBookMasterVOs;
	private String voucherKey;
	private boolean tiny;
	private List<StockMaster> stockMasters;
	
	public List<StockMaster> getStockMasters() {
		return stockMasters;
	}
	public void setStockMasters(List<StockMaster> stockMasters) {
		this.stockMasters = stockMasters;
	}
	public boolean isTiny() {
		return tiny;
	}
	public void setTiny(boolean tiny) {
		this.tiny = tiny;
	}
	public String getVoucherKey() {
		return voucherKey;
	}
	public void setVoucherKey(String voucherKey) {
		this.voucherKey = voucherKey;
	}
	public List<DayBookMasterVO> getDayBookMasterVOs() {
		return dayBookMasterVOs;
	}
	public void setDayBookMasterVOs(List<DayBookMasterVO> dayBookMasterVOs) {
		this.dayBookMasterVOs = dayBookMasterVOs;
	}
	public String getTrackingID() {
		return trackingID;
	}
	public void setTrackingID(String trackingID) {
		this.trackingID = trackingID;
	}
	public String getDayBook() {
		return dayBook;
	}
	public void setDayBook(String dayBook) {
		this.dayBook = dayBook;
	}
	
	
}
