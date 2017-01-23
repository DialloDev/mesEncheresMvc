package com.edugroup.dpv.mesEncheresMVC.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroup.dpv.mesEncheresMVC.metier.Enchere;
import com.edugroup.dpv.mesEncheresMVC.metier.SessionEnchere;
import com.edugroup.dpv.mesEncheresMVC.repositories.EnchereRepository;
import com.edugroup.dpv.mesEncheresMVC.repositories.SessionEnchereRepository;
import com.edugroup.dpv.mesEncheresMVC.utils.JsonPageable;

@Controller
@RequestMapping(value="/enchere")
public class EnchereController {
	
	@Autowired
	private EnchereRepository enchereRepository;
	@Autowired
	private SessionEnchereRepository sessionEnchereRepository;
	public EnchereRepository getEnchereRepository() {
		return enchereRepository;
	}
	public void setEnchereRepository(EnchereRepository enchereRepository) {
		this.enchereRepository = enchereRepository;
	}
	
	public SessionEnchereRepository getSessionEnchereRepository() {
		return sessionEnchereRepository;
	}
	public void setSessionEnchereRepository(SessionEnchereRepository sessionEnchereRepository) {
		this.sessionEnchereRepository = sessionEnchereRepository;
	}
	
	@RequestMapping(value="/liste", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Page<Enchere> liste(@PageableDefault(page=0, size=10) Pageable pageRequest){
		return JsonPageable.fromPage(this.getEnchereRepository().findAll(pageRequest));
	}
	
	@RequestMapping(value="/save", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Enchere addEnchere(Enchere enchere){
		Enchere ench = enchereRepository.findOne(enchere.getId());
		if(ench != null){
			return null;
		}
		return this.getEnchereRepository().save(enchere);
	}
	
	public Enchere saveEnchere(Enchere enchere){
		Enchere ench = enchereRepository.findOne(enchere.getId());
		if (ench == null){
			return null;
		}
		return ench;
	}
}
