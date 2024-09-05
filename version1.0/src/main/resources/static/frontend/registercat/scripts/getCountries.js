// Make sure the DOM is fully loaded before running the script
document.addEventListener('DOMContentLoaded', function() {
    const countryDatalist = document.getElementById('countries');

    // Check if the element exists
    if (!countryDatalist) {
        console.error("Datalist element with id 'countries' not found");
        return;
    }

    // Fetch the country list from the RestCountries API
    fetch('https://restcountries.com/v3.1/all')
        .then(response => response.json())
        .then(data => {
            data.forEach(country => {
                const option = document.createElement('option');
                option.value = country.name.common; // Country name
                countryDatalist.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching country list:', error));
});