(function(){
    var CreateEditUserController = angular
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

            $scope.cancel = function() {
                $location.path("home/users");
            }          

            //Criar / Atualizar utilizador
            $scope.saveOrUpdate = function() {

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

                if ($scope.mode == 'update') {
                    UserService.update(update,$scope.user.username).then(
                        function(data){
                            dialogs.notify("Utilizador","Utilizador atualizado com sucesso").result.then(goToUsers,goToUsers);
                        }, function(err){
                            var msg = err.data.statusMessage ? err.data.statusMessage : "Não foi possível atualizar o utilizador. Tente mais tarde";
                            dialogs.error("Utilizador",msg);
                    });
                } else {
                    UserService.create(create).then(
                        function(data){
                            if(data.statusCode == 0){
                                dialogs.notify("Utilizador","Utilizador criado com sucesso").result.then(goToUsers,goToUsers)
                                return;
                            }
                            else{
                                dialogs.error("Utilizador", data.statusMessage ? data.statusMessage : "Erro a criar o utilizador. Tente mais tarde.").result.then(goToUsers,goToUsers) 
                            }
                            
                        }, function(err){
                            var msg = err.data.statusMessage ? err.data.statusMessage : "Não foi possível criar o utilizador. Tente mais tarde";
                            dialogs.error("Utilizador",msg);
                        })
                }              
            }           
    }]);
}())