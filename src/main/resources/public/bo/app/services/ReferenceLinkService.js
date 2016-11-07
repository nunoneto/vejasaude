"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("ReferenceLinkService",['$http','$q',function($http,$q){

    var path= "/api/v1/referenceLink";


    var create = function(data){
       
        return $q(function(resolve, reject){
             $http({
                url: path,
                method: 'POST',
                headers: {
                'Content-Type': "application/json"
                },
                data: data
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    }
    
    return {
        create: create
    };

  }]);
}());
