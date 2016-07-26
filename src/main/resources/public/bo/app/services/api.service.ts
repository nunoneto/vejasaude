import { Response, Headers } from '@angular/http';
import { Status } from '../model/status';


export class APIService{

    relativeUrl:string = document.location.origin+"/api/v1";
    headers = new Headers({ 'Content-Type': 'application/json' });
    options = {headers: this.headers};
    
    status(resp: Response): boolean{
       return resp.json().statusCode == Status.OK 
    }
}