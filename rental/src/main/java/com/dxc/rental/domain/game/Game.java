package com.dxc.rental.domain.game;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dxc.rental.domain.ad.Ad;
import com.dxc.rental.domain.genre.Genre;
import com.dxc.rental.domain.platform.Platform;
import com.dxc.rental.domain.user.ApplicationUser;

/**
 * Entity model for games
 * 
 * @author astefanov4
 *
 */
@Entity
@Table(name = "games")
public class Game implements Serializable {

	/**
	 * Generated serializable value for the purposes of streaming the object
	 */
	private static final long serialVersionUID = -3755607163389384444L;

	@Id
	private Long id;

	private String name;

	private String developer;

	private LocalDate takenOn;

	private Boolean deleteFlag;
	@OneToOne(mappedBy = "game")
	private Ad ad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "platform_id")
	private Platform platform;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "game_genres", joinColumns = { @JoinColumn(name = "game_id") }, inverseJoinColumns = {
			@JoinColumn(name = "genre_id") })
	private Set<Genre> genres;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private ApplicationUser owner;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "beholder_id")
	private ApplicationUser beholder;

	public Game() {
		this.deleteFlag = false;
	}
	
	public Game(String name, String developer, Platform platform) {
		this();
		this.name = name;
		this.developer = developer;
		this.platform = platform;
		this.genres = new HashSet<Genre>();
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

	public String getDeveloper() {
		return developer;
	}

	public final void setDeveloper(String developer) {
		this.developer = developer;
	}

	public LocalDate getTakenOn() {
		return takenOn;
	}

	public final void setTakenOn(LocalDate takenOn) {
		this.takenOn = takenOn;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public final void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Platform getPlatform() {
		return platform;
	}

	public final void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public ApplicationUser getOwner() {
		return owner;
	}

	public final void setOwner(ApplicationUser owner) {
		this.owner = owner;
	}

	public ApplicationUser getBeholder() {
		return beholder;
	}

	public final void setBeholder(ApplicationUser beholder) {
		this.beholder = beholder;
	}

	@Override
	public int hashCode() {
		StringBuilder stringToHash = new StringBuilder();
		stringToHash.append(name);
		stringToHash.append(developer);
		return Objects.hash(stringToHash.toString());
	}

	@Override
	public boolean equals(Object objectToCompare) {

		if (objectToCompare == null) {
			return false;
		}

		if (!(objectToCompare instanceof Game)) {
			return false;
		}

		final Game anotherObject = (Game) objectToCompare;
		if (!(this.name.equals(anotherObject.name) && this.developer.equals(anotherObject.developer))) {
			return false;
		}
		return true;
	}

}
