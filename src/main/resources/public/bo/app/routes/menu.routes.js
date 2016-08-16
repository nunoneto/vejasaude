"use strict";
var home_component_1 = require('../components/home.component');
var users_component_1 = require('../components/menus/users.component');
var doctors_component_1 = require('../components/menus/doctors.component');
exports.MenuRoutes = [
    {
        path: 'home',
        component: home_component_1.HomeComponent,
        children: [
            {
                path: 'users',
                component: users_component_1.ListUsersComponent,
            },
            {
                path: 'doctors',
                component: doctors_component_1.ListDoctorsComponent,
            }
        ]
    }
];
//# sourceMappingURL=menu.routes.js.map