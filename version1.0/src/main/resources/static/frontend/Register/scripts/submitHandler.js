document.getElementById('registration-form').onsubmit = function(event) {

    event.preventDefault(); // Stop the form from reloading the page

    const usernameString = document.getElementById('input-username').value;
    const emailString = document.getElementById('input-email').value;
    const passwordString = document.getElementById('input-password').value;

    // Get the values from the form inputs
    const formData = {
        username: usernameString,
        email: emailString,
        password: passwordString
    };

    console.log(JSON.stringify(formData));

    // Send the inputs to the server
    fetch('/api/registration', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
    })
        .then(response => response.json()) // Get the JSON response from the server
        .catch(error => {
            console.error(error);
            alert('There was a problem submitting the form.');
        });
};