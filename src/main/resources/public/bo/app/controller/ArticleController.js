(function(){
    angular
    .module("vejaSaudeBo")
    .controller('ArticleController', ['$scope','$location','ArticleService','dialogs','ngToast', 
        function($scope,$location,ArticleService,dialogs,ngToast) {
            
            function loadArticles() {
                ArticleService.getAll().then(
                    function(result){
                        $scope.articles = result.content;
                    },function(err){
                        ngToast.create({
                            className: 'danger',
                            content: 'Não foi possível carregar os artigos. Tente novamente mais tarde',
                        });
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
                                ngToast.create({
                                    className: 'danger',
                                    content: 'Não foi possível apagar o artigo. Tente novamente mais tarde',
                                });
                            }
                        );
                    },function(){
                        //do nothing
                    }
                );
            }

    }]);
}())
