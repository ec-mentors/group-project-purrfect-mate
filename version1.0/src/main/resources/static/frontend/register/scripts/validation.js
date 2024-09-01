// Function to validate the entire form fields
export function validateForm(username, email, password, repeatPassword) {
    let isValid = true;

    // Validate each field using dedicated functions
    if (!validateUsername(username)) {
        isValid = false;
    }
    if (!validateEmail(email)) {
        isValid = false;
    }
    if (!validatePassword(password, username)) {
        isValid = false;
    }
    if (!validateRepeatPassword(password, repeatPassword)) {
        isValid = false;
    }

    return isValid;
}

// Validate username
function validateUsername(username) {
    if (username === "") {
        displayError('username-error', 'Username is required');
        return false;
    }
    return true;
}

// Validate email using a basic pattern
function validateEmail(email) {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        displayError('email-error', 'Invalid email address');
        return false;
    }
    return true;
}

// Validate password with specific requirements
function validatePassword(password, username) {
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordPattern.test(password)) {
        displayError('password-error', 'Password must be at least 8 characters long and include uppercase, lowercase, number, and a special character');
        return false;
    } else if (/\s/.test(password)) {
        displayError('password-error', 'Password must not contain spaces');
        return false;
    } else if (password === username) {
        displayError('password-error', 'Username and password must be different');
        return false;
    }
    return true;
}

// Validate repeat password to match the password
function validateRepeatPassword(password, repeatPassword) {
    if (password !== repeatPassword) {
        displayError('repeat-password-error', 'Passwords do not match');
        return false;
    }
    return true;
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

    usernameElement.addEventListener('input', () => clearErrorsOnInput('username-error', validateUsername(usernameElement.value.trim())));
    emailElement.addEventListener('input', () => clearErrorsOnInput('email-error', validateEmail(emailElement.value)));
    passwordElement.addEventListener('input', () => clearErrorsOnInput('password-error', validatePassword(passwordElement.value, usernameElement.value)));
    repeatPasswordElement.addEventListener('input', () => clearErrorsOnInput('repeat-password-error', validateRepeatPassword(passwordElement.value, repeatPasswordElement.value)));
}

// Helper to clear specific error on valid input
function clearErrorsOnInput(elementId, condition) {
    if (condition) document.getElementById(elementId).textContent = '';
}