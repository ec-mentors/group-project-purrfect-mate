// Load the navbar before other content is rendered
document.addEventListener('DOMContentLoaded', async function () {
    await loadNavbar();
    await changeNavIfLoggedIn();
    // You can place additional logic here that depends on the navbar being loaded
    console.log('Navbar loaded, now rendering the rest of the page');
});

// Function to load the navbar from the server
async function loadNavbar() {
    try {
        const response = await fetch('/nav');
        if (!response.ok) {
            console.log("Nav loaded successfully...");
        }
        document.getElementById('navbar-placeholder').innerHTML = await response.text();
    } catch (error) {
        console.error('Error loading navbar:', error);
    }
}

// Function to change the navbar login link if the user is logged in
async function changeNavIfLoggedIn() {
    if (await isLoggedIn()) {
        const navLogin = document.getElementById('nav-login');
        if (navLogin) {
            // Update the login link to a profile link
            navLogin.innerHTML = '<a class="nav-link text-decoration-none hover-grow" href="/perform_logout">Logout</a>';
        } else {
            console.warn('Navigation login element not found.');
        }
    }
}

async function isLoggedIn() {

    try {
        const response = await fetch('/auth/status', {
            method: 'GET',
            credentials: 'include' // Include session cookies for authentication check
        });

        // Only return true if the status is 200
        return response.status === 200;
    } catch (error) {
        console.error('Error checking login status:', error);
        return false;
    }
}