"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var router_1 = require('@angular/router');
var login_component_1 = require('./login.component');
var home_component_1 = require('./home.component');
var session_service_1 = require('../services/session.service');
var AppComponent = (function () {
    function AppComponent(sessionService, router) {
        this.sessionService = sessionService;
        this.router = router;
    }
    AppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sessionService.getSession()
            .then(function (user) { return user ? _this.home() : _this.login(); })
            .catch(function (err) { return _this.login(); });
    };
    AppComponent.prototype.home = function () {
        console.log("Going Home!");
        this.router.navigate(['/home']);
    };
    AppComponent.prototype.login = function () {
        console.log("Back to loign you fool!");
        this.router.navigate(['/login']);
    };
    AppComponent = __decorate([
        core_1.Component({
            template: '<router-outlet></router-outlet>',
            selector: 'vejasaude-bo',
            providers: [session_service_1.SessionService, router_1.ROUTER_PROVIDERS],
            directives: [router_1.ROUTER_DIRECTIVES]
        }),
        router_1.Routes([
            { path: '/login', component: login_component_1.LoginComponent },
            { path: '/home', component: home_component_1.HomeComponent },
        ]), 
        __metadata('design:paramtypes', [session_service_1.SessionService, router_1.Router])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map