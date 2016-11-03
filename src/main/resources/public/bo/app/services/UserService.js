"use strict";

(function(){
    var app = angular.module("vejaSaudeBo");
    app.factory("UserService", ['$http', '$q', function($http, $q){

        var path = "/api/v1/bouser",
            users = null;

        var _findUser = function(username){
            var _user = null;
            angular.forEach(users, function(user){
                if(user.username == username){
                    _user = user;
                }
            });
            return _user;
        }

        var getAll = function(forceUpdate){
            return $q(function(resolve, reject){
                if(users != null && !forceUpdate){
                    resolve(users);
                    return;    
                }

                $http({
                    url:     path,
                    method:  'GET',
                    headers: {
                        'Content-Type' : "application/json"
                    }
                }).then(function successCallback(response){
                    users = response.data.content || null;
                    users ? resolve(users) : reject(response.data);
                }, function errorCallback(response) {
                    reject(response);
                });
            });
        };

        var find = function(username){
            return $q(function(resolve, reject){
                if(users != null){
                    var user = _findUser(username);
                    user ? resolve(user) : reject("error: user not found");
                    return;
                }

                getAll().then(
                    function(data){
                        resolve(_findUser(username));
                    },
                    function(err){
                        reject(err);
                    }
                );
            });
        };

        var create = function(data){
            return $q(function(resolve, reject){

                $http({
                    url: path,
                    method: 'POST',
                    headers: {
                        'Content-Type' : 'application/json'
                    },
                    data: data
                }).then(function successCallback(response){
                    _resetCache();
                    resolve(response.data);
                }, function errorCallback(response){
                    reject(response);
                });
            });
        };

        var update = function(update, username){

            console.log(update);
       
            return $q(function(resolve, reject){
                $http({
                    url: path + "/" + username,
                    method: 'POST',
                    headers: {
                        'Content-Type': "application/json"
                    },
                    data: update
                }).then(function successCallback(response) {
                    _resetCache();
                    resolve(response.data);
                }, function errorCallback(response) {
                    reject(response);
                });
          });
        }

        var deleteUser = function(username){
            return $q(function(resolve, reject){

                $http({
                    url: path + '/' + username,
                    method: 'DELETE',
                    headers:  {
                        'Content-Type' : 'application/json'
                    }
                }).then(function successCallback(response){
                    resolve(response);
                }, function errorCallback(response){
                    reject(response);
                });
            });
        }

        var _resetCache = function(){
            users = null;
        }

        return {
            getAll: getAll,
            find:   find,
            create: create,
            update: update,
            delete: deleteUser
        };  
    }]);

}());