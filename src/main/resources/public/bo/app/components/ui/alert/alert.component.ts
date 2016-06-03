import { Component, Input } from "@angular/core";
import { NgClass } from '@angular/common';
import { Alert } from './alert.model';

@Component({
    selector: 'alert',
    template: '<div class="alert {{alert.type}} {{alert.visible}}" role="alert">{{alert.message}}</div>',
})

export class AlertComponent{
    
    @Input() alert: Alert;    
}

export class Type{
    
    static SUCCESS:string = 'alert-success'; 
    static INFO:string = 'alert-info'; 
    static WARNING:string = 'alert-warning'; 
    static DANGER:string = 'alert-danger'; 
    
}