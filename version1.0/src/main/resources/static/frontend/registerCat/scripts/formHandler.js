function clearErrors() {
    ['catname-error', 'age-error', 'gender-error', 'description-error', 'location-error'].forEach(id => {
        document.getElementById(id).textContent = '';
    });
}

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
    event.preventDefault();  // Prevent default form submission
    clearErrors();  // Clear previous error messages

    // Gather input values
    const name = document.getElementById('input-catname').value.trim();
    const age = document.getElementById('input-age').value.trim();
    const gender = document.getElementById('input-gender').value.toUpperCase();
    const description = document.getElementById('input-description').value;
    const location = document.getElementById('input-location').value;

    const formData = { name, age, gender, description, location };

    try {
        // Fetch userId from the backend
        const userId = await fetchUserId();

        // Make the API request with the userId
        const response = await fetch(`/cats/${userId}/registerCat`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        });

        if (response.ok) {
            console.log('Cat registered successfully!');
        } else {
            console.error('Failed to register cat');
        }
    } catch (error) {
        console.error('Error:', error);
    }
};
