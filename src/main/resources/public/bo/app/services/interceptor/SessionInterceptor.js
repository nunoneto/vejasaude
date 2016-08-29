"use strict";

(function(){
    
    angular.module('vejaSaudeBo').factory('SessionInterceptor', ['$q','$location', '$injector',
         function($q, $location, $injector) {  

            var requestInterceptor = {
                request: function(config) {
                    return $q(function(resolve, reject){
                        
                        if (config.url.indexOf('/api/v1/session') == 0) {
                            resolve(config);
                            return;
                        }

                        var SessionService = $injector.get('SessionService');
                        SessionService.get().then(
                            function(session) {
                                resolve(config);
                            }, function(err) {
                                $location.path("/login");
                                resolve(config);
                            }
                        );
                    });
                }
        };

            return requestInterceptor;
        }]);


}());