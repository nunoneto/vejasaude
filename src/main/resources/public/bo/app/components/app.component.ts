import { Component, OnInit } from "@angular/core";
import { Router, ROUTER_DIRECTIVES } from '@angular/router';
import { LoginComponent } from './login.component'
import { HomeComponent } from './home.component'
import { ListUsersComponent } from './menus/users.component'
import { SessionService } from '../services/session.service';


@Component({
    template: '<router-outlet></router-outlet>',
    selector: 'vejasaude-bo',
    providers: [SessionService],
    directives: [ROUTER_DIRECTIVES]
})

export class AppComponent implements OnInit{
    
    constructor(private sessionService: SessionService, private router: Router){}
    
    ngOnInit(){
        this.sessionService.getSession()
        .then(user => user ? this.home() : this.login())
        .catch(err => this.login());
    }
    
    home(){
        console.info("Going Home!");
        this.router.navigate(['home']);
    }
    
    login(){
        console.info("Back to login you fool!");
        this.router.navigate(['login']);
    }
}