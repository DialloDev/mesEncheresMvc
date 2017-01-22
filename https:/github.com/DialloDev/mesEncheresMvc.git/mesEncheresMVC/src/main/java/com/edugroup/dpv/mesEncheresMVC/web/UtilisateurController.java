package com.edugroup.dpv.mesEncheresMVC.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroup.dpv.mesEncheresMVC.metier.UtilisateurBasic;
import com.edugroup.dpv.mesEncheresMVC.metier.UtilisateurPro;
import com.edugroup.dpv.mesEncheresMVC.repositories.UtilisateurBasicRepository;
import com.edugroup.dpv.mesEncheresMVC.repositories.UtilisateurProRepository;
import com.edugroup.dpv.mesEncheresMVC.utils.JsonPageable;

@Controller
@RequestMapping(value="/utilisateur")
public class UtilisateurController
{
	@Autowired
	private UtilisateurBasicRepository utilisateurBasicRepository;
	public UtilisateurBasicRepository getUtilisateurBasicRepository() {return utilisateurBasicRepository;}
	public void setUtilisateurBasicRepository(UtilisateurBasicRepository utilisateurBasicRepository) {this.utilisateurBasicRepository = utilisateurBasicRepository;}

	@Autowired
	private UtilisateurProRepository utilisateurProRepository;
	public UtilisateurProRepository getUtilisateurProRepository() {return utilisateurProRepository;}
	public void setUtilisateurProRepository(UtilisateurProRepository utilisateurProRepository) {this.utilisateurProRepository = utilisateurProRepository;}

	@RequestMapping(value="/basic",method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public UtilisateurBasic addOneBasic(@RequestBody UtilisateurBasic user)
	{
		return this.getUtilisateurBasicRepository().save(user);
	}

	@RequestMapping(value="/pro",method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public UtilisateurPro addOnePro(@RequestBody UtilisateurPro user)
	{
		return null; //this.getUtilisateurProRepository().save(user);
	}

	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	//@JsonView(TagOnly.class)
	public Page<UtilisateurBasic> liste(@PageableDefault(page=0, size=10) Pageable pageRequest) {
		return JsonPageable.fromPage(this.getUtilisateurBasicRepository().findAll(pageRequest));
	}

	@RequestMapping(value="/basic/{id:[0-9]+}", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	//@JsonView(TagOnly.class)
	public void removeOneBasic(@PathVariable("id") int id) {
		this.getUtilisateurBasicRepository().delete(id);
	}

	@RequestMapping(value="/pro/{id:[0-9]+}", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	//@JsonView(TagOnly.class)
	public void removeOnePro(@PathVariable("id") int id) {
		this.getUtilisateurProRepository().delete(id);
	}

	@RequestMapping(value="/basic", method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public UtilisateurBasic updateOneBasic(@RequestBody UtilisateurBasic user)
	{
		UtilisateurBasic old = this.getUtilisateurBasicRepository().findOne(user.getId());
		if (old == null)
		{
			return null;
		}
		else
		{
			old.setNom(user.getNom());
			old.setPrenom(user.getPrenom());
			old.setAdresse(user.getAdresse());
			this.getUtilisateurBasicRepository().save(old);
			return old;
		}
		
	}
	
	@RequestMapping(value="/pro",method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public UtilisateurPro updateOnePro(@RequestBody UtilisateurPro user)
	{
		UtilisateurPro old = (UtilisateurPro) this.getUtilisateurProRepository().findOne(user.getId());
		if (old == null)
		{
			return null;
		}
		else
		{
			old.setNom(user.getNom());
			old.setPrenom(user.getPrenom());
			old.setAdresse(user.getAdresse());
			old.setRaisonSociale(user.getRaisonSociale());
			//this.getUtilisateurProRepository().save(old);
			return old;
		}
		
	}

}