"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("CurriculumService",['$http','$q',function($http,$q){

    var path= "/api/v1/curriculum",
        doctors = null;

    var create = function(text){

        return $q(function(resolve, reject){

            $http({
                url: path,
                method: 'POST',
                headers: {
                'Content-Type': "application/json"
                },
                data: JSON.stringify({description: text})
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    var update = function(text, id){

        return $q(function(resolve, reject){

            $http({
                url: path+"/"+id,
                method: 'POST',
                headers: {
                'Content-Type': "application/json"
                },
                data: JSON.stringify({description: text})
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    
    return {
        create: create,
        update: update  
    };

  }]);
}());
