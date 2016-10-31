(function(){
    angular
    .module("vejaSaudeBo")
    .controller('ArticleController', ['$scope','$location','ArticleService','dialogs', 
        function($scope,$location,ArticleService,dialogs) {
            
            function loadArticles() {
                ArticleService.getAll().then(
                    function(result){
                        $scope.articles = result.content.articles;
                    },function(err){
                        //TODO error     
                    });
            }

            loadArticles();

            $scope.createArticle = function(){
                
            }
            $scope.editArticle = function(article){
                
            }
            $scope.deleteArticle = function(article){
                
            }

    }]);
}())
