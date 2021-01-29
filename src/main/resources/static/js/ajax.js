$(document).ready(
		function() {

			// SUBMIT FORM
			$("#productForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					id : $("#productid").val(),
					category : $("#category").val(),
					categoryType : $("#categoryType").val(),
					name : $("#name").val(),
					status : $("#status").val(),
					count : $("#count").val(),
					price : $("#price").val()
				}
               console.log(JSON.stringify(formData));
				// DO POST
				$.ajax({
					type : "PUT",
					contentType : "application/json",
					url : "updateProduct",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							var productData=result.data;
							var rows = document.getElementsByClassName("productRows");
							for(var i=0; i<rows.length; i++) {
								var td = rows[i].getElementsByTagName("td");
								var id = td[0].getElementsByTagName("input")[0].value;
								if(id==productData.id){
									td[1].getElementsByTagName("input")[0].value=productData.category;
									td[2].getElementsByTagName("input")[0].value=productData.categoryType;
									td[3].getElementsByTagName("input")[0].value=productData.name;
									td[4].getElementsByTagName("input")[0].value=productData.status;
									td[5].getElementsByTagName("input")[0].value=productData.count;
									td[6].getElementsByTagName("input")[0].value=productData.price;
									var updateButton =td[7].getElementsByTagName("a")[1];
                                    updateButton.setAttribute('data-category',productData.category);
									updateButton.setAttribute('data-categorytype',productData.categoryType);
									updateButton.setAttribute('data-name',productData.name);
									updateButton.setAttribute('data-status',productData.status);
									updateButton.setAttribute('data-count',productData.count);
									updateButton.setAttribute('data-price',productData.price);
									$( '.modal' ).modal( 'hide' ).data( 'bs.modal', null );
									alert("Günceleme işlemi BAŞARILI !");
					                 break;
                                   }
									
								} 
								
							}else {
							alert("Güncelleme yapılamadı. Tekrar deneyiniz.")
						} 
						 console.log(result);
						},
					error : function(e) {
						alert("İşlem sırasında beklenmedik bir hata oluştu!")
						console.log("ERROR: ", e);
					}
				});

			}});