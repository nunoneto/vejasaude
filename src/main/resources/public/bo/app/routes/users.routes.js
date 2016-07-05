"use strict";
var users_component_1 = require('../components/menus/users.component');
var users_edit_component_1 = require('../components/menus/users.edit.component');
exports.UserRoutes = [
    {
        path: 'users',
        component: users_component_1.ListUsersComponent,
        children: [
            {
                path: ':id',
                component: users_edit_component_1.UserEditComponent,
            }
        ]
    }
];
//# sourceMappingURL=users.routes.js.map