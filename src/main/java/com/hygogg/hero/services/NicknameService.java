package com.hygogg.hero.services;

import org.springframework.stereotype.Service;

import com.hygogg.hero.models.Nickname;
import com.hygogg.hero.repositories.NicknameRepository;

@Service
public class NicknameService {

	private NicknameRepository nicknameRepository;
	
	public NicknameService (NicknameRepository nicknameRepository) {
		this.nicknameRepository = nicknameRepository;
	}
	
	public void assignNickname(Nickname nickname) {
		this.nicknameRepository.save(nickname);
	}
	
}
