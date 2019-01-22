package com.hygogg.hero.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hygogg.hero.models.SuperAbility;
import com.hygogg.hero.repositories.SuperAbilityRepository;

@Service
public class SuperAbilityService {
	private SuperAbilityRepository superAbilityRepository;
	
	public SuperAbilityService(SuperAbilityRepository superAbilityRepository) {
		this.superAbilityRepository = superAbilityRepository;
	}
	
	public void create(SuperAbility ability) {
		this.superAbilityRepository.save(ability);
	}
	
	public List<SuperAbility > getAll() {
		return (List<SuperAbility>) this.superAbilityRepository.findAll();
	}
	
	public SuperAbility getById(Long id) {
		return this.superAbilityRepository.findById(id).get();
	}
}
