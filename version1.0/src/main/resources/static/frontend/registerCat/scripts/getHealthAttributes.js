// Exporting the list and necessary functions to another file
export const healthAttributesList = [];

export const initHealthInput = () => {
    const healthInput = document.getElementById('input-health');
    const placeholderText = document.getElementById('placeholder-text');

    // Remove the placeholder when typing starts
    healthInput.addEventListener('input', function () {
        if (healthInput.innerText.trim().length > 0) {
            placeholderText.style.display = 'none';
        } else {
            placeholderText.style.display = 'block';
        }
    });

    // Listen for the keydown event
    healthInput.addEventListener('keydown', function(event) {
        // Check if the Tab key was pressed
        if (event.key === 'Tab') {
            event.preventDefault(); // Prevent default tab behavior (focus switch)

            // Capture the typed text and trim it
            const textNodes = Array.from(healthInput.childNodes).filter(node => node.nodeType === Node.TEXT_NODE);
            const attribute = textNodes.map(node => node.textContent.trim()).join('');

            // Only proceed if there's some text input
            if (attribute) {
                healthAttributesList.push(attribute); // Add the attribute to the list
                addAttributeTag(attribute); // Create and display a tag for this attribute
            }

            // Remove only text nodes after adding the attribute
            textNodes.forEach(node => healthInput.removeChild(node));
        }
    });

    // Function to add a tag for the health attribute
    const addAttributeTag = (attribute) => {
        const span = document.createElement('span');
        span.textContent = attribute;
        span.style.display = 'inline-block';
        span.style.margin = '4px';
        span.style.padding = '4px 8px';
        span.style.backgroundColor = '#e9ecef';
        span.style.borderRadius = '12px';
        span.style.fontSize = '14px';

        // Append the tag into the contenteditable div
        healthInput.appendChild(span);
        // Add a non-breaking space after the tag so user can type further
        const space = document.createTextNode('\u00A0');
        healthInput.appendChild(space);

        // Move the cursor to the end of the contenteditable div
        moveCursorToEnd(healthInput);
    }

    // Function to move the cursor to the end of the contenteditable div
    const moveCursorToEnd = (element) => {
        const range = document.createRange();
        const selection = window.getSelection();
        range.selectNodeContents(element);
        range.collapse(false);
        selection.removeAllRanges();
        selection.addRange(range);
    }
};