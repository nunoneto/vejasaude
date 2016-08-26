(function(){
    angular
    .module("vejaSaudeBo")
    .controller('LoginController', ['$scope','SessionService','$location', function($scope,SessionService,$location) {

        console.log("login!")

        $scope.doLogin = function(){
            //this.alert.setVisible(false);
            SessionService.create($scope.username,$scope.password)
            .then(function(user){
                console.log(user);
                $location.path('/home');
            }, function(){
                //console.log(err);
                //this.alert.type = Type.DANGER;
                //this.alert.message = err;
                //this.alert.setVisible(true);
            });
        }
        
        $scope.eventHandler= function(event) {
            if(event.keyCode == 13){
            this.doLogin();
            }
        } 
        
        $scope.remember = function(event){
            console.log(event.target.checked);
        }

        
    }]);
}())
