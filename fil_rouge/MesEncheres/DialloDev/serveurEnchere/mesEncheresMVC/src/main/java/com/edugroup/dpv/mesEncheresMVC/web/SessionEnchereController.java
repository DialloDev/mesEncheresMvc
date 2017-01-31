package com.edugroup.dpv.mesEncheresMVC.web;

import java.util.List;
import java.util.Set;

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
@RequestMapping(value="/session")
public class SessionEnchereController {
	@Autowired
	private SessionEnchereRepository sessionEnchereRepository;
	@Autowired
	private EnchereRepository enchereRepository;
	
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
	public Page<SessionEnchere> liste(@PageableDefault(page=0, size=10) Pageable pageRequest){
		return JsonPageable.fromPage(this.getSessionEnchereRepository().findAll(pageRequest));
	}
	
	public SessionEnchere createOne(SessionEnchere session){
		Set<Enchere> ench = session.getEncheres();
		if (ench == null)
			return null;
	
		return this.getSessionEnchereRepository().save(session);
	}
}
