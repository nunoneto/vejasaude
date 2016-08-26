"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("SessionService",['$http','$q',function($http,$q){

    var path= "/api/v1/session",
        session;

    var api = {
        create : function(username, password){

            return $q(function(resolve, reject){
            
                if(session){
                    resolve(session);
                    return;
                }
                    
                $http({
                    url: path,
                    method: 'POST',
                    data: JSON.stringify({
                        username: username,
                        password: password 
                    }),
                    headers: {
                    'Content-Type': "application/json"
                    }
                }).then(function successCallback(response) {
                    session = response.data.content || null;
                    session ? resolve(session) : reject(response.data);
                }, function errorCallback(response) {
                    reject(response);
                });

            });
        },
        get: function(){

            return $q(function(resolve, reject){
            
                if(session){
                    resolve(session);
                    return;
                }

                $http({
                    method: 'GET',
                    url: path
                }).then(function successCallback(response) {
                    session = response.data.content;
                    session ? resolve(session) : reject(response.data);
                }, function errorCallback(response) {
                    reject(response);
                });

            });

        }
    };
    
    return api;

  }]);
}());
