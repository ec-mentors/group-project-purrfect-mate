import { validateForm, clearErrors, setupValidationListeners } from './validation.js';
import { submitForm } from './api.js';

// Setup validation listeners for immediate feedback on user input
setupValidationListeners();

// Event listener for form submission
document.getElementById('registration-form').onsubmit = function(event) {
    event.preventDefault(); // Prevent default form submission

    clearErrors(); // Clear any existing errors

    // Gather input values
    const username = document.getElementById('input-username').value.trim();
    const email = document.getElementById('input-email').value.trim();
    const password = document.getElementById('input-password').value;
    const repeatPassword = document.getElementById('repeat-password').value;

    // Validate the form and submit if valid
    if (validateForm(username, email, password, repeatPassword)) {
        const formData = { username, email, password }; // Prepare form data
        submitForm(formData); // Submit form data
    }
};