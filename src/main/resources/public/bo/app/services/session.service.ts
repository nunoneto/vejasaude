import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { APIService } from './api.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/toPromise';

import { User } from '../model/user';
import { APIResponse } from '../model/response';
import { Status } from '../model/status';



@Injectable()
export class SessionService extends APIService{
    
    private url:string;
    private activeUser: User;
    
    constructor (private http: Http) {
        super();
        this.url = this.relativeUrl + "/session";
    }
    
    /** ------------------ Login ------------------- **/
    
    public login(username:string, password:string): Promise<APIResponse>{

        return new Promise((resolve, reject)=>{  
            
            this.http.post(
                this.url,
                JSON.stringify({
                    username: username,
                    password: password 
                }),{headers: this.headers}).toPromise()
            .then(resp => {
                if(this.status(resp))
                    resolve(resp.json().content);
                else
                    reject(resp.json().statusMessage);
            })
            .catch(err => {
                reject(null);
            });
        
        });
   
    }
    
    /** ------------------ Change Password ------------------- **/
    
    changePassword(currentPassword:string, newPassword:String){
        
    }
    
    /** ------------------ Get Session ------------------- **/

    getSession(){
        
    }

    
    
    /** ------------------ Logout ------------------- **/

    logout(){
        
    }
    
   
  

    
    
    
}