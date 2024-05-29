package com.vti.testing.DATN.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.testing.DATN.dto.AccountDTO;
import com.vti.testing.DATN.entity.Account;
import com.vti.testing.DATN.form.Account.CreatingAccountForm;
import com.vti.testing.DATN.form.Account.UpdatingAccountForm;

public interface IAccountService extends UserDetailsService {
	public Account getAccountById(int id);

	public void createAccount(CreatingAccountForm form);

	public Page<AccountDTO> getAllAccount(Pageable pageable);

	public void deleteAccount(int id);

	public void updateAccount(UpdatingAccountForm form, int id);

	public Account getAccountByUsername(String username);
}
