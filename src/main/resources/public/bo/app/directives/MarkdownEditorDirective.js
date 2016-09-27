(function(){
    
    angular.module('vejaSaudeBo').directive('markdownEditor', function () {
        return {
            restrict: 'A',
            templateUrl: 'views/directives/markdown.html',
            link: function ($scope, element, attrs) { 
                var simplemde = new SimpleMDE(
                    { 
                        element: element[0],
                        hideIcons: ["link", "image","side-by-side","fullscreen"],
                    });

                attrs.$observe('value', function(value){
                    console.log(value);
                });

                $scope.setValue = function(value) {
                    simplemde.value(value);
                }
            } 
        };
    });

}())