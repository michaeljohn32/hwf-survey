var hwfSurveyControllers = angular.module('hwfSurveyControllers', []);

hwfSurveyControllers.controller('HwfSurveyController', function ($scope, $http) {
    var controller = this;

    $scope.surveySubmitted = false;
    $scope.showResults = false;

    $http.get('/hwf-survey/survey-definition').success(
        function (data) {
            $scope.surveydef = data;
        });

    controller.submitSurvey = function submitSurvey() {
        $http.post('/hwf-survey/survey-submission', $scope.surveydef);
        $scope.surveySubmitted = true;

        setTimeout(function(){
            $scope.showResults = true;
            $http.get('/hwf-survey/survey-results').success(
                function (data) {
                    var i = 1;
                    data.forEach(function(surveyQuestion) {
                        if (!surveyQuestion.freeformAnswer) {
                            var div = document.createElement("div");
                            div.id = "container" + i;
                            document.getElementById('charts').appendChild(div);
                            Highcharts.chart('container' + i, {
                                chart: { type: 'bar' },
                                title: { text: surveyQuestion.questionText },
                                xAxis: { categories: surveyQuestion.answers, title: { text: null } },
                                yAxis: { title: { text: 'Count' } },
                                series: [{ showInLegend: false, data: surveyQuestion.counts, name: null }],
                                credits: { enabled: false },
                            });
                            i++;
                        } else {
                            var div = document.createElement("h4");
                            div.id = "container" + i;
                            div.innerHTML = surveyQuestion.questionText;
                            document.getElementById('charts').appendChild(div);
                            surveyQuestion.answers.forEach(function(answer) {
                                var answerDiv = document.createElement("blockquote");
                                answerDiv.innerHTML = answer;
                                document.getElementById('charts').appendChild(answerDiv);
                            });
                        }
                    });
                });
        }, 500);
    };
});
