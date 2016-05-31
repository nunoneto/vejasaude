"use strict";
var User = (function () {
    //JSON constructor
    function User(jsonData) {
        this.username = jsonData.username;
        this.email = jsonData.email;
        this.sessionID = jsonData.sessionID;
        this.prettyName = jsonData.prettyName;
    }
    return User;
}());
exports.User = User;
//# sourceMappingURL=user.js.map