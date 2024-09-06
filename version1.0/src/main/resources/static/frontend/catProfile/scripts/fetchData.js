document.addEventListener('DOMContentLoaded', async function () {
    try {

        const dbResponse = await fetch("/cats/numberOfCatsInDatabase");

        let dbSize = 0;
// Check if response is OK and parse it correctly
        if (dbResponse.ok) {
            // Assuming the response is JSON, parse it
            dbSize = await dbResponse.json(); // if the response type is JSON
        } else {
            console.error("Failed to fetch data:", dbResponse.statusText);
        }

        const randomInt = getRandomInteger(dbSize);
        // Fetch the cat data from the endpoint, assuming the endpoint returns the CatResponseDTO object
        const response = await fetch("/cats/" + randomInt);


        // Check if the response is successful
        if (!response.ok) {
            throw new Error(`Error while fetching cat data: ${response.status}`);
        }

        // Parse the response JSON
        const catData = await response.json();

        // Get the image format from the picture data
        const format = getImageFormat(catData.picture);

        // Set the image source and other cat data
        document.getElementById("profile-pic").src = `data:image/${format};base64,${catData.picture}`;
        document.getElementById("name").innerText = catData.name;
        document.getElementById("age").innerText = catData.age;
        document.getElementById("sex").innerText = catData.gender;
        document.getElementById("country").innerText = catData.location;
        document.getElementById("description").innerText = catData.description;

    } catch (error) {
        console.error('Error fetching the cat data:', error);
    }
});

function getRandomInteger(max) {
    return Math.floor(Math.random() * max) + 1;
}

function getImageFormat(base64String) {
    // Check for known image signatures (magic numbers) for popular formats
    if (base64String.startsWith('/9j/')) return 'jpeg';         // JPEG
    if (base64String.startsWith('iVBORw0KGgo')) return 'png';   // PNG
    if (base64String.startsWith('R0lGODdh') || base64String.startsWith('R0lGODlh')) return 'gif'; // GIF
    if (base64String.startsWith('Qk')) return 'bmp';            // BMP
    if (base64String.startsWith('UklGR')) return 'webp';        // WebP
    if (base64String.startsWith('PD94bWwg') || base64String.startsWith('PHN2Zw')) return 'svg+xml'; // SVG
    if (base64String.startsWith('AAABAAEAEBAAAAEAIABoBAAAFgAAACgAAAAgAAAAQAAAAAEA')) return 'ico'; // ICO
    // Default fallback to 'jpeg' if format is unknown or cannot be determined
    return 'jpeg';
}

async function fetchCatData() {

}