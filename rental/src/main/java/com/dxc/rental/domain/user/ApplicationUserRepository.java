package com.dxc.rental.domain.user;

public interface ApplicationUserRepository {

	ApplicationUser findByEmail(String email);
}
