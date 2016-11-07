"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("ArticleService",['$http','$q',function($http,$q){

    var path= "/api/v1/article",
        articles;

    var getAll = function() {
        return $q(function(resolve, reject){
            
            if (articles) {
                resolve(articles);
                return;
            }

            $http({
                url: path,
                method: 'GET',
                headers: {
                    'Content-Type': "application/json"
                }
            }).then(function successCallback(response) {
                articles = response.data;
                resolve(articles);
            }, function errorCallback(response) {
                reject(response);

            });
        });
    };

    var find = function(articleId) {
        return $q(function(resolve, reject){
            
            $http({
                url: path+"/"+articleId,
                method: 'GET',
                headers: {
                    'Content-Type': "application/json"
                }
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);

            });
        });
    };

    var create = function(article){

        return $q(function(resolve, reject){

            $http({
                url: path,
                method: 'POST',
                headers: {
                'Content-Type': "application/json"
                },
                data: JSON.stringify(article)
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    var update = function(article, id){

        return $q(function(resolve, reject){

            $http({
                url: path+"/"+id,
                method: 'POST',
                headers: {
                'Content-Type': "application/json"
                },
                data: JSON.stringify(article)
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    var remove = function(id){

        return $q(function(resolve, reject){

            $http({
                url: path+"/"+id,
                method: 'DELETE',
                headers: {
                'Content-Type': "application/json"
                },
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    
    return {
        find: find,
        getAll: getAll,
        create: create,
        update: update,
        remove: remove  
    };

  }]);
}());
