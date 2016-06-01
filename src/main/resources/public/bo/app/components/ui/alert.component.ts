import { Component, Input } from "@angular/core";
import { NgClass } from '@angular/common';

@Component({
    selector: 'alert',
    template: '<div class="alert" [ngClass]="{hidden: visible, type: type}" role="alert">{{message}}</div>',
    directives: [NgClass]
})

export class AlertComponent{
    
    visible: boolean;
    @Input() type: string;
    @Input() message: string;
    
    constructor(){
        this.visible = true;
    }
    
    public toggle(visible: boolean){
        this.visible = visible;
    }
    
    public setType(type:string){
        this.type = type;
    }
    
    public setMessage(message:string){
        this.message = message;
    }
    
    public change(type:string, message:string){
        this.type = type;
        this.message = message;
    }
    
}

export class Type{
    
    static SUCCESS:string = 'alert-success'; 
    static INFO:string = 'alert-info'; 
    static WARNING:string = 'alert-warning'; 
    static DANGER:string = 'alert-danger'; 
    
}