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
var doctor_service_1 = require("../../services/doctor.service");
var ListDoctorsComponent = (function () {
    function ListDoctorsComponent(doctorService, router, route) {
        this.doctorService = doctorService;
        this.router = router;
        this.route = route;
    }
    ListDoctorsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.doctorService.getAll()
            .then(function (doctors) { return _this.doctors = doctors; })
            .catch(function (err) { return console.log(err); });
    };
    ListDoctorsComponent.prototype.editUser = function (doctor) {
        this.router.navigate(['/doctor', doctor.id]);
    };
    ListDoctorsComponent = __decorate([
        core_1.Component({
            templateUrl: 'views/menus/doctors/list-doctors.html',
            providers: [doctor_service_1.DoctorService],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [doctor_service_1.DoctorService, router_1.Router, router_1.ActivatedRoute])
    ], ListDoctorsComponent);
    return ListDoctorsComponent;
}());
exports.ListDoctorsComponent = ListDoctorsComponent;
//# sourceMappingURL=doctors.component.js.map