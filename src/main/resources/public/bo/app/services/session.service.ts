import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { BasicHttpService } from './basichttp.service';
import { Observable }     from 'rxjs/Observable';



@Injectable()
export class SessionService extends BasicHttpService{
    
    url:string;
    
    constructor (private http: Http) {
        super();
        this.url = this.relativeUrl + "/session";
    }
    
    login(username:string, password:string): Observable<Hero>{
        
        
        this.http.post(
            this.url,
            JSON.stringify({
               username: username,
               password: password 
            }));
    }
    
    changePassword(currentPassword:string, newPassword:String){
        
    }
    
    logout(){
        
    }
    
    
    
}