"use strict";
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var http_1 = require('@angular/http');
var routes_1 = require('./routes');
var app_component_1 = require('./components/app.component');
var common_1 = require('@angular/common');
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [
    http_1.HTTP_PROVIDERS,
    routes_1.APP_ROUTER_PROVIDERS,
    { provide: common_1.LocationStrategy, useClass: common_1.HashLocationStrategy }
])
    .catch(function (err) { return console.error(err); });
//# sourceMappingURL=main.js.map