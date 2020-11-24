package com.management.account.service;

import java.util.List;

import javax.security.auth.login.AccountException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.management.account.dao.AccountRepository;
import com.management.account.model.AcctDetailsRequest;
import com.management.account.model.AcctDetailsResponse;

@Service
public class AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	AccountRepository repo;

	public List<AcctDetailsResponse> getAccountDetails(AcctDetailsRequest req) throws AccountException {
		System.out.println(req.getAccountId());
		if(req.getAccountId() == 0) {
			throw new AccountException("Account id not specified");
		}
		return repo.getAccountDetails(req);
	}

	
}