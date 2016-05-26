import { Component } from '@angular/core';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'bo-login',
  templateUrl: '/views/login.html',
  providers: [SessionService]
})
export class LoginComponent { 
  
  username: string;
  password: string;
  rememberMe : boolean;
  
  constructor(private sessionService:SessionService){
    
  }
  
  doLogin(){
    this.sessionService.login(this.username,this.password);
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
