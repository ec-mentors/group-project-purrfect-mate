import { validateForm, clearErrors, setupValidationListeners, displayError, validateRecaptcha } from './validation.js';

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
    if (!validateForm(username, email, password, repeatPassword) || !validateRecaptcha()) {
        return; // Stop if validation or reCAPTCHA check fails
    }

    // Prepare form data
    const formData = {
        username,
        email,
        password,
    };

    try {
        // Make the registration API request and handle response
        const registrationResponse = await fetch('/api/registration', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        });

        // Check if response is not ok, and handle errors
        if (!registrationResponse.ok) {
            const errorMessage = await registrationResponse.text();
            handleError(errorMessage);
            return;
        }

        // Automatically log in the user after successful registration
        const loginResponse = await fetch('/perform_login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({
                username,
                password,
            }),
        });

        // Check if login response is not ok and handle errors
        if (!loginResponse.ok) {
            handleError('Auto-login failed'); // You can customize this message as needed
            return;
        }

        // Redirect to the home page or the desired landing page after successful login
        window.location.href = '/home'; // Change this to your desired route

    } catch (error) {
        console.error('Error:', error.message); // Log any error that occurs
        handleError('An unexpected error occurred. Please try again.');
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
        case "Auto-login failed":
            displayError('password-error', 'Auto-login failed. Please log in manually.');
            break;
        default:
            console.error('Unexpected error:', errorMessage);
            displayError('general-error', 'An unexpected error occurred.');
            break;
    }
}

