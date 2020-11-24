package com.management.account.controller;

import java.util.List;

import javax.security.auth.login.AccountException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.account.model.AcctDetailsRequest;
import com.management.account.model.AcctDetailsResponse;
import com.management.account.security.jwt.AuthenticationRequest;
import com.management.account.security.jwt.AuthenticationResponse;
import com.management.account.service.AccountService;
import com.management.account.service.AccountUserDetailsService;
import com.management.account.utils.JwtUtil;

@RestController
@RequestMapping("/account")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	AccountService service;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtTokenUtil;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return jwt;
	}

	@RequestMapping(value = "/get/filter", method = RequestMethod.POST)
	@ResponseBody
	public List<AcctDetailsResponse> getFilteredDetails(@RequestBody AcctDetailsRequest req) throws AccountException {
		return service.getAccountDetails(req);

	}

	@RequestMapping(value = "/get/last/three", method = RequestMethod.GET)
	@ResponseBody
	public List<AcctDetailsResponse> getLastThreeMonthsBackStmt(@RequestParam(name = "accountID") String accountID)
			throws AccountException {
		AcctDetailsRequest req = new AcctDetailsRequest();

		if (StringUtils.isNotEmpty(accountID)) {
			req.setAccountId(Integer.parseInt(accountID));
			return service.getAccountDetails(req);
		}
		else {
			throw new AccountException("Account id cannot be null");
		}
		

	}

}
