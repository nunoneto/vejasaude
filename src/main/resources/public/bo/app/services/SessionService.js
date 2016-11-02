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
        },
        terminate: function(){
            
            return $q(function(resolve, reject){
                $http({
                    method: 'DELETE',
                    url: path
                }).then(
                    function(response){
                        resolve(response.data.content);
                    },function(err){
                        reject(err);
                    }
                );
            });
        },
        changePassword: function(oldPassword, newPassword){
            return $q(function(resolve, reject){
                
                var data = JSON.stringify({
                    newPassword: newPassword,
                    currentPassword: oldPassword
                });

                $http({
                    method: 'POST',
                    url: path+"/password",
                    data: data
                }).then(
                    function(response){
                        resolve(response.data);
                    },function(err){
                        reject(err);
                    }
                );
            });
        }


    };
    
    return api;

  }]);
}());
