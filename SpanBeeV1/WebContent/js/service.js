app.service('CommonService', function() {
	this.close = function($scope) {
		$('.modal').modal('hide');
	};
});
