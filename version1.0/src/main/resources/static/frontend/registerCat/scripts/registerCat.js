import { healthAttributesList, initHealthInput } from './getHealthAttributes.js';

// Initialize the health input field functionality
initHealthInput();

async function fetchUserId() {
    const response = await fetch('/users/me');
    if (response.ok) {
        const user = await response.json();
        console.log(user);  // Log the entire response object
        // alert(user);  // Check the entire response in the console
        return user.id;
    } else {
        throw new Error('Failed to fetch user information');
    }
}

document.getElementById('cat-form').onsubmit = async function(event) {

    event.preventDefault();

    const name = document.getElementById('input-catname').value.trim();
    const age = document.getElementById('input-age').value.trim();
    const gender = document.getElementById('input-gender').value;
    const description = document.getElementById('input-description').value;
    const location = document.getElementById('input-location').value;

    const isUpForAdoption = document.getElementById('input-adoption-yes').checked;
    const isNeutered = document.getElementById('input-neuter-yes').checked;
    const isOutdoorCat = document.getElementById('input-outdoor-yes').checked;

    // save health attributes from getHealthAttributes function to another variable
    const healthAttributes = [...healthAttributesList];
    // console.log('Health Attributes:', healthAttributes);

    const catData = {
        name,
        age,
        gender,
        description,
        location,
        isUpForAdoption,
        isNeutered,
        isOutdoorCat,
        healthAttributes
    };

    const fileInput = document.getElementById('file-selector');
    const file = fileInput.files[0];  // Assuming you select only one file

    const formData = new FormData();
    formData.append('file', file);  // Append the file
    formData.append('cat', new Blob([JSON.stringify(catData)], { type: "application/json" }));  // append the cat JSON

    // for (const [key, value] of formData.entries()) {
    //     if (value instanceof Blob) {
    //         value.text().then(text => console.log(`${key}:`, text));
    //     } else {
    //         console.log(`${key}:`, value);
    //     }
    // }

    try {
        // Fetch userId from the backend
        const userId = await fetchUserId();

        // Make the API request with the userId
        const response = await fetch(`/cats/${userId}/registerCat`, {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            console.log('Cat registered successfully!');
            // window.location.href = "/yourCollection"
        } else {
            console.error('Failed to register cat');
        }
    } catch (error) {
        console.error('Error:', error);
    }
};