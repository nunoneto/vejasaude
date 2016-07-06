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
var speciality_1 = require('../model/speciality');
var SpecialityService = (function (_super) {
    __extends(SpecialityService, _super);
    function SpecialityService(http) {
        _super.call(this);
        this.http = http;
        this.url = this.relativeUrl + "/speciality";
    }
    SpecialityService.prototype.getAll = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            if (_this.specialities != null) {
                resolve(_this.specialities);
                return;
            }
            _this.http
                .get(_this.url, _this.options)
                .toPromise()
                .then(function (response) {
                var body = response.json();
                if (_this.status(response)) {
                    _this.specialities = _this.mapResponseToSpecialities(body.content);
                    resolve(_this.specialities);
                }
                else
                    reject(body.statusMessage);
            })
                .catch(function (err) { return reject(null); });
        });
    };
    SpecialityService.prototype.find = function (id) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            if (_this.specialities != null) {
                var user = _this._findUser(id);
                user ? resolve(user) : reject("speciality not found");
                return;
            }
            _this.http
                .get(_this.url, _this.options)
                .toPromise()
                .then(function (response) {
                var body = response.json();
                if (_this.status(response)) {
                    _this.specialities = _this.mapResponseToSpecialities(body.content);
                    var user = _this._findUser(id);
                    user ? resolve(user) : reject("speciality not found");
                }
                else
                    reject(body.statusMessage);
            })
                .catch(function (err) { return reject(null); });
        });
    };
    SpecialityService.prototype.create = function () {
    };
    SpecialityService.prototype.update = function () {
    };
    SpecialityService.prototype.delete = function (id) {
        return true;
    };
    SpecialityService.prototype.mapResponseToSpecialities = function (content) {
        return content.map(function (item) { return new speciality_1.Speciality(item); });
    };
    SpecialityService.prototype._findUser = function (id) {
        var _speciality = null;
        this.specialities.forEach(function (speciality) {
            if (speciality.id === id) {
                _speciality = speciality;
            }
        });
        return _speciality;
    };
    SpecialityService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], SpecialityService);
    return SpecialityService;
}(api_service_1.APIService));
exports.SpecialityService = SpecialityService;
//# sourceMappingURL=speciality.service.js.map