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
var users_service_1 = require("../../services/users.service");
var ListUsersComponent = (function () {
    function ListUsersComponent(userService, router, route) {
        this.userService = userService;
        this.router = router;
        this.route = route;
    }
    ListUsersComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userService.getUsers()
            .then(function (users) { return _this.users = users; })
            .catch(function (err) { return console.log(err); });
    };
    ListUsersComponent.prototype.editUser = function (user) {
        this.router.navigate(['../home/users', user.username]);
    };
    ListUsersComponent = __decorate([
        core_1.Component({
            templateUrl: 'views/menus/users/list-users.html',
            providers: [users_service_1.UserService],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [users_service_1.UserService, router_1.Router, router_1.ActivatedRoute])
    ], ListUsersComponent);
    return ListUsersComponent;
}());
exports.ListUsersComponent = ListUsersComponent;
//# sourceMappingURL=users.component.js.map