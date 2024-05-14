$(document).ready(function () {

    $('#dataTable').DataTable({
        dom: 'Bfrtip',
        info: false,
        paging: false,
        ordering: true,
        autoWidth: false,
        order: [[0, 'asc']],
    });

    $('#addProductButton').click(function () {
        $('#addProductModal').modal('show');
    });
});

// handle search datatable
$(document).ready(function () {
    $('#searchInput').on('keyup', function () {
        $('#dataTable').DataTable().search(this.value).draw();
    });
});

// Handle add product form submission
document.getElementById('addProductForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent default form submission

    // Get form data
    const sku = document.getElementById('sku').value;
    const description = document.getElementById('description').value;
    const category = document.getElementById('category').value;
    const stock = parseInt(document.getElementById('stock').value);
    const price = parseFloat(document.getElementById('price').value);
    const image = document.getElementById('image').value;

    // Create a product object
    const product = {
        sku: sku,
        description: description,
        category: category,
        stock: stock,
        price: price,
        image: image
    };

    fetch('/api/products/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // Handle success (e.g., display a success message or update the UI)
            console.log('Product added:', data);
            alert('Product added successfully');
            // Close the modal
            $('#addProductModal').modal('hide');

            // reset form and reload the page
            document.getElementById('addProductForm').reset();
            location.reload();

        })
        .catch(error => {
            // Handle error (e.g., display an error message)
            console.error('Error adding product:', error);
            alert('Error adding product');
        });
});

$('.editProductButton').click(function () {
    const id = $(this).data('id');

    // Fetch the current data of the product
    fetch('/api/products/' + id)
        .then(response => response.json())
        .then(product => {
            // Fill the input fields with the current values of the product
            $('#editId').val(product.id);
            $('#editSku').val(product.sku);
            $('#editDescription').val(product.description);
            $('#editCategory').val(product.category);
            $('#editStock').val(product.stock);
            $('#editPrice').val(product.price);
            $('#editImage').val(product.image);

            // Open the edit modal
            $('#editProductModal').modal('show');
        });

});

// Handle form submission of the edit modal
document.getElementById('editProductForm').addEventListener('submit', function (event) {
    event.preventDefault();

    // Get form data
    const id = document.getElementById('editId').value;
    const sku = document.getElementById('editSku').value;
    const description = document.getElementById('editDescription').value;
    const category = document.getElementById('editCategory').value;
    const stock = parseInt(document.getElementById('editStock').value);
    const price = parseFloat(document.getElementById('editPrice').value);
    const image = document.getElementById('editImage').value;

    // Create a product object
    const product = {
        id: id,
        sku: sku,
        description: description,
        category: category,
        stock: stock,
        price: price,
        image: image
    };

    // Make PUT request to update the product
    fetch('/api/products/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Product updated:', data);
            alert('Product updated successfully');
            $('#editProductModal').modal('hide');

            // Reload the page to show the updated product
            location.reload();
        })
        .catch(error => {
            console.error('Error updating product:', error);
            alert('Error updating product');
        });
});

$(document).on('click', '#deleteProductButton', function () {
    const id = document.getElementById('editId').value;
    const description = document.getElementById('editDescription').value;

    console.log('Delete product:', id, description);

    // Display a confirmation dialog
    if (confirm('Are you sure you want to delete the product ' + description + '?')) {
        // Make a DELETE request to delete the product
        fetch('/api/products/delete/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log('Product deleted:', data);
                alert('Product deleted successfully');

                // Reload the page to show the updated product list
                location.reload();
            })
            .catch(error => {
                console.error('Error deleting product:', error);
                alert('Error deleting product');
            });
    }
    console.log('after delete product');
});