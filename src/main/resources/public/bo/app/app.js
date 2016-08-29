var vejaSaudeBo = angular.module('vejaSaudeBo', ['ngRoute', 'ngAnimate','ngSanitize','ngToast'  ]);

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
        }).when('/home/doctors', {
          controller: 'DoctorController',
          templateUrl: 'views/menus/doctors/list-doctors.html'
        }).otherwise({
          redirectTo: '/'
        });

        ngToastProvider.configure({
          verticalPosition: 'top',
          horizontalPosition: 'center',
          maxNumber: 3,
          dismissOnTimeout: 4000,
          animation: 'slide' // or 'fade'

        });
      
    }
  ]);

