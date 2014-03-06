package org.techiekernel.repository;

import java.util.List;

import org.techiekernel.model.Account;

public interface AccountRepository {
	List<Account> getAccounts();

	Account getAccount(Long accountId) throws Exception;

	Long createAccount(Account account);

	Account editAccount(Account account) throws Exception;

	boolean deleteAccount(Long accountId) throws Exception;
}
