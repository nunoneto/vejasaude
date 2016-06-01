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
var alert_component_1 = require('./ui/alert.component');
var router_1 = require('@angular/router');
var LoginComponent = (function () {
    function LoginComponent(sessionService, router) {
        this.sessionService = sessionService;
        this.router = router;
    }
    LoginComponent.prototype.routerOnActivate = function (curr, prev, currTree) {
        this.currSegment = curr;
    };
    LoginComponent.prototype.doLogin = function () {
        var _this = this;
        this.sessionService.login(this.username, this.password)
            .then(function (user) {
            _this.router.navigate(['/home'], _this.currSegment);
        })
            .catch(function (err) {
            console.log(err);
            _this.alertType = alert_component_1.Type.DANGER;
            _this.alertMessage = err;
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
        core_1.Component({
            templateUrl: 'views/login.html',
            providers: [session_service_1.SessionService],
            directives: [alert_component_1.AlertComponent]
        }), 
        __metadata('design:paramtypes', [session_service_1.SessionService, router_1.Router])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map