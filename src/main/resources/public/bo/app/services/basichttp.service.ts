import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';


export class BasicHttpService{

    relativeUrl:string = "/api/v1";
    headers = new Headers({ 'Content-Type': 'application/json' });


}