(function(){
    angular
    .module("vejaSaudeBo")
    .controller('DoctorController', ['$scope','$location','DoctorService','dialogs','ngToast', 
        function($scope,$location,DoctorService,dialogs,ngToast) {            
            var updateUI = function(){
                DoctorService.getAll().then(
                    function(data){
                        $scope.doctors = data;
                    },
                    function(err){
                        ngToast.create({
                            className: 'danger',
                            content: 'Não foi possível carregar os médicos. Tente novamente mais tarde',
                        });
                    }
                );
            }
            updateUI();

            $scope.editDoctor = function(doctor){
                $location.path('/doctor/edit/'+doctor.id);
            }

            $scope.newDoctor = function(doctor){
                $location.path('/doctor/new');
            }

            $scope.deleteDoctor = function(doctor){
                var diagPromise = dialogs.confirm("Apagar Médico", "Tem a certeza que quer apagar o médico '"+doctor.name+"'?", null);
                diagPromise.result.then(function(btn){
                    DoctorService.remove(doctor.id).then(
                        function(data){
                             updateUI();
                        },function(err){
                            
                        }
                    );
                   
                },function(btn){
                    // do nothing
                });    
            }

    }]);
}())
