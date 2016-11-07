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
                            ngToast.create({
                                className: 'danger',
                                content: 'Não foi possível carregar as sub-especialidades. Tente novamente mais tarde',
                            });
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
                            ngToast.create({
                                className: 'danger',
                                content: 'Não foi possível criar o artigo. Tente novamente mais tarde',
                            });
                        }
                    );
                }
               
                
            }

            // Reference Links 

            $scope.deleteReferenceLink = function(referenceLink, index) {
                dialogs.confirm("Link de Referência","Deseja mesmo apagar o link '"+referenceLink.referenceLink+"'?")
                    .result
                    .then(function(){
                        if (!$scope.article.referenceLinks[index].id) {
                            $scope.article.referenceLinks.splice(index,1);
                            return;
                        }

                        ReferenceLinkService.remove($scope.article.referenceLinks[index].id).then(
                            function(data){
                                $scope.article.referenceLinks.splice(index,1);
                            },function(err){
                                ngToast.create({
                                    className: 'danger',
                                    content: 'Não foi possível apagar a referência indicada. Tente novamente mais tarde',
                                });
                            }
                        );
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
                            originalArticle = angular.copy(article.content,originalArticle);
                            $scope.article = article.content;
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
                        ngToast.create({
                            className: 'danger',
                            content: 'Não foi possível carregar os tipos de artigos. Tente novamente mais tarde',
                        });
                    }
                );
            }

            var loadDoctors = function(){
                DoctorService.getAll().then(
                    function(data){
                        $scope.doctors = data;
                    },function(err){
                        ngToast.create({
                            className: 'danger',
                            content: 'Não foi possível carregar os médicos. Tente novamente mais tarde',
                        });
                    }
                );
            }

            var loadSpecialities = function(){
                SpecialityService.getAll().then(
                    function(data){
                        $scope.specialties = data;
                    },function(err){
                        ngToast.create({
                            className: 'danger',
                            content: 'Não foi possível carregar as especialidades. Tente novamente mais tarde',
                        });
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
