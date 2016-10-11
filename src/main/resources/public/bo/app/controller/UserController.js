(function(){
    angular
    .module("vejaSaudeBo")
    .controller('UserController', ['$scope', '$location', 'UserService',
        function($scope, $location, UserService){
        
            //Get all users
            UserService.getAll().then(
                function(data){
                    $scope.users = data;
                },
                function(err){
                    //show warning
                }
            );

            //Create User
            $scope.createUser = function(user){
                $location.path('/user/create');
            }

            //Edit user
            $scope.editUser = function(user){
                $location.path('/user/'+user.username+'/edit');
            }

            //Delete User
            $scope.deleteUser = function(user){
                $location.path('/user/'+user.username+'/delete')
            }
            
        }]);
}())