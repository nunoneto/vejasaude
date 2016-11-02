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
                $location.path('/article/new');
            }
            $scope.editArticle = function(article){
                $location.path('/article/edit/'+article.id);
            }
            $scope.deleteArticle = function(article){
                var diagPromise = dialogs.confirm("Apagar Artigo", "Tem a certeza que quer apagar o artigo '"+article.title+"'?", null);
                diagPromise.result.then(
                    function(btn){
                        ArticleService.remove(article.id).then(
                            function(data){
                                loadArticles();   
                            },function(err){
                                //TODO error msg
                            }
                        );
                    },function(){
                        //do nothing
                    }
                );
            }

    }]);
}())
