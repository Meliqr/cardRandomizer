var cardRandomizer = angular.module('cardRandomizer', []);
cardRandomizer.controller('allUsersCtrl', function($scope, $http) {
    $scope.users = {};
    $scope.getAllUsers = function() {
        $http.get("/getAllUsers")
            .success(function(data) {
                $scope.users = data;
            });
    }
});

cardRandomizer.controller('userLoginCtrl', function($scope, $http) {
    $scope.user = {};
    $scope.getUser = function() {
        var request = $http({
            method: "post",
            url: "/getUser",
            data: {
                "userName": $scope.userName,
                "pass": $scope.userPass
            }
        });

        request.success(function (data) {
            console.log(data);
            $scope.user = data;
        });
    }

});

cardRandomizer.controller('userRegisterCtrl', function($scope, $http) {
    $scope.registerReturn = "";
    $scope.registerUserButton = function() {
        var request = $http({
            method: "post",
            url: "/registerUser",
            data: {
                "userName": $scope.registerUser,
                "pass": $scope.registerPass,
                "firstName": $scope.registerFirstName,
                "lastName": $scope.registerLastName,
                "emailAddress": $scope.registerEmailAddress
            }
        });

        request.success(function (data) {
            console.log(data);
            $scope.registerReturn = data;
        });
    }
});
