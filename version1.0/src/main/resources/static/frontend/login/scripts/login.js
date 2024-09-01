document.getElementById('login-form').onsubmit = async function (event) {

    const userName = document.getElementById("input-username").value.trim();
    const password = document.getElementById("input-password").value.trim();

}

async function postLoginData(loginData) {

    try {
        // Make the API request and handle response
        const response = await fetch('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginData),
        });

        // Check if response is not ok, and handle errors
        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage);
        }

        // Handle successful response
        const data = await response.json();
        alert('Login successful: ' +  data);
        // Additional success logic can go here
    } catch (error) {
        console.error('Error:', error.message); // Log any error that occurs
    }
}