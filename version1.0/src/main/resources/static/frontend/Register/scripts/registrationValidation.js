
const usernameElement = document.getElementById('input-username');
const emailElement = document.getElementById('input-email');
const passwordElement = document.getElementById('input-password');
const repeatPasswordElement = document.getElementById('repeat-password');


document.getElementById('registration-form').addEventListener('submit', function (e) {
    e.preventDefault();

    // Clear all errors first
    clearErrors();

    // Validate each field
    let isValid = true;

    const username = usernameElement.value;
    const email = emailElement.value;
    const password = passwordElement.value;
    const repeatPassword = repeatPasswordElement.value;

    // Username validation
    if (username.trim() === "") {
        displayError('username-error', 'Username is required');
        isValid = false;
    }

    // Email validation ("simple" regex that checks if the input is structured like an email: something@something.something)
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        displayError('email-error', 'Invalid email address');
        isValid = false;
    }

    // Password validation
    if (password.length < 6) {
        displayError('password-error', 'Password must be at least 6 characters long');
        isValid = false;
    }
    if (password == username) {
        displayError("password-error", "Username and password must be different");
    }

    // Confirm password validation
    if (repeatPassword !== password) {
        displayError('repeat-password-error', 'Passwords do not match');
        isValid = false;
    }

    // Submit the form if all validations pass
    if (isValid) {
        this.submit();
    }
});

function displayError(elementId, message) {
    document.getElementById(elementId).textContent = message;
}

function clearErrors() {
    document.getElementById('username-error').textContent = '';
    document.getElementById('email-error').textContent = '';
    document.getElementById('password-error').textContent = '';
    document.getElementById('repeat-password-error').textContent = '';
}

// Validate fields on input to remove errors as the user corrects them

usernameElement.addEventListener('input', function() {
    if (this.value.trim() !== "") {
        document.getElementById('username-error').textContent = '';
    }
});

emailElement.addEventListener('input', function() {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (emailPattern.test(this.value)) {
        document.getElementById('email-error').textContent = '';
    }
});

passwordElement.addEventListener('input', function() {
    if (this.value.length >= 6) {
        document.getElementById('password-error').textContent = '';
    }
});

repeatPasswordElement.addEventListener('input', function() {
    if (this.value === document.getElementById('input-password').value) {
        document.getElementById('repeat-password-error').textContent = '';
    }
});