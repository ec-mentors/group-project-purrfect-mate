import {displayError} from "./validation.js";

// Function to make the API request
function postFormData(url, formData) {
    return fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
    });
}

// Function to handle the response
function handleResponse(response) {
    if (!response.ok) {
        // Return a promise that handles the error text
        return response.text().then(errorMessage => {
            handleError(response, errorMessage); // Handle specific error messages
            throw new Error(errorMessage); // Throw error to be caught in catch block
        });
    }
    return response.json(); // Parse response JSON if successful
}

// Function to handle different error messages
function handleError(response, errorMessage) {
    if (errorMessage === "Username already taken") {
        displayError('username-error', errorMessage); // Show specific error for username
    } else if (errorMessage === "Email already registered")
        {
        displayError('email-error', 'Email already registered'); // Generic error display
    }
}

// Function to handle successful registration
function handleSuccess(data) {
    console.log('Registration successful:', data); // Handle success
    // Additional success logic can go here
}

// Main function to submit the form
export function submitForm(formData) {
    postFormData('/api/registration', formData)
        .then(handleResponse)
        .then(handleSuccess)
        .catch(error => {
            console.error('Error:', error.message); // Log error
        });
}