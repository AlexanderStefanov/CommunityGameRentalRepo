package com.dxc.rental.domain.game;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.dxc.rental.core.CoreRepositoryImplementation;
import com.dxc.rental.domain.user.ApplicationUser;

@Component
public class JpaGameRepository extends CoreRepositoryImplementation<Game> implements GameRepository {

	public JpaGameRepository() {
		entryClass = Game.class;
	}

	@Override
	public Game rentingGame(ApplicationUser renter, Game game, LocalDate date) {
		game.setBeholder(renter);
		game.setTakenOn(date);
		Game result = null;
		try {
			context.getTransaction().begin();
			context.merge(game);
			context.getTransaction().commit();
			result = game;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Game returningGame(Game game, LocalDate date) {
		game.setBeholder(game.getOwner());
		game.setTakenOn(null);
		Game result = null;
		try {
			context.getTransaction().begin();
			context.merge(game);
			context.getTransaction().commit();
			result = game;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Set<Game> getOwnersGames(ApplicationUser owner) {
		Collection<Game> result = context.createQuery(
		        "SELECT g FROM Game g"
		+" WHERE g.owner IS ?owner",Game.class)
		        .setParameter("owner", owner)
		        .getResultList();
		return new HashSet<Game>(result);
	}

	@Override
	public Set<Game> getUsersInventoryGames(ApplicationUser user) {
		Collection<Game> result = context.createQuery(
		        "SELECT g FROM Game g"
		+" WHERE g.beholder IS ?beholder",Game.class)
		        .setParameter("beholder", user)
		        .getResultList();
		return new HashSet<Game>(result);
	}

	@Override
	public Game changeDeleteFlag(Game game) {
		if(game.getDeleteFlag() == false) {
			game.setDeleteFlag(true);
		} else {
			game.setDeleteFlag(false);
		}
		Game result = null;
		try {
			context.getTransaction().begin();
			context.merge(game);
			context.getTransaction().commit();
			result = game;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}
}
