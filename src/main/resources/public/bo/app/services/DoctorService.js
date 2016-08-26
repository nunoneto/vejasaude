"use strict";

(function(){
  var app = angular.module("vejaSaudeBo");
  app.factory("DoctorService",['$http','$q',function($http,$q){

    var path= "/api/v1/doctor",
        doctors = null;

    var _findDoctor= function(id){
        var _doctor = null;
        doctors.each(function(doctor){
            if(doctor.id === id){
                _doctor = doctor;
            }
        });
        return _doctor;
    }



    var api = {
         getAll: function(){

            return $q(function(resolve, reject){
                
                if(doctors != null){
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
        },
    
        find: function(id){
            
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

    };
    
    return api;

  }]);
}());
