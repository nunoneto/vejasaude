(function(){
    var CreateEditDoctorController = angular
    .module("vejaSaudeBo")
    .controller('CreateEditDoctorController', ['$scope','$location','DoctorService','SpecialityService','CurriculumService','$routeParams','ngToast', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,$routeParams,ngToast) {
            
            var doctorId = $routeParams.id;
            $scope.mode = $routeParams.mode;

            $scope.submitDoctor = function(){
                //TODO submit or update doctor
            }

            $scope.evalSpecialty = function() {
                console.log($scope.selectedSpecialty);
            }

            $scope.save = function() {

                var update = {
                    name: $scope.doctor.name, 
                    idSpecialty: $scope.selectedSpeciality,
                    idCurriculum: $scope.doctor.curriculum ? $scope.doctor.curriculum.id : null
                }

                var updateDoctor = function(){
                    DoctorService.update(update,$scope.doctor.id).then(
                        function(data){
                            console.log(data);
                        },function(err){

                        });
                }

                if ($scope.doctor.curriculum == null) {
                    // create curriculum
                    CurriculumService.create($scope.textEditor())
                        .then(function(data){
                            update.idCurriculum = data.content.id;
                            updateDoctor();
                        },function(err){

                        });
                }else{
                    // update curriculum
                    CurriculumService.update($scope.textEditor(),$scope.doctor.curriculum.id)
                        .then(function(){
                            updateDoctor();
                        },function(err){
                            
                        });
                }
                
            }


            return {
                resolve : {
                    specialities : SpecialityService.getAll().then(
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
                        ),
                    doctor: DoctorService.find(doctorId).then(
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
                        )
                }
            }
    }]);


}())
