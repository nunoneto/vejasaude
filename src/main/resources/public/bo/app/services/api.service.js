"use strict";
var http_1 = require('@angular/http');
var status_1 = require('../model/status');
var APIService = (function () {
    function APIService() {
        this.relativeUrl = document.location.origin + "/api/v1";
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.options = { headers: this.headers };
    }
    APIService.prototype.status = function (resp) {
        return resp.json().statusCode == status_1.Status.OK;
    };
    return APIService;
}());
exports.APIService = APIService;
//# sourceMappingURL=api.service.js.map