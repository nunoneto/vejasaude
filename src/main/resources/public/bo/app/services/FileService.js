"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("FileService",['$http','$q',function($http,$q){

    var path= "/api/v1/attachment";


    var upload = function (files){

        return $q(function(resolve, reject){
           
            var formData = new FormData();
            formData.append("files", files);

            $http.post(
                path, 
                formData, 
                {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                }
                ).then(function (data) {
                    resolve(data);
            },function(err){
                resolve(err);
            });

        });
    }

    return {
        upload: upload
    }


  }]);
}());
