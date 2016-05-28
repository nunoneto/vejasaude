import { Response, Headers } from '@angular/http';
import { Status } from '../model/status';


export class APIService{

    relativeUrl:string = "http://localhost:8082/api/v1";
    headers = new Headers({ 'Content-Type': 'application/json' });

    status(resp: Response): boolean{
       return resp.json().statusCode == Status.OK 
    }
}