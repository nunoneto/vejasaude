import { RouterConfig } from '@angular/router';
import { ListUsersComponent } from '../components/menus/users.component'
import { UserEditComponent } from '../components/menus/users.edit.component'

export const UserRoutes: RouterConfig = [
    {
        path: 'users',
        children: [
            {
                path: '',
                component: ListUsersComponent,
            },
            {
                path: ':id',
                component: UserEditComponent,
            }
        ]
    }

];