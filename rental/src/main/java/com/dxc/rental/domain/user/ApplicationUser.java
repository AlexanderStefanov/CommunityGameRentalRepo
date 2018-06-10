package com.dxc.rental.domain.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.rental.domain.ad.Ad;
import com.dxc.rental.domain.game.Game;
import com.dxc.rental.domain.role.Role;

/**
 * Entity model for users
 * 
 * @author astefanov4
 *
 */

@Entity
@Table(name = "accounts")
public class ApplicationUser implements Serializable {

	/**
	 * Generated serializable value for the purposes of streaming the object
	 */
	private static final long serialVersionUID = 2636989772966938313L;
	@Id
	private Long id;
	private String name;
	private String password;
	private String email;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "account_roles", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private Set<Ad> ads;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private Set<Game> games;

	public ApplicationUser() {
	}

	public ApplicationUser(String name, String password, String email) {
		this.name = name;
		// Add BCryptPasswordEncoder().encode
		this.password = password;
		this.email = email;
		this.roles = new HashSet<Role>();
		this.ads = new HashSet<Ad>();
		this.games = new HashSet<Game>();
	}

	public Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		// Add BCryptPasswordEncoder().encode
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public Set<Ad> getAds() {
		return ads;
	}

	public final void setAds(Set<Ad> ads) {
		this.ads = ads;
	}

	public Set<Game> getGames() {
		return games;
	}

	public final void setGames(Set<Game> games) {
		this.games = games;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.email);
	}

	@Override
	public boolean equals(Object objectToCompare) {

		if (objectToCompare == null) {
			return false;
		}

		if (!(objectToCompare instanceof ApplicationUser)) {
			return false;
		}

		final ApplicationUser anotherObject = (ApplicationUser) objectToCompare;
		if (!(this.email.equals(anotherObject.email))) {
			return false;
		}
		return true;
	}

}
