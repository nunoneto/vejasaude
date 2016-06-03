import { Component } from '@angular/core';
import { SessionService } from '../services/session.service';
import { HomeComponent } from './home.component';
import { AlertComponent, Type, Alert } from './ui/alert/alert';
import { OnActivate, Router, RouteSegment, RouteTree } from '@angular/router';


@Component({
  templateUrl: 'views/login.html',
  providers: [SessionService],
  directives: [AlertComponent]
})

export class LoginComponent implements OnActivate{ 
  
  username: string;
  password: string;
  rememberMe: boolean;
  private currSegment: RouteSegment;
  
  //alert props
  alert:Alert;
  
  constructor(private sessionService:SessionService, private router: Router){ 
    this.alert = new Alert();
   }
    
  routerOnActivate(curr: RouteSegment, prev: RouteSegment, currTree: RouteTree) {
    this.currSegment = curr;
  }
    
  doLogin(){
    this.alert.setVisible(false);
    this.sessionService.login(this.username,this.password)
      .then(user =>{
        this.router.navigate(['/home'],this.currSegment);
      })
      .catch(err => {
        console.log(err);
        this.alert.type = Type.DANGER;
        this.alert.message = err;
        this.alert.setVisible(true);
        
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
