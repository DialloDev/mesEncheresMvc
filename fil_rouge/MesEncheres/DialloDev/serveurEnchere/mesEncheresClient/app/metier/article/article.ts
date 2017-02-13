export class Article{
    public id : number;
    public nom : string;
    public description : string;
    public miseDepart : number;
    public enchereMinimum : number;
    

    constructor(id: number, nom: string, description: string, miseDepart: number, enchereMinimum: number){
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.miseDepart = miseDepart;
        this.enchereMinimum = enchereMinimum;
        
    }
};