document.getElementById('login-form').onsubmit = async function (event) {

    event.preventDefault();

    const userName = getInputValue("input-username");
    const password = getInputValue("input-password");

    await handleLogin(userName, password);
}

function getInputValue(elementId) {

    return document.getElementById(elementId).value.trim();

}

async function handleLogin(username, password) {

    try {

        const response = await postLoginData(username, password);
        await processResponse(response);

    } catch (error) {

        console.error('Error:', error);

    }
}

async function postLoginData(userName, password) {

    return fetch('/api/login', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: JSON.stringify(
            userName,
            password
        ),
        redirect: "follow"
    });
}

async function processResponse(response) {
    if (!response.ok) {
        const errorMessage = await response.text();
        displayErrorMessage(errorMessage);
        return;
    }
    const data = await response.json();
    alert('Login successful: ' + data);
    console.log("Successfully logged in!");
}

function displayErrorMessage(errorMessage) {
    switch (errorMessage) {
        case "Username or password is incorrect":
            displayError('username-error', errorMessage);
            break;
        default:
            console.error('Unexpected error:', errorMessage);
    }
}

function displayError(elementId, message) {
    document.getElementById(elementId).textContent = message;
}
