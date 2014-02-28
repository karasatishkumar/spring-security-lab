package org.techiekernel.repository;

import java.util.List;

import org.techiekernel.model.Account;

public interface AccountRepository {
	List<Account> getAccounts();

	Account getAccount(Long accountId);

	Long createAccount(Account account);

	Account editAccount(Account account);

	boolean deleteAccount(Long accountId);
}
