package com.dxc.rental.domain.ad;

import java.math.BigDecimal;
import java.util.Set;

import com.dxc.rental.domain.user.ApplicationUser;

public interface AdRepository {

	Ad ChangeActiveStatus(Ad ad);
	Ad ChangeTakenStatus(Ad ad);
	Ad ChangeDeleteFlag(Ad ad);
	Ad ChangePrice(Ad ad, BigDecimal newPrice);
	Set<Ad> getOwnAds(ApplicationUser owner);
	Set<Ad> getElsesAds(ApplicationUser user);
}
