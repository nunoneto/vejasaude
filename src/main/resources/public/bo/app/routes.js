"use strict";
var router_1 = require('@angular/router');
var login_component_1 = require('./components/login.component');
var home_component_1 = require('./components/home.component');
var app_component_1 = require('./components/app.component');
var users_component_1 = require('./components/menus/users.component');
exports.routes = [
    { path: '', component: app_component_1.AppComponent },
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'home', component: home_component_1.HomeComponent },
    { path: 'users', component: users_component_1.ListUsersComponent },
];
exports.APP_ROUTER_PROVIDERS = [
    router_1.provideRouter(exports.routes)
];
//# sourceMappingURL=routes.js.map