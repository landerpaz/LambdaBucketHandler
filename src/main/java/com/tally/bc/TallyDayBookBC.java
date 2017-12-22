package com.tally.bc;

import static com.tally.util.Constants.ACTION;
import static com.tally.util.Constants.ALTERID;
import static com.tally.util.Constants.AMOUNT;
import static com.tally.util.Constants.BILLEDQTY;
import static com.tally.util.Constants.DATE;
import static com.tally.util.Constants.EFFECTIVEDATE;
import static com.tally.util.Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE;
import static com.tally.util.Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY;
import static com.tally.util.Constants.LEDGERNAME;
import static com.tally.util.Constants.MASTERID;
import static com.tally.util.Constants.PARTYLEDGERNAME;
import static com.tally.util.Constants.PERSISTEDVIEW;
import static com.tally.util.Constants.RATE;
import static com.tally.util.Constants.STOCKITEMNAME;
import static com.tally.util.Constants.TALLYMESSAGE_COUNT_EXP;
import static com.tally.util.Constants.TALLYMESSAGE_COUNT_EXP_TINY;
import static com.tally.util.Constants.VCHTYPE;
import static com.tally.util.Constants.VOUCHERKEY;
import static com.tally.util.Constants.VOUCHERNUMBER;
import static com.tally.util.Constants.VOUCHERTYPENAME;
import static com.tally.util.Constants.VOUCHER_ALLLEDGERENTRIES_LIST;
import static com.tally.util.Constants.VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_1;
import static com.tally.util.Constants.VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_1_TINY;
import static com.tally.util.Constants.VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_2;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIES_IN_LIST;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIES_IN_LIST_COUNT_2;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIES_LIST;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIES_LIST_COUNT_1;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIES_LIST_COUNT_1_TINY;
import static com.tally.util.Constants.VOUCHER_INVENTORYENTRIES_LIST_COUNT_2;
import static com.tally.util.Constants.VOUCHER_LEDGERENTRIES_LIST;
import static com.tally.util.Constants.VOUCHER_LEDGERENTRIES_LIST_COUNT_1;
import static com.tally.util.Constants.VOUCHER_LEDGERENTRIES_LIST_COUNT_1_TINY;
import static com.tally.util.Constants.VOUCHER_LEDGERENTRIES_LIST_COUNT_2;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.amazonaws.services.s3.model.S3Object;
import com.tally.dao.TallyDAO;
//import com.tally.dto.Response;
import com.tally.dto.TallyInputDTO;
import com.tally.util.Constants;
import com.tally.util.Utility;
//import com.tally.util.Utility;
import com.tally.vo.DayBookMasterVO;
import com.tally.vo.InventoryEntryVO;
import com.tally.vo.LedgerEntryVO;
import com.tally.vo.StockBatchUDF;
import com.tally.vo.StockDetail;
import com.tally.vo.StockMaster;

public class TallyDayBookBC {
	
	//private final Logger LOG = LoggerFactory.getLogger(TallyDayBookBC.class);
	
	private String formatString(String xpath, Object...objects) {
		
		return MessageFormat.format(xpath, objects);
	}
	
	public static void main(String[] args) {
		//TallyDayBookBC tallyDayBookBC = new TallyDayBookBC();
		//Response response = tallyDayBookBC.addTallyDayBookData(new TallyInputDTO());
		//tallyDayBookBC.getTallyDayBookData(new TallyInputDTO());
		
		/*InventoryEntryVO inventoryEntryVO = new InventoryEntryVO();
		inventoryEntryVO.setRate("25000.00/Ton");
		inventoryEntryVO.setBilledQuantity("102.000 Kgs = 1.200 Ton");
		Utility.formatInventory(inventoryEntryVO);
		
		System.out.println(inventoryEntryVO.getRate());
		System.out.println(inventoryEntryVO.getBilledQuantity());*/
		
		try {
			
			
			//message format - start
			
			/*TallyDayBookBC tallyDayBookBC = new TallyDayBookBC();
			String formattedXpth = tallyDayBookBC.formatString("//hello/hi[{0}]/wow[{1}]//true[{2}]//go", 10, 20, 30);
			System.out.println(formattedXpth);*/
			//message format - end
			
			
			/*Path path = Paths.get("/Users/ashokarulsamy/Downloads/day_book_latest.xml");
			Charset charset = StandardCharsets.UTF_8;

			String content = new String(Files.readAllBytes(path), charset);
			content = content.replaceAll("&#", "");
			Files.write(path, content.getBytes(charset));*/
			
			
			/*TallyInputDTO tallyInputDTO = new TallyInputDTO();
			tallyInputDTO.setTiny(false);
			String bucket = "tallyselvabk";
			//String key = "REQUEST_XML_DAY_BOOK__2017-10-14-01-56-31.xml";
			String key ="day_book_latest.xml";
			
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJVV726YW43DBG4TA", "LyMMDpZ1wgcIZ7InITAl/DQOpdVtXQrFOW7HKvFR");
		    AmazonS3 s3 = AmazonS3ClientBuilder.standard()
		    						.withRegion(Regions.fromName("ap-south-1"))
                                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
		                            .build();
		    
		    TallyDayBookBC tallyDayBookBC = new TallyDayBookBC();
		    tallyDayBookBC.addTallyDayBookData(tallyInputDTO, s3, key, bucket);*/
			
			/*TallyInputDTO tallyInputVO = new TallyInputDTO();
			TallyDayBookBC tallyDayBookBC = new TallyDayBookBC();
			tallyDayBookBC.addFromStockFile(tallyInputVO);
			*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    
	}
	
	//public void addTallyDayBookData(TallyInputDTO tallyInputDTO, AmazonS3 s3, String key, String bucket) throws Exception {
	//public void addTallyDayBookData(TallyInputDTO tallyInputDTO, AmazonS3 s3, String key, String bucket)  {
	public void addTallyDayBookData(TallyInputDTO tallyInputDTO, S3Object s3, String key, String bucket)  {
		
		//long startTime = System.currentTimeMillis();
	   
		//Response response = new Response();
		
		//LOG.info(LOG_BASE_FORMAT, tallyInputDTO.getTrackingID(), "addTallyDayBookData In");
		//LOG.info(LOG_BASE_FORMAT, tallyInputDTO.getTrackingID(), "addTallyDayBookData, xml parsing started");
		 
		//get data from xml file
		//List<DayBookMasterVO> dayBookMasterVOs = addFromFile(tallyInputDTO);
		
		System.out.println("Parsing xml file..........");
		
		//get data from tally response
		List<DayBookMasterVO> dayBookMasterVOs = addFromRequest(tallyInputDTO, s3, key, bucket);
		
		System.out.println("Inserting data in DB..........");
		
		//LOG.info(LOG_DATA_FORMAT, tallyInputDTO.getTrackingID(), "addTallyDayBookData, xml parsing completed", "time_elapsed:" + (startTime - System.currentTimeMillis()));
	    
		//insert data in DB
		if(null != dayBookMasterVOs && dayBookMasterVOs.size() > 0) {
		
			//startTime = System.currentTimeMillis();
			//LOG.info(LOG_BASE_FORMAT, tallyInputDTO.getTrackingID(), "addTallyDayBookData, insert data in DB started");
			
			//startTime = System.currentTimeMillis();
			
			tallyInputDTO.setDayBookMasterVOs(dayBookMasterVOs);
			TallyDAO tallyDAO = new TallyDAO();
			//response = tallyDAO.addTallyDayBook(tallyInputDTO);
			tallyDAO.addTallyDayBook(tallyInputDTO);
			
			//LOG.info(LOG_DATA_FORMAT, tallyInputDTO.getTrackingID(), "addTallyDayBookData, insert data in DB completed", "time_elapsed:" + (startTime - System.currentTimeMillis()));
			
		}
		
		//return response;
		
	}
	
	private List<DayBookMasterVO> addFromFile(TallyInputDTO tallyInputVO) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		DayBookMasterVO dayBookMasterVO = null;
		LedgerEntryVO ledgerEntryVO = null;
		InventoryEntryVO inventoryEntryVO = null;
		List<DayBookMasterVO> dayBookMasterVOs = new ArrayList<>();
		
		try {
			
			builder = factory.newDocumentBuilder();
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/2.xml");
            //doc = builder.parse("/Users/ashokarulsamy/Downloads/day_book.xml");
            doc = builder.parse("/Users/ashokarulsamy/Downloads/test_file_1.xml");
			//doc = builder.parse("/Users/ashokarulsamy/Downloads/db_923-2.xml");
			
            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, Constants.TALLYMESSAGE_COUNT_EXP);
            
            System.out.println("tallyMessageCount : " + tallyMessageCount);
            
            for(int index=1; index<=tallyMessageCount; index++) {
            	/*System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VCHTYPE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.ACTION, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.DATE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHERTYPENAME, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHERNUMBER, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.PARTYLEDGERNAME, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.VOUCHERKEY, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.EFFECTIVEDATE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.PERSISTEDVIEW, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.ALTERID, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, Constants.ENVELOPE_BODY_DATA_TALLYMESSAGE, Constants.MASTERID, index));
            	System.out.println("----------------------");*/
            	
            	dayBookMasterVO = new DayBookMasterVO();
            	dayBookMasterVO.setVoucherType(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VCHTYPE, index));
            	dayBookMasterVO.setVoucherAction(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, ACTION, index));
            	dayBookMasterVO.setVoucherDate(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, DATE, index));
            	dayBookMasterVO.setVoucherTypeName(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERTYPENAME, index));
            	dayBookMasterVO.setVoucherNumber(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERNUMBER, index));
            	dayBookMasterVO.setPartyLedgerName(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, PARTYLEDGERNAME, index));
            	dayBookMasterVO.setVoucherKey(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERKEY, index));
            	dayBookMasterVO.setEffectiveDate(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, EFFECTIVEDATE, index));
            	dayBookMasterVO.setPersistedView(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, PERSISTEDVIEW, index));
            	dayBookMasterVO.setAlterId(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, ALTERID, index));
            	dayBookMasterVO.setMasterId(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, MASTERID, index));
            	
            	
            	//get allledgerentries
            	int allLedgerEntriesListCount = Utility.getCount(doc, xpath, new StringBuilder(VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_2).toString());
            	
            	//System.out.println("allLedgerEntriesListCount : " + allLedgerEntriesListCount);
            	
            	List<LedgerEntryVO> ledgerEntryVOs = new ArrayList<>();
            	for(int subIndex=1; subIndex<=allLedgerEntriesListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, ISPARTYLEDGER, index, subIndex));
            		System.out.println("----------------------");*/
            		
            		ledgerEntryVO = new LedgerEntryVO();
            		ledgerEntryVO.setLedgerName(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		ledgerEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		ledgerEntryVOs.add(ledgerEntryVO);
            	}
            	
            	
            	int ledgerEntriesListCount = Utility.getCount(doc, xpath, new StringBuilder(VOUCHER_LEDGERENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_LEDGERENTRIES_LIST_COUNT_2).toString());
            	
            	//System.out.println("ledgerEntriesListCount : " + ledgerEntriesListCount);
            	
            	for(int subIndex=1; subIndex<=ledgerEntriesListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println("----------------------");*/
            		
            		ledgerEntryVO = new LedgerEntryVO();
            		ledgerEntryVO.setLedgerName(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		ledgerEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		ledgerEntryVOs.add(ledgerEntryVO);
            		
            	}
            	
            	//set ledger list in master vo in one place for both allledgerentrieslist and ledgerentrieslist, because either one will be available not both for same tallmessage
            	dayBookMasterVO.setLedgerEntryVOs(ledgerEntryVOs);
            	
            	
            	//get inventoryentries
            	int inventoryEntriesListCount = Utility.getCount(doc, xpath, new StringBuilder(VOUCHER_INVENTORYENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_INVENTORYENTRIES_LIST_COUNT_2).toString());
            	
            	//System.out.println("inventoryEntriesListCount : " + inventoryEntriesListCount);
            	
            	List<InventoryEntryVO> inventoryEntryVOs = new ArrayList<>();
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, STOCKITEMNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, RATE, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, BILLEDQTY, index, subIndex));
            		
            		System.out.println("----------------------");*/
            		
            		inventoryEntryVO = new InventoryEntryVO();
            		inventoryEntryVO.setStockItemName(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, STOCKITEMNAME, index, subIndex));
            		inventoryEntryVO.setRate(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, RATE, index, subIndex));
            		inventoryEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, AMOUNT, index, subIndex));
            		inventoryEntryVO.setBilledQuantity(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, BILLEDQTY, index, subIndex));
            		
            		inventoryEntryVOs.add(inventoryEntryVO);
            	}
            	
            	dayBookMasterVO.setInventoryEntryVOs(inventoryEntryVOs);
            	dayBookMasterVOs.add(dayBookMasterVO);
            	
            	//System.out.println("########################################################");
            	
            }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return dayBookMasterVOs;
		
	}
	
	private List<DayBookMasterVO> addFromRequest(TallyInputDTO tallyInputDTO, S3Object s3object, String key, String bucket) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc = null;
		DayBookMasterVO dayBookMasterVO = null;
		LedgerEntryVO ledgerEntryVO = null;
		InventoryEntryVO inventoryEntryVO = null;
		List<DayBookMasterVO> dayBookMasterVOs = new ArrayList<>();
		
		try {
			
			//S3Object s3object = s3.getObject(new GetObjectRequest(bucket, key));
			
			builder = factory.newDocumentBuilder();
			//InputSource is = new InputSource(new StringReader(tallyInputDTO.getDayBook()));
            doc = builder.parse(s3object.getObjectContent());
            
            
			
            // Create XPathFactory object
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // Create XPath object
            XPath xpath = xpathFactory.newXPath();

            int tallyMessageCount = Utility.getCount(doc, xpath, tallyInputDTO.isTiny() ? TALLYMESSAGE_COUNT_EXP_TINY : TALLYMESSAGE_COUNT_EXP);
            
            if(tallyMessageCount < 1) {
            	//LOG.info(LOG_BASE_FORMAT, tallyInputDTO.getTrackingID(), "addFromRequest, valid tally message count is 0, so xml parsing cancelled");
            }
            
            for(int index=1; index<=tallyMessageCount; index++) {
            	/*System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VCHTYPE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, ACTION, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, DATE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERTYPENAME, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERNUMBER, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, PARTYLEDGERNAME, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERKEY, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, EFFECTIVEDATE, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, PERSISTEDVIEW, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, ALTERID, index));
            	System.out.println(Utility.getPrimaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, MASTERID, index));
            	System.out.println("----------------------");*/
            	
            	dayBookMasterVO = new DayBookMasterVO();
            	dayBookMasterVO.setVoucherType(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VCHTYPE, index));
            	dayBookMasterVO.setVoucherAction(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, ACTION, index));
            	dayBookMasterVO.setVoucherDate(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, DATE, index));
            	dayBookMasterVO.setVoucherTypeName(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERTYPENAME, index));
            	dayBookMasterVO.setVoucherNumber(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERNUMBER, index));
            	dayBookMasterVO.setPartyLedgerName(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, PARTYLEDGERNAME, index));
            	dayBookMasterVO.setVoucherKey(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHERKEY, index));
            	dayBookMasterVO.setEffectiveDate(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, EFFECTIVEDATE, index));
            	dayBookMasterVO.setPersistedView(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, PERSISTEDVIEW, index));
            	dayBookMasterVO.setAlterId(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, ALTERID, index));
            	dayBookMasterVO.setMasterId(Utility.getPrimaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, MASTERID, index));
            	
            	
            	//get allledgerentries
            	int allLedgerEntriesListCount = Utility.getCount(doc, xpath, new StringBuilder(tallyInputDTO.isTiny() ? VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_1_TINY : VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_ALLLEDGERENTRIES_LIST_COUNT_2).toString());
            	
            	//System.out.println("allLedgerEntriesListCount : " + allLedgerEntriesListCount);
            	
            	List<LedgerEntryVO> ledgerEntryVOs = new ArrayList<>();
            	for(int subIndex=1; subIndex<=allLedgerEntriesListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, ISPARTYLEDGER, index, subIndex));
            		System.out.println("----------------------");*/
            		
            		ledgerEntryVO = new LedgerEntryVO();
            		ledgerEntryVO.setLedgerName(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		ledgerEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_ALLLEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		ledgerEntryVOs.add(ledgerEntryVO);
            	}
            	
            	
            	int ledgerEntriesListCount = Utility.getCount(doc, xpath, new StringBuilder(tallyInputDTO.isTiny() ? VOUCHER_LEDGERENTRIES_LIST_COUNT_1_TINY : VOUCHER_LEDGERENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_LEDGERENTRIES_LIST_COUNT_2).toString());
            	
            	//System.out.println("ledgerEntriesListCount : " + ledgerEntriesListCount);
            	
            	for(int subIndex=1; subIndex<=ledgerEntriesListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println("----------------------");*/
            		
            		ledgerEntryVO = new LedgerEntryVO();
            		ledgerEntryVO.setLedgerName(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, LEDGERNAME, index, subIndex));
            		ledgerEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_LEDGERENTRIES_LIST, AMOUNT, index, subIndex));
            		ledgerEntryVOs.add(ledgerEntryVO);
            		
            	}
            	
            	//set ledger list in master vo in one place for both allledgerentrieslist and ledgerentrieslist, because either one will be available not both for same tallmessage
            	dayBookMasterVO.setLedgerEntryVOs(ledgerEntryVOs);
            	
            	
            	//get inventoryentries
            	int inventoryEntriesListCount = Utility.getCount(doc, xpath, new StringBuilder(tallyInputDTO.isTiny() ? VOUCHER_INVENTORYENTRIES_LIST_COUNT_1_TINY : VOUCHER_INVENTORYENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_INVENTORYENTRIES_LIST_COUNT_2).toString());
            	
            	//System.out.println("inventoryEntriesListCount : " + inventoryEntriesListCount);
            	
            	List<InventoryEntryVO> inventoryEntryVOs = new ArrayList<>();
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, STOCKITEMNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, RATE, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, BILLEDQTY, index, subIndex));
            		
            		System.out.println("----------------------");*/
            		
            		inventoryEntryVO = new InventoryEntryVO();
            		inventoryEntryVO.setStockItemName(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, STOCKITEMNAME, index, subIndex));
            		inventoryEntryVO.setRate(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, RATE, index, subIndex));
            		inventoryEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, AMOUNT, index, subIndex));
            		inventoryEntryVO.setBilledQuantity(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, BILLEDQTY, index, subIndex));
            		
            		inventoryEntryVOs.add(inventoryEntryVO);
            	}
            	
            	
            	//get inventoryentries In
            	int inventoryEntriesInListCount = Utility.getCount(doc, xpath, new StringBuilder(tallyInputDTO.isTiny() ? VOUCHER_INVENTORYENTRIES_LIST_COUNT_1_TINY : VOUCHER_INVENTORYENTRIES_LIST_COUNT_1).append(index).append(VOUCHER_INVENTORYENTRIES_IN_LIST_COUNT_2).toString());
            	
            	//System.out.println("inventoryEntriesListCount : " + inventoryEntriesListCount);
            	
            	for(int subIndex=1; subIndex<=inventoryEntriesInListCount; subIndex++) {
            		/*System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, STOCKITEMNAME, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, RATE, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, AMOUNT, index, subIndex));
            		System.out.println(Utility.getSecondaryData(doc, xpath, ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_LIST, BILLEDQTY, index, subIndex));
            		
            		System.out.println("----------------------");*/
            		
            		inventoryEntryVO = new InventoryEntryVO();
            		inventoryEntryVO.setStockItemName(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_IN_LIST, STOCKITEMNAME, index, subIndex));
            		inventoryEntryVO.setRate(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_IN_LIST, RATE, index, subIndex));
            		inventoryEntryVO.setAmount(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_IN_LIST, AMOUNT, index, subIndex));
            		inventoryEntryVO.setBilledQuantity(Utility.getSecondaryData(doc, xpath, tallyInputDTO.isTiny() ? ENVELOPE_BODY_DATA_TALLYMESSAGE_TINY : ENVELOPE_BODY_DATA_TALLYMESSAGE, VOUCHER_INVENTORYENTRIES_IN_LIST, BILLEDQTY, index, subIndex));
            		
            		inventoryEntryVOs.add(inventoryEntryVO);
            	}
            	
            	//set inventory list in master vo in one place for both ALLINVENTORYENTRIES.LIST and INVENTORYENTRIESIN.LIST, because either one will be available not both for same tallmessage
            	dayBookMasterVO.setInventoryEntryVOs(inventoryEntryVOs);
            	
            	
            	dayBookMasterVOs.add(dayBookMasterVO);
            	
            	//System.out.println("########################################################");
            	
            }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		return dayBookMasterVOs;
	}
	
}
