angular.module('mathtest.home', ['ui.router'])

.config(function($stateProvider, $urlRouterProvider){
      
      // For any unmatched url, send to /route1
      $urlRouterProvider.otherwise("/")
      $stateProvider
      .state('home', {
        templateUrl: 'html/home.html'
       });
})
.controller('HomeCtrl', ['$scope', '$rootScope', '$http', '$location', 'AuthService',
  function($scope, $rootScope, $http, $location, authService) {
 
  
}]);
