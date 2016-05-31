import { Component } from '@angular/core';
import { SessionService } from '../services/session.service';
import { HomeComponent } from './home.component';
import { OnActivate, Router, RouteSegment, RouteTree } from '@angular/router';


@Component({
  templateUrl: 'views/login.html',
  providers: [SessionService],
})

export class LoginComponent implements OnActivate{ 
  
  username: string;
  password: string;
  rememberMe: boolean;
  private currSegment: RouteSegment;
  
  constructor(private sessionService:SessionService, private router: Router){  }
    
  routerOnActivate(curr: RouteSegment, prev: RouteSegment, currTree: RouteTree) {
    this.currSegment = curr;
  }
    
  doLogin(){
    this.sessionService.login(this.username,this.password)
      .then(user =>{
        this.router.navigate(['/home'],this.currSegment);
      })
      .catch(err => {
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
