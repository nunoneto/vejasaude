import { Component } from '@angular/core';
import { SessionService } from '../services/session.service';
import { HomeComponent } from './home.component';
import { Router, RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';

@RouteConfig([
  {
    path: '/home',
    name: 'Home',
    component: HomeComponent
  }
])

@Component({
  selector: 'bo-login',
  templateUrl: 'views/login.html',
  providers: [SessionService, ROUTER_PROVIDERS],
  directives: [ROUTER_DIRECTIVES]
})

export class LoginComponent { 
  
  username: string;
  password: string;
  rememberMe: boolean;
  
  constructor(private sessionService:SessionService, private router:Router){  }
  
  doLogin(){
    this.sessionService.login(this.username,this.password)
      .then(user =>{
        //redirect user to homepage
        console.log(user);
        this.router.navigateByUrl('/home');


      })
      .catch(err => {
        //show error message
        console.log(err);
      });
  }
  
  eventHandler(event) {
    if(event.keyCode == 13){
      this.doLogin();
    }
  } 
  
  remember(event){
    console.log(event.target.checked);
  }

  
}
