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
var core_1 = require('@angular/core');
var session_service_1 = require('../services/session.service');
var home_component_1 = require('./home.component');
var router_deprecated_1 = require('@angular/router-deprecated');
var LoginComponent = (function () {
    function LoginComponent(sessionService, router) {
        this.sessionService = sessionService;
        this.router = router;
    }
    LoginComponent.prototype.doLogin = function () {
        var _this = this;
        this.sessionService.login(this.username, this.password)
            .then(function (user) {
            //redirect user to homepage
            console.log(user);
            _this.router.navigateByUrl('/home');
        })
            .catch(function (err) {
            //show error message
            console.log(err);
        });
    };
    LoginComponent.prototype.eventHandler = function (event) {
        if (event.keyCode == 13) {
            this.doLogin();
        }
    };
    LoginComponent.prototype.remember = function (event) {
        console.log(event.target.checked);
    };
    LoginComponent = __decorate([
        router_deprecated_1.RouteConfig([
            {
                path: '/home',
                name: 'Home',
                component: home_component_1.HomeComponent
            }
        ]),
        core_1.Component({
            selector: 'bo-login',
            templateUrl: 'views/login.html',
            providers: [session_service_1.SessionService, router_deprecated_1.ROUTER_PROVIDERS],
            directives: [router_deprecated_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [session_service_1.SessionService, router_deprecated_1.Router])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map