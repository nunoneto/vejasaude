"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("SpecialityService",['$http','$q',function($http,$q){

    var path= "/api/v1/specialty",
        specialities = null;

    var _findSpeciality= function(id){
        var _speciality = null;
        angular.forEach(specialities,function(speciality){
            if(speciality.id == id){
                _speciality = doctor;
            }
        });
        return _speciality;
    }



    var getAll = function(forceUpdate){

        return $q(function(resolve, reject){
            
            if(specialities != null && !forceUpdate){
                resolve(specialities);
                return;
            }

            $http({
                url: path,
                method: 'GET',
                headers: {
                'Content-Type': "application/json"
                }
            }).then(function successCallback(response) {
                specialities = response.data.content || null;
                specialities ? resolve(specialities) : reject(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    var find = function(id){
        
            return $q(function(resolve, reject){
                if(specialities != null){
                    var speciality = _findSpeciality(id);
                    speciality ? resolve(speciality) : reject("speciality not found");
                    return;
                }
                
                getAll().then(
                    function(data){
                        resolve(_findSpeciality(id));
                    },
                    function(err){
                        reject(err);
                    }
                );
            });
    }

    
    return {
        getAll: getAll,
        find: find    
    };

  }]);
}());
