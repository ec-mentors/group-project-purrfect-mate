// Function to validate the form fields
export function validateForm(username, email, password, repeatPassword) {
    let isValid = true;

    // Username validation
    if (username === "") {
        displayError('username-error', 'Username is required');
        isValid = false;
    }

    // Email validation using a basic pattern
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        displayError('email-error', 'Invalid email address');
        isValid = false;
    }

    // Improved Password validation
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordPattern.test(password)) {
        displayError('password-error', 'Password must be at least 8 characters long and include uppercase, lowercase, number, and a special character');
        isValid = false;
    } else if (/\s/.test(password)) {
        displayError('password-error', 'Password must not contain spaces');
        isValid = false;
    } else if (password === username) {
        displayError('password-error', 'Username and password must be different');
        isValid = false;
    }

    // Confirm password validation
    if (password !== repeatPassword) {
        displayError('repeat-password-error', 'Passwords do not match');
        isValid = false;
    }

    return isValid;
}

// Function to display error messages
export function displayError(elementId, message) {
    document.getElementById(elementId).textContent = message;
}

// Function to clear all error messages
export function clearErrors() {
    ['username-error', 'email-error', 'password-error', 'repeat-password-error'].forEach(id => {
        document.getElementById(id).textContent = '';
    });
}

// Function to set up input event listeners to clear errors on valid input
export function setupValidationListeners() {
    const usernameElement = document.getElementById('input-username');
    const emailElement = document.getElementById('input-email');
    const passwordElement = document.getElementById('input-password');
    const repeatPasswordElement = document.getElementById('repeat-password');

    usernameElement.addEventListener('input', () => clearErrorsOnInput('username-error', usernameElement.value.trim() !== ""));
    emailElement.addEventListener('input', () => clearErrorsOnInput('email-error', /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(emailElement.value)));
    passwordElement.addEventListener('input', () => clearErrorsOnInput('password-error', passwordElement.value.length >= 6));
    repeatPasswordElement.addEventListener('input', () => clearErrorsOnInput('repeat-password-error', repeatPasswordElement.value === passwordElement.value));
}

// Helper to clear specific error on valid input
function clearErrorsOnInput(elementId, condition) {
    if (condition) document.getElementById(elementId).textContent = '';
}