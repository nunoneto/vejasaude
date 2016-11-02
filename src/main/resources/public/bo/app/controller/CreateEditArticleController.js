(function(){
    var CreateEditArticleController = angular
    .module("vejaSaudeBo")
    .controller('CreateEditArticleController', 
        ['$scope','$location','DoctorService','SpecialityService','CurriculumService','$routeParams','ngToast','dialogs','ArticleService','ArticleTypeService', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,$routeParams,ngToast,dialogs,ArticleService,ArticleTypeService) {
            
            var articleId = $routeParams.articleId;
            $scope.mode = $routeParams.mode;
            
            var originalArticle;

            var goToArticles = function(){
                $location.path("home/articles");
            };


            $scope.saveOrUpdate = function() {

            }

            // Resolvers
            var loadArticle = function(){
                if (articleId) {
                    ArticleService.find(articleId).then(
                        function(article){
                            originalArticle = angular.copy(article,originalArticle);
                            $scope.article = article;
                        },
                        function(err){
                            ngToast.create({
                                className: 'danger',
                                content: 'Artigo não encontrado',
                            });
                        }
                    );
                }
            }

            var loadArticleTypes = function(){
                ArticleTypeService.getAll().then(
                    function(data){
                        $scope.articleTypes = data;
                    },
                    function(err){
                        //TODO error message
                    }
                );
            }

            return {
                resolve : {
                    article:        loadArticle(),
                    articleTypes:   loadArticleTypes()
                }
            }
    }]);

}())
