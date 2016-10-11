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

        var createUser = function(email, name, username, password){
            return $q(function(resolve, reject){

                $http({
                    url: path,
                    method: 'POST',
                    headers: {
                        'Content-Type' : 'application/json'
                    },
                    data: {
                        email: email,
                        prettyname: name,
                        username: username,
                        password: password
                    }
                }).then(function successCallback(response){
                    resolve(response.data);
                }, function errorCallback(response){
                    reject(response);
                });
            });
        };

        var updateUser = function(email, name, username, password){
            return $q(function(resolve, reject){

                $http({
                    url: path + '/' + username,
                    method: 'POST',
                    headers: {
                        'Content-Type' : 'application/json'
                    },
                    data: {
                        email: email,
                        prettyname: name,
                        username: username,
                        password: password
                    }
                }).then(function successCallback(response){
                    resolve(response.data);
                }, function errorCallback(response){
                    reject(response);
                });
            });
        };     

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
        };

        return {
            getAll: getAll,
            find:   find,
            create: createUser,
            update: updateUser,
            delete: deleteUser
        };  
    }]);
;}());