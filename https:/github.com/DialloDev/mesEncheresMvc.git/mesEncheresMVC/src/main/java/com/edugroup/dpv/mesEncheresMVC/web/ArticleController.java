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

import com.edugroup.dpv.mesEncheresMVC.metier.Article;
import com.edugroup.dpv.mesEncheresMVC.metier.SessionEnchere;
import com.edugroup.dpv.mesEncheresMVC.metier.UtilisateurBasic;
import com.edugroup.dpv.mesEncheresMVC.repositories.ArticleRepository;
import com.edugroup.dpv.mesEncheresMVC.repositories.UtilisateurBasicRepository;
import com.edugroup.dpv.mesEncheresMVC.utils.JsonPageable;

@Controller
@RequestMapping(value="/article")
public class ArticleController
{
	@Autowired
	private ArticleRepository articleRepository;
	public ArticleRepository getArticleRepository() {return articleRepository;}
	public void setArticleRepository(ArticleRepository articleRepository) {this.articleRepository = articleRepository;}

	@Autowired
	private UtilisateurBasicRepository utilisateurBasicRepository;
	public UtilisateurBasicRepository getUtilisateurBasicRepository() {return utilisateurBasicRepository;}
	public void setUtilisateurBasicRepository(UtilisateurBasicRepository utilisateurBasicRepository) {this.utilisateurBasicRepository = utilisateurBasicRepository;}
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Article addOne(@RequestBody Article article)
	{
		if (article.getProprietaire() == null)
			return null;
		UtilisateurBasic user = this.getUtilisateurBasicRepository().findOne(article.getProprietaire().getId());
		if (user == null)
			return null;
		article.setProprietaire(user);
		return this.getArticleRepository().save(article);
	}

	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	//@JsonView(TagOnly.class)
	public Page<Article> liste(@PageableDefault(page=0, size=10) Pageable pageRequest) {
		return JsonPageable.fromPage(this.getArticleRepository().findAll(pageRequest));
	}

	@RequestMapping(value="/{id:[0-9]+}", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	//@JsonView(TagOnly.class)
	public void removeOne(@PathVariable("id") int id) {
		this.getArticleRepository().delete(id);
	}

	@RequestMapping(method=RequestMethod.PUT, produces="application/json")
	@ResponseBody
	public Article updateOne(@RequestBody Article article)
	{
		Article old = this.getArticleRepository().findOne(article.getId());
		if (old == null)
		{
			return null;
		}
		else
		{
			old.setDescription(article.getDescription());
			old.setMiseDeDepart(article.getMiseDeDepart());
			old.setNom(article.getNom());
			old.setEnchereMinimum(article.getEnchereMinimum());
			this.getArticleRepository().save(old);
			return old;
		}
		
	}
	
	@RequestMapping(value = "/demarrer/{id:[0-9]+}", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Article demarrer(@PathVariable("id") int id)
	{
		Article article = this.getArticleRepository().findOne(id);
		if (article.getSession() != null)
			return null;
		article.setSession(new SessionEnchere(0, 0));
		this.getArticleRepository().save(article);
		return article;
	}

}
