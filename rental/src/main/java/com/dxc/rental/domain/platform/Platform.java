package com.dxc.rental.domain.platform;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.rental.domain.game.Game;

/**
 * Entity model for game platforms
 * 
 * @author astefanov4
 *
 */
@Entity
@Table(name = "platforms")
public class Platform implements Serializable {

	/**
	 * Generated serializable value for the purposes of streaming the object
	 */
	private static final long serialVersionUID = -2115023326926336603L;

	@Id
	private Long id;

	private String name;

	@OneToMany(mappedBy = "platform")
	private Set<Game> games;

	Platform(){
		
	}

	Platform(String name){
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

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object objectToCompare) {

		if (objectToCompare == null) {
			return false;
		}

		if (!(objectToCompare instanceof Platform)) {
			return false;
		}

		final Platform anotherObject = (Platform) objectToCompare;
		if (!(this.name.equals(anotherObject.name))) {
			return false;
		}
		return true;
	}
}
