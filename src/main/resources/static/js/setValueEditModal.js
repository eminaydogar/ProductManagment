$('#editModal').on('show.bs.modal', function (event) {
  var productList = event.relatedTarget.dataset;
  var productid = document.getElementById("productid");
  var procudtCategory = document.getElementById("category");
  var procudtCategoryType  = document.getElementById("categoryType");
  var procudtName  = document.getElementById("name");
  var procudtStatus  = document.getElementById("status");
  var procudtCount  = document.getElementById("count");
  var procudtPrice  = document.getElementById("price");
  productid.value = productList.id;
  procudtCategory.value = productList.category;
  procudtCategoryType.value = productList.categorytype;
  procudtName.value = productList.name;
  procudtStatus.value = productList.status;
  procudtCount.value = productList.count;
  procudtPrice.value =  productList.price;
});

$('#deleteModal').on('shown.bs.modal', function (event) {
  var id = $(event.relatedTarget).data('id');
  var button = document.getElementById('delete');
  button.setAttribute('value',id);
});
