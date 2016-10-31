var vejaSaudeBo = angular.module('vejaSaudeBo', ['ngRoute', 'ngAnimate','ngSanitize','ngToast','ui.bootstrap','dialogs.main']);

vejaSaudeBo.config(['$locationProvider', '$routeProvider','$httpProvider','ngToastProvider',
    function config($locationProvider, $routeProvider,$httpProvider, ngToastProvider) {

      // validate session
      $httpProvider.interceptors.push('SessionInterceptor');

      $locationProvider.hashPrefix('!');
      $routeProvider.
        when('/', {  
          controller: 'MainController',
          templateUrl: 'views/home.html'
        }).when('/login', {
          controller: 'LoginController',
          templateUrl: 'views/login.html'
        }).when('/home', {
          controller: 'MainController',
          templateUrl: 'views/home.html'
        }).when('/home/users', {
          controller:   'UserController',
          templateUrl:  'views/menus/users/list-users.html'
        }).when('/user/:mode/:username?', {
          controller:   'CreateEditUserController',
          templateUrl:  'views/menus/users/edit-user.html'
        }).when('/home/doctors', {
          controller: 'DoctorController',
          templateUrl: 'views/menus/doctors/list-doctors.html'
        }).when('/doctor/:mode/:id?', {
          controller: 'CreateEditDoctorController',
          templateUrl: 'views/menus/doctors/createedit-doctor.html',
          resolve: "CreateEditDoctorController.resolve"
        }).when('/home/articles', {
          controller:   'ArticleController',
          templateUrl:  'views/menus/articles/list.html'

        }).otherwise({
          redirectTo: '/'
        });

        ngToastProvider.configure({
          verticalPosition: 'top',
          horizontalPosition: 'center',
          maxNumber: 3,
          dismissOnTimeout: 2500,
          animation: 'slide' // or 'fade'

        });
      
    }
  ]);

  vejaSaudeBo.filter('capitalize', function() {
    return function(input, all) {
      var reg = (all) ? /([^\W_]+[^\s-]*) */g : /([^\W_]+[^\s-]*)/;
      return (!!input) ? input.replace(reg, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}) : '';
    }
  });


