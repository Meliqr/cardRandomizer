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
    $scope.boosterReturn = [];
    $scope.allSets = "";
    $scope.cardSetName = "Magic Origins";
    $scope.setCards = {};
    $scope.cardSet = "ORI";

    $http.get('cards/setInfo.json').success(function (data) {
        $scope.allSets = data;
    });

    $http.get("/cards/" + $scope.cardSet + "_cards.json").success(function (data) {
            $scope.setCards = data;
    });


    $scope.openBooster = function() {
        var cardsReduced = $scope.setCards.map(function(curCard){
            return curCard.editions.filter(function(curEd){
             return curEd.set_id == $scope.cardSet;
            }).map(function(singleEd){
                return {"name":curCard.name,"url":curCard.url,"set":singleEd.set,"set_id":singleEd.set_id,"rarity":singleEd.rarity,"image_url":singleEd.image_url};
            });
        }).reduce(function(pre, cur) {
            return pre.concat(cur);
        });

        var commonCards = cardsReduced.filter(function(curCard){
            return curCard.rarity == "common";
        });
        var uncommonCards = cardsReduced.filter(function(curCard){
            return curCard.rarity == "uncommon";
        });
        var rareCards = cardsReduced.filter(function(curCard){
            return curCard.rarity == "rare";
        });
        var mythicCards = cardsReduced.filter(function(curCard){
            return curCard.rarity == "mythic";
        });
        var landCards = cardsReduced.filter(function(curCard){
            return curCard.rarity == "basic";
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
            $scope.boosterReturn =[];
            data.cards.forEach(function(curCard){
                if(curCard.cardId <= commonCards.length){
                    $scope.boosterReturn.push(commonCards[curCard.cardId-1]);
                }
                else if(curCard.cardId <= (commonCards.length + uncommonCards.length)){
                    $scope.boosterReturn.push(uncommonCards[curCard.cardId-commonCards.length-1]);
                }
                else if((curCard.cardId <= (commonCards.length + uncommonCards.length + rareCards.length)) && (rareCards.length)){
                    $scope.boosterReturn.push(rareCards[curCard.cardId-commonCards.length-uncommonCards.length-1]);
                }
                else if((curCard.cardId <= (commonCards.length + uncommonCards.length + rareCards.length +mythicCards.length)) && (mythicCards.length)){
                    $scope.boosterReturn.push(mythicCards[curCard.cardId-commonCards.length-uncommonCards.length-rareCards.length-1]);
                }
                else{
                    $scope.boosterReturn.push(landCards[curCard.cardId-commonCards.length-uncommonCards.length-rareCards.length-mythicCards.length-1]);
                }
            });
        });
    }
    $scope.getSetCards = function() {
        var singleSet = $scope.allSets.forEach(function(setCheck){if(setCheck.id == $scope.cardSet){$scope.cardSetName = setCheck.name;}});
        $http.get("/cards/"+$scope.cardSet+"_cards.json")
            .success(function(data) {
                $scope.setCards = data;
            });
    }

});
