"use strict";
var home_component_1 = require('../components/home.component');
var users_component_1 = require('../components/menus/users.component');
exports.MenuRoutes = [
    {
        path: 'home',
        component: home_component_1.HomeComponent,
        children: [
            {
                path: 'users',
                component: users_component_1.ListUsersComponent,
            }
        ]
    }
];
//# sourceMappingURL=menu.routes.js.map