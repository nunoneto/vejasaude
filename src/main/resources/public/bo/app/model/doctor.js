"use strict";
var speciality_1 = require("./speciality");
var curriculum_1 = require("./curriculum");
var Doctor = (function () {
    //JSON constructor
    function Doctor(jsonData) {
        this.name = jsonData.name;
        this.specialty = new speciality_1.Speciality(jsonData.specialty);
        this.curriculum = new curriculum_1.Curriculum(jsonData.curriculum);
        this.id = jsonData.id;
    }
    return Doctor;
}());
exports.Doctor = Doctor;
//# sourceMappingURL=doctor.js.map