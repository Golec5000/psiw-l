const slider = document.getElementById('sizeSlider');
const circle = document.getElementById('circle');

// Funkcja do aktualizacji koła
function updateCircle(size) {
    circle.style.width = size + "px";
    circle.style.height = size + "px";
    circle.textContent = size + "px";

    // Im większe koło, tym jaśniejszy kolor
    const brightness = 80 + (size / 500) * 20;
    circle.style.backgroundColor = `hsl(60, 100%, ${brightness}%)`;
}

// Obsługa suwaka
slider.addEventListener("input", function () {
    updateCircle(this.value);
});

// Obsługa klawiatury
document.addEventListener("keydown", function (event) {
    let newSize = parseInt(slider.value);

    if (event.key === "ArrowRight" && newSize < 500) {
        newSize += 5;
    } else if (event.key === "ArrowLeft" && newSize > 10) {
        newSize -= 5;
    }

    slider.value = newSize;
    updateCircle(newSize);
});

// Inicjalizacja
updateCircle(slider.value);
