app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.categories = [];
    $scope.form = {};
    $scope.tab = {};
    $scope.page = {
        pageIndex: 0,
        pageTotal: 0,
        searchText: "",
        sortBy: "id",
        sortDirection: "asc",
        pageSize: "10",
    };
    $scope.btn = {
        next: false,
        prev: false,
        last: false,
        first: false,
    }
    $scope.initialize = function () {
        $http.get("/rest/products/")
            .then(response => {
                $scope.items = response.data;
                $scope.page.pageTotal = response.headers("boolean");
                $scope.items.forEach(item => {
                    item.createDate = new Date(item.createDate)
                });
            });
        //! load categories
        $http.get("/rest/category/")
            .then(response => {
                $scope.categories = response.data;
                console.log(response.data);
            })
        $scope.page.pageIndex = 0;
        if ($scope.page.pageIndex <= 0) {
            return $scope.btn.prev = true;
        }
        // if ($scope.page.pageIndex ==0) {
        //     return $scope.btn.first = true;
        // }
        return $scope.btn.prev = false;
    }

    $scope.initialize();

    $scope.pager = {

        next() {
            var pageIndex = $scope.page.pageIndex++;
            $http.get("/rest/products/", {
                params: {
                    "pageIndex": pageIndex, "name": $scope.page.searchText, "sortBy": $scope.page.sortBy,
                    "sortDirection": $scope.page.sortDirection, "pageSize": $scope.page.pageSize
                }
            })
                .then(response => {
                    $scope.btn.prev = false;
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (pageIndex == parseInt(response.headers("boolean"))) {
                            return $scope.btn.next = true;
                        }
                    });
                });
        },
        prev() {
            let pageIndex;
            if ($scope.page.pageIndex > 0)
                pageIndex = $scope.page.pageIndex--;
            if (pageIndex == 0) {
                return $scope.btn.prev = true;
            }
            $http.get("/rest/products/", {
                params: {
                    "pageIndex": pageIndex, "name": $scope.page.searchText, "sortBy": $scope.page.sortBy,
                    "sortDirection": $scope.page.sortDirection, "pageSize": $scope.page.pageSize
                }
            })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (params == 0) {
                            return $scope.btn.prev = true;
                        }
                    });
                });
        },
        first() {
            let pageIndex = 0;
            $http.get("/rest/products/", {
                params: {
                    "pageIndex": pageIndex, "name": $scope.page.searchText, "sortBy": $scope.page.sortBy,
                    "sortDirection": $scope.page.sortDirection, "pageSize": $scope.page.pageSize
                }
            })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (pageIndex == 0) {
                            console.log(true)
                            return $scope.btn.first = true;
                        }
                    });
                });
        },
        last() {
            $scope.btn.prev = false;
            let pageIndex = $scope.page.pageTotal;
            $http.get("/rest/products/", {
                params: {
                    "pageIndex": pageIndex, "name": $scope.page.searchText, "sortBy": $scope.page.sortBy,
                    "sortDirection": $scope.page.sortDirection, "pageSize": $scope.page.pageSize
                }
            })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (pageIndex == parseInt(response.headers("boolean"))) {
                            console.log(true)
                            return $scope.dsblbtn.lastBtn = true;
                        }
                    });
                });
        }
    }
    $scope.next = function () {
        alert('next')
    }

    $scope.openSearch = function (searchText) {
        $scope.page.searchText = angular.copy(searchText)
        $http.get("/rest/products/", {
            params: {
                "pageIndex": $scope.page.pageIndex, "name": $scope.page.searchText, "sortBy": $scope.page.sortBy,
                "sortDirection": $scope.page.sortDirection, "pageSize": $scope.page.pageSize
            }
        })
            .then(response => {
                $scope.items = response.data;
                $scope.page.pageTotal = response.headers("boolean");
                if ($scope.items.length == 0) {
                    alert('Kh??ng c?? s???n ph???m c???n t??m');
                    $scope.page.searchText = "";
                } else {
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        // console.log(item);
                    });
                }
                // console.log($scope.items);
            });
        console.log($scope.page.searchText);
        console.log($scope.page.sortBy);
        console.log($scope.page.sortDirection);
    }

    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        }
    }

    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        console.log(item);
        $http.post(`/rest/products/create`, item)
            .then(response => {
                response.data.createDate = new Date(response.data.createDate);
                $scope.items.push(response.data);
                $scope.reset();
                alert('Th??m m???i th??nh c??ng!');
            })
            .catch(response => {
                alert('L???i th??m m???i th???t b???i');
                console.log("error", response);
            })
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        console.log(item.available)
        // item.available = 0;
        $http.put(`/rest/products/update/${item.id}`, item, { params: { "categoryId": item.category.id } })
            .then(response => {
                var index = $scope.items.findIndex(p => p.id == item.id);
                $scope.items[index] = item;
                alert('C???p nh???t th??nh c??ng');
            })
            .catch(response => {
                alert('L???i c???p nh???t s???n ph???m');
                console.log("error", response);
            })
    }

    $scope.delete = function (item) {
        $http.delete(`/rest/products/delete/${item.id}`)
            .then(response => {
                var index = $scope.items.findIndex(item => item.id === item.id);
                $scope.items.splice(index, 1);
                $scope.reset();
                alert('Xo?? s???n ph???m th??nh c??ng')
            })
            .catch(response => {
                alert('L???i xo?? s???n ph???m');
                console.log("error", response);
            })
    }

    $scope.imageChanged = function (files) {
        const data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        })
            .then(response => {
                $scope.form.image = response.data.name;
                console.log(response.data);
            })
            .catch(err => {
                alert('Error upload image')
                console.error(err);
            })
    }

});