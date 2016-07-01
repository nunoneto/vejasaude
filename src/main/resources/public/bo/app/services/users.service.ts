import { Injectable } from '@angular/core';
import { APIService } from './api.service'
import { Http } from '@angular/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';
import { APIResponse } from "../model/response"

@Injectable()
export class UserService extends APIService{
    
    private url:string;
    private users: User[];   
     
    constructor(private http: Http){
        super();
        this.url = this.relativeUrl + "/bouser";
    }
    
    getUsers(): Promise<User[]>{

        return new Promise<User[]>((resolve,reject) => {
            this.http
                .get(this.url,this.options)
                .toPromise()
                .then(response => {

                    var body = response.json();
                    if(this.status(response)){
                        this.users = this.mapResponseToUsers(body.content);
                        resolve(this.users);
                    }else
                        reject(body.statusMessage);
                })
                .catch(err => reject(null));
        });
    }
    
    findUser(id: number): boolean{
        return true;
    }
    
    createUser(){
        
    }
    
    updateUser(){
        
    }
    
    deleteUser(id: number):boolean{
        return true;
    }

    mapResponseToUsers(content): User[]{
        return content.map(item => new User(item));
    }
}