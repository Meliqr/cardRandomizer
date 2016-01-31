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

cardRandomizer.controller('openBoosterCtrl', function($scope, $http) {
    $scope.boosterReturn = "";
    $scope.allSets = "";
    $http.get('cards/setInfo.json').success(function(data) {
        $scope.allSets = data;
    });

    $scope.cardSet = "ZEN";
    $scope.openBooster = function() {
        var request = $http({
            method: "post",
            url: "/getBooster",
            data: {
                "setName": "Zendikar",
                "numCommon": 101,
                "setAbbreviation": "ZEN",
                "numUncommon": 60,
                "numRare": 53,
                "numMythic": 15,
                "numLands": 20,
                "packSize": 15,
                "hasPremium": true
            }
        });

        request.success(function (data) {
            console.log(data);
            $scope.boosterReturn = data;
        });
    }
});
