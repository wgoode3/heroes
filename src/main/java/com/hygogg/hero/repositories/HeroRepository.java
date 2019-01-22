package com.hygogg.hero.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hygogg.hero.models.Hero;

@Repository
public interface HeroRepository extends CrudRepository<Hero, Long>{
}
