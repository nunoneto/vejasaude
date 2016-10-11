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

            //Eliminar utilizador
            $scope.deleteUser = function(){
                if($scope.user.username == null){
                    //TODO error
                } else {
                    UserService.delete($scope.user.id);
                }
            }
    }]);
}())