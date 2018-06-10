package com.dxc.rental.domain.ad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dxc.rental.AdStatus;
import com.dxc.rental.domain.game.Game;
import com.dxc.rental.domain.user.ApplicationUser;

/**
 * Entity model for ads
 * @author astefanov4
 *
 */
@Entity
@Table(name = "ads")
public class Ad implements Serializable{
	/**
	 * Generated serializable value for the purposes of streaming
	 * the object
	 */
	private static final long serialVersionUID = -4465798751691936217L;

	@Id
	private Long id;
		
	private LocalDate returnDate;
	
	private BigDecimal pricePerDay;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private ApplicationUser owner;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;
	
	private AdStatus status;
	
	private Boolean deleteFlag;
	
	
	public Ad(){
		this.status = AdStatus.ACTIVE;
		this.deleteFlag = false;
	}
	
	public Ad(LocalDate returnDate, ApplicationUser owner, Game game){
		this();
		this.returnDate = returnDate;
		this.owner = owner;
		this.game = game;
	}

	public Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public final void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getPricePerDay() {
		return pricePerDay;
	}

	public final void setPricePerDay(BigDecimal pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public ApplicationUser getOwner() {
		return owner;
	}

	public final void setOwner(ApplicationUser owner) {
		this.owner = owner;
	}

	public Game getGame() {
		return game;
	}

	public final void setGame(Game game) {
		this.game = game;
	}

	public AdStatus getStatus() {
		return status;
	}

	public final void setStatus(AdStatus status) {
		this.status = status;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public final void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	@Override
	public int hashCode() {
		StringBuilder stringToHash = new StringBuilder();
		stringToHash.append(owner);
		stringToHash.append(game);
		return Objects.hash(stringToHash.toString());
	}

	@Override
	public boolean equals(Object objectToCompare) {

		if (objectToCompare == null) {
			return false;
		}

		if (!(objectToCompare instanceof Ad)) {
			return false;
		}

		final Ad anotherObject = (Ad) objectToCompare;
		if (!(this.owner.equals(anotherObject.getOwner()) 
				&& this.game.equals(anotherObject.getGame()))) {
			return false;
		}
		return true;
	}
	
	
}
