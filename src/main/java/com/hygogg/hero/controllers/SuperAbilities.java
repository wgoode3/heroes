package com.hygogg.hero.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hygogg.hero.models.SuperAbility;
import com.hygogg.hero.services.SuperAbilityService;

@Controller
public class SuperAbilities {

	private SuperAbilityService superAbilityService;
	
	public SuperAbilities(SuperAbilityService superAbilityService) {
		this.superAbilityService = superAbilityService;
	}
	
	@RequestMapping(value="/ability", method=RequestMethod.POST)
	public String create(@Valid @ModelAttribute("ability") SuperAbility ability, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        } else {
            this.superAbilityService.create(ability);
            return "redirect:/main";
        }
    }
	
}
