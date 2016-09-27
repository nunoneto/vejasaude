(function(){
    angular
    .module("vejaSaudeBo")
    .controller('CreateEditDoctorController', ['$scope','$location','DoctorService','SpecialityService','CurriculumService','$routeParams', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,$routeParams) {
            
            var doctorId = $routeParams.id;
            $scope.mode = $routeParams.mode;

            SpecialityService.getAll().then(
                function(specialities){
                   $scope.specialities =  specialities;
                },
                function(err){
                    console.log(err);
                    ngToast.create({
                        className: 'danger',
                        content: 'Não foi possível carregar as especialidades. Tente mais tarde',
                    });
                    //TODO disable do submit
                }
            );


            DoctorService.find(doctorId).then(
                function(doctor){
                    $scope.doctor = doctor;
                },
                function(err){
                    console.log(err);
                    ngToast.create({
                        className: 'danger',
                        content: 'Médico não encontrado',
                    });
                }
            );

            $scope.submitDoctor = function(){
                //TODO submit or update doctor
            }

            $scope.evalSpecialty = function() {
                console.log($scope.selectedSpecialty);
            }

            $scope.save = function() {
                //TODO save curriculum
                if ($scope.doctor.id == null) {
                    // create curriculum
                }else{
                    // update curriculum
                    CurriculumService.update($scope.curriculumText,$scope.doctor.id);
                }
                
                var update = {
                    name: $scope.doctor.name, 
                    idSpecialty: $scope.selectedSpeciality,
                    idCurriculum: $scope.doctor.curriculum.id
                }

                console.log(update);


            }
            
    }]);
}())
