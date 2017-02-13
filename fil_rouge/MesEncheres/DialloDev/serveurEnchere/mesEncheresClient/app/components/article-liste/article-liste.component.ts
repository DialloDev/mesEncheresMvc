import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs/Rx';

import {Article} from '../../metier/article/article';
import {ArticleService} from '../../services/article.service';


@Component({
    moduleId : module.id,
    selector: 'article-liste',
    templateUrl: './article-liste.component.html'
})
export class ArticleListeComponent implements OnInit
 {
    private _articles : Article[];
    articles : Observable<Article[]>;
    
    // constructeur magique de typescript
    // ici, le mot clé private fait que l'attribut
    // sera automatiquement crée et affecté
    constructor(private _articleService:ArticleService) {
        this.articles = null;
        
    }

    ngOnInit() {
       this.articles = this._articleService.getArticles();
        this.articles.subscribe( article => this._articles = article);

    }
 }