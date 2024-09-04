async function loadNavbar() {
    try {
        const response = await fetch('/nav');
        if (!response.ok) {
            throw new Error('Failed to load navbar');
        }
        const data = await response.text();
        document.getElementById('navbar-placeholder').innerHTML = data;
    } catch (error) {
        console.error('Error loading navbar:', error);
    }
}

// Load the navbar before other content is rendered
document.addEventListener('DOMContentLoaded', async function () {
    await loadNavbar();
    // You can place additional logic here that depends on the navbar being loaded
    console.log('Navbar loaded, now rendering the rest of the page');
});