"use strict";
var http_1 = require('@angular/http');
var BasicHttpService = (function () {
    function BasicHttpService() {
        this.relativeUrl = "/api/v1";
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
    }
    return BasicHttpService;
}());
exports.BasicHttpService = BasicHttpService;
//# sourceMappingURL=basichttp.service.js.map