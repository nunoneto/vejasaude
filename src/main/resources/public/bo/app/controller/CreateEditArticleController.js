(function(){
    var CreateEditArticleController = angular
    .module("vejaSaudeBo")
    .controller('CreateEditArticleController', 
        ['$scope','$location','DoctorService','SpecialityService','CurriculumService',
        '$routeParams','ngToast','dialogs','ArticleService','ArticleTypeService','SubSpecialtyService', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,
        $routeParams,ngToast,dialogs,ArticleService,ArticleTypeService,SubSpecialtyService) {
            
            var originalArticle, 
                articleId = $routeParams.articleId;
            $scope.mode = $routeParams.mode;

            switch($scope.mode) {
                case 'new':
                    $scope.article = {};
                    break;
                case 'edit':
                    
                    break;
            }
            

            var goToArticles = function(){
                $location.path("home/articles");
            };

            $scope.onSpecialityChanged = function(){
                if ($scope.article.specialty && $scope.article.specialty.id) {
                    SubSpecialtyService.findBySpeciality($scope.article.specialty.id).then(
                        function(data){
                            $scope.subSpecialties = data;
                        },function(err){
                            //TODO handle err
                        }
                    );
                } else {
                    $scope.subSpecialties = [];
                }

            }

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

            var loadDoctors = function(){
                DoctorService.getAll().then(
                    function(data){
                        $scope.doctors = data;
                    },function(err){
                        //TODO error message
                    }
                );
            }

            var loadSpecialities = function(){
                SpecialityService.getAll().then(
                    function(data){
                        $scope.specialties = data;
                    },function(err){
                        //TODO error message
                    }
                );
            }

            return {
                resolve : {
                    article:        loadArticle(),
                    articleTypes:   loadArticleTypes(),
                    doctors:        loadDoctors(),
                    specialities:   loadSpecialities()
                }
            }
    }]);

}())
