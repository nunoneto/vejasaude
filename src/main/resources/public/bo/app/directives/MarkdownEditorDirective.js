(function(){
    
    angular.module('vejaSaudeBo').directive('markdownEditor', ['dialogs','FileService',function (dialogs,FileService) {
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
                        toolbar: [
                            "bold", "italic", "heading", 'horizontal-rule','table','ordered-list','unordered-list',"quote",'link','preview',
                            {
                                name: "insertImage",
                                action: addImage,
                                className: "fa fa-bold fa-picture-o",
                                title: "Inserir Imagem",
                            }
                        ]
                    });

                attrs.$observe('value', function(value){
                    console.log(value);
                    simplemde.value(value);
                });

                $scope.getValue = function(value) {
                    return simplemde.value();
                }

                function addImage() {
                    var dlg = dialogs.create('/views/dialogs/add-image.html','AddImageDialogController',{},'lg');
                    dlg.result.then(
                        function(files){
                            if (files) {
                                FileService.upload(files.item(0)).then(
                                    function(resp){
                                        if (resp.data.content && resp.data.content.attachmentList && resp.data.content.attachmentList[0]) {
                                            var url = document.location.origin + "/api/v1/attachment/"+resp.data.content.attachmentList[0].id;
                                            url = '![alt text]('+url+' "")';

                                            simplemde.value(simplemde.value()+"\n"+url);
                                        }
                                    },
                                    function(err){

                                    }
                                );
                            }else{
                                //TODO error message
                            }
                        },
                        function(){

                    });

                }


                
            } 
        };
    }]);

    angular
        .module("vejaSaudeBo")
        .controller('AddImageDialogController', ['$scope','$uibModalInstance',
            function($scope,$uibModalInstance) {

                $scope.save = function() {
                    $uibModalInstance.close($scope.files());
                }
                
                $scope.cancel = function() {
                    $uibModalInstance.dismiss();
                }

        }]);


}())