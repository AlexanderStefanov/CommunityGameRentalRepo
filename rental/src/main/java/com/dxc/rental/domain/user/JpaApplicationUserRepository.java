package com.dxc.rental.domain.user;


import org.springframework.stereotype.Component;

import com.dxc.rental.core.CoreRepositoryImplementation;

@Component
public class JpaApplicationUserRepository extends CoreRepositoryImplementation<ApplicationUser>
		implements ApplicationUserRepository {

	public JpaApplicationUserRepository() {
		entryClass = ApplicationUser.class;
	}

	@Override
	public ApplicationUser findByEmail(String email) {
		ApplicationUser result = context
				.createQuery("SELECT a FROM ApplicationUser a" + " WHERE a.email IS ?email", ApplicationUser.class)
				.setParameter("email", email).getSingleResult();
		return result;
	}
}
