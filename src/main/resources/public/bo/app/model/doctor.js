"use strict";
var Doctor = (function () {
    //JSON constructor
    function Doctor(jsonData) {
        this.name = jsonData.name;
        this.speciality = jsonData.speciality;
        this.curriculum = jsonData.curriculum;
        this.id = jsonData.id;
    }
    return Doctor;
}());
exports.Doctor = Doctor;
//# sourceMappingURL=doctor.js.map