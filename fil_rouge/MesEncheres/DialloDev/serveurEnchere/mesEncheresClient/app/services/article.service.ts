
import {Injectable} from '@angular/core'
import {Article} from '../metier/article/article';
import {BehaviorSubject, Observable} from 'rxjs/Rx';
//import {Http, Response} from '@angular/http';
 const SAMPLE_ARTILCE : Article[] = [
    new Article(1, "SUPER JAGUAR", 'app/pictures/jaguar.jpeg', 150000, 3000),
    new Article(2, "SUPER BOLID", '../../app/pictures/bolid.jpg', 99000, 5000),
    new Article(3, "SUPER FERRARI", '../../pictures/ferrari.jpeg', 179000, 9000),
    new Article(4, "SUPER JAGUAR", '../app/pictures/jaguar2.jpg', 200000, 10000),
    new Article(5, "SUPER BOLID", '../pictures/bolid1.jpg', 127000, 12000),
    new Article(6, "SUPER FERRARI", '../../pictures/ferrari.jpg', 150000, 10000),


]

@Injectable()
export class ArticleService 
{

    private _articles : Article[];
    private _articleObservableBuilder : BehaviorSubject<Article[]>;

    constructor() {
        this._articles = SAMPLE_ARTILCE;
       
        this._articleObservableBuilder = 
            new BehaviorSubject<Article[]>(this._articles);
    }

    getArticles(): Observable<Article[]>{
        return this._articleObservableBuilder.asObservable();
    }

    findByid(id: number) : Article {
        return this._articles.find(artilce => artilce.id == id);
    }

}