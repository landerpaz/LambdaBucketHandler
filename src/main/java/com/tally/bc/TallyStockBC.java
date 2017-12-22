package com.tally.bc;

import static com.tally.util.Constants.ACTION;
import static com.tally.util.Constants.ALTERID;
import static com.tally.util.Constants.AMOUNT;
import static com.tally.util.Constants.BILLEDQTY;
import static com.tally.util.Constants.DATE;
import static com.tally.util.Constants.EFFECTIVEDATE;
import static com.tally.util.Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE;
import static com.tally.util.Constants.MASTERID;
import static com.tally.util.Constants.PERSISTEDVIEW;
import static com.tally.util.Constants.RATE;
import static com.tally.util.Constants.STOCKITEMNAME;
import static com.tally.util.Constants.VCHTYPE;
import static com.tally.util.Constants.VOUCHERKEY;
import static com.tally.util.Constants.VOUCHERNUMBER;
import static com.tally.util.Constants.VOUCHERTYPENAME;

import static com.tally.util.Constants.VCHTYPE_XPATH;
import static com.tally.util.Constants.ACTION_XPATH;
import static com.tally.util.Constants.DATE_XPATH;
import static com.tally.util.Constants.VOUCHERTYPENAME_XPATH;
import static com.tally.util.Constants.VOUCHERNUMBER_XPATH;
import static com.tally.util.Constants.PARTYLEDGERNAME_XPATH;
import static com.tally.util.Constants.VOUCHERKEY_XPATH;
import static com.tally.util.Constants.EFFECTIVEDATE_XPATH;
import static com.tally.util.Constants.PERSISTEDVIEW_XPATH;
import static com.tally.util.Constants.ALTERID_XPATH;
import static com.tally.util.Constants.MASTERID_XPATH;

import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIESIN_LIST_COUNT;
import static com.tally.util.Constants.STOCKITEMNAME_XPATH;
import static com.tally.util.Constants.TALLYMESSAGE_COUNT_EXP;
import static com.tally.util.Constants.TALLYMESSAGE_COUNT_EXP_TINY;
import static com.tally.util.Constants.RATE_XPATH;
import static com.tally.util.Constants.AMOUNT_XPATH;
import static com.tally.util.Constants.BILLEDQTY_XPATH;
import static com.tally.util.Constants.INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT;
import static com.tally.util.Constants.UDF_671089649_XPATH;
import static com.tally.util.Constants.UDF_671089650_XPATH;
import static com.tally.util.Constants.UDF_671089651_XPATH;
import static com.tally.util.Constants.UDF_671089652_XPATH;
import static com.tally.util.Constants.UDF_671089655_XPATH;
import static com.tally.util.Constants.UDF_671089656_XPATH;
import static com.tally.util.Constants.UDF_671089657_XPATH;
import static com.tally.util.Constants.UDF_671089660_XPATH;
import static com.tally.util.Constants.UDF_788530753_XPATH;
import static com.tally.util.Constants.UDF_788538154_XPATH;
import static com.tally.util.Constants.UDF_788538155_XPATH;
import static com.tally.util.Constants.UDF_788538156_XPATH;
import static com.tally.util.Constants.UDF_788538157_XPATH;
import static com.tally.util.Constants.UDF_788538159_XPATH;

import static com.tally.util.Constants.UDF_671089649_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089650_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089651_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089652_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089655_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089656_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089657_OUT_XPATH;
import static com.tally.util.Constants.UDF_671089660_OUT_XPATH;
import static com.tally.util.Constants.UDF_788530753_OUT_XPATH;
import static com.tally.util.Constants.UDF_788538154_OUT_XPATH;
import static com.tally.util.Constants.UDF_788538155_OUT_XPATH;
import static com.tally.util.Constants.UDF_788538156_OUT_XPATH;
import static com.tally.util.Constants.UDF_788538157_OUT_XPATH;
import static com.tally.util.Constants.UDF_788538159_OUT_XPATH;

import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIESIN_STATUS;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIESOUT_STATUS;


import static com.tally.util.Utility.getData;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import com.amazonaws.services.s3.model.S3Object;
import com.tally.dao.TallyDAO;
import com.tally.dto.TallyInputDTO;
import com.tally.util.Constants;
import com.tally.util.Utility;
import com.tally.vo.StockBatchUDF;
import com.tally.vo.StockDetail;
import com.tally.vo.StockMaster;

public class TallyStockBC {

	public static void main(String[] args) {
		try {
			TallyInputDTO tallyInputDTO = new TallyInputDTO();
			TallyStockBC tallyStockBC = new TallyStockBC();
			
			//tallyStockBC.addFromStockFile(tallyInputDTO);
			tallyStockBC.addStockData(tallyInputDTO, null, null, null);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addStockData(TallyInputDTO tallyInputDTO, S3Object s3, String key, String bucket) {
		
		long startTime = System.currentTimeMillis();
	   
		System.out.println("Parsing xml file..........");
		
		//get data from tally response
		List<StockMaster> stockMasters = addStockDataFromFile(tallyInputDTO, s3, key, bucket);
		
		System.out.println("Parsed xml file. Time taken : " + (System.currentTimeMillis() - startTime));
		
		//insert data in DB
		if(null != stockMasters && stockMasters.size() > 0) {
		
			System.out.println("Inserting data in DB..........");
			
			startTime = System.currentTimeMillis();
			
			tallyInputDTO.setStockMasters(stockMasters);
			TallyDAO tallyDAO = new TallyDAO();
			tallyDAO.addTallyStock(tallyInputDTO);
			
			System.out.println("Inserted data in DB. Time taken : " + (System.currentTimeMillis() - startTime));
		}
		
		//return response;
		
	}
	
	private List<StockMaster> addFromStockFile(TallyInputDTO tallyInputVO) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		StockMaster stockMaster = null;
		StockDetail stockDetail = null;
		StockBatchUDF stockBatchUDF = null;
		List<StockMaster> stockMasters = new ArrayList<>();
		
		try {
			
			builder = factory.newDocumentBuilder();
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/2.xml");
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/day_book.xml");
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/test_file_1.xml");
			//doc = builder.parse("/Users/ashokarulsamy/Downloads/db_923-2.xml");
			
			doc = builder.parse("/Users/ashokarulsamy/Downloads/stock1.xml");
			
            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, Constants.TALLYMESSAGE_COUNT_EXP);
            System.out.println("tallyMessageCount : " + tallyMessageCount);
            
            for(int index=1; index<=tallyMessageCount; index++) {
            	
            	//testing - start
            	/*System.out.println("------------------------index : " + index + " : ----------------------------");
            	int INVENTORYENTRIESIN = Utility.getCount(doc, xpath, new StringBuilder("count(//ENVELOPE/BODY/DATA/TALLYMESSAGE[").append(index).append("]/VOUCHER/INVENTORYENTRIESIN.LIST)").toString());
            	int BATCHALLOCATIONS = 0;
            	for(int secondIndex=0; secondIndex<INVENTORYENTRIESIN; secondIndex++) {
            		BATCHALLOCATIONS = Utility.getCount(doc, xpath, new StringBuilder("count(//ENVELOPE/BODY/DATA/TALLYMESSAGE[").append(index).append("]/VOUCHER/INVENTORYENTRIESIN.LIST[").append(secondIndex).append("]/BATCHALLOCATIONS.LIST)").toString());
                	//System.out.println("BATCHALLOCATIONS : " + BATCHALLOCATIONS);
            		for(int thirdIndex=0; thirdIndex<BATCHALLOCATIONS; thirdIndex++) {
            			XPathExpression expr = xpath.compile(new StringBuilder("//ENVELOPE/BODY/DATA/TALLYMESSAGE[").append(index).
            					append("]/VOUCHER/INVENTORYENTRIESIN.LIST[").append(secondIndex).append("]/BATCHALLOCATIONS.LIST[")
            					.append(thirdIndex).append("]/UDF_UDF_671089650.LIST//UDF_UDF_671089650").toString());
            			
            	        String result = (String) expr.evaluate(doc, XPathConstants.STRING);
                        System.out.println("result : " + result);
                     }
            	}*/
            	//testing - end
            	
            	
            	/*System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VCHTYPE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.ACTION, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.DATE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHERTYPENAME, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHERNUMBER, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHERKEY, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.EFFECTIVEDATE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.PERSISTEDVIEW, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.ALTERID, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.MASTERID, index));
            	System.out.println("----------------------");*/
            	
            	stockMaster = new StockMaster();
            	stockMaster.setVoucherType(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VCHTYPE, index));
            	stockMaster.setAction(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, ACTION, index));
            	stockMaster.setVoucherDate(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, DATE, index));
            	stockMaster.setVoucherTypeName(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERTYPENAME, index));
            	stockMaster.setVoucherNumber(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERNUMBER, index));
            	stockMaster.setVoucherKey(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERKEY, index));
            	stockMaster.setVoucherEffectiveDate(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, EFFECTIVEDATE, index));
            	stockMaster.setPersistedView(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, PERSISTEDVIEW, index));
            	stockMaster.setAlterId(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, ALTERID, index));
            	stockMaster.setMasterId(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, MASTERID, index));
            	
            	
            	//get inventoryEntriesInListCount
            	int inventoryEntriesInListCount = Utility.getCount(doc, xpath, new StringBuilder(Constants.VOUCHER_INVENTORYENTRIESIN_LIST_COUNT_1).append(index).append(Constants.VOUCHER_INVENTORYENTRIESIN_LIST_COUNT_2).toString());
            	System.out.println("inventoryEntriesInListCount : " + inventoryEntriesInListCount);
            	
            	List<StockDetail> stockDetails = new ArrayList<>();
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesInListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, STOCKITEMNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, RATE, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, BILLEDQTY, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.ACTUALQTY, index, subIndex));
            		System.out.println("----------------------");
            		*/
            		stockDetail = new StockDetail();
            		stockDetail.setStockItemName(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, STOCKITEMNAME, index, subIndex));
            		stockDetail.setRate(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, RATE, index, subIndex));
            		stockDetail.setAmount(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, AMOUNT, index, subIndex));
            		stockDetail.setBilledQty(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, BILLEDQTY, index, subIndex));
            		stockDetail.setStatus("IN");
            		
            		//get inventoryEntriesInBatchAllocationListCount
                	int inventoryEntriesInBatchAllocationListCount = Utility.getCount(doc, xpath, new StringBuilder(Constants.INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT_1).append(index).append(Constants.INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT_2).append(subIndex).append(Constants.BATCHALLOCATIONS_LIST_COUNT_3).toString());
                	System.out.println("inventoryEntriesInBatchAllocationListCount : " + inventoryEntriesInBatchAllocationListCount);
                
                	List<StockBatchUDF> stockBatchUDFs = new ArrayList<>();
                	
                	for(int thirdIndex=1; thirdIndex<=inventoryEntriesInBatchAllocationListCount; thirdIndex++) {
                		/*System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089649, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089652, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089655, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089656, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089657, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089660, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788530753, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538154, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538155, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538156, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538157, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538159, index, subIndex, thirdIndex));
                		System.out.println("----------------------");*/
                		
                		stockBatchUDF = new StockBatchUDF();
                		stockBatchUDF.setUDF_671089649(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089649, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089650(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089651(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089652(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089652, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089655(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089655, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089656(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089656, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089657(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089657, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089660(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089660, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788530753(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788530753, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538154(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538154, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538155(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538155, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538156(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538156, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538157(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538157, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538159(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESIN_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538159, index, subIndex, thirdIndex));
                		
                		stockBatchUDFs.add(stockBatchUDF);
                	}
                	
                	stockDetail.setBatchUDF(stockBatchUDFs);
                	stockDetails.add(stockDetail);
            	}
            	
            	
            	
            	
            	//get inventoryEntriesOutListCount
            	int inventoryEntriesOutListCount = Utility.getCount(doc, xpath, new StringBuilder(Constants.VOUCHER_INVENTORYENTRIESOUT_LIST_COUNT_1).append(index).append(Constants.VOUCHER_INVENTORYENTRIESOUT_LIST_COUNT_2).toString());
            	System.out.println("inventoryEntriesOutListCount : " + inventoryEntriesOutListCount);
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesOutListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, STOCKITEMNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, RATE, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, BILLEDQTY, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.ACTUALQTY, index, subIndex));
            		System.out.println("----------------------");*/
            		
            		stockDetail = new StockDetail();
            		stockDetail.setStockItemName(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, STOCKITEMNAME, index, subIndex));
            		stockDetail.setRate(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, RATE, index, subIndex));
            		stockDetail.setAmount(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, AMOUNT, index, subIndex));
            		stockDetail.setBilledQty(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, BILLEDQTY, index, subIndex));
            		stockDetail.setStatus("OUT");
            		
            		//get inventoryEntriesInBatchAllocationListCount
                	int inventoryEntriesOutBatchAllocationListCount = Utility.getCount(doc, xpath, new StringBuilder(Constants.INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT_1).append(index).append(Constants.INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT_2).append(subIndex).append(Constants.BATCHALLOCATIONS_LIST_COUNT_3).toString());
                	System.out.println("inventoryEntriesInBatchAllocationListCount : " + inventoryEntriesOutBatchAllocationListCount);
                
                	List<StockBatchUDF> stockBatchUDFs = new ArrayList<>();
                	
                	for(int thirdIndex=1; thirdIndex<=inventoryEntriesOutBatchAllocationListCount; thirdIndex++) {
                		/*System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089649, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089652, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089655, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089656, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089657, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089660, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788530753, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538154, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538155, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538156, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538157, index, subIndex, thirdIndex));
                		System.out.println(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538159, index, subIndex, thirdIndex));
                		System.out.println("----------------------");
                	*/	
                		stockBatchUDF = new StockBatchUDF();
                		stockBatchUDF.setUDF_671089649(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089649, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089650(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089651(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089650, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089652(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089652, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089655(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089655, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089656(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089656, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089657(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089657, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089660(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_671089660, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788530753(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788530753, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538154(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538154, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538155(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538155, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538156(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538156, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538157(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538157, index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538159(Utility.getThirdData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST, Constants.BATCHALLOCATIONS_LIST, Constants.UDF_788538159, index, subIndex, thirdIndex));
                		
                		stockBatchUDFs.add(stockBatchUDF);
                	}
                	
                	stockDetail.setBatchUDF(stockBatchUDFs);
                	stockDetails.add(stockDetail);
            	}
            	
            	stockMaster.setStockDetails(stockDetails);
            	stockMasters.add(stockMaster);
            	
            	//System.out.println("########################################################");
            	
            }
            
            System.out.println("Done.................");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return stockMasters;
		
	}
	
	private List<StockMaster> addStockDataFromFile(TallyInputDTO tallyInputDTO, S3Object s3object, String key, String bucket) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		StockMaster stockMaster = null;
		StockDetail stockDetail = null;
		StockBatchUDF stockBatchUDF = null;
		List<StockMaster> stockMasters = new ArrayList<>();
		String currentTimeForRandomNumber = null;
		
		try {
			
			currentTimeForRandomNumber = Utility.getCurrentdateForRandomNumber();
			
			/*************************************************Read data from bucket - START*************************************************/
			builder = factory.newDocumentBuilder();
            doc = builder.parse(s3object.getObjectContent());

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, Constants.TALLYMESSAGE_COUNT_EXP1);
            /*************************************************Read data from bucket - END*************************************************/
            
            /*************************************************Read data from local File for testing - START*************************************************/
			/*builder = factory.newDocumentBuilder();
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/stock1.xml");
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/TALLY_STOCK__2017-10-21-12-56-22.xml");
            doc = builder.parse("/Users/ashokarulsamy/Downloads/Temp1.xml");
            
            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, TALLYMESSAGE_COUNT_EXP);*/
            /*************************************************Read data from local File for testing - END*************************************************/
            
            System.out.println("tallyMessageCount : " + tallyMessageCount);
            
            for(int index=1; index<=tallyMessageCount; index++) {
            	
            	System.out.println("tallyMessageCount index : " + index);
            	
            	stockMaster = new StockMaster();
            	stockMaster.setVoucherType(getData(doc, xpath, Constants.VCHTYPE_XPATH1, index));
            	stockMaster.setAction(getData(doc, xpath, Constants.ACTION_XPATH1, index));
            	stockMaster.setVoucherDate(getData(doc, xpath, Constants.DATE_XPATH1, index));
            	stockMaster.setVoucherTypeName(getData(doc, xpath, Constants.VOUCHERTYPENAME_XPATH1, index));
            	stockMaster.setVoucherNumber(getData(doc, xpath, Constants.VOUCHERNUMBER_XPATH1, index));
            	stockMaster.setVoucherKey(getData(doc, xpath, Constants.VOUCHERKEY_XPATH1, index));
            	stockMaster.setVoucherEffectiveDate(getData(doc, xpath, Constants.EFFECTIVEDATE_XPATH1, index));
            	stockMaster.setPersistedView(getData(doc, xpath, Constants.PERSISTEDVIEW_XPATH1, index));
            	stockMaster.setAlterId(getData(doc, xpath, Constants.ALTERID_XPATH1, index));
            	stockMaster.setMasterId(getData(doc, xpath, Constants.MASTERID_XPATH1, index));
            	
            	//get inventoryEntriesInListCount
            	int inventoryEntriesInListCount = Utility.getCount(doc, xpath, Constants.VOUCHER_INVENTORYENTRIESIN_LIST_COUNT1, index);
            	//System.out.println("inventoryEntriesInListCount : " + inventoryEntriesInListCount);
            	
            	List<StockDetail> stockDetails = new ArrayList<>();
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesInListCount; subIndex++) {
            		
            		stockDetail = new StockDetail();
            		stockDetail.setStockDetailsId(Utility.getRandomNumber(currentTimeForRandomNumber));
            		stockDetail.setStockItemName(getData(doc, xpath, Constants.STOCKITEMNAME_XPATH1, index, subIndex));
            		stockDetail.setRate(getData(doc, xpath, Constants.RATE_XPATH1, index, subIndex));
            		stockDetail.setAmount(getData(doc, xpath, Constants.AMOUNT_XPATH1, index, subIndex));
            		stockDetail.setBilledQty(getData(doc, xpath, Constants.BILLEDQTY_XPATH1, index, subIndex));
            		stockDetail.setStatus(Constants.VOUCHER_INVENTORYENTRIESIN_STATUS);
            		
            		//get inventoryEntriesInBatchAllocationListCount
                	int inventoryEntriesInBatchAllocationListCount = Utility.getCount(doc, xpath,Constants.INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT1 ,index, subIndex);
                	//System.out.println("inventoryEntriesInBatchAllocationListCount : " + inventoryEntriesInBatchAllocationListCount);
                
                	List<StockBatchUDF> stockBatchUDFs = new ArrayList<>();
                	
                	for(int thirdIndex=1; thirdIndex<=inventoryEntriesInBatchAllocationListCount; thirdIndex++) {
                		
                		stockBatchUDF = new StockBatchUDF();
                		stockBatchUDF.setUDF_671089649(Utility.getData(doc, xpath, Constants.UDF_671089649_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089650(Utility.getData(doc, xpath, Constants.UDF_671089650_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089651(Utility.getData(doc, xpath, Constants.UDF_671089651_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089652(Utility.getData(doc, xpath, Constants.UDF_671089652_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089655(Utility.getData(doc, xpath, Constants.UDF_671089655_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089656(Utility.getData(doc, xpath, Constants.UDF_671089656_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089657(Utility.getData(doc, xpath, Constants.UDF_671089657_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089660(Utility.getData(doc, xpath, Constants.UDF_671089660_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788530753(Utility.getData(doc, xpath, Constants.UDF_788530753_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538154(Utility.getData(doc, xpath, Constants.UDF_788538154_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538155(Utility.getData(doc, xpath, Constants.UDF_788538155_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538156(Utility.getData(doc, xpath, Constants.UDF_788538156_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538157(Utility.getData(doc, xpath, Constants.UDF_788538157_XPATH1 ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538159(Utility.getData(doc, xpath, Constants.UDF_788538159_XPATH1 ,index, subIndex, thirdIndex));
                		
                		stockBatchUDFs.add(stockBatchUDF);
                	}
                	
                	stockDetail.setBatchUDF(stockBatchUDFs);
                	stockDetails.add(stockDetail);
            	}
            	
            	//get inventoryEntriesOutListCount
            	int inventoryEntriesOutListCount = Utility.getCount(doc, xpath, Constants.VOUCHER_INVENTORYENTRIESOUT_LIST_COUNT1,index);
            	//System.out.println("inventoryEntriesOutListCount : " + inventoryEntriesOutListCount);
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesOutListCount; subIndex++) {
            		
            		stockDetail = new StockDetail();
            		stockDetail.setStockDetailsId(Utility.getRandomNumber(currentTimeForRandomNumber));
            		stockDetail.setStockItemName(getData(doc, xpath, Constants.STOCKITEMNAME_XPATH1, index, subIndex));
            		stockDetail.setRate(getData(doc, xpath, Constants.RATE_XPATH1, index, subIndex));
            		stockDetail.setAmount(getData(doc, xpath, Constants.AMOUNT_XPATH1, index, subIndex));
            		stockDetail.setBilledQty(getData(doc, xpath, Constants.BILLEDQTY_XPATH1, index, subIndex));
            		stockDetail.setStatus(VOUCHER_INVENTORYENTRIESOUT_STATUS);
            		
            		//get inventoryEntriesInBatchAllocationListCount
                	/*int inventoryEntriesOutBatchAllocationListCount = Utility.getCount(doc, xpath,INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT ,index, subIndex);
                	//System.out.println("inventoryEntriesInBatchAllocationListCount : " + inventoryEntriesOutBatchAllocationListCount);
                
                	List<StockBatchUDF> stockBatchUDFs = new ArrayList<>();
                	
                	for(int thirdIndex=1; thirdIndex<=inventoryEntriesOutBatchAllocationListCount; thirdIndex++) {
                		stockBatchUDF = new StockBatchUDF();
                		stockBatchUDF.setUDF_671089649(Utility.getData(doc, xpath, UDF_671089649_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089650(Utility.getData(doc, xpath, UDF_671089650_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089651(Utility.getData(doc, xpath, UDF_671089651_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089652(Utility.getData(doc, xpath, UDF_671089652_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089655(Utility.getData(doc, xpath, UDF_671089655_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089656(Utility.getData(doc, xpath, UDF_671089656_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089657(Utility.getData(doc, xpath, UDF_671089657_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089660(Utility.getData(doc, xpath, UDF_671089660_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788530753(Utility.getData(doc, xpath, UDF_788530753_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538154(Utility.getData(doc, xpath, UDF_788538154_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538155(Utility.getData(doc, xpath, UDF_788538155_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538156(Utility.getData(doc, xpath, UDF_788538156_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538157(Utility.getData(doc, xpath, UDF_788538157_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538159(Utility.getData(doc, xpath, UDF_788538159_OUT_XPATH ,index, subIndex, thirdIndex));
                		
                		stockBatchUDFs.add(stockBatchUDF);
                	}
                	
                	stockDetail.setBatchUDF(stockBatchUDFs);*/
            		
                	stockDetails.add(stockDetail);
            	}
            	
            	stockMaster.setStockDetails(stockDetails);
            	stockMasters.add(stockMaster);
            	//System.out.println("########################################################");
            }
            
            //System.out.println("Done.................");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stockMasters;
	}
	
	private List<StockMaster> addStockDataFromFile_bk(TallyInputDTO tallyInputDTO, S3Object s3object, String key, String bucket) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		StockMaster stockMaster = null;
		StockDetail stockDetail = null;
		StockBatchUDF stockBatchUDF = null;
		List<StockMaster> stockMasters = new ArrayList<>();
		String currentTimeForRandomNumber = null;
		
		try {
			
			currentTimeForRandomNumber = Utility.getCurrentdateForRandomNumber();
			
			/*************************************************Read data from bucket - START*************************************************/
			builder = factory.newDocumentBuilder();
            doc = builder.parse(s3object.getObjectContent());

            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, TALLYMESSAGE_COUNT_EXP);
            /*************************************************Read data from bucket - END*************************************************/
            
            /*************************************************Read data from local File for testing - START*************************************************/
			/*builder = factory.newDocumentBuilder();
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/stock1.xml");
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/TALLY_STOCK__2017-10-21-12-56-22.xml");
            doc = builder.parse("/Users/ashokarulsamy/Downloads/Temp1.xml");
            
            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, TALLYMESSAGE_COUNT_EXP);*/
            /*************************************************Read data from local File for testing - END*************************************************/
            
            System.out.println("tallyMessageCount : " + tallyMessageCount);
            
            for(int index=1; index<=tallyMessageCount; index++) {
            	
            	System.out.println("tallyMessageCount index : " + index);
            	
            	stockMaster = new StockMaster();
            	stockMaster.setVoucherType(getData(doc, xpath, VCHTYPE_XPATH, index));
            	stockMaster.setAction(getData(doc, xpath, ACTION_XPATH, index));
            	stockMaster.setVoucherDate(getData(doc, xpath, DATE_XPATH, index));
            	stockMaster.setVoucherTypeName(getData(doc, xpath, VOUCHERTYPENAME_XPATH, index));
            	stockMaster.setVoucherNumber(getData(doc, xpath, VOUCHERNUMBER_XPATH, index));
            	stockMaster.setVoucherKey(getData(doc, xpath, VOUCHERKEY_XPATH, index));
            	stockMaster.setVoucherEffectiveDate(getData(doc, xpath, EFFECTIVEDATE_XPATH, index));
            	stockMaster.setPersistedView(getData(doc, xpath, PERSISTEDVIEW_XPATH, index));
            	stockMaster.setAlterId(getData(doc, xpath, ALTERID_XPATH, index));
            	stockMaster.setMasterId(getData(doc, xpath, MASTERID_XPATH, index));
            	
            	//get inventoryEntriesInListCount
            	int inventoryEntriesInListCount = Utility.getCount(doc, xpath, VOUCHER_INVENTORYENTRIESIN_LIST_COUNT, index);
            	//System.out.println("inventoryEntriesInListCount : " + inventoryEntriesInListCount);
            	
            	List<StockDetail> stockDetails = new ArrayList<>();
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesInListCount; subIndex++) {
            		
            		stockDetail = new StockDetail();
            		stockDetail.setStockDetailsId(Utility.getRandomNumber(currentTimeForRandomNumber));
            		stockDetail.setStockItemName(getData(doc, xpath, STOCKITEMNAME_XPATH, index, subIndex));
            		stockDetail.setRate(getData(doc, xpath, RATE_XPATH, index, subIndex));
            		stockDetail.setAmount(getData(doc, xpath, AMOUNT_XPATH, index, subIndex));
            		stockDetail.setBilledQty(getData(doc, xpath, BILLEDQTY_XPATH, index, subIndex));
            		stockDetail.setStatus(VOUCHER_INVENTORYENTRIESIN_STATUS);
            		
            		//get inventoryEntriesInBatchAllocationListCount
                	int inventoryEntriesInBatchAllocationListCount = Utility.getCount(doc, xpath,INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT ,index, subIndex);
                	//System.out.println("inventoryEntriesInBatchAllocationListCount : " + inventoryEntriesInBatchAllocationListCount);
                
                	List<StockBatchUDF> stockBatchUDFs = new ArrayList<>();
                	
                	for(int thirdIndex=1; thirdIndex<=inventoryEntriesInBatchAllocationListCount; thirdIndex++) {
                		
                		stockBatchUDF = new StockBatchUDF();
                		stockBatchUDF.setUDF_671089649(Utility.getData(doc, xpath, UDF_671089649_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089650(Utility.getData(doc, xpath, UDF_671089650_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089651(Utility.getData(doc, xpath, UDF_671089651_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089652(Utility.getData(doc, xpath, UDF_671089652_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089655(Utility.getData(doc, xpath, UDF_671089655_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089656(Utility.getData(doc, xpath, UDF_671089656_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089657(Utility.getData(doc, xpath, UDF_671089657_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089660(Utility.getData(doc, xpath, UDF_671089660_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788530753(Utility.getData(doc, xpath, UDF_788530753_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538154(Utility.getData(doc, xpath, UDF_788538154_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538155(Utility.getData(doc, xpath, UDF_788538155_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538156(Utility.getData(doc, xpath, UDF_788538156_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538157(Utility.getData(doc, xpath, UDF_788538157_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538159(Utility.getData(doc, xpath, UDF_788538159_XPATH ,index, subIndex, thirdIndex));
                		
                		stockBatchUDFs.add(stockBatchUDF);
                	}
                	
                	stockDetail.setBatchUDF(stockBatchUDFs);
                	stockDetails.add(stockDetail);
            	}
            	
            	//get inventoryEntriesOutListCount
            	int inventoryEntriesOutListCount = Utility.getCount(doc, xpath, VOUCHER_INVENTORYENTRIESIN_LIST_COUNT,index);
            	//System.out.println("inventoryEntriesOutListCount : " + inventoryEntriesOutListCount);
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesOutListCount; subIndex++) {
            		
            		stockDetail = new StockDetail();
            		stockDetail.setStockDetailsId(Utility.getRandomNumber(currentTimeForRandomNumber));
            		stockDetail.setStockItemName(getData(doc, xpath, STOCKITEMNAME_XPATH, index, subIndex));
            		stockDetail.setRate(getData(doc, xpath, RATE_XPATH, index, subIndex));
            		stockDetail.setAmount(getData(doc, xpath, AMOUNT_XPATH, index, subIndex));
            		stockDetail.setBilledQty(getData(doc, xpath, BILLEDQTY_XPATH, index, subIndex));
            		stockDetail.setStatus(VOUCHER_INVENTORYENTRIESOUT_STATUS);
            		
            		//get inventoryEntriesInBatchAllocationListCount
                	/*int inventoryEntriesOutBatchAllocationListCount = Utility.getCount(doc, xpath,INVENTORYENTRIESIN_BATCHALLOCATIONS_LIST_COUNT ,index, subIndex);
                	//System.out.println("inventoryEntriesInBatchAllocationListCount : " + inventoryEntriesOutBatchAllocationListCount);
                
                	List<StockBatchUDF> stockBatchUDFs = new ArrayList<>();
                	
                	for(int thirdIndex=1; thirdIndex<=inventoryEntriesOutBatchAllocationListCount; thirdIndex++) {
                		stockBatchUDF = new StockBatchUDF();
                		stockBatchUDF.setUDF_671089649(Utility.getData(doc, xpath, UDF_671089649_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089650(Utility.getData(doc, xpath, UDF_671089650_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089651(Utility.getData(doc, xpath, UDF_671089651_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089652(Utility.getData(doc, xpath, UDF_671089652_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089655(Utility.getData(doc, xpath, UDF_671089655_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089656(Utility.getData(doc, xpath, UDF_671089656_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089657(Utility.getData(doc, xpath, UDF_671089657_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_671089660(Utility.getData(doc, xpath, UDF_671089660_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788530753(Utility.getData(doc, xpath, UDF_788530753_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538154(Utility.getData(doc, xpath, UDF_788538154_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538155(Utility.getData(doc, xpath, UDF_788538155_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538156(Utility.getData(doc, xpath, UDF_788538156_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538157(Utility.getData(doc, xpath, UDF_788538157_OUT_XPATH ,index, subIndex, thirdIndex));
                		stockBatchUDF.setUDF_788538159(Utility.getData(doc, xpath, UDF_788538159_OUT_XPATH ,index, subIndex, thirdIndex));
                		
                		stockBatchUDFs.add(stockBatchUDF);
                	}
                	
                	stockDetail.setBatchUDF(stockBatchUDFs);*/
            		
                	stockDetails.add(stockDetail);
            	}
            	
            	stockMaster.setStockDetails(stockDetails);
            	stockMasters.add(stockMaster);
            	//System.out.println("########################################################");
            }
            
            //System.out.println("Done.................");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stockMasters;
	}

}
