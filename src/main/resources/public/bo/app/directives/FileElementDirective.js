(function(){
    
    angular.module('vejaSaudeBo').directive('fileElement', [function () {
        return {
            restrict: 'A',
            scope: {
                bind: '='
            },

            template: '<input type="file" name="file" class="form-control" required accept=".jpg,.jpeg,.png,.gif" />',
            link: function ($scope, element, attrs) { 

                $scope.bind = function() {
                        return element[0].children[0].files;
                }
                
            } 
        };
    }]);

}())