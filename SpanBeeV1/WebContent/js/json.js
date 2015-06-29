var appStaticData = appStaticData || {};

var appStaticData = (function() {
	var templatePath = {
			"landingPage": "pages/landing.htm",
			"welcome": "pages/welcome.htm",
			"userDetails": "pages/userDetails.htm",
			"profileView": "pages/profileView.htm",
			"share": "pages/share.htm",
			"loginRegister": "pages/loginRegister.htm"
	};
	
	var requestUrl = "http://10.10.10.20:9000/span-bee-0.0.1-SNAPSHOT/";
	var session_id = "";
	var jsonResponse = "";
	var formParam = "";
	var version = "1.0";
	return {
		templatePath: templatePath,
		requestUrl: requestUrl,
		session_id: session_id,
		jsonResponse: jsonResponse,
		formParam:formParam,
		version: version
	};
})();