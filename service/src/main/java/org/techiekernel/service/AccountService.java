package org.techiekernel.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.techiekernel.model.Account;
import org.techiekernel.repository.AccountRepository;

@Controller
@RequestMapping("/accounts")
public class AccountService {

	@Autowired
	AccountRepository accountRepository;

	@RequestMapping(value = "/{accountId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public Account getAccount(@PathVariable Long accountId) throws Exception{
		return accountRepository.getAccount(accountId);
	}

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public List<Account> getAccounts() {
		return accountRepository.getAccounts();
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.PUT, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public Account editAccount(@RequestBody Account account,
			@PathVariable Long accountId) throws Exception{
		return accountRepository.editAccount(account);
	}

	@RequestMapping(value = "/{accountId}", method = RequestMethod.DELETE, headers = "Accept=application/json", produces = { "application/json" })
	@ResponseBody
	public boolean deleteAccount(@PathVariable Long accountId) throws Exception{
		return accountRepository.deleteAccount(accountId);
	}

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" }, consumes = { "application/json" })
	@ResponseBody
	public Long createAccount(@RequestBody Account account) {
		return accountRepository.createAccount(account);
	}

}
