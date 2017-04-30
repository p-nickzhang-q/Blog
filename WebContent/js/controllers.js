'use strict';

var blogControllers = angular.module('blogControllers', []);

blogControllers.controller('BlogCtrl', [ '$scope', 'BlogList', '$location',
		'checkCreds', function($scope, BlogList, $location, checkCreds) {
			if (!checkCreds()) {
				$location.path('/login');
			}
			$scope.brandColor = "color: white;";
			$scope.blogList = [];
			BlogList.get({}, function success(response) {
				console.log("success: " + JSON.stringify(response));
				$scope.blogList = response;
			}, function error(errorResponse) {
				console.log("error: " + JSON.stringify(errorResponse));
			});
		} ]);

blogControllers.controller('BlogViewCtrl', [
		'$scope',
		'$routeParams',
		'BlogPost',
		'$location',
		'checkCreds',
		'$http',
		'getToken',
		'BlogPostComments',
		'$route',
		function($scope, $routeParams, BlogPost, $location, checkCreds, $http,
				getToken, BlogPostComments, $route) {
			if (!checkCreds()) {
				$location.path('/login');
			}
			var blogId = $routeParams.id;
			BlogPost.get({
				id : blogId
			}, function success(response) {
				console.log("success: " + JSON.stringify(response));
				$scope.blogEntry = response;
			}, function error(errorResponse) {
				console.log("error: " + JSON.stringify(errorResponse));
			});

			$scope.submit = function() {
				$scope.sub = true;
				$http.defaults.headers.common['Authorization'] = 'Basic '
						+ getToken();
				var postData = {
					commentText : $scope.commentText,
					blog : blogId
				};

				BlogPostComments.save(postData, function success(response) {
					console.log("success: " + JSON.stringify(response));
					$location.path('/blogPost/' + blogId);
					$route.reload();
				}, function error(errorResponse) {
					console.log("error: " + JSON.stringify(errorResponse));
				});
			};
		} ]);


blogControllers.controller('LoginCtrl', [ '$scope', '$location',
		'Login', 'setCreds', 'checkCreds',
		function LoginCtrl($scope, $location, Login, setCreds, checkCreds) {
		    if (checkCreds()) {
		        $location.path('/');
		    }
		    $scope.submit = function() {
		        $scope.sub = true;
		        var postData = {
		            username: $scope.username,
		            password: $scope.password
		        };
		        Login.login(postData,
		                function success(response) {
		                    console.log("Success:" + JSON.stringify(response));
		                    if (response.authenticated) {
		                        setCreds($scope.username, $scope.password)
		                        $location.path('/');
		                    } else {
		                        $scope.error = "Login Failed"
		                    }
		
		                },
		                function error(errorResponse) {
		                	$scope.error = "Login Failed"
		                    console.log("Error:" + JSON.stringify(errorResponse));
		                }
		        );
		
		    };			
		} ]);

blogControllers.controller('LogoutCtrl', [ '$location', 'deleteCreds',
		function($location, deleteCreds) {
			deleteCreds();
		    $location.path('/login');
		} ]);

blogControllers.controller('NewBlogPostCtrl', [ '$scope', 'BlogPost', '$location', 'checkCreds', '$http', 'getToken',
		function($scope, BlogPost, $location, checkCreds, $http, getToken) {
		    if (!checkCreds()) {
		        $location.path('/login');
		    }
	        $scope.languageList = [ {
				"id" : 1,
				"name" : "English"
			}, {
				"id" : 2,
				"name" : "Spanish"
			} ];
	        $scope.languageId = 1;
	        $scope.newActiveClass = "active";
	        $scope.submit = function() { 
	        	$scope.sub = true;
	            $http.defaults.headers.common['Authorization'] = 'Basic ' + getToken();
	            var postData = {
	                introText: $scope.introText,
	                blogText : $scope.blogText,
	                languageId : $scope.languageId
	            };
	            BlogPost.save(postData,
	                    function success(response) {
	                        //alert($scope.challenge.question);
	                        console.log("Success:" + JSON.stringify(response));
	                        $location.path('/');
	                    },
	                    function error(errorResponse) {
	                        console.log("Error:" + JSON.stringify(errorResponse));
	                    }
	            );
	        };
		} ]);


