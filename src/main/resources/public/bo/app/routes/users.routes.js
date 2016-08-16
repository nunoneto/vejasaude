"use strict";
var users_component_1 = require('../components/menus/users.component');
var users_edit_component_1 = require('../components/menus/users.edit.component');
exports.UserRoutes = [
    {
        path: 'users',
        children: [
            {
                path: '',
                component: users_component_1.ListUsersComponent,
            },
            {
                path: ':id',
                component: users_edit_component_1.UserEditComponent,
            }
        ]
    }
];
//# sourceMappingURL=users.routes.js.map