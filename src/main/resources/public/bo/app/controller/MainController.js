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


    }]);
}())
