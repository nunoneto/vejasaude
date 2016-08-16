import { Injectable } from '@angular/core';
import { APIService } from './api.service'
import { Http } from '@angular/http';
import { Speciality } from '../model/speciality';
import { Observable } from 'rxjs/Observable';
import { APIResponse } from "../model/response"

@Injectable()
export class SpecialityService extends APIService{
    
    private url:string;
    private specialities: Speciality[];   
     
    constructor(private http: Http){
        super();
        this.url = this.relativeUrl + "/speciality";
    }
    
    getAll(): Promise<Speciality[]>{

        return new Promise<Speciality[]>((resolve,reject) => {
            
            if(this.specialities != null){
                resolve(this.specialities);
                return;
            }

            this.http
                .get(this.url,this.options)
                .toPromise()
                .then(response => {

                    var body = response.json();
                    if(this.status(response)){
                        this.specialities = this.mapResponseToSpecialities(body.content);
                        resolve(this.specialities);
                    }else
                        reject(body.statusMessage);
                })
                .catch(err => reject(null));
        });
    }
    
    find(id: number): Promise<Speciality>{
        return new Promise<Speciality>((resolve,reject) => {
            if(this.specialities != null){
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
                        this.specialities = this.mapResponseToSpecialities(body.content);
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

    mapResponseToSpecialities(content): Speciality[]{
        return content.map(item => new Speciality(item));
    }

    private _findUser(id: number): Speciality{
        let _speciality = null;
        this.specialities.forEach(function(speciality){
            if(speciality.id === id){
                _speciality = speciality;
            }
        });
        return _speciality;
    }
}