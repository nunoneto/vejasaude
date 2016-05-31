import { Component, OnInit } from "@angular/core";
import {Routes , Router, ROUTER_PROVIDERS, ROUTER_DIRECTIVES} from '@angular/router';
import { LoginComponent } from './login.component'
import { HomeComponent } from './home.component'
import { SessionService } from '../services/session.service';


@Component({
    template: '<router-outlet></router-outlet>',
    selector: 'vejasaude-bo',
    providers: [SessionService, ROUTER_PROVIDERS],
    directives: [ROUTER_DIRECTIVES]
})

@Routes([
    { path: '/login', component: LoginComponent },
    { path: '/home', component: HomeComponent },
])


export class AppComponent implements OnInit{
    
    constructor(private sessionService: SessionService, private router: Router){}
    
    ngOnInit(){
        this.sessionService.getSession()
        .then(user => user ? this.home() : this.login())
        .catch(err => this.login());
    }
    
    home(){
        console.log("Going Home!");
        this.router.navigate(['/home']);
    }
    
    login(){
        console.log("Back to loign you fool!");
        this.router.navigate(['/login']);
    }
}