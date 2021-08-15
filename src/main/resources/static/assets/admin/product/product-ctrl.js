app.controller("product-ctrl", function ($scope, $http) {
    $scope.items = [];
    $scope.categories = [];
    $scope.form = {};
    $scope.tab = {};
    $scope.initialize = function () {
        //! load products
        $http.get("/rest/products/")
            .then(response => {
                $scope.items = response.data;
                // console.log(response.data);
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
    }

    $scope.initialize();

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
        $http.put(`/rest/products/update/${item.id}`, item, {params:{"categoryId": item.category.id}})
        .then(response =>{
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert('Cập nhật thành công');
        })
        .catch(response => {
            alert('Lỗi cập nhật sản phẩm');
            console.log("error", response);
        })
    }

    $scope.delete = function () {

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