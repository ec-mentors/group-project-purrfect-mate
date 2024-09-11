document.addEventListener('DOMContentLoaded', function() {
    const inputLocation = document.getElementById('input-location');
    const citiesDatalist = document.getElementById('cities');

    if (!inputLocation || !citiesDatalist) {
        console.error("Input or Datalist element not found");
        return;
    }

    inputLocation.addEventListener('input', function() {
        const query = inputLocation.value.trim();

        if (query.length < 3) {
            // Clear suggestions if input is less than 3 characters
            citiesDatalist.innerHTML = '';
            return;
        }

        // Fetch cities matching the query from Nominatim OpenStreetMap API
        fetch(`https://nominatim.openstreetmap.org/search?city=${query}&format=json&limit=10`)
            .then(response => response.json())
            .then(data => {
                citiesDatalist.innerHTML = ''; // Clear existing options

                if (data.length === 0) {
                    console.log('No cities found for query:', query);
                    return;
                }

                data.forEach(city => {
                    const option = document.createElement('option');
                    option.value = `${city.display_name}`; // Display the full location
                    citiesDatalist.appendChild(option);
                });
            })
            .catch(error => console.error('Error fetching city list:', error));
    });
});
