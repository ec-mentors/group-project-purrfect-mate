document.addEventListener('DOMContentLoaded', async function () {
    try {

        const numberOfCatsResponse = await fetch("/cats/numberOfCatsInDatabase");

        const dbSize = await getDbSize();

        const catProfileResponse = await fetch("/cats/" + getRandomInteger(dbSize));
        const catData = await catProfileResponse.json();
        const format = getImageFormat(catData.picture);

        fillElements(catData, format);


    } catch (error) {
        console.error('Error fetching the cat data:', error);
    }
});

function getRandomInteger(max) {
    return Math.floor(Math.random() * max) + 1;
}

function getImageFormat(base64String) {

    const header = base64String.slice(0, 12);

    switch (true) {

        case header.startsWith('/9j/'):
            return 'jpeg';

        case header.startsWith('iVBORw0KGgo'):
            return 'png';

        case header.startsWith('R0lGODdh') || header.startsWith('R0lGODlh'):
            return 'gif';

        case header.startsWith('Qk'):
            return 'bmp';

        case header.startsWith('UklGR'):
            return 'webp';

        case header.startsWith('PD94bWwg') || header.startsWith('PHN2Zw'):
            return 'svg+xml';

        case header.startsWith('AAABAAEAEBAAAAEAIABoBAAAFgAAACgAAAAgAAAAQAAAAAEA'):
            return 'ico';
        default:
            return 'jpeg';
    }
}

function fillElements(catData, format) {

    document.getElementById("profile-pic").src = `data:image/${format};base64,${catData.picture}`;
    document.getElementById("name").innerText = catData.name;
    document.getElementById("age").innerText = catData.age;
    document.getElementById("sex").innerText = catData.gender;
    document.getElementById("country").innerText = catData.location;
    document.getElementById("description").innerText = catData.description;

}

async function getDbSize() {

    const numberOfCatsResponse = await fetch("/cats/numberOfCatsInDatabase");

    if (numberOfCatsResponse.ok) {
        return await numberOfCatsResponse.json();
    } else {
        console.error("Failed to fetch data:", numberOfCatsResponse.statusText);
    }
}