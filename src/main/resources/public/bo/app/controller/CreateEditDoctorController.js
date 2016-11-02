(function(){
    var CreateEditDoctorController = angular
    .module("vejaSaudeBo")
    .controller('CreateEditDoctorController', ['$scope','$location','DoctorService','SpecialityService','CurriculumService','$routeParams','ngToast','dialogs', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,$routeParams,ngToast,dialogs) {
            
            var doctorId = $routeParams.id;
            $scope.mode = $routeParams.mode;
            var originalDoctor;

            var goToDoctors = function(){
                $location.path("home/doctors");
            };

            $scope.isNewSpecialityOption = function() {
             
                if($scope.doctor.speciality && $scope.doctor.speciality.id == -1) {
                    var dlg = dialogs.create('/views/dialogs/create-speciality.html','CreateSpecialityDialogController',{},'lg');
                    dlg.result.then(
                        function(newSpeciality){
                            loadSpecialities(function(){
                                $scope.doctor.speciality = $scope.specialities[$scope.specialities.length-1]; 
                            });
                        },
                        function(){
                            $scope.doctor.speciality = originalDoctor ? originalDoctor.speciality : null; 

                    });
                }
            }

            $scope.saveOrUpdate = function() {

                var update = {
                    name: $scope.doctor.name, 
                    idSpecialty: $scope.doctor.speciality.id,
                    idCurriculum: $scope.doctor.curriculum ? $scope.doctor.curriculum.id : null
                }

                var create = {
                    name: $scope.doctor.name,
                    idSpecialty: $scope.doctor.speciality.id,
                }


                var saveUpdateDoctor = function(){
                    if ($scope.doctor.id) {
                        DoctorService.update(update,$scope.doctor.id).then(
                        function(data){
                            dialogs.notify("Médico","Médico atualizado com sucesso").result.then(goToDoctors,goToDoctors);
                        },function(err){
                            var msg = err.data.statusMessage ? err.data.statusMessage : "Não foi possível atualizar o médico. Tente mais tarde";
                            dialogs.error("Médico",msg);
                        });
                    } else {
                        DoctorService.create(create).then(
                            function(data){
                            dialogs.notify("Médico","Médico criado com sucesso").result.then(goToDoctors,goToDoctors)
                            },function(err){
                                var msg = err.data.statusMessage ? err.data.statusMessage : "Não foi possível criar o médico. Tente mais tarde";
                                dialogs.error("Médico",msg);
                            });
                    }
                    
                }

                if ($scope.doctor.curriculum == null) {
                    // create curriculum
                    CurriculumService.create($scope.textEditor())
                        .then(function(data){
                            update.idCurriculum = data.content.id;
                            create.idCurriculum = data.content.id;
                            saveUpdateDoctor();
                        },function(err){
                            ngToast.create({
                                className: 'danger',
                                content: 'Não foi possível guardar o currículo. Tente mais tarde',
                            });
                        });
                }else{
                    // update curriculum
                    CurriculumService.update($scope.textEditor(),$scope.doctor.curriculum.id)
                        .then(function(){
                            saveUpdateDoctor();
                        },function(err){
                            ngToast.create({
                                className: 'danger',
                                content: 'Não foi possível atualizar o currículo. Tente mais tarde',
                            });
                        });
                }
                
            }

            // Resolvers
            var loadSpecialities = function(successCallback){
                SpecialityService.getAll().then(
                    function(specialities){
                        var selectSpecialities = specialities.slice(0);
                        $scope.specialities =  selectSpecialities;
                        $scope.specialities.splice(0,0,{id:-1,specialty:"--Nova Especialidade--"})
                        if (successCallback != null && typeof successCallback === "function") {
                            successCallback();
                        }
                    },
                    function(err){
                        ngToast.create({
                            className: 'danger',
                            content: 'Não foi possível carregar as especialidades. Tente mais tarde',
                        });
                        //TODO disable do submit
                    }
                );

            }
            var loadDoctor = function(){
                DoctorService.find(doctorId).then(
                    function(doctor){
                        originalDoctor = angular.copy(doctor,originalDoctor);
                        $scope.doctor = doctor;
                    },
                    function(err){
                        ngToast.create({
                            className: 'danger',
                            content: 'Médico não encontrado',
                        });
                    }
                );
            }

            return {
                resolve : {
                    specialities : loadSpecialities(),
                    doctor: loadDoctor() 
                    
                }
            }
    }]);

    angular
        .module("vejaSaudeBo")
        .controller('CreateSpecialityDialogController', ['$scope','$uibModalInstance','SpecialityService',
            function($scope,$uibModalInstance,SpecialityService) {

                $scope.save = function() {

                    SpecialityService.create($scope.name).then(
                        function(data){
                            $uibModalInstance.close(data);
                        },function(err){
                            $uibModalInstance.dismiss();
                        }
                    );
                }
                
                $scope.cancel = function() {
                    $uibModalInstance.dismiss();
                }

        }]);


}())
