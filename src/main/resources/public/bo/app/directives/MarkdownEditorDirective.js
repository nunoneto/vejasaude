(function(){
    
    angular.module('vejaSaudeBo').directive('markdownEditor', function () {
        return {
            restrict: 'A',
            scope: {
                getValue: '='
            },

            templateUrl: 'views/directives/markdown.html',
            link: function ($scope, element, attrs) { 
                var simplemde = new SimpleMDE(
                    { 
                        element: element[0],
                        hideIcons: ["link", "image","side-by-side","fullscreen"],
                    });

                attrs.$observe('value', function(value){
                    console.log(value);
                    simplemde.value(value);
                });

                $scope.getValue = function(value) {
                        return simplemde.value();
                }

                
            } 
        };
    });

}())