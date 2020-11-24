package com.management.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.management.account.model.AcctDetailsRequest;
import com.management.account.model.AcctDetailsResponse;

@Repository
public class AccountRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepository.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final String DATE_FORMAT = "dd.MM.yyyy";

	public List<AcctDetailsResponse> getAccountDetails(AcctDetailsRequest req) {

		StringBuffer sb = new StringBuffer();
		sb.append(
				"select a.account_type as accountType,a.account_number as accountNumber,s.amount as amount,s.datefield as dateField from account a,statement s where a.ID = s.ID and s.account_id = "
						+ req.getAccountId());

		if (StringUtils.isNotEmpty(req.getStartDt())) {

			sb.append(" and to_date(s.datefield,'" + DATE_FORMAT + "') >= to_date('" + req.getStartDt() + "','"
					+ DATE_FORMAT + "')");

		}

		if (StringUtils.isNotEmpty(req.getEndDt())) {

			sb.append(" and to_date(s.datefield,'" + DATE_FORMAT + "') <= to_date('" + req.getEndDt() + "','"
					+ DATE_FORMAT + "')");

		}

		if (req.getMinAmount() > 0) {
			sb.append(" and s.amount >=" + req.getMinAmount());
		}

		if (req.getMaxAmount() > 0) {
			sb.append(" and s.amount <=" + req.getMaxAmount());
		}

		if (req.getStartDt() == null && req.getEndDt() == null && req.getMinAmount() == 0.0
				&& req.getMaxAmount() == 0.0) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH, -1 * 3);
			Date last3MonthsDt = c.getTime();
			Date now = new Date();
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			String last3MonthsDtStr = df.format(last3MonthsDt);
			String nowStr = df.format(now);

			sb.append(" and to_date(s.datefield,'" + DATE_FORMAT + "') >= to_date('" + last3MonthsDtStr + "','"
					+ DATE_FORMAT + "')");

			sb.append(" and to_date(s.datefield,'" + DATE_FORMAT + "') <= to_date('" + nowStr + "','" + DATE_FORMAT
					+ "')");

		}

		LOGGER.info(sb.toString());
		return jdbcTemplate.query(sb.toString(), new AccountDetailsRowMapper());

	}

	public class AccountDetailsRowMapper implements RowMapper<AcctDetailsResponse> {

		@Override
		public AcctDetailsResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
			AcctDetailsResponse acctDetails = new AcctDetailsResponse();
			acctDetails.setAccountNumber(rs.getString("accountNumber"));
			acctDetails.setAccountType(rs.getString("accountType"));
			acctDetails.setAmount(rs.getDouble("amount"));

			acctDetails.setStmtDt(rs.getString("dateField"));

			return acctDetails;
		}
	}

}

// https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
