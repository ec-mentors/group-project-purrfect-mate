document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');

    if (error === 'true') {
        displayErrorMessage('Username or password is incorrect');
    }

    // Form submission validation
    const form = document.getElementById('login-form');
    form.addEventListener('submit', function (event) {
        // const recaptchaResponse = grecaptcha.getResponse();
        // if (!recaptchaResponse) {
        //     displayRecaptchaError('Please complete the reCAPTCHA to proceed.');
        //     event.preventDefault(); // Prevent form submission if reCAPTCHA is not completed
        // }
    });
});

// Function to display error message below the password input
function displayErrorMessage(message) {
    const errorElement = document.getElementById('password-error');
    errorElement.textContent = message;
    errorElement.classList.add('d-block'); // Ensure the message is visible
}

// Function to display error message below the reCAPTCHA
function displayRecaptchaError(message) {
    const recaptchaErrorElement = document.getElementById('recaptcha-error');
    recaptchaErrorElement.textContent = message;
    recaptchaErrorElement.classList.add('d-block'); // Ensure the message is visible
}