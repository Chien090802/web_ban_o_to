package com.vti.testing.DATN.service.Account;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vti.testing.DATN.dto.AccountDTO;
import com.vti.testing.DATN.entity.Account;
import com.vti.testing.DATN.form.Account.CreatingAccountForm;
import com.vti.testing.DATN.form.Account.UpdatingAccountForm;
import com.vti.testing.DATN.repository.IAccountRepository;
import com.vti.testing.DATN.service.IAccountService;

@Service
public class AccountService implements IAccountService {
	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Account getAccountById(int id) {
		return accountRepository.findById(id).get();
	}

	@Override
	public void createAccount(CreatingAccountForm form) {
		TypeMap typeMap = modelMapper.getTypeMap(CreatingAccountForm.class, Account.class);
		if (typeMap == null) {
			modelMapper.addMappings(new PropertyMap<CreatingAccountForm, Account>() {
				protected void configure() {
					skip(destination.getId());
				}
			});
		}
		Account account = modelMapper.map(form, Account.class);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);
	}

	@Override
	public Page<AccountDTO> getAllAccount(Pageable pageable) {
		Page<Account> accounts = accountRepository.findAll(pageable);
		List<AccountDTO> dtos = modelMapper.map(accounts.getContent(), new TypeToken<List<AccountDTO>>() {
		}.getType());

		Page<AccountDTO> dtoPage = new PageImpl<>(dtos, pageable, accounts.getTotalElements());
		return dtoPage;
	}

	@Override
	public void updateAccount(UpdatingAccountForm form, int id) {
//		TypeMap typeMap = modelMapper.getTypeMap(UpdatingAccountForm.class, Account.class);
//		if (typeMap == null) {
//			modelMapper.addMappings(new PropertyMap<UpdatingAccountForm, Account>() {
//				protected void configure() {
//					skip(destination.getUsername());
//				}
//			});
//		}
//		Account account = modelMapper.map(form, Account.class);
//
//		account.setUsername(form.getUsername());
//		account.setFullName(form.getFullName());
//		account.setRole(form.getRole());
//		account.setEmail(form.getEmail());
//		accountRepository.save(account);

		Account account=accountRepository.findById(id).get();
		account.setUsername(form.getUsername());
		account.setFullName(form.getFullName());
		account.setRole(form.getRole());
		account.setEmail(form.getEmail());
		accountRepository.save(account);
	}

	@Override
	public void deleteAccount(int id) {
		accountRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// dangblack ->
		// mật khẩu : $10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi
		Account account = accountRepository.findByUsername(username);

		if (account == null) {
			throw new UsernameNotFoundException(username);
		}

		return new User(account.getUsername(), account.getPassword(),
				AuthorityUtils.createAuthorityList(account.getRole().toString()));
	}

	@Override
	public Account getAccountByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
}
