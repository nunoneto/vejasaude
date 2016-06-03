"use strict";
var Alert = (function () {
    function Alert() {
        this.visible = "hidden";
    }
    Alert.prototype.setVisible = function (visible) {
        this.visible = visible ? "" : "hidden";
    };
    return Alert;
}());
exports.Alert = Alert;
//# sourceMappingURL=alert.model.js.map