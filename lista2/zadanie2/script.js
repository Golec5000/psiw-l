document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("packageForm"); // Formularz dodawania paczki
    const packageList = document.getElementById("packageList"); // Lista paczek w tabeli
    const totalVolumeEl = document.getElementById("totalVolume"); // Pole sumarycznej objętości
    const searchInput = document.getElementById("search"); // Pole wyszukiwania paczek

    let totalVolume = 0;

    // Obsługa formularza przy jego zatwierdzeniu
    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Zablokowanie domyślnego przesyłania formularza

        const name = document.getElementById("name").value.trim();
        const width = parseFloat(document.getElementById("width").value);
        const height = parseFloat(document.getElementById("height").value);
        const depth = parseFloat(document.getElementById("depth").value);

        // Walidacja poprawnych wartości (czy nie są puste i mieszczą się w zakresie 1-1000 cm)
        if (!name || isNaN(width) || isNaN(height) || isNaN(depth) || width <= 0 || height <= 0 || depth <= 0) {
            alert("Please enter valid dimensions (1-1000 cm)!");
            return;
        }

        // Obliczenie objętości paczki w metrach sześciennych (1 cm³ = 0.000001 m³)
        const volume = (width * height * depth) / 1000000;
        const volumeStr = volume.toFixed(2); // Zaokrąglenie wartości do 2 miejsc po przecinku
        totalVolume = parseFloat(totalVolume) + volume; // Aktualizacja sumarycznej objętości paczek

        // Tworzenie nowego wiersza w tabeli
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${name}</td>
            <td>${width}</td>
            <td>${height}</td>
            <td>${depth}</td>
            <td>${volumeStr}</td>
        `;
        packageList.appendChild(row);

        totalVolumeEl.textContent = totalVolume.toFixed(2); // Aktualizacja sumarycznej objętości w stopce tabeli
        form.reset(); // Czyszczenie formularza po dodaniu paczki

    });

    // Obsługa wyszukiwania paczek po nazwie
    searchInput.addEventListener("input", function () {
        const filter = searchInput.value.toLowerCase(); // Pobranie wartości wpisanej w wyszukiwarkę
        const rows = packageList.getElementsByTagName("tr"); // Pobranie wszystkich wierszy tabeli

        // Iteracja po wierszach i filtrowanie paczek
        for (let row of rows) {
            let name = row.cells[0].textContent.toLowerCase(); // Pobranie nazwy paczki z tabeli
            row.style.display = name.includes(filter) ? "" : "none"; // Ukrywanie/wyświetlanie wierszy
        }
    });
});
