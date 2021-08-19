app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.categories = [];
    $scope.form = {};
    $scope.tab = {};
    $scope.page = {
        pageIndex: 0,
        pageTotal: 0
    };
    $scope.btn = {
        next: false,
        prev: false,
        last: false,
        first: false,
    }
    $scope.initialize = function () {
        //! load products
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
        $scope.page.pageIndex = 1;
    }

    $scope.initialize();

    $scope.pager = {
        next() {
            var params = $scope.page.pageIndex++;
            $http.get("/rest/products/", { params: { "pageIndex": params } })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (params == parseInt(response.headers("boolean"))) {
                            console.log(true)
                            return $scope.btn.next = true;
                        }
                    });
                });
        },
        prev() {
            let params = $scope.page.pageIndex--;
            $http.get("/rest/products/", { params: { "pageIndex": params } })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (params == 0) {
                            console.log(true)
                            return $scope.btn.prev = true;
                        }
                    });
                });
        },
        first() {
            let params = 0;
            $http.get("/rest/products/", { params: { "pageIndex": params } })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (params == 0) {
                            console.log(true)
                            return $scope.btn.first = true;
                        }
                    });
                });
        },
        last() {
            let params = $scope.page.pageTotal;
            $http.get("/rest/products/", { params: { "pageIndex": params } })
                .then(response => {
                    $scope.items = response.data;
                    $scope.items.forEach(item => {
                        item.createDate = new Date(item.createDate)
                        if (params == parseInt(response.headers("boolean"))) {
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

    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        }
    }

    $scope.edit = function (item) {
        $scope.form = angular.copy(item);
        // console.log(this.form);
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
                alert('Thêm mới thành công!');
            })
            .catch(response => {
                alert('Lỗi thêm mới thất bại');
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
                alert('Cập nhật thành công');
            })
            .catch(response => {
                alert('Lỗi cập nhật sản phẩm');
                console.log("error", response);
            })
    }

    $scope.delete = function (item) {
        $http.delete(`/rest/products/delete/${item.id}`)
            .then(response => {
                var index = $scope.items.findIndex(item => item.id === item.id);
                $scope.items.splice(index, 1);
                $scope.reset();
                alert('Xoá sản phẩm thành công')
            })
            .catch(response => {
                alert('Lỗi xoá sản phẩm');
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