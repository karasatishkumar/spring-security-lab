package org.techiekernel.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.techiekernel.exception.AccountNotFoundException;
import org.techiekernel.model.Account;

@Repository
public class InMemoryAccountRepository implements AccountRepository {
	
	private static List<Account> accountRepository;
	
	static{
		accountRepository = new ArrayList<>();
		Account account;
		for (long i = 100000000; i < 100001000; i++) {
			account = new Account(i, "A" + i, "N" + i, "INR", "IN");
			accountRepository.add(account);
		}
	}

	@Override
	public List<Account> getAccounts(){
		return accountRepository;
	}

	@Override
	public Account getAccount(Long accountId) throws Exception{
		for (Account account : accountRepository) {
			if(account.getAccountId().equals(accountId))
				return account;
		}
		throw new AccountNotFoundException(accountId + " not found in the repository ");
	}

	@Override
	public Long createAccount(Account account) {
		Long accountId = accountRepository.get(accountRepository.size()-1).getAccountId() + 1;
		account.setAccountId(accountId);
		accountRepository.add(account);
		return accountId;
	}

	@Override
	public Account editAccount(Account account) throws Exception{
		for (int i = 0; i < accountRepository.size(); i++) {
			Account oldAccount = accountRepository.get(i);
			if(account.getAccountId().equals(oldAccount.getAccountId())){
				accountRepository.add(i, account);
				return account;
			}
		}
		throw new AccountNotFoundException(account.getAccountNo() + " not found in the repository ");
	}

	@Override
	public boolean deleteAccount(Long accountId) throws Exception{
		Iterator<Account> accountIterator = accountRepository.iterator();
		while(accountIterator.hasNext()){
			Account account = accountIterator.next();
			if(account.getAccountId().equals(accountId)){
				accountIterator.remove();
				return true;
			}
		}
		throw new AccountNotFoundException(accountId + " not found in the repository ");
	}

}
