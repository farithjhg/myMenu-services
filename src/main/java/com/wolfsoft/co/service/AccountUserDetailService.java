package com.wolfsoft.co.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wolfsoft.co.repository.AccountRepository;

@Service
public class AccountUserDetailService implements UserDetailsService {
	private final AccountRepository accountRepository;
	
	public AccountUserDetailService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername");
		return accountRepository.findByUserName(userName)
				.map(account -> new User(account.getUserName(), 
						account.getPassword(), account.isActive(), account.isActive()
						, account.isActive(), account.isActive(), AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")))
				.orElseThrow(() -> new UsernameNotFoundException("Couldn´t find the username: "+userName+"!"));
	}
		
}