var hwfSurveyControllers = angular.module('hwfSurveyControllers', []);

hwfSurveyControllers.controller('HwfSurveyController', function ($scope, $http) {
    var controller = this;

    $http.get('/hwf-survey/survey-definition').success(
        function (data) {
            $scope.surveydef = data;
        });

    controller.submitSurvey = function submitSurvey() {
        $http.post('/hwf-survey/survey-submission', $scope.surveydef);
    };

    controller.getResults = function getResults() {
        $http.get('/hwf-survey/survey-results').success(
            function (data) {
                $scope.results = data;
            });
    };
});