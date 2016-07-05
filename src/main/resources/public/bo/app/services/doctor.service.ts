import { Injectable } from '@angular/core';
import { APIService } from './api.service'
import { Http } from '@angular/http';
import { Doctor } from '../model/doctor';
import { Observable } from 'rxjs/Observable';
import { APIResponse } from "../model/response"

@Injectable()
export class DoctorService extends APIService{
    
    private url:string;
    private doctors: Doctor[];   
     
    constructor(private http: Http){
        super();
        this.url = this.relativeUrl + "/doctor";
    }
    
    getAll(): Promise<Doctor[]>{

        return new Promise<Doctor[]>((resolve,reject) => {
            
            if(this.doctors != null){
                resolve(this.doctors);
                return;
            }

            this.http
                .get(this.url,this.options)
                .toPromise()
                .then(response => {

                    var body = response.json();
                    if(this.status(response)){
                        this.doctors = this.mapResponseToDoctors(body.content);
                        resolve(this.doctors);
                    }else
                        reject(body.statusMessage);
                })
                .catch(err => reject(null));
        });
    }
    
    find(id: number): Promise<Doctor>{
        return new Promise<Doctor>((resolve,reject) => {
            if(this.doctors != null){
                var user = this._findUser(id);
                user ? resolve(user) : reject("doctor not found");
                return;
            }
            
            this.http
                .get(this.url,this.options)
                .toPromise()
                .then(response => {
                    var body = response.json();
                    if(this.status(response)){
                        this.doctors = this.mapResponseToDoctors(body.content);
                        var user = this._findUser(id);
                        user ? resolve(user) : reject("doctor not found");
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

    mapResponseToDoctors(content): Doctor[]{
        return content.map(item => new Doctor(item));
    }

    private _findUser(id: number): Doctor{
        let _doctor = null;
        this.doctors.forEach(function(doctor){
            if(doctor.id === id){
                _doctor = doctor;
            }
        });
        return _doctor;
    }
}