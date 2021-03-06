'use strict';

/* Services */

var blogServices = angular.module('blogServices', [ 'ngResource' ]);

blogServices.factory('BlogPost', [ '$resource', '$routeParams',
		function($resource, $routeParams) {
			return $resource("./blog/blogPost.do", {}, {
				get : {
					method : 'POST',
					cache : false,
					isArray : false
				},
				save : {
					method : 'JSONP',
					cache : false,
					isArray : false,
					params: {callback: 'JSON_CALLBACK', method : 'save'}
				},
				update : {
					method : 'PUT',
					cache : false,
					isArray : false
				},
				del : {
					method : 'DELETE',
					cache : false,
					isArray : false
				}
			});
		} ]);

blogServices.factory('BlogList', [ '$resource', function($resource) {
	return $resource("./blog/blogList.do", {}, {
		get : {
			method : 'GET',
			cache : false,
			isArray : true
		}
	});
} ]);

blogServices.factory('test', [ '$resource', function($resource) {
	return $resource("./test", {}, {
		get : {
			method : 'GET',
			cache : false,
			isArray : false
		}
	});
} ]);

blogServices.factory('Login', [ '$resource', function($resource) {
	return $resource("./login.do", {}, {
		login : {
			method : 'POST',
			cache : false,
			isArray : false,
			headers : {'Content-Type' : 'application/json;charset=utf-8'}
//			params: {callback: 'JSON_CALLBACK'}
//			format: 'json'
		}
	});
} ]);

blogServices.factory('BlogPostComments', [ '$resource', function($resource) {
	return $resource("./comment", {}, {
		save : {
			method : 'JSONP',
			cache : false,
			isArray : false,
			params: {callback: 'JSON_CALLBACK', method : 'save'}
		}
	});
} ]);
