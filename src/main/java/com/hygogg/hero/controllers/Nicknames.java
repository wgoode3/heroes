package com.hygogg.hero.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hygogg.hero.models.Hero;
import com.hygogg.hero.models.Nickname;
import com.hygogg.hero.services.HeroService;
import com.hygogg.hero.services.NicknameService;

@Controller
public class Nicknames {

	private HeroService heroService;
	private NicknameService nicknameService;
	
	public Nicknames (NicknameService nicknameService, HeroService heroService) {
		this.nicknameService = nicknameService;
		this.heroService = heroService;
	}
	
	@RequestMapping(value="/assignNickname", method=RequestMethod.POST)
	public String assignNickname(@RequestParam(value="hero_id") Long hero_id, @RequestParam(value="nickname") String nickname) {
		Nickname nick = new Nickname(nickname);
		Hero h = this.heroService.getById(hero_id);
		nick.setHero(h);
		this.nicknameService.assignNickname(nick);
		return "redirect:/";
    }
	
}
