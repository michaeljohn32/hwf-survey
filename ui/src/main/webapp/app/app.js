var hwfSurveyApp = angular.module('hwfSurveyApp', [
    'ngRoute',
    'ui.bootstrap',
    'hwfSurveyControllers',
]);

hwfSurveyApp
    .config(function($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'app/hwf-survey/survey-display.html'
            }).
            otherwise({
                redirectTo: '/'
            });
    });