(function(){
    angular
    .module("vejaSaudeBo")
    .controller('CreateEditUserController', ['$scope','$location','UserService', '$routeParams', 'ngToast', 'dialogs',
        function($scope, $location, UserService, $routeParams, ngToast, dialogs) {

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

            var goToUsers = function(){
                $location.path("home/users");
            };

            //Criar / Atualizar utilizador
             $scope.saveUpdate = function() {

                 var update = {
                     email: $scope.user.email,
                     prettyName: $scope.user.prettyName,
                     password: $scope.user.password
                 }

                 var create = {
                     email: $scope.user.email,
                     prettyName: $scope.user.prettyName,
                     username: $scope.user.username,
                     password: $scope.user.password
                 }                                

                var saveUpdateUser = function(){
                    if ($scope.user.username) {
                        UserService.update(update,$scope.user.username).then(
                        function(data){
                            dialogs.notify("Utilizador","Utilizador atualizado com sucesso").result.then(goToUsers,goToUsers);
                        },function(err){
                            var msg = err.data.statusMessage ? err.data.statusMessage : "Não foi possível atualizar o utilizador. Tente mais tarde";
                            dialogs.error("Utilizador",msg);
                        });
                    } else {
                        //TODO: Create
                    }
                }                  
            }           
    }]);
}())