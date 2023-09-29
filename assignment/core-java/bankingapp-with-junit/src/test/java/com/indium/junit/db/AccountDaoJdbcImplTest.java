package com.indium.junit.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.indium.junit.modal.Account;
import com.indium.junit.modal.AccountType;

public class AccountDaoJdbcImplTest {
	private AccountDao accountDao;

	@BeforeEach
    public void setUp() throws Exception {
        accountDao = new AccountDaoJdbcImpl();
    }

 

    @Test
    public void testCreateAccount() {
        Account account = new Account(1, "John Doe", 1000.0, AccountType.SAVING);
        assertTrue(accountDao.create(account));
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account(2, "Jane Smith", 2000.0, AccountType.CURRENT);
        accountDao.create(account);
        account.setAccountHolderName("Updated Name");
        assertTrue(accountDao.update(account));
    }

    @Test
    public void testDeleteAccount() {
        Account account = new Account(3, "Alice Johnson", 3000.0, AccountType.SAVING);
        accountDao.create(account);
        assertTrue(accountDao.delete(account.getAccountNumber()));
    }

    @Test
    public void testGetAccount() {
        
        Account account = new Account(4, "Bob Brown", 4000.0, AccountType.CURRENT);
        accountDao.create(account);
        Account retrievedAccount = accountDao.get(account.getAccountNumber());
        assertNotNull(retrievedAccount);
        assertEquals("Bob Brown", retrievedAccount.getAccountHolderName());
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = accountDao.getAll();
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

	
}
