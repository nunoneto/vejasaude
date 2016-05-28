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
        this.activeUser = null;
    }
    
    /** ------------------ Login ------------------- **/
    
    public login(username:string, password:string): Promise<User>{

        return new Promise<User>((resolve, reject)=>{  
            
            this.http.post(
                this.url,
                JSON.stringify({
                    username: username,
                    password: password 
                }),this.options).toPromise()
            .then(resp => {
                var body = resp.json();
                if(this.status(resp)){
                    this.activeUser = new User(body.content);
                    resolve(this.activeUser);
                }else
                    reject(body.statusMessage);
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

    getSession(): Promise<User>{
        return new Promise<User>((resolve,reject) => {
            
            if(this.activeUser != null){
                resolve(this.activeUser);
            }else{
                this.http.get(this.url,this.options).toPromise()
                .then(resp => {
                    var body = resp.json();
                    if(this.status(resp)){
                        this.activeUser = new User(body.content);
                        resolve(this.activeUser);
                    }else
                        reject(body.statusCode);
                })
                .catch(err => reject());
            }
                
            
        });
    }

    
    
    /** ------------------ Logout ------------------- **/

    logout(){
        
    }
    
   
  

    
    
    
}