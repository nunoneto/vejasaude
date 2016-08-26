var vejaSaudeBo = angular.module('vejaSaudeBo', ['ngRoute']);

vejaSaudeBo.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
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
      
    }
  ]);

