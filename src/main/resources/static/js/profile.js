function updateUser(e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const name = document.getElementById('first_name').value;
    const lastName = document.getElementById('last_name').value;

    const user = {
        username: username,
        name: name,
        lastName: lastName
    };

    fetch('http://localhost:8080/api/users/update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            window.location.reload(); // Refresh the page
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

document.getElementById('saveProfileButton').addEventListener('click', updateUser);