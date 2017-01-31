package com.edugroup.dpv.mesEncheresMVC.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.edugroup.dpv.mesEncheresMVC.metier.Article;
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
	
	@RequestMapping(value="/create", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Enchere addEnchere(Enchere enchere){
		return this.getEnchereRepository().save(enchere);
	}
	@RequestMapping(value="/update", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public Enchere updateEnchere(@RequestBody Enchere enchere){
		Enchere ench = enchereRepository.findOne(enchere.getId());
		if (ench == null){
			return null;
		}
		else {
			ench.setNouveauMontant(enchere.getNouveauMontant());
			ench.setDateEnchere(enchere.getDateEnchere());
		}
		return ench;
	}
	@RequestMapping(value="/delete/{id:[0-9]+}", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	public void deleteOne(@PathVariable("id") int id){
		Enchere ench = enchereRepository.findOne(id);
		if (ench == null){
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Enchere non trouve");
		}
		this.getEnchereRepository().delete(id); 	
	}
	/*
	@RequestMapping(value="/encherir", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public Enchere encherir(@RequestBody Enchere enchere){
		Enchere ench = this.getEnchereRepository().findOne(enchere.getId());
		if (ench == null)
			return null;
		ench.setNouveauMontant(enchere.getNouveauMontant() + enchere.getNouveauMontant() * 0.05); 
		 return ench;
	}*/
	
	@RequestMapping(value="/encherir", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public Enchere encherire(@RequestBody Enchere enchere){
		Enchere ench = enchereRepository.findOne(enchere.getId());
		if (ench != null){
			Article a = ench.getSession().getArticle();
			if (a != null){
				if (a.getEnchereMinimum() < ench.getNouveauMontant()){
					a.setEnchereMinimum(ench.getNouveauMontant());
				}
				else {
					throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "le nouveau montant doit être > à l'enchere min");
				}
			}
			else{
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Article non trouve");
			}
		}
		return ench;
	}
	
}
