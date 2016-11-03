(function(){
    angular
    .module("vejaSaudeBo")
    .controller('SpecialtyController',['$scope','$location','SpecialityService','dialogs',
    function($scope,$location,SpecialityService,dialogs){
        var updateUI = function(){
            SpecialityService.getAll().then(
                function(data){
                    $scope.specialities = data;
                },
                function(err){
                    //TODO show warning
                }
            );
        }
        updateUI();
    }]);

}())