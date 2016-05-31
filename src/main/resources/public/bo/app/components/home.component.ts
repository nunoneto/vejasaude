import { Component } from '@angular/core';
import { Router, Routes, ROUTER_PROVIDERS, ROUTER_DIRECTIVES} from '@angular/router';
import { ListUsersComponent } from './menus/users.component';


@Component({
  selector: 'bo-home',
  templateUrl: 'views/home.html',
  providers: [
    ROUTER_PROVIDERS
  ],
  directives: [ 
    ROUTER_DIRECTIVES
  ]
})

@Routes([
  {path: '/users', component: ListUsersComponent}  
])

export class HomeComponent{
  

    
}