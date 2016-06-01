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
var common_1 = require('@angular/common');
var AlertComponent = (function () {
    function AlertComponent() {
        this.visible = true;
    }
    AlertComponent.prototype.toggle = function (visible) {
        this.visible = visible;
    };
    AlertComponent.prototype.setType = function (type) {
        this.type = type;
    };
    AlertComponent.prototype.setMessage = function (message) {
        this.message = message;
    };
    AlertComponent.prototype.change = function (type, message) {
        this.type = type;
        this.message = message;
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], AlertComponent.prototype, "type", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', String)
    ], AlertComponent.prototype, "message", void 0);
    AlertComponent = __decorate([
        core_1.Component({
            selector: 'alert',
            template: '<div class="alert" [ngClass]="{hidden: visible, type: type}" role="alert">{{message}}</div>',
            directives: [common_1.NgClass]
        }), 
        __metadata('design:paramtypes', [])
    ], AlertComponent);
    return AlertComponent;
}());
exports.AlertComponent = AlertComponent;
var Type = (function () {
    function Type() {
    }
    Type.SUCCESS = 'alert-success';
    Type.INFO = 'alert-info';
    Type.WARNING = 'alert-warning';
    Type.DANGER = 'alert-danger';
    return Type;
}());
exports.Type = Type;
//# sourceMappingURL=alert.component.js.map