
document.getElementById('login-form').onsubmit = async function (event) {

    event.preventDefault();

    const userName = document.getElementById("input-username").value.trim();
    const password = document.getElementById("input-password").value.trim();

    const loginData = {
        userName,
        password
    }

    await postLoginData(loginData);

}

async function postLoginData(loginData) {

    try {
        // Make the API request and handle response
        const response = await fetch('/api/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(loginData),
        });

        // Check if response is not ok, and handle errors
        if (!response.ok) {
            const errorMessage = await response.text();
            handleError(errorMessage);
        }

        // Handle successful response
        const data = await response.json();
        console.log("Successfully logged in!")
        alert('Login successful: ' + data);
        // Additional success logic can go here
    } catch (error) {
        console.error('Error:', error.message); // Log any error that occurs
    }

    function displayError(elementId, message) {
        document.getElementById(elementId).textContent = message;
    }

    function handleError(errorMessage) {
        switch (errorMessage) {
            case "Username or password is incorrect":
                displayError('username-error', errorMessage);
                break;
            default:
                console.error('Unexpected error:', errorMessage);
                break;
        }
    }
}
