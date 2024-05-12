function formatCurrency(amount) {
    return new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(amount);
}

$(document).ready(function () {
    $('#saleTable').DataTable({
        dom: 'Bfrtip',
        info: false,
        paging: false,
        ordering: true,
        autoWidth: false,
        order: [[0, 'asc']],
    });
});

$('.showDetailsButton').click(function () {
    const saleId = $(this).data('id');

    // Fetch the details of the sale
    fetch('/api/sale-details/sale/' + saleId)
        .then(response => response.json())
        .then(saleDetails => {
            // Clear the table body
            $('.modal-body tbody').empty();

            // Populate the table with the sale details
            saleDetails.forEach(saleDetail => {
                $('.modal-body tbody').append(`
                    <tr>
                        <td>${saleDetail.product.description}</td>
                        <td>${saleDetail.quantity}</td>
                        <td>${formatCurrency(saleDetail.product.price)}</td>
                        <td>${formatCurrency(saleDetail.quantity * saleDetail.product.price)}</td>
                    </tr>
                `);
            });

            // Initialize the DataTable
            if (!$.fn.DataTable.isDataTable('#saleDetailsTable')) {
                $('#saleDetailsTable').DataTable({
                    dom: 'Bfrtip',
                    info: false,
                    paging: false,
                    ordering: false,
                    searching: false,
                    autoWidth: false,
                    order: [[0, 'asc']],
                });
            }

            // Open the modal
            $('#showDetailsModal').modal('show');
        });
});