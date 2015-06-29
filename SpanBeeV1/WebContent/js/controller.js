(function() {

	app.controller('LandingPage',function($location, CommonFactory, $scope, CommonService,ModalService) {
		
		$scope.login = function() {
			ModalService.showModal({
				templateUrl : appStaticData.templatePath.loginRegister,
				controller : "Login"
			}).then(function(modal) {
				modal.element.modal();
			});
		};
	});

	app.controller('Login',function($location, CommonFactory, $scope, CommonService,ModalService) {
		
		$scope.signInForm = function(signInFormData){
			var formValue = {
					"login" : angular.copy(signInFormData)
			};
			CommonFactory.ajaxRequest(CommonFactory.createFormParam("services/login/authenticate",formValue));
			CommonService.close($scope);
			$location.path('/welcome');
		}
		
		$scope.forgotPassword = function(signInFormData){
			var formValue = {
					"forgotPassword" : angular.copy(signInFormData)
			};
			//CommonFactory.ajaxRequest(CommonFactory.createFormParam("services/login/forgotPassword",formValue));
			//CommonService.close($scope);
			//$location.path('/welcome');
		}
		
		$scope.registerForm = function(registerFormData){
			var formValue = {
					"register" : angular.copy(registerFormData)
			};
	        CommonFactory.ajaxRequest(CommonFactory.createFormParam("services/registration/register",formValue));
	        CommonService.close($scope);
	        $location.path('/welcome');
		}
		
	});
	
	app.controller('Welcome',function($location, CommonFactory, $scope, CommonService,ModalService) {

		$scope.logout = function(){
			$location.path('/landingPage');
		}
		
		$scope.welcome = function(){
			$location.path('/welcome');
		}
		
		$scope.share = function(){
			ModalService.showModal({
				templateUrl : appStaticData.templatePath.share,
				controller : "Welcome"
			}).then(function(modal) {
				modal.element.modal();
			});
		}
		
		$scope.profileView = function(){
			$location.path('/profileView');
		}
		
		$scope.userDetails = function(){
			$location.path('/userDetails');
		}
	});
})();