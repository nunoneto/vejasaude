(function(){
    angular
    .module("vejaSaudeBo")
    .controller('UserController', ['$scope', '$location', 'UserService', 'dialogs',
        function($scope, $location, UserService, dialogs){
        
            //Get all users
            var updateList = function(){
                UserService.getAll().then(
                    function(data){
                        $scope.users = data;
                    },
                    function(err){
                        //show warning
                    }
                );
            }
            updateList();

            //Create User
            $scope.createUser = function(user){
                $location.path('/user/create');
            }

            //Edit User
            $scope.editUser = function(user){
                $location.path('/user/edit/'+user.username);
            }

            //Eliminar User
            $scope.deleteUser = function(user){
                //$location.path('/user/'+user.username+'/delete')
                var confirmDialog = dialogs.confirm("Apagar Utilizador", "Tem a certeza que quer apagar o utilizador '" + user.prettyName + "'?", null);
                confirmDialog.result.then(function(btn){
                    UserService.delete(user.username);
                    updateList();
                });
            }
            
        }]);
}())