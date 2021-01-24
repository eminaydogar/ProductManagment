$('#editModal').on('show.bs.modal', function (event) {
  var productList = $(event.relatedTarget);
  var productid = document.getElementById("productid");
  var procudtCategory = document.getElementById("category");
  var procudtCategoryType  = document.getElementById("categorytype");
  var procudtName  = document.getElementById("name");
  var procudtStatus  = document.getElementById("status");
  var procudtCount  = document.getElementById("count");
  var procudtPrice  = document.getElementById("price");
  productid.setAttribute('value' , productList.data('id'));
  procudtCategory.setAttribute('value' , productList.data('category'));
  procudtCategoryType.setAttribute('value' , productList.data('categorytype'));
  procudtName.setAttribute('value' , productList.data('name'));
  procudtStatus.value=productList.data('status');
  procudtCount.setAttribute('value' , productList.data('count'));
  procudtPrice.setAttribute('value' , productList.data('price'));
});

$('#deleteModal').on('shown.bs.modal', function (event) {
  var id = $(event.relatedTarget).data('id');
  var button = document.getElementById('delete');
  button.setAttribute('value',id);
});