(function(){
    angular
    .module("vejaSaudeBo")
    .controller('MainController', ['$scope','SessionService','$location','$routeParams',
        function($scope,SessionService,$location,$routeParams) {
        
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
