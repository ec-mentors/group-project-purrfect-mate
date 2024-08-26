document.getElementById('registration-form').onsubmit = function(event) {
    event.preventDefault(); // Stop the form from reloading the page

    // Get the values from the form inputs
    const formData = JSON.stringify({
        username: document.getElementById('input-username').value,
        email: document.getElementById('input-email').value,
        password: document.getElementById('input-password').value,
        confirmPassword: document.getElementById('repeat-password').value
    });

    // Send the inputs to the server
    fetch('/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: formData
    })
        .then(response => response.json()) // Get the JSON response from the server
        .then(data => {
            alert(data.message); // Show a simple message with the server's response
        })
        .catch(error => {
            alert('There was a problem submitting the form.');
            console.error(error);
        });
};