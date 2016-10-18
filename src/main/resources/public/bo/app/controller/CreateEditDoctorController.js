(function(){
    var CreateEditDoctorController = angular
    .module("vejaSaudeBo")
    .controller('CreateEditDoctorController', ['$scope','$location','DoctorService','SpecialityService','CurriculumService','$routeParams','ngToast','dialogs', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,$routeParams,ngToast,dialogs) {
            
            var doctorId = $routeParams.id;
            $scope.mode = $routeParams.mode;

            var goToDoctors = function(){
                $location.path("home/doctors");
            };

            $scope.isNewSpecialityOption = function() {
             
                if($scope.doctor.speciality.id == -1) {
                    var dlg = dialogs.create('/views/dialogs/create-speciality.html','CreateSpecialityDialogController',{},'lg');
                    dlg.result.then(function(name){
                        console.log(name);
                        $scope.name = name;
                    },function(){

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


            return {
                resolve : {
                    specialities : SpecialityService.getAll().then(
                            function(specialities){
                                $scope.specialities =  specialities;
                                $scope.specialities.splice(0,0,{id:-1,specialty:"--Nova Especialidade--"})
                            },
                            function(err){
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
                                    ngToast.create({
                                        className: 'danger',
                                        content: 'Médico não encontrado',
                                    });
                                }
                            )  
                    
                }
            }
    }]);

    angular
        .module("vejaSaudeBo")
        .controller('CreateSpecialityDialogController', ['$scope','$uibModalInstance',
            function($scope,$uibModalInstance) {

                $scope.save = function() {
                    $uibModalInstance.dismiss();
                }
                
                $scope.cancel = function() {
                    $uibModalInstance.close(null);
                }

        }]);


}())
