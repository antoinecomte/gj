'use strict';

/* Controllers */

var myAppControlers = angular.module('myApp.controllers', []);

myAppControlers.controller('DashboardCtrl', ['$scope', 'Bucket', 'metricSource', function ($scope, Bucket, metricSource) {

    $scope.values = [];

    $scope.chartSeries = [
        {"name": "Plot", "data": []}

    ];

    $scope.chartConfig = {
        options: {
            chart: {
              //  type: 'spline',
                animation: Highcharts.svg,
                zoomType: 'x'
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            }
        },
        series: $scope.chartSeries,
        title: {
            text: ''
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        credits: {
            enabled: true
        },
        loading: false};


    $scope.subscribe = function (b) {
        metricSource.subscribe($scope, b)
    }
    $scope.unsubscribe = function (b) {
        metricSource.unsubscribe(b)
    }

    $scope.buckets = Bucket.query();
    $scope.showedBuckets = []

}]);

myAppControlers.controller('buckets', ['$scope', 'Bucket', function ($scope, Bucket) {

} ]);

myAppControlers.controller('SettingsCtrl', ['$scope', function ($scope) {

} ]);