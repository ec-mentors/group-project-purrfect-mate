import { validateForm, clearErrors, setupValidationListeners, displayError } from './validation.js';

// Initialize validation listeners on form load
setupValidationListeners();

// Main function to handle form submission
document.getElementById('registration-form').onsubmit = async function (event) {
    event.preventDefault(); // Prevent default form submission

    clearErrors(); // Clear previous error messages

    // Gather input values
    const username = document.getElementById('input-username').value.trim();
    const email = document.getElementById('input-email').value.trim();
    const password = document.getElementById('input-password').value;
    const repeatPassword = document.getElementById('repeat-password').value;

    // Validate form data
    if (!validateForm(username, email, password, repeatPassword)) {
        return; // Stop if validation fails
    }

    // Prepare form data
    const formData = {
        username,
        email,
        password
    };

    try {
        // Make the API request and handle response
        const response = await fetch('/api/registration', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        });

        // Check if response is not ok, and handle errors
        if (!response.ok) {
            const errorMessage = await response.text();
            handleError(errorMessage);
        }

        // Handle successful response
        const data = await response.json();
        console.log('Registration successful:', data);
        // Additional success logic can go here
    } catch (error) {
        console.error('Error:', error.message); // Log any error that occurs
    }
};

// Function to handle specific error messages
function handleError(errorMessage) {
    switch (errorMessage) {
        case "Username already taken":
            displayError('username-error', errorMessage);
            break;
        case "Email already registered":
            displayError('email-error', errorMessage);
            break;
        default:
            console.error('Unexpected error:', errorMessage);
            break;
    }
}