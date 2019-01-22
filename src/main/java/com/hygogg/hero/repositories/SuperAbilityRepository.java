package com.hygogg.hero.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.hero.models.SuperAbility;

@Repository
public interface SuperAbilityRepository extends CrudRepository<SuperAbility, Long>{
}
