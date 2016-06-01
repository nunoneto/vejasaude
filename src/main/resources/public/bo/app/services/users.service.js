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
var UsersService = (function (_super) {
    __extends(UsersService, _super);
    function UsersService(http) {
        _super.call(this);
        this.http = http;
        this.url = this.relativeUrl + "/bouser";
    }
    UsersService.prototype.getUsers = function () {
        return null;
    };
    UsersService.prototype.findUser = function () {
    };
    UsersService.prototype.createUser = function () {
    };
    UsersService.prototype.updateUser = function () {
    };
    UsersService.prototype.deleteUser = function () {
    };
    UsersService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], UsersService);
    return UsersService;
}(api_service_1.APIService));
exports.UsersService = UsersService;
//# sourceMappingURL=users.service.js.map