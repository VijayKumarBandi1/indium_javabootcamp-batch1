package com.indium.bankingapp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.indium.bankingapp.enums.AccountType;
import com.indium.bankingapp.model.Account;
import com.mysql.cj.jdbc.MysqlDataSource;

public class AccountDaoJdbcImpl implements AccountDao {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	

//++++++++++++++++++++++++++++++++++++++++ DB Connection++++++++++++++++++++++++++++++++++	 
	public Connection getConnection() {
		try {
			if(conn == null) {
						MysqlDataSource datasource = new MysqlDataSource();
						datasource.setServerName("localhost");
						datasource.setDatabaseName("training");
						datasource.setUser("root");
						datasource.setPassword("Vijay@2304");
						
				conn = datasource.getConnection();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

//++++++++++++++++++++++++++++++++++create function+++++++++++++++++++++++++++++++++++++++++++++++++	
	public boolean create(Account account) {
		// INSERT account data
		boolean status = false;
		try {
			String query = "INSERT INTO accounts(accountId, accountName, balance, accType) values(?,?,?,?)";
			pstmt = getConnection().prepareStatement(query);
			pstmt.setInt(1, account.getAccountNumber());
			pstmt.setString(2, account.getAccountHolderName());
			pstmt.setDouble(3, account.getBalance());
			pstmt.setString(4, (account.getAccType().name()));
			
			status = pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

//+++++++++++++++++++++++++++++++update function++++++++++++++++++++++++++++++++++++++++++++++++++++	
	public boolean update(Account account) {
		// UPDATE account data
		boolean status = false;
		try {
			String query = "UPDATE accounts SET accountId=?, accountName=?, balance=?, accType=? WHERE accountId = ?";
			pstmt = getConnection().prepareStatement(query);
			pstmt.setInt(1, account.getAccountNumber());
			pstmt.setString(2, account.getAccountHolderName());
			pstmt.setDouble(3, account.getBalance());
			pstmt.setString(4, (account.getAccType().name()));
			pstmt.setInt(5, account.getAccountNumber());
			
			status = pstmt.executeUpdate() > 0 ? true : false;

		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return status;
	}

//+++++++++++++++++++++++++++++++++++delete Account+++++++++++++++++++++++++++++++++++++++++++++++++	
	public boolean delete(int id) {
	    // DELETE account data
	    boolean status = false;
	    try {
	        stmt = getConnection().createStatement();

	        String query = "DELETE FROM accounts WHERE accountId = " + id;

	        // Execute the DELETE query
	        int rowCount = stmt.executeUpdate(query);

	        if (rowCount > 0) {
	            // Rows were deleted
	            status = true;
	        } else {
	            // No rows were deleted
	            System.out.println("No rows were deleted for id: " + id);
	        }
	    } catch (SQLException e) {
	        // Handle any SQL exceptions
	        e.printStackTrace();
	    }
	   
	    return status;
	}

//+++++++++++++++++++++++++++++++++ Get Account++++++++++++++++++++++++++++++++++++++++++++
	public Account get(int accId) {
		// SELECT account data
		Account acc = null;
		String query = "SELECT * FROM accounts WHERE accountId = " + accId;
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int accountId = rs.getInt("accountId");
				String accountName = rs.getString("accountName");
				double balance = rs.getInt("balance");
				String accTypeStr = rs.getString("accType");
			    AccountType accType=null;
			    switch (accTypeStr) {
			        case "SAVING":
			            accType = AccountType.SAVING;
			            break;
			        case "CURRENT":
			            accType = AccountType.CURRENT;
			            break;
			    }

				acc=new Account(accountId, accountName, balance, accType);
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}

//++++++++++++++++++++++++++++++++++ Get All Account++++++++++++++++++++++++++++++++++++++++++++	
	public List<Account> getAll() {
		// SELECT All accounts
		List<Account> accounts = new ArrayList<>();
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery("SELECT * FROM accounts");

			while (rs.next()) {
				int accountId = rs.getInt("accountId");
				String accountName = rs.getString("accountName");
				double balance = rs.getInt("balance");
				String accTypeStr = rs.getString("accType");
			    AccountType accType=null;
			    switch (accTypeStr) {
			        case "SAVING":
			            accType = AccountType.SAVING;
			            break;
			        case "CURRENT":
			            accType = AccountType.CURRENT;
			            break;
			    }

				accounts.add(new Account(accountId, accountName, balance, accType));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}
}
