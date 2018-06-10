package com.dxc.rental.ui.web.user;

import java.util.Set;

import com.dxc.rental.domain.ad.Ad;
import com.dxc.rental.domain.game.Game;
import com.dxc.rental.domain.role.Role;

public class ApplicationUserDTO {
	
	private String name;
	
	private String password;
	
	private String email;

	private Set<Role> roles;

	private Set<Ad> ads;

	private Set<Game> games;
}
