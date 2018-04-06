'use strict';
// Declare app level module which depends on views, and components
angular.module('mathtest', [
  'ngRoute',
/*  'mathtest.home',
*/  'mathtest.login',
  'mathtest.services'
]).
config(['$locationProvider', '$routeProvider', "$httpProvider", function($locationProvider, $routeProvider, $httpProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$routeProvider.otherwise({redirectTo: '/'});
	$locationProvider.hashPrefix('');
}])
.controller('MathTestsCtrl', ['$scope', '$rootScope', '$http', '$location', 'AuthService',
  function($scope, $rootScope, $http, $location, authService) {
	if(!authService.getJwtToken()){
	   $location.path('/login');
	}
	/*else {
	   $location.path('/home');
	}*/
  }
]);