// Pobranie referencji do elementów
const slider = document.getElementById('sizeSlider');
const circle = document.getElementById('circle');

function updateCircle(size) {
    // Ustawienie szerokości i wysokości koła
    circle.style.width = size + "px";
    circle.style.height = size + "px";

    // Aktualizacja wyświetlanego tekstu (średnicy)
    circle.textContent = size + "px";

    // Im większe koło, tym jaśniejszy kolor (zakres od 80% do 100% jasności)
    const brightness = 80 + (size / 500) * 20;
    circle.style.backgroundColor = `hsl(60, 100%, ${brightness}%)`;
}

// Obsługa zmiany suwaka (dynamiczna zmiana koła)
slider.addEventListener("input", function () {
    updateCircle(this.value); // Przekazanie aktualnej wartości suwaka
});

// Obsługa klawiatury (zmiana wartości suwaka strzałkami)
document.addEventListener("keydown", function (event) {
    let newSize = parseInt(slider.value); // Pobranie aktualnej wartości suwaka

    // Zwiększenie rozmiaru (strzałka w prawo) - max 500px
    if (event.key === "ArrowRight" && newSize < 500) {
        newSize += 5;
    }
    // Zmniejszenie rozmiaru (strzałka w lewo) - min 10px
    else if (event.key === "ArrowLeft" && newSize > 10) {
        newSize -= 5;
    }

    // Aktualizacja wartości suwaka i koła
    slider.value = newSize;
    updateCircle(newSize);
});

// Inicjalizacja - ustawienie koła zgodnie z początkową wartością suwaka
updateCircle(slider.value);
