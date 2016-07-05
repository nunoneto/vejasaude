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
var users_service_1 = require("../../services/users.service");
var router_1 = require('@angular/router');
var UserEditComponent = (function () {
    function UserEditComponent(userService, route) {
        this.userService = userService;
        this.route = route;
    }
    UserEditComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.route.params.subscribe(function (params) {
            _this.username = params['id'];
            _this.userService.findUser(_this.username)
                .then(function (user) { return _this.user = user; })
                .catch(function (err) { return console.error(err); });
        });
    };
    UserEditComponent.prototype.editUser = function (event) {
    };
    UserEditComponent = __decorate([
        core_1.Component({
            templateUrl: 'views/menus/users/edit-user.html',
            providers: [users_service_1.UserService],
        }), 
        __metadata('design:paramtypes', [users_service_1.UserService, router_1.ActivatedRoute])
    ], UserEditComponent);
    return UserEditComponent;
}());
exports.UserEditComponent = UserEditComponent;
//# sourceMappingURL=users.edit.component.js.map