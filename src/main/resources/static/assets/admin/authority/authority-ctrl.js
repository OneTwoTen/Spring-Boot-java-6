app.controller("authority-ctrl", function ($scope, $http) {
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function () {

        $http.get("/rest/roles")
            .then(response => {
                $scope.roles = response.data;
                console.log($scope.roles);
            });

        $http.get("/rest/accounts?admin=true")
            .then(response => {
                $scope.admins = response.data;
                console.log($scope.admins);
            });

        $http.get("/rest/authorities?admin=true")
            .then(response => {
                $scope.authorities = response.data;
                console.log($scope.authorities);
            })
            .catch(error => {
                $location.path("/unauthorized");
            })
    };

    $scope.authority_of = function (account, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(authority => authority.account.username == account.username && authority.role.id == role.id);
        }
    }

    $scope.authority_changed = function (account, role) {
        var authority = $scope.authority_of(account, role);
        if (authority) {
            $scope.revoke_authority(authority);
        }
        else {
            authority = { account: account, role: role };
            $scope.grant_authority(authority);
        }
    }

    $scope.grant_authority = function (authority) {
        $http.post("/rest/authorities", authority)
            .then(response => {
                $scope.authorities.push(response.data);
                alert("Cấp quyền thành công!");
            })
            .catch(error => {
                alert("Cấp quyền thất bại!");
                console.log("error", error);
                console.log(authority)
            }
            )
    }

    $scope.revoke_authority = function (authority) {
        $http.delete("/rest/authorities/" + authority.id)
            .then(response => {
                var index = $scope.authorities.findIndex(a => a.id == authority.id);
                $scope.authorities.splice(index, 1);
                alert("Thu hồi quyền sử dụng thành công");
            })
            .catch(error => {
                alert("Có lỗi xảy ra. Vui lòng thử lại");
                console.log("error", error);
            })
    }
    $scope.initialize();
})