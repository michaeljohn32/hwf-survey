var hwfSurveyControllers = angular.module('hwfSurveyControllers', []);

hwfSurveyControllers.controller('HwfSurveyController', function ($scope, $http) {
    $scope.initialMessage = 'Hello from controller.';


    $http.get('/hwf-survey/survey-definition').success(
        function (data) {
            $scope.surveydef = data;
        });
});