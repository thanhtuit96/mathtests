'use strict';
// Declare app level module which depends on views, and components
angular.module('mathtest', [
  'ui.router',
  'mathtest.home',
  'mathtest.login',
  'mathtest.services'
]).
config(['$locationProvider', "$httpProvider",'$stateProvider', '$urlRouterProvider', function($locationProvider, $httpProvider,$stateProvider, $urlRouterProvider) {
	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
/*	$urlRouterProvider.otherwise('/');
*/	$locationProvider.hashPrefix('');
}])
.controller('MathTestsCtrl', ['$scope', '$rootScope', '$http', '$location', 'AuthService', '$state', 
  function($scope, $rootScope, $http, $location, authService, $state) {
	if(!authService.getJwtToken()){
	   //$location.path('login');
	   $state.transitionTo('login');
	}
	else {
	   //$location.path('home');
	   $state.transitionTo('home');
	}
  }
]);