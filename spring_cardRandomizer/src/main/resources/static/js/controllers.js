var cardControllers = angular.module('cardControllers', []);

cardControllers.controller('allUsersCtrl', function ($scope, $http) {
    $scope.users = {};
    $scope.getAllUsers = function () {
        $http.get("/getAllUsers")
            .success(function (data) {
                $scope.users = data;
            });
    }
});


cardControllers.controller('userLoginCtrl', function ($scope, $http) {
    $scope.user = {};
    $scope.loginUser = {};
    $scope.getUser = function () {
        var request = $http({
            method: "post",
            url: "/getUser",
            data: {
                "userName": $scope.loginUser.userName,
                "pass": $scope.loginUser.userPass
            }
        });

        request.success(function (data) {
            console.log(data);
            $scope.user = data;
            $scope.loginUser = {};
            $scope.loginForm.$setPristine();
            $scope.loginForm.$setUntouched();
        });
    }

});


cardControllers.controller('userRegisterCtrl', function ($scope, $http) {
    $scope.registerReturn = "";
    $scope.registerIn = {};
    $scope.registerUserButton = function () {
        var request = $http({
            method: "post",
            url: "/registerUser",
            data: $scope.registerIn
        });

        request.success(function (data) {
            console.log(data);
            $scope.registerReturn = data.queryResponse;
            $scope.registerIn = {};
            $scope.registerForm.$setPristine();
            $scope.registerForm.$setUntouched();
        });
    }
});


cardControllers.controller('openBoosterCtrl', function ($scope, $http) {
    $scope.boosterReturn = [];
    $scope.allSets = [];
    $scope.cardSetName = "Magic Origins";
    $scope.setCards = [];
    $scope.cardSet = "ORI";

    $http.get('cards/setInfo.json').success(function (data) {
        $scope.allSets = data;
    });

    $http.get("cards/" + $scope.cardSet + "_cards.json").success(function (data) {
        $scope.setCards = data;
    });


    $scope.openBooster = function () {
        var cardsReduced = $scope.setCards.map(function (curCard) {
            return curCard.editions.filter(function (curEd) {
                return curEd.set_id === $scope.cardSet;
            }).map(function (singleEd) {
                return {
                    "name": curCard.name,
                    "url": curCard.url,
                    "set": singleEd.set,
                    "set_id": singleEd.set_id,
                    "rarity": singleEd.rarity,
                    "image_url": singleEd.image_url
                };
            });
        }).reduce(function (pre, cur) {
            return pre.concat(cur);
        });

        var commonCards = cardsReduced.filter(function (curCard) {
                return curCard.rarity === "common";
            }),
            uncommonCards = cardsReduced.filter(function (curCard) {
                return curCard.rarity === "uncommon";
            }),
            rareCards = cardsReduced.filter(function (curCard) {
                return curCard.rarity === "rare";
            }),
            mythicCards = cardsReduced.filter(function (curCard) {
                return curCard.rarity === "mythic";
            }),
            landCards = cardsReduced.filter(function (curCard) {
                return curCard.rarity === "basic";
            });

        var request = $http({
            method: "post",
            url: "/getBooster",
            data: {
                "setName": $scope.cardSetName,
                "numCommon": commonCards.length,
                "setAbbreviation": $scope.cardSet,
                "numUncommon": uncommonCards.length,
                "numRare": rareCards.length,
                "numMythic": mythicCards.length,
                "numLands": landCards.length,
                "packSize": 15,
                "hasPremium": true
            }
        });

        request.success(function (data) {
            $scope.boosterReturn = [];
            var commonIndex = commonCards.length,
                uncommonIndex = commonCards.length + uncommonCards.length,
                rareIndex = commonCards.length + uncommonCards.length + rareCards.length,
                mythicIndex = commonCards.length + uncommonCards.length + rareCards.length + mythicCards.length;
            data.cards.forEach(function (curCard) {
                if (curCard.cardId <= commonIndex) {
                    $scope.boosterReturn.push(commonCards[curCard.cardId - 1]);
                }
                else if (curCard.cardId <= uncommonIndex) {
                    $scope.boosterReturn.push(uncommonCards[curCard.cardId - commonIndex - 1]);
                }
                else if ((curCard.cardId <= rareIndex) && (rareCards.length)) {
                    $scope.boosterReturn.push(rareCards[curCard.cardId - uncommonIndex - 1]);
                }
                else if ((curCard.cardId <= mythicIndex) && (mythicCards.length)) {
                    $scope.boosterReturn.push(mythicCards[curCard.cardId - rareIndex - 1]);
                }
                else {
                    $scope.boosterReturn.push(landCards[curCard.cardId - mythicIndex - 1]);
                }
            });
        });
    }
    $scope.getSetCards = function () {
        var singleSet = $scope.allSets.filter(function (curSet) {
            return curSet.id === $scope.cardSet
        })[0];
        $http.get("cards/" + $scope.cardSet + "_cards.json")
            .success(function (data) {
                $scope.setCards = data;
            });
    }

});


cardControllers.controller('allSetsCtrl', function ($scope, $http) {
    $scope.setReturn = [];
    $scope.allSets = [];
    $scope.cardSetName = "Magic Origins";
    $scope.setCards = [];
    $scope.cardSet = "ORI";

    $http.get('cards/setInfo.json').success(function (data) {
        $scope.allSets = data;
    });

    $http.get("cards/" + $scope.cardSet + "_cards.json").success(function (data) {
        $scope.setCards = data;
    });


    $scope.showCardsInSet = function () {
        var cardsReduced = $scope.setCards.map(function (curCard) {
            return curCard.editions.filter(function (curEd) {
                return curEd.set_id === $scope.cardSet;
            }).map(function (singleEd) {
                return {
                    "name": curCard.name,
                    "url": curCard.url,
                    "set": singleEd.set,
                    "set_id": singleEd.set_id,
                    "rarity": singleEd.rarity,
                    "image_url": singleEd.image_url
                };
            });
        }).reduce(function (pre, cur) {
            return pre.concat(cur);
        });

        $scope.setReturn = cardsReduced;
    }
    $scope.getSetCards = function () {
        var singleSet = $scope.allSets.filter(function (curSet) {
            return curSet.id === $scope.cardSet
        })[0];
        $http.get("cards/" + $scope.cardSet + "_cards.json")
            .success(function (data) {
                $scope.setCards = data;
            });
    }

});


