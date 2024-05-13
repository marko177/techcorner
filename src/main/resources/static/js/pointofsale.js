function updateTotals() {
    let subtotal = 0;
    $('#dataTable tbody tr').each(function() {
        const price = parseFloat($(this).find('td').eq(2).text().replace('$', ''));
        const quantity = parseFloat($(this).find('input[name="Quantity"]').val());
        const total = price * quantity;
        $(this).find('td').eq(4).text(`$${total.toFixed(2)}`);
        subtotal += total;
    });

    const iva = subtotal * 0.16;
    const total = subtotal + iva;

    $('.subtotal').text(`$${subtotal.toFixed(2)}`);
    $('.iva').text(`$${iva.toFixed(2)}`);
    $('.total').text(`$${total.toFixed(2)}`);

    let payButton = $('#pay-button');
    if (total === 0) {
        payButton.prop('disabled', true);
        payButton.addClass('btn-disabled');
    } else {
        payButton.prop('disabled', false);
        payButton.removeClass('btn-disabled');
    }
}


$(document).ready(function() {

    $('#payment-input').val('');
    const selectInput = $('#search-input');
    selectInput.append(new Option('', ''));
    fetch('/api/products/all')
        .then(response => response.json())
        .then(products => {
            products.forEach(product => {
                let productLabel = product.sku + ' - ' + product.description;
                if (product.stock === 0) {
                    productLabel += ' (Out of stock)';
                }
                selectInput.append(new Option(productLabel, product.sku));
            });
            selectInput.select2({
                placeholder: 'Search item',
                allowClear: true
            });
        });

    $('#add-to-items').click(function() {
        const selectedProduct = $('#search-input').val();
        if (selectedProduct) {
            fetch(`/api/products/search/${selectedProduct}`)
                .then(response => response.json())
                .then(product => {
                    if (product.stock === 0) {
                        alert('The selected product is out of stock');
                        return;
                    }

                    const quantityInput = parseFloat($('#quantity-input').val());
                    if (quantityInput > product.stock) {
                        alert('The quantity entered is more than the available stock');
                        return;
                    }

                    const table = $('#dataTable tbody'); // Select the table body
                    const formattedPrice = `$${product.price.toFixed(2)}`; // Format the product price
                    const row = $(`<tr>
                    <td>${product.sku}</td>
                    <td>${product.description}</td>
                    <td>${formattedPrice}</td>
                    <td><input type="number" class="quantity-input" style="width: 50px;" min="1" value="1" name="Quantity"></td>
                    <td>${formattedPrice}</td>
                    <td><i class="fas fa-trash-alt delete-icon clickable-icon"></i></td>
                 </tr>`);
                    row.data('product', product);
                    table.append(row);
                    updateTotals();
                    $('.quantity-input').change(function() {
                        const price = parseFloat($(this).closest('tr').find('td').eq(2).text().replace('$', ''));
                        const quantity = parseFloat($(this).val());
                        if (quantity > product.stock) {
                            alert('The quantity entered is more than the available stock');
                            $(this).val(product.stock);
                            return;
                        }
                        const total = price * quantity;
                        $(this).closest('tr').find('td').eq(4).text(`$${total.toFixed(2)}`);
                        updateTotals();
                    });
                    $('.delete-icon').click(function() {
                        $(this).closest('tr').remove();
                        updateTotals();
                    });
                });
            $('#search-input').val('').trigger('change');
        }
    });

    $('#pay-button').click(function() {
        const paymentInput = $('#payment-input').val();
        if (!paymentInput) {
            alert('Please enter a payment amount');
            return;
        }

        const userId = $('#userId').val();
        console.log('User ID:', userId);
        const subtotalAmount = parseFloat($('.subtotal').text().replace('$', ''));
        const taxAmount = parseFloat($('.iva').text().replace('$', ''));
        const totalAmountText = $('.total').text().replace('$', '').trim();
        const totalAmount = totalAmountText ? parseFloat(totalAmountText) : 0;
        const paymentAmount = isNaN(paymentInput) ? 0 : parseFloat(paymentInput);
        const changeAmount = paymentAmount - totalAmount;

        if (paymentAmount >= totalAmount) {
            let sale = {
                userId: userId,
                preTaxTotal: subtotalAmount,
                tax: taxAmount,
                total: totalAmount,
                payment: paymentAmount,
                date: new Date(),
            };

            let saleDetails = [];
            $('#dataTable tbody tr').each(function() {
                const product = $(this).data('product');
                const quantity = parseFloat($(this).find('input[name="Quantity"]').val());

                saleDetails.push({
                    product: product,
                    quantity: quantity,
                });
            });

            sale.saleDetails = saleDetails;

            // Save the sale to the database
            fetch('/api/sales/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(sale),
            })
                .then(response => response.json())
                .then(data => {
                    alert('Sale successful! Change: $' + changeAmount.toFixed(2));
                    console.log('Sale saved successfully:', data);
                    location.reload();
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        } else {
            alert('Payment amount is less than the total amount');
        }
    });
});