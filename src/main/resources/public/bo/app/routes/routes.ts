import { provideRouter, RouterConfig } from '@angular/router';
import { LoginComponent } from '../components/login.component'
import { HomeComponent } from '../components/home.component'
import { AppComponent } from '../components/app.component'
import { ListUsersComponent } from '../components/menus/users.component'

import { MenuRoutes } from './menu.routes'
import { UserRoutes } from './users.routes'
import { DoctorRoutes } from './doctors.routes'

export const routes: RouterConfig = [
    { path: '', component: AppComponent },
    { path: 'login', component: LoginComponent },
    { path: 'home', component: HomeComponent },
    ...MenuRoutes,
    ...UserRoutes,
    ...DoctorRoutes
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];

