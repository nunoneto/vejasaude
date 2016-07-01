import { provideRouter, RouterConfig } from '@angular/router';
import { LoginComponent } from './components/login.component'
import { HomeComponent } from './components/home.component'
import { AppComponent } from './components/app.component'
import { ListUsersComponent } from './components/menus/users.component'

export const routes: RouterConfig = [
    { path: '', component: AppComponent },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    { path: 'users', component: ListUsersComponent },
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];

