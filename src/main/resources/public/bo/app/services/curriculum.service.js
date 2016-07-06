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
var curriculum_1 = require('../model/curriculum');
var CurriculumService = (function (_super) {
    __extends(CurriculumService, _super);
    function CurriculumService(http) {
        _super.call(this);
        this.http = http;
        this.url = this.relativeUrl + "/curriculum";
    }
    CurriculumService.prototype.getAll = function () {
        var _this = this;
        return new Promise(function (resolve, reject) {
            if (_this.curriculums != null) {
                resolve(_this.curriculums);
                return;
            }
            _this.http
                .get(_this.url, _this.options)
                .toPromise()
                .then(function (response) {
                var body = response.json();
                if (_this.status(response)) {
                    _this.curriculums = _this.mapResponseToSpecialities(body.content);
                    resolve(_this.curriculums);
                }
                else
                    reject(body.statusMessage);
            })
                .catch(function (err) { return reject(null); });
        });
    };
    CurriculumService.prototype.find = function (id) {
        var _this = this;
        return new Promise(function (resolve, reject) {
            if (_this.curriculums != null) {
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
                    _this.curriculums = _this.mapResponseToSpecialities(body.content);
                    var user = _this._findUser(id);
                    user ? resolve(user) : reject("speciality not found");
                }
                else
                    reject(body.statusMessage);
            })
                .catch(function (err) { return reject(null); });
        });
    };
    CurriculumService.prototype.create = function () {
    };
    CurriculumService.prototype.update = function () {
    };
    CurriculumService.prototype.delete = function (id) {
        return true;
    };
    CurriculumService.prototype.mapResponseToSpecialities = function (content) {
        return content.map(function (item) { return new curriculum_1.Curriculum(item); });
    };
    CurriculumService.prototype._findUser = function (id) {
        var _curriculum = null;
        this.curriculums.forEach(function (curriculum) {
            if (curriculum.id === id) {
                _curriculum = curriculum;
            }
        });
        return _curriculum;
    };
    CurriculumService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], CurriculumService);
    return CurriculumService;
}(api_service_1.APIService));
exports.CurriculumService = CurriculumService;
//# sourceMappingURL=curriculum.service.js.map