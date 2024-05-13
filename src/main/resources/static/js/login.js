$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();

        let username = $('#username').val();
        let password = $('#password').val();

        // Check if both fields are filled in
        if (!username || !password) {
            alert('Please enter both username and password');
            return false;
        }

        $.ajax({
            url: '/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ username: username, password: password }),
            success: function(response) {
                // Handle successful login here, e.g. redirecting to another page
                window.location.href = '/'; // Redirect to home page
            },
            error: function(response) {
                // Handle failed login here, e.g. showing an error message
                alert('Invalid username or password');
            }
        });
        return false;
    });
});