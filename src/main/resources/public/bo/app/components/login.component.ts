import { Component } from '@angular/core';
import { SessionService } from '../services/session.service';
import { HomeComponent } from './home.component';
import { OnActivate, Router, RouteSegment, RouteTree, RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router';

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

export class LoginComponent implements OnActivate{ 
  
  private currSegment: RouteSegment;
  private username: string;
  private password: string;
  private rememberMe: boolean;
  
  constructor(private sessionService:SessionService, private router:Router){  }
  
  doLogin(){
    this.sessionService.login(this.username,this.password)
      .then(user =>{
        //TODO redirect user to homepage
        console.log(user);
        this.router.navigateByUrl(['./home'],currSegment);


      })
      .catch(err => {
        //TODO show error message
        console.log(err);
      });
  }
  
  eventHandler(event) {
    if(event.keyCode == 13){
      this.doLogin();
    }
  } 
  
  remember(event){
    //TODO: store preference
    console.log(event.target.checked);
  }

  routerOnActivate(curr: RouteSegment, prev: RouteSegment, currTree: RouteTree) {
    this.currSegment = curr;
  }

  
}
