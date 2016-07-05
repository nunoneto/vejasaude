"use strict";
var router_1 = require('@angular/router');
var login_component_1 = require('../components/login.component');
var home_component_1 = require('../components/home.component');
var app_component_1 = require('../components/app.component');
var menu_routes_1 = require('./menu.routes');
var users_routes_1 = require('./users.routes');
var doctors_routes_1 = require('./doctors.routes');
exports.routes = [
    { path: '', component: app_component_1.AppComponent },
    { path: 'login', component: login_component_1.LoginComponent },
    { path: 'home', component: home_component_1.HomeComponent }
].concat(menu_routes_1.MenuRoutes, users_routes_1.UserRoutes, doctors_routes_1.DoctorRoutes);
exports.APP_ROUTER_PROVIDERS = [
    router_1.provideRouter(exports.routes)
];
//# sourceMappingURL=routes.js.map