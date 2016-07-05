import { Component } from '@angular/core';
import { SessionService } from '../services/session.service';
import { HomeComponent } from './home.component';
import { AlertComponent, Type, Alert } from './ui/alert/alert';
import {  Router } from '@angular/router';


@Component({
  templateUrl: 'views/login.html',
  providers: [SessionService],
  directives: [AlertComponent]
})

export class LoginComponent { 
  
  username: string;
  password: string;
  rememberMe: boolean;
  
  //alert props
  alert:Alert;
  
  constructor(private sessionService:SessionService, private router: Router){ 
    this.alert = new Alert();
   }
        
  doLogin(){
    this.alert.setVisible(false);
    this.sessionService.login(this.username,this.password)
      .then(user =>{
        this.router.navigate(['home']);
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
