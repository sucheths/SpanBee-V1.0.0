<!DOCTYPE html>
<html lang="en" ng-app="app" ng-controller='LandingPage'>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<title>Span-Bee</title>
		<meta name="description" content="Span-Bee">
		<meta name="author" content="Nagarjun Sanji">

		<!-- Mobile Meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Favicon -->
		<link rel="shortcut icon" href="images/favicon.ico">

		<!-- Bootstrap core CSS -->
		<link href="css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
		<link href="css/lib/bootstrap/carousel.css" rel="stylesheet">
		<link href="css/lib/bootstrap/dashboard.css" rel="stylesheet">
		<link href="css/lib/bootstrap/sticky-footer.css" rel="stylesheet">

		<!-- Font Awesome CSS -->
		<link href="css/lib/font-awesome.css" rel="stylesheet">

		<!-- Plugins -->
		<link href="css/lib/animations.css" rel="stylesheet">

		<!-- Material Design css --> 
		<link href="css/lib/materialDesign/roboto.min.css" rel="stylesheet">
        <link href="css/lib/materialDesign/material-fullpalette.min.css" rel="stylesheet">
        <link href="css/lib/materialDesign/ripples.min.css" rel="stylesheet">

		<!-- Custom css --> 
		<link href="css/custom.css" rel="stylesheet">
		
	</head>

	<body>
		
		<div ng-view></div>
		
		<!-- JavaScript files placed at the end of the document so the pages load faster
		================================================== -->
		<!-- Jquery and Bootstap core js files -->
		<script type="text/javascript" src="js/lib/plugins/jquery.min.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap/bootstrap.js"></script>
		<script type="text/javascript" src="js/lib/material.js"></script>
		<script type="text/javascript" src="js/lib/ripples.js"></script>
		
		<!-- angular js files -->
		<script type="text/javascript" src="js/lib/angular/angular.min.js"></script>
		<script type="text/javascript" src="js/lib/angular/angular-modal-service.js"></script>
		<script type="text/javascript" src="js/lib/angular/angular-route.js"></script>
		<script type="text/javascript" src="js/lib/angular/angular-cookies.js"></script>
		
		<!-- Initialization of Plugins -->
		<script type="text/javascript" src="js/lib/bootstrap/bootstrap-dialog.js"></script> 
		
		
		<!-- custom js files -->
		<script type="text/javascript" src="js/config.js"></script>
		<script type="text/javascript" src="js/factory.js"></script>
		<script type="text/javascript" src="js/service.js"></script>
		<script type="text/javascript" src="js/directive.js"></script>
		<script type="text/javascript" src="js/controller.js"></script>
		<script type="text/javascript" src="js/json.js"></script>

		<!-- Custom Scripts -->
		<script type="text/javascript" src="js/custom.js"></script>
	</body>
</html>
