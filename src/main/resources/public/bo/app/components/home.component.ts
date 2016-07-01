import { Component } from '@angular/core';
import { Router,  ROUTER_DIRECTIVES} from '@angular/router';
import { ListUsersComponent } from './menus/users.component';


@Component({
  selector: 'bo-home',
  templateUrl: 'views/home.html',
  directives: [ 
    ROUTER_DIRECTIVES
  ],
})


export class HomeComponent{
  

    
}