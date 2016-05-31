import { Component } from '@angular/core';
import { Router, ROUTER_PROVIDERS } from '@angular/router';

@Component({
    selector: 'bo-menubar',
    templateUrl: 'views/menubar.html',
    providers: [ROUTER_PROVIDERS]
})

export class MenuBarComponent{
    
    constructor(private router:Router){ }
    
    openMenu(menu:string){
        //this.router.navigate();
    }
}