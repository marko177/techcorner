// Main page functionality

// Open the product form
function openAddProductForm() {
    $('#addProductModal').modal('show');
}

// Handle form submission
document.getElementById('addProductForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Get form data
    const barcode = document.getElementById('barcode').value;
    const description = document.getElementById('description').value;
    const stock = parseInt(document.getElementById('stock').value);
    const price = parseFloat(document.getElementById('price').value);

    // Create a product object
    const product = {
        barcode: barcode,
        description: description,
        stock: stock,
        price: price
    };

    // Make POST request to add the product
    fetch('/api/products/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => response.json())
        .then(data => {
            // Handle success (e.g., display a success message or update the UI)
            console.log('Product added:', data);
            // Close the modal
            $('#addProductModal').modal('hide');
        })
        .catch(error => {
            // Handle error (e.g., display an error message)
            console.error('Error adding product:', error);
        });
});


// View Products
function viewProducts() {
    // Use fetch API to get a list of products from your backend API
    fetch('/api/products/all')
        .then(response => response.json())
        .then(data => {
            // Display the list of products (you can replace this with your desired display method)
            console.log(data);
        })
        .catch(error => console.error('Error fetching products:', error));
}

// View Cashiers
function viewCashiers() {
    // Use fetch API to get a list of cashiers from your backend API
    fetch('/api/cashiers/')
        .then(response => response.json())
        .then(data => {
            // Display the list of cashiers (you can replace this with your desired display method)
            console.log(data);
        })
        .catch(error => console.error('Error fetching cashiers:', error));
}

// Add Cashier
function addCashier() {
    // Display form or modal to add a new cashier
    // Handle form submission using fetch API
    console.log('Add cashier functionality');
}

// View Sales
function viewSales() {
    // Use fetch API to get a list of sales from your backend API
    fetch('/api/sales/')
        .then(response => response.json())
        .then(data => {
            // Display the list of sales (you can replace this with your desired display method)
            console.log(data);
        })
        .catch(error => console.error('Error fetching sales:', error));
}

// Open POS Screen
function openPOS() {
    // Logic for opening the POS screen
    console.log('Open POS screen functionality');
}
