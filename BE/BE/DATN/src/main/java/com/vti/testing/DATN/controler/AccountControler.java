package com.vti.testing.DATN.controler;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.testing.DATN.dto.AccountDTO;
import com.vti.testing.DATN.dto.LoginInfoDto;
import com.vti.testing.DATN.entity.Account;
import com.vti.testing.DATN.form.Account.CreatingAccountForm;
import com.vti.testing.DATN.form.Account.UpdatingAccountForm;
import com.vti.testing.DATN.service.IAccountService;

@RestController
@RequestMapping("/api/v1/Accounts")
public class AccountControler {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private IAccountService accountService;

	@GetMapping("/login")
	public LoginInfoDto login(Principal principal) {

		String username = principal.getName();
		Account entity = accountService.getAccountByUsername(username);

		LoginInfoDto dto = modelMapper.map(entity, LoginInfoDto.class);

		return dto;
	}

	@GetMapping("/sign-in")
	public ResponseEntity<?> login() {

		return new ResponseEntity<> (true, HttpStatus.OK);
	}
	
	@GetMapping()
	public Page<AccountDTO> getAllAccount(Pageable pageable) {
		return accountService.getAllAccount(pageable);
	}

	@GetMapping("/{id}")
	public AccountDTO getAccountById(@PathVariable int id) {
		Account account = accountService.getAccountById(id);
		return modelMapper.map(account, AccountDTO.class);
	}

	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody CreatingAccountForm form) {
		try {
			accountService.createAccount(form);
			return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("fail");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatingAccount(@PathVariable(name="id") int id, @RequestBody UpdatingAccountForm form) {
		try {
			accountService.updateAccount(form, id);
			return new ResponseEntity<>("Update successfully", HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("fail");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAccountById(@PathVariable(name = "id") int id) {
		accountService.deleteAccount(id);
		return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
	}

}
