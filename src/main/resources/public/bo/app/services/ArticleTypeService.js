"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("ArticleTypeService",['$http','$q',function($http,$q){

    var path= "/api/v1/articleType",
        articleTypes;

    var getAll = function() {
        return $q(function(resolve, reject){
            
            if (articleTypes) {
                resolve(articleTypes);
                return;
            }

            $http({
                url: path,
                method: 'GET',
                headers: {
                    'Content-Type': "application/json"
                }
            }).then(function successCallback(response) {
                articleTypes = response.data.content;
                resolve(articleTypes);
            }, function errorCallback(response) {
                reject(response);

            });
        });
    };

    
    return {
        getAll: getAll
    };

  }]);
}());
