(function(){
    angular
    .module("vejaSaudeBo")
    .controller('DoctorController', ['$scope','$location','DoctorService', 
        function($scope,$location,DoctorService) {
            
            DoctorService.getAll().then(
                function(data){
                    $scope.doctors = data;
                },
                function(err){
                    //TODO show warning
                }
            );

            $scope.editDoctor = function(doctor){
                location.path('home/doctors/'+doctor.id);
            }

            $scope.goToCurriculum = function(doctor){
                location.path('home/curriculum/'+doctor.curriculum.id);
            }

        
    }]);
}())
