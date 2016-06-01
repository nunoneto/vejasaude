import { Injectable } from '@angular/core';
import { APIService } from './api.service'
import { Http } from '@angular/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UsersService extends APIService{
    
    private url:string;
    private users: User[];   
     
    constructor(private http: Http){
        super();
        this.url = this.relativeUrl + "/bouser";
    }
    
    getUsers(): Observable<User[]>{
        
        
        
        return null;
    }
    
    findUser(){
        
    }
    
    createUser(){
        
    }
    
    updateUser(){
        
    }
    
    deleteUser(){
        
    }
}