"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("DoctorService",['$http','$q',function($http,$q){

    var path= "/api/v1/doctor",
        doctors = null;

    var _findDoctor= function(id){
        var _doctor = null;
        angular.forEach(doctors,function(doctor){
            if(doctor.id == id){
                _doctor = doctor;
            }
        });
        return _doctor;
    }



    var getAll = function(forceUpdate){

        return $q(function(resolve, reject){
            
            if(doctors != null && !forceUpdate){
                resolve(doctors);
                return;
            }

            $http({
                url: path,
                method: 'GET',
                headers: {
                'Content-Type': "application/json"
                }
            }).then(function successCallback(response) {
                doctors = response.data.content || null;
                doctors ? resolve(doctors) : reject(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    };

    var find = function(id){
        
        return $q(function(resolve, reject){
            if(doctors != null){
                var doctor = _findDoctor(id);
                doctor ? resolve(doctor) : reject("doctor not found");
                return;
            }
            
            getAll().then(
                function(data){
                    resolve(_findDoctor(id));
                },
                function(err){
                    reject(err);
                }
            );
        });
    }

    var update = function(update, doctorId){
       
        return $q(function(resolve, reject){
             $http({
                url: path+"/"+doctorId,
                method: 'POST',
                headers: {
                'Content-Type': "application/json"
                },
                data: update
            }).then(function successCallback(response) {
                resolve(response.data);
            }, function errorCallback(response) {
                reject(response);
            });
        });
    }



    
    return {
        getAll: getAll,
        find: find,
        update: update
    };

  }]);
}());
