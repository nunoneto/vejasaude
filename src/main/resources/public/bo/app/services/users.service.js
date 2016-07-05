"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
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
var api_service_1 = require('./api.service');
var http_1 = require('@angular/http');
var user_1 = require('../model/user');
var UserService = (function (_super) {
    __extends(UserService, _super);
    function UserService(http) {
        _super.call(this);
        this.http = http;
        this.url = this.relativeUrl + "/bouser";
    }
    UserService.prototype.getUsers = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.http
                .get(_this.url, _this.options)
                .toPromise()
                .then(function (response) {
                var body = response.json();
                if (_this.status(response)) {
                    _this.users = _this.mapResponseToUsers(body.content);
                    resolve(_this.users);
                }
                else
                    reject(body.statusMessage);
            })
                .catch(function (err) { return reject(null); });
        });
    };
    UserService.prototype.findUser = function (id) {
        return true;
    };
    UserService.prototype.createUser = function () {
    };
    UserService.prototype.updateUser = function () {
    };
    UserService.prototype.deleteUser = function (id) {
        return true;
    };
    UserService.prototype.mapResponseToUsers = function (content) {
        return content.map(function (item) { return new user_1.User(item); });
    };
    UserService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], UserService);
    return UserService;
}(api_service_1.APIService));
exports.UserService = UserService;
//# sourceMappingURL=users.service.js.map