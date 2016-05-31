"use strict";
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var http_1 = require('@angular/http');
var core_1 = require('@angular/core');
var common_1 = require('@angular/common');
// Components
var app_component_1 = require('./components/app.component');
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [
    http_1.HTTP_PROVIDERS,
    core_1.provide(common_1.LocationStrategy, { useClass: common_1.HashLocationStrategy })
]);
//# sourceMappingURL=main.js.map