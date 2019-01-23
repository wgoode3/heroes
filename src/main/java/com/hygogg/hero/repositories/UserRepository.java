package com.hygogg.hero.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hygogg.hero.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
}
