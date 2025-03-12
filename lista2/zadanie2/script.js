document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("packageForm");
    const packageList = document.getElementById("packageList");
    const totalVolumeEl = document.getElementById("totalVolume");
    const searchInput = document.getElementById("search");

    let totalVolume = 0;

    // Obsługa formularza
    form.addEventListener("submit", function (event) {
        event.preventDefault();

        // Pobranie wartości
        const name = document.getElementById("name").value.trim();
        const width = parseFloat(document.getElementById("width").value);
        const height = parseFloat(document.getElementById("height").value);
        const depth = parseFloat(document.getElementById("depth").value);

        // Walidacja poprawnych wartości
        if (!name || isNaN(width) || isNaN(height) || isNaN(depth) || width <= 0 || height <= 0 || depth <= 0) {
            alert("Please enter valid dimensions (1-1000 cm)!");
            return;
        }

        console.log(name, width, height, depth);

        // Obliczenie objętości w metrach sześciennych
        const volume = (width * height * depth) / 1000000;
        // console.log(volume);
        const volumeStr = volume.toFixed(2);
        // console.log(volumeStr);
        totalVolume = parseFloat(totalVolume) + volume;

        // Dodanie nowego wiersza do tabeli
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${name}</td>
            <td>${width}</td>
            <td>${height}</td>
            <td>${depth}</td>
            <td>${volumeStr}</td>
        `;
        packageList.appendChild(row);

        // Aktualizacja sumarycznej objętości
        totalVolumeEl.textContent = totalVolume.toFixed(2);

        // Czyszczenie formularza
        form.reset();
    });

    // Filtrowanie paczek po nazwie
    searchInput.addEventListener("input", function () {
        const filter = searchInput.value.toLowerCase();
        const rows = packageList.getElementsByTagName("tr");

        for (let row of rows) {
            let name = row.cells[0].textContent.toLowerCase();
            row.style.display = name.includes(filter) ? "" : "none";
        }
    });
});
