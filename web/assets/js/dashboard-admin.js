document.addEventListener("DOMContentLoaded", function () {
    const btnProducts = document.getElementById("btnProducts");
    const btnCategories = document.getElementById("btnCategories");
    const dashboardProducts = document.getElementById("dashboard-products");
    const dashboardCategories = document.getElementById("dashboard-categories");

    // Exibir produtos por padrão
    dashboardProducts.style.display = "block";
    dashboardCategories.style.display = "none";

    btnProducts.addEventListener("click", function () {
        dashboardProducts.style.display = "block";
        dashboardCategories.style.display = "none";
    });

    btnCategories.addEventListener("click", function () {
        dashboardProducts.style.display = "none";
        dashboardCategories.style.display = "block";
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
