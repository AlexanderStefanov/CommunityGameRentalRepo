package com.dxc.rental.ui.web.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.rental.domain.user.JpaApplicationUserRepository;

public class ApplicationUserController {
	
	private final JpaApplicationUserRepository applicationUserRepository;

	@Autowired
	public ApplicationUserController(JpaApplicationUserRepository filmRepository) {
		this.applicationUserRepository = filmRepository;
	}
	
	
}
