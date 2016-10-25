(function(){
    angular
    .module("vejaSaudeBo")
    .controller('CreateEditUserController', ['$scope','$location','UserService', '$routeParams',
        function($scope, $location, UserService, $routeParams) {

            var username   = $routeParams.username;
            $scope.mode    = $routeParams.mode;

            //Encontrar utilizador pelo username
            UserService.find(username).then(
                function(user){
                    $scope.user = user;
                },
                function(err){
                    console.log(err);
                    ngToast.create({
                        className: 'danger',
                        content: 'Utilizador não encontrado',
                    });
                }
            );

            //Criar utilizador
            $scope.createUser = function(){
                UserService.create($scope.user.email, $scope.user.name, $scope.user.username, $scope.user.password)
            }

            //Atualizar utilizador
            $scope.editUser = function(){
                if($scope.user.username == null){
                    //TODO error
                } else {
                    UserService.update($scope.user.email, $scope.user.name, $scope.user.username, $scope.user.password)
                }
            }

            console.log($scope);

            //Eliminar utilizador
            $scope.deleteUserOption = function() {             
                if($scope.user.username == null) {
                    //TODO error
                } else {
                    var dlg = dialogs.create('/views/dialogs/delete-user.html','CreateDeleteDialogController',{},'lg');
                    dlg.result.then(
                        function(){
                            console.log('Deleting user...');
                            UserService.delete($scope.user.username);
                    });
                }
                    var dlg = dialogs.create('/views/dialogs/create-speciality.html','CreateSpecialityDialogController',{},'lg');
                    dlg.result.then(
                        function(newSpeciality){
                            loadSpecialities(function(){
                                $scope.doctor.speciality = $scope.specialities[$scope.specialities.length-1]; 
                            });
                        },
                        function(){
                            
                    });
                }            
    }]);
}())