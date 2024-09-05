document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');

    if (error === 'true') {
        displayErrorMessage('Username or password is incorrect');
    }
});

function displayErrorMessage(message) {
    // Display error message below the username input
    const errorElement = document.getElementById('password-error');
    errorElement.textContent = message;
    errorElement.classList.add('d-block'); // Ensure the message is visible
}
