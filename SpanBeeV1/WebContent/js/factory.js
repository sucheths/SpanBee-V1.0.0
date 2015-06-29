app.factory('CommonFactory', function() {
	var factory = {};

	factory.createFormParam = function(callBackFunction,formValueJson) {
		var formValue = {};
		formValue = formValueJson;
		
		var jsonTemplate = {
			    "version": appStaticData.version,
			    "session_id": appStaticData.session_id,
			    "request_origin": "mozilla/chrome",
			    "data": formValue
			};
		var formParam = {
				"callBackFunction" : callBackFunction,
				"formValue" : jsonTemplate
			};
		
		return formParam;
	}
	
	factory.ajaxRequest = function(formParam) {

		appStaticData.formParam = formParam;
		var jsonResponse = "";
		$.ajax({
			url : appStaticData.requestUrl + formParam.callBackFunction,
			method : "POST",
			async : false,
			dataType : "json",
			contentType: "application/json",
			cache : true,
			beforeSend : function() {
			},
			complete : function() {
			},
			data : JSON.stringify(formParam.formValue),
			success : function(response) {
				console.log(response);
				jsonResponse = response;
				appStaticData.jsonResponse = response;
				if(jsonResponse != undefined && jsonResponse != null){
					if(jsonResponse.userId != undefined && jsonResponse.userId != null){
						appStaticData.session_id = jsonResponse.userId;
					}else{
						appStaticData.session_id = "";
					}
				}else{
					appStaticData.session_id = "";
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				appStaticData.session_id = "";
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});

		return jsonResponse;
	}

	var jsonResponse = jsonResponse;

	factory.getJsonResponse = function() {
		return this.jsonResponse;
	}

	factory.setJsonResponse = function(jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	
	return factory;
});