package com.indium.junit.db;

import java.util.List;

import com.indium.junit.modal.Account;



public interface AccountDao {
	
	public boolean create(Account account);

	public boolean update(Account account);

	public boolean delete(int id);

	public Account get(int accId);

	public List<Account> getAll();

}
