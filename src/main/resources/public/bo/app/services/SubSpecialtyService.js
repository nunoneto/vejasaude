"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("SubSpecialtyService",['$http','$q',function($http,$q){

    var path= "/api/v1/subSpecialty";

    var findBySpeciality = function(specialtyId){
        
        return $q(function(resolve, reject){
            $http({
                url: path,
                method: 'GET',
                headers: {
                    'Content-Type': "application/json"
                },
                params: {
                    specialtyId: specialtyId
                }
            }).then(function successCallback(response) {
                resolve(response.data.content);
            }, function errorCallback(response) {
                reject(response);

            });

        });
    }
    
    return {
        findBySpeciality: findBySpeciality,
    };

  }]);
}());
