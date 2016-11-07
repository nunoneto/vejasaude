(function(){
    var CreateEditArticleController = angular
    .module("vejaSaudeBo")
    .controller('CreateEditArticleController', 
        ['$scope','$location','DoctorService','SpecialityService','CurriculumService',
        '$routeParams','ngToast','dialogs','ArticleService','ArticleTypeService','SubSpecialtyService','ReferenceLinkService', 
        function($scope,$location,DoctorService,SpecialityService,CurriculumService,
        $routeParams,ngToast,dialogs,ArticleService,ArticleTypeService,SubSpecialtyService,ReferenceLinkService) {
            
            var originalArticle, 
                articleId = $routeParams.articleId;
            $scope.mode = $routeParams.mode;            

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

                if ($scope.article.id) {
                // update article


                } else {
                    // create article
                    var createRequest = {
                        typeArticle:        $scope.article.articleType.id,
                        title:              $scope.article.title,
                        description:        $scope.textEditor(),
                        specialty:          $scope.article.specialty.id,
                        doctor:             $scope.article.doctor.id,
                        referenceLinks:     $scope.article.referenceLinks,
                        listIdAttachments:  []
                    }

                    if ($scope.article.subSpecialty) {
                        createRequest.subSpecialty = $scope.article.subSpecialty.id;
                    }
                    
                    if ($scope.article.videoLink) 
                        createRequest.videoLink = $scope.article.videoLink;
                    
                    ArticleService.create(createRequest).then(
                        function(data) {
                            
                        },
                        function(err) {

                        }
                    );
                }
               
                
            }

            // Reference Links 

            $scope.deleteReferenceLink = function(referenceLink, index) {
                dialogs.confirm("Link de Referência","Deseja mesmo apagar o link '"+referenceLink.referenceLink+"'?")
                    .result
                    .then(function(){
                        $scope.article.referenceLinks.splice(index,1);
                    },null);
            }

            var addReferenceLink = function() {
                if ($scope.article && !$scope.article.referenceLinks) {
                    $scope.article.referenceLinks = [];
                }

                if ($scope.article.referenceLinks.length > 0) {
                    var areFieldsToFill = false; 
                    angular.forEach($scope.article.referenceLinks, function(value){
                        if ((!value.id && value.referenceLink === "") || (value.id && value.referenceLink === "")) {
                            areFieldsToFill = true;
                        }
                    });
                    if (areFieldsToFill && $scope.articleForm['reference'+new String($scope.article.referenceLinks.length-1)].$valid) {
                        dialogs.notify("Erro","Verifique se os links que já inseriu são válidos");
                        return;
                    }
                        
                }

                $scope.article.referenceLinks.push({referenceLink:""});
            }

            $scope.addReferenceLink = addReferenceLink;


            // Inits

            switch($scope.mode) {
                case 'new':
                    $scope.article = {};
                    addReferenceLink();
                    break;
                case 'edit':
                    break;
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
