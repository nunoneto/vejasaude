import { RouterConfig }          from '@angular/router';
import { HomeComponent } from './components/home.component'
import { ListUsersComponent } from './components/menus/users.component'

export const MenuRoutes: RouterConfig = [
  {
    path: 'home',
    component: HomeComponent,
    terminal: true,
    children: [
        {
            path: 'users',
            component: ListUsersComponent,
        }
    ]
  }

];