package com.hygogg.hero.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hygogg.hero.models.Hero;
import com.hygogg.hero.models.SuperAbility;
import com.hygogg.hero.models.User;
import com.hygogg.hero.services.HeroService;
import com.hygogg.hero.services.SuperAbilityService;

@Controller
public class Heroes {
	
	private HeroService heroService;
	private SuperAbilityService superAbilityService;
	
	public Heroes(HeroService heroService, SuperAbilityService superAbilityService) {
		this.heroService = heroService;
		this.superAbilityService = superAbilityService;
	}

	@RequestMapping("/main")
	public String index(Model model, HttpSession session, RedirectAttributes flash) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			HashMap<String, Object> map = new HashMap<String, Object> ();
			map.put("login", "You must log in first");
			flash.addFlashAttribute("errors", map);
			return "redirect:/";
		}
		model.addAttribute("heroes", this.heroService.assembleHeroes());
		model.addAttribute("abilities", this.superAbilityService.getAll());
		model.addAttribute("hero", new Hero());
		model.addAttribute("ability", new SuperAbility());
		return "index";
	}
	
	@RequestMapping(value="/hero", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("hero") Hero hero, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        } else {
            heroService.heroOrigin(hero);
            return "redirect:/main";
        }
    }
	
	@RequestMapping(value="/assignAbility", method=RequestMethod.POST)
	public String assignAbility(@RequestParam(value="hero_id") Long hero_id, @RequestParam(value="ability_id") Long ability_id) {
        Hero h = this.heroService.getById(hero_id);
        SuperAbility s = this.superAbilityService.getById(ability_id);
        this.heroService.addAbilityToHero(h, s);
		return "redirect:/main";
    }
	
}
