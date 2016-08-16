import { Injectable } from '@angular/core';
import { APIService } from './api.service'
import { Http } from '@angular/http';
import { Curriculum } from '../model/curriculum';
import { Observable } from 'rxjs/Observable';
import { APIResponse } from "../model/response"

@Injectable()
export class CurriculumService extends APIService{
    
    private url:string;
    private curriculums: Curriculum[];   
     
    constructor(private http: Http){
        super();
        this.url = this.relativeUrl + "/curriculum";
    }
    
    getAll(): Promise<Curriculum[]>{

        return new Promise<Curriculum[]>((resolve,reject) => {
            
            if(this.curriculums != null){
                resolve(this.curriculums);
                return;
            }

            this.http
                .get(this.url,this.options)
                .toPromise()
                .then(response => {

                    var body = response.json();
                    if(this.status(response)){
                        this.curriculums = this.mapResponseToSpecialities(body.content);
                        resolve(this.curriculums);
                    }else
                        reject(body.statusMessage);
                })
                .catch(err => reject(null));
        });
    }
    
    find(id: number): Promise<Curriculum>{
        return new Promise<Curriculum>((resolve,reject) => {
            if(this.curriculums != null){
                var user = this._findUser(id);
                user ? resolve(user) : reject("speciality not found");
                return;
            }
            
            this.http
                .get(this.url,this.options)
                .toPromise()
                .then(response => {
                    var body = response.json();
                    if(this.status(response)){
                        this.curriculums = this.mapResponseToSpecialities(body.content);
                        var user = this._findUser(id);
                        user ? resolve(user) : reject("speciality not found");
                    }else
                        reject(body.statusMessage);
                })
                .catch(err => reject(null));
        });
    }
    
    create(){
        
    }
    
    update(){
        
    }
    
    delete(id: number):boolean{
        return true;
    }

    mapResponseToSpecialities(content): Curriculum[]{
        return content.map(item => new Curriculum(item));
    }

    private _findUser(id: number): Curriculum{
        let _curriculum = null;
        this.curriculums.forEach(function(curriculum){
            if(curriculum.id === id){
                _curriculum = curriculum;
            }
        });
        return _curriculum;
    }
}