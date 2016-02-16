var cardRandomizer = angular.module('cardRandomizer', [
    'ngRoute',
    'cardControllers'
]);

cardRandomizer.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/user/login', {
            templateUrl: 'partials/user-login.html',
            controller: 'userLoginCtrl'
        }).
        when('/user/register', {
            templateUrl: 'partials/user-registration.html',
            controller: 'userRegisterCtrl'
        }).
        when('/user/all', {
            templateUrl: 'partials/all-users.html',
            controller: 'allUsersCtrl'
        }).
        when('/booster', {
            templateUrl: 'partials/open-booster.html',
            controller: 'openBoosterCtrl'
        }).
        when('/cardSets', {
            templateUrl: 'partials/all-sets.html',
            controller: 'allSetsCtrl'
        }).
        otherwise({
            redirectTo: '/booster'
        });
    }]);