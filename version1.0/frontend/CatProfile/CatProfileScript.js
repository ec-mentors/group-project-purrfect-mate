//Read more - button functionality

const allDiv = document.getElementById("all");
const readMoreDiv = document.getElementById("readMore")

const readMoreButton = document.getElementById("showAllBtn")
const goBackButton = document.getElementById("goBack")
const mobileReadMoreButton = document.getElementById("mobile-showAllBtn")


readMoreButton.addEventListener("click", function() {
    allDiv.style.display = "none";
    readMoreDiv.style.display = "block";
})

mobileReadMoreButton.addEventListener("click", function() {
    allDiv.style.display = "none";
    readMoreDiv.style.display = "block";
})

goBackButton.addEventListener("click", function() {
    allDiv.style.display = "block";
    readMoreDiv.style.display = "none";
    window.scrollTo({
        top: 0,
        behavior: "instant"
    });
})

//Save button functionality

const saveButton = document.getElementById("save");

let saved = false;

if(saved === false) {
    saveButton.style.opacity = "50%";
}
else {
    saveButton.style.opacity = "100%";
}

saveButton.addEventListener("click", function() {
    
    saved = !saved;

    if(saved === false) {
        saveButton.style.opacity = "50%";
    }
    else {
        saveButton.style.opacity = "100%";
    }
    
})

