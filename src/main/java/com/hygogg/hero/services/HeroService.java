package com.hygogg.hero.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hygogg.hero.models.Hero;
import com.hygogg.hero.models.SuperAbility;
import com.hygogg.hero.repositories.HeroRepository;

@Service
public class HeroService {
	
	private HeroRepository heroRepository;
	
	public HeroService(HeroRepository heroRepository) {
		this.heroRepository = heroRepository;
	}
	
	public void heroOrigin(Hero hero) {
		this.heroRepository.save(hero);
	}
	
	public List<Hero> assembleHeroes() {
		return (List<Hero>) this.heroRepository.findAll();
	}
	
	public Hero getById(Long id) {
		return this.heroRepository.findById(id).get();
	}
	
	public void addAbilityToHero(Hero h, SuperAbility s) {
		List<SuperAbility> abilities = h.getAbilities();
		abilities.add(s);
		h.setAbilities(abilities);
		this.heroRepository.save(h);
	}

}
