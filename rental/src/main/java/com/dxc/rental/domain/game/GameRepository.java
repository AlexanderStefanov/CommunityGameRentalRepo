package com.dxc.rental.domain.game;

import java.time.LocalDate;
import java.util.Set;

import com.dxc.rental.domain.user.ApplicationUser;

public interface GameRepository {

	Game rentingGame(ApplicationUser renter, Game game, LocalDate date);
	Game returningGame(Game game, LocalDate date);
	Set<Game> getOwnersGames(ApplicationUser owner);
	Set<Game> getUsersInventoryGames(ApplicationUser user);
	Game changeDeleteFlag(Game game);
	
}
