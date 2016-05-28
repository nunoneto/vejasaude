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
var http_1 = require('@angular/http');
var api_service_1 = require('./api.service');
require('rxjs/add/operator/toPromise');
var SessionService = (function (_super) {
    __extends(SessionService, _super);
    function SessionService(http) {
        _super.call(this);
        this.http = http;
        this.url = this.relativeUrl + "/session";
    }
    /** ------------------ Login ------------------- **/
    SessionService.prototype.login = function (username, password) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            _this.http.post(_this.url, JSON.stringify({
                username: username,
                password: password
            }), { headers: _this.headers }).toPromise()
                .then(function (resp) {
                if (_this.status(resp))
                    resolve(resp.json().content);
                else
                    reject(resp.json().statusMessage);
            })
                .catch(function (err) {
                reject(null);
            });
        });
    };
    /** ------------------ Change Password ------------------- **/
    SessionService.prototype.changePassword = function (currentPassword, newPassword) {
    };
    /** ------------------ Get Session ------------------- **/
    SessionService.prototype.getSession = function () {
    };
    /** ------------------ Logout ------------------- **/
    SessionService.prototype.logout = function () {
    };
    SessionService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], SessionService);
    return SessionService;
}(api_service_1.APIService));
exports.SessionService = SessionService;
//# sourceMappingURL=session.service.js.map