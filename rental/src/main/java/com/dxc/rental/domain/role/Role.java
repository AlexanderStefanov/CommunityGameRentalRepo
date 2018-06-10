package com.dxc.rental.domain.role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dxc.rental.domain.user.ApplicationUser;

/**
 * Entity model for user role
 * 
 * @author astefanov4
 *
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
	/**
	 * Generated serializable value for the purposes of streaming the object
	 */
	private static final long serialVersionUID = 1724723271119231947L;

	@Id
	private Long id;
	
	private String role;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<ApplicationUser> users;
	
	Role(){
		
	}

	Role(String role){
		this.role = role;
		this.users = new HashSet<ApplicationUser>();
	}
	
	public Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return role;
	}

	public final void setName(String role) {
		this.role = role;
	}

	public Set<ApplicationUser> getUsers() {
		return users;
	}

	public final void setUser(Set<ApplicationUser> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		return Objects.hash(role);
	}

	@Override
	public boolean equals(Object objectToCompare) {

		if (objectToCompare == null) {
			return false;
		}

		if (!(objectToCompare instanceof Role)) {
			return false;
		}

		final Role anotherObject = (Role) objectToCompare;
		if (!(this.role.equals(anotherObject.role))) {
			return false;
		}
		return true;
	}
}
