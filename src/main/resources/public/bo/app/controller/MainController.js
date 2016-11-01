(function(){
    angular
    .module("vejaSaudeBo")
    .controller('MainController', ['$scope','SessionService','$location','$routeParams','dialogs','ngToast',
        function($scope,SessionService,$location,$routeParams,dialogs,ngToast) {
        
        var menu = $routeParams.menu;
        //validate session and redirect
        SessionService.get()
            .then(function(session){
                if(session && $location.path().indexOf("/home") < 0)
                    $location.path('/home');
                else if (!session)
                    $location.path('/login');
            },
            function(reason){
                $location.path('/login');
            });

        $scope.changePassword = function(){
            var dlg = dialogs.create('/views/dialogs/change-password.html','ChangePasswordDialogController',{},'lg');
            dlg.result.then(
                function(data){
                    dialogs.notify("Password","Password alterada com sucesso");
                },
                function(){

            });

        }

        $scope.logout = function() {
            SessionService.terminate().then(
                function(data){
                    location.reload();
                },function(err){
                    //TODO err
                }
            );
        }

    }]);

        angular
        .module("vejaSaudeBo")
        .controller('ChangePasswordDialogController', ['$scope','$uibModalInstance','SessionService','ngToast',
            function($scope,$uibModalInstance,SessionService,ngToast) {

                $scope.save = function() {

                    if ($scope.newPassword && $scope.currentPassword && $scope.confirmPassword
                        && $scope.newPassword === $scope.confirmPassword) {

                            SessionService
                                .changePassword($scope.currentPassword, $scope.newPassword)
                                .then(
                                    function(data){
                                        
                                        if (data.statusCode != 0) {
                                            $scope.errorMessage = data.statusMessage;
                                            return;
                                        }

                                        $uibModalInstance.close();
                                    },function(err){
                                        $scope.errorMessage = "Erro ao alterar a password! Tente novamente mais tarde";
                                    }
                                );


                    } else if ($scope.newPassword != $scope.confirmPassword) {
                            $scope.errorMessage = "As novas passwords não coincidem!";
                    }

                }
                
                $scope.cancel = function() {
                    $uibModalInstance.dismiss();
                }

        }]);

}())
