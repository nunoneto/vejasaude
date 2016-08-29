(function(){
    angular
    .module("vejaSaudeBo")
    .controller('LoginController', ['$scope','SessionService','$location','ngToast', 
        function($scope,SessionService,$location,ngToast) {

        var toasty;
        var rememberMeKey = 'CREDENTIALS';        

        $scope.doLogin = function(){

            if ($scope.username.trim() == "" || $scope.password.trim() == "") {
                toasty = ngToast.create({
                className: 'danger',
                content: 'Insira o username e password para prosseguir',
                });

                return;
            }

            SessionService.create($scope.username,$scope.password)
            .then(function(user){
               
                toggleSaveCredentials(
                    $scope.rememberMe ? 
                    {
                        username: $scope.username,
                        password: $scope.password
                    }:
                    null
                );

                $location.path('/home');
                toasty = ngToast.create({
                className: 'success',
                content: 'Login com sucesso!',
                });

            }, function(err){
                toasty = ngToast.create({
                className: 'danger',
                content: err.statusMessage || 'Não foi possível concluir a operação. Tente mais tarde',
                });
            });
        }
        
        $scope.eventHandler = function(event) {
            if(event.keyCode == 13){
            this.doLogin();
            }
        } 
        
        $scope.remember = function($event){
            var checkbox = $event.target;
            if (checkbox.checked) {

            } else {

            }
        }

        var toggleSaveCredentials = function(creds){
            if (creds) {
                window.localStorage.setItem(rememberMeKey,JSON.stringify(creds));
            }else{
                window.localStorage.removeItem(rememberMeKey);
            }
        }

        var getSavedCredentials = function(){
            try {
                return JSON.parse(window.localStorage.getItem(rememberMeKey));
            }catch(ex) {
                return null;
            }
        }

        // get rememeber me credentials
        var credentials = getSavedCredentials();
        if (credentials) {
            $scope.username = credentials.username;
            $scope.password = credentials.password;
            $scope.rememberMe = true;
        }
        
    }]);
}())
