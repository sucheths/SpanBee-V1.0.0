(function() {

	app = angular.module('app', [ 'ngRoute','angularModalService','app.directives']);

	app.config(function($routeProvider, $locationProvider) {
		$routeProvider.when('/landingPage', {
			controller : 'LandingPage',
			templateUrl : appStaticData.templatePath.landingPage
		}).when('/welcome', {
			controller : 'Welcome',
			templateUrl : appStaticData.templatePath.welcome
		}).when('/userDetails', {
			controller : 'Welcome',
			templateUrl : appStaticData.templatePath.userDetails
		}).when('/profileView', {
			controller : 'Welcome',
			templateUrl : appStaticData.templatePath.profileView
		}).otherwise({
			redirectTo : '/landingPage'
		});
	});
	
})();
