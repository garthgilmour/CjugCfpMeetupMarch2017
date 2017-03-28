var restApp = angular.module('coursesApp', []);

restApp.constant('baseURL','/courses/');

restApp.controller('CoursesController', function (baseURL, $scope,$http) {
    fetchCourses();
    $scope.noCourseSelected = true;
    $scope.courseBeingUpdated = false;
    
    function fetchCourses() {
        function storeCourses(data) {
			$scope.error = "";
            $scope.courses = data;
        }
		function failure() {
			$scope.error = "Cannot load courses";
		}
        $http.get(baseURL)
			 .success(storeCourses)
			 .error(failure);
    }
    $scope.fetchCourses = fetchCourses;
    
    $scope.beginUpdateOfCourse = function(course) {
        $scope.courseBeingUpdated = angular.copy(course);
    };
    $scope.updateCourse = function() {
        function resetForm() {
            $scope.courseBeingUpdated = false;
            fetchCourses();
        }
        var course = $scope.courseBeingUpdated;
        $http.put(baseURL, course).success(resetForm);
    };
    $scope.selectCourse = function(course) {
		$scope.error = "";
        $scope.noCourseSelected = false;
        $scope.selectedCourse = course;
    };
});