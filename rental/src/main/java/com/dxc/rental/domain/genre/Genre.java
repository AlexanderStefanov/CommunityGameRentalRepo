package com.dxc.rental.domain.genre;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.dxc.rental.domain.game.Game;

/**
 * Entity model for genre
 * 
 * @author astefanov4
 *
 */
@Entity
@Table(name = "genres")
public class Genre implements Serializable {

	/**
	 * Generated serializable value for the purposes of streaming the object
	 */
	private static final long serialVersionUID = 2232513170323216984L;

	@Id
	private Long id;

	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "ganres")
	private Set<Game> games;
	Genre(){
		
	}
	
	Genre(String name){
		this.name = name;
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

	public Set<Game> getGames() {
		return games;
	}

	public final void setGames(Set<Game> games) {
		this.games = games;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object objectToCompare) {

		if (objectToCompare == null) {
			return false;
		}

		if (!(objectToCompare instanceof Genre)) {
			return false;
		}

		final Genre anotherObject = (Genre) objectToCompare;
		if (!(this.name.equals(anotherObject.name))) {
			return false;
		}
		return true;
	}
}
