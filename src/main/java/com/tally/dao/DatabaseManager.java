package com.tally.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * purpose of this class is to create the connection object
 *  
 * 
 *
 */
public class DatabaseManager implements Serializable {
	protected Connection connection = null;	
	private static DatabaseManager instance = null;	
	private DatabaseManager() {}
		
	/**
	* This method creates instance for the current class 
	* 
	*  
	*/	 
	public static DatabaseManager getInstance() {
		if (instance == null)
			instance = new DatabaseManager();
		return instance;
	}
		
	/**
	*  
	* @return	void 
	* 
	*/
	public static void closeInstance() {
		if (instance != null)
			instance = null;
	}
		
	private Connection getConnectionByDriverManager() throws Exception {
		try {	
			Class.forName("com.mysql.jdbc.Driver");  
			
			//local
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","root");  
			
			//ohio
			//connection = DriverManager.getConnection("jdbc:mysql://inventory.cc8nzr3j37vf.us-east-2.rds.amazonaws.com:3306/inventory","root","root1234");
			
			//mumbai
			connection = DriverManager.getConnection("jdbc:mysql://inventory.cvskko4c1etv.ap-south-1.rds.amazonaws.com:3306/inventory","root","root1234");
			
			
		} catch (Exception e) {			
			throw new Exception("Could not get connection from Driver manager " + e.getMessage(),e);
		}		
		return connection;
	}
		
		 
	/**
	* Purpose of this method is to get connection
	* 
	* @return	Connection	connection object
	* 
	* @throws	
	* 
	*/
	public Connection getConnection() throws Exception {		
		try {
			if(connection == null || connection.isClosed())				
				//connection = getConnectionByDriverManagerUseProperties();	
				connection = getConnectionByDriverManager();
		} catch(Exception e) {			
			throw new Exception(this.getClass().getName() + " :Could not get Connection " + e.getMessage(),e);
		}
		return connection;
	}
		
	/**
	* Purpose of this method is to close the connection
	* 
	* @return	void
	* 
	*/
	public void closeConnection() {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {}
	}
}
