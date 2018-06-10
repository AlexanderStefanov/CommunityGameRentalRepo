package com.dxc.rental.domain.ad;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.dxc.rental.AdStatus;
import com.dxc.rental.core.CoreRepositoryImplementation;
import com.dxc.rental.domain.user.ApplicationUser;

@Component
public class JpaAdRepository extends CoreRepositoryImplementation<Ad> implements AdRepository {

	public JpaAdRepository() {
		entryClass = Ad.class;
	}

	@Override
	public Ad ChangeActiveStatus(Ad ad) {
		if (ad.getStatus() != AdStatus.TAKEN) {
			if (ad.getStatus() == AdStatus.ACTIVE) {
				ad.setStatus(AdStatus.DEACTIVATED);
			} else {
				ad.setStatus(AdStatus.ACTIVE);
			}
		}

		Ad result = null;
		try {
			context.getTransaction().begin();
			context.merge(ad);
			context.getTransaction().commit();
			result = ad;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Ad ChangeTakenStatus(Ad ad) {
		if (ad.getStatus() == AdStatus.TAKEN) {
			ad.setStatus(AdStatus.ACTIVE);
		} else {
			ad.setStatus(AdStatus.TAKEN);
		}

		Ad result = null;
		try {
			context.getTransaction().begin();
			context.merge(ad);
			context.getTransaction().commit();
			result = ad;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Ad ChangeDeleteFlag(Ad ad) {
		if (ad.getDeleteFlag() == false) {
			ad.setDeleteFlag(true);
		} else {
			ad.setDeleteFlag(false);
		}
		Ad result = null;
		try {
			context.getTransaction().begin();
			context.merge(ad);
			context.getTransaction().commit();
			result = ad;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Ad ChangePrice(Ad ad, BigDecimal newPrice) {
		ad.setPricePerDay(newPrice);
		Ad result = null;
		try {
			context.getTransaction().begin();
			context.merge(ad);
			context.getTransaction().commit();
			result = ad;
		} catch (Exception e) {
			e.printStackTrace();
			context.getTransaction().rollback();
		}
		return result;
	}

	@Override
	public Set<Ad> getOwnAds(ApplicationUser owner) {
		Collection<Ad> result = context.createQuery("SELECT a FROM Ad a" + " WHERE a.owner IS ?owner", Ad.class)
				.setParameter("owner", owner).getResultList();
		return new HashSet<Ad>(result);
	}

	@Override
	public Set<Ad> getElsesAds(ApplicationUser user) {
		Collection<Ad> result = context.createQuery("SELECT a FROM Ad a" + " WHERE a.user IS ?user", Ad.class)
				.setParameter("user", user).getResultList();
		return new HashSet<Ad>(result);
	}

}
