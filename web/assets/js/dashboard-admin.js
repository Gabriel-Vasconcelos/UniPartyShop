document.addEventListener("DOMContentLoaded", function () {
    const btnProducts = document.getElementById("btnProducts");
    const btnCategories = document.getElementById("btnCategories");
    const btnSales = document.getElementById("btnSales");

    const dashboardProducts = document.getElementById("dashboard-products");
    const dashboardCategories = document.getElementById("dashboard-categories");
    const dashboardSales = document.getElementById("dashboard-sales");

    dashboardProducts.style.display = "block";
    dashboardCategories.style.display = "none";

    btnProducts.addEventListener("click", function () {
        dashboardProducts.style.display = "block";
        dashboardCategories.style.display = "none";
        dashboardSales.style.display = "none";
    });

    btnCategories.addEventListener("click", function () {
        dashboardProducts.style.display = "none";
        dashboardCategories.style.display = "block";
        dashboardSales.style.display = "none";
    });

    btnSales.addEventListener("click", function () {
        dashboardProducts.style.display = "none";
        dashboardCategories.style.display = "none";
        dashboardSales.style.display = "block";
    });
});

function deleteCategory(contextPath, categoryId) {
    if (confirm('Tem certeza de que deseja excluir esta categoria?')) {
        fetch(contextPath + '/admin/delete-category?id=' + categoryId, {
            method: 'POST'
        }).then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Falha ao excluir a categoria.');
            }
        });
    }
}

function deleteProduct(contextPath, productId) {
    if (confirm('Tem certeza de que deseja excluir este produto??')) {
        fetch(contextPath + '/admin/delete-product?id=' + productId, {
            method: 'POST'
        }).then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Falha ao excluir o produto.');
            }
        });
    }
}

function deleteSale(contextPath, saleId, productId) {
    if (confirm('Tem certeza de que deseja excluir esta venda??')) {
        fetch(contextPath + '/admin/delete-sale-products?saleId=' + saleId + '&productId=' + productId, {
            method: 'POST'
        }).then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('Falha ao excluir a venda.');
            }
        });
    }
}
