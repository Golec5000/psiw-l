document.addEventListener("DOMContentLoaded", function () {
    const notesList = document.getElementById("notesList"); // Lista notatek
    const noteForm = document.getElementById("noteForm"); // Formularz dodawania/edycji notatek
    const noteTitle = document.getElementById("noteTitle"); // Pole tytułu notatki
    const noteContent = document.getElementById("noteContent"); // Pole treści notatki
    const noteId = document.getElementById("noteId"); // Ukryte pole ID notatki (dla edycji)
    const exportBtn = document.getElementById("exportBtn"); // Przycisk eksportu do JSON
    const importFile = document.getElementById("importFile"); // Pole wyboru pliku JSON do importu

    let notes = []; // Tablica przechowująca notatki

    // Pobiera notatki z localStorage i renderuje je na stronie
    function loadNotes() {
        const savedNotes = localStorage.getItem("notes");
        notes = savedNotes ? JSON.parse(savedNotes) : []; // Jeśli dane istnieją, parsujemy je, inaczej tworzymy pustą tablicę
        renderNotes();
    }

    //Zapisuje aktualne notatki do localStorage
    function saveNotes() {
        localStorage.setItem("notes", JSON.stringify(notes)); // Konwersja do JSON i zapis
    }

    // Renderuje listę notatek w interfejsie
    function renderNotes() {
        notesList.innerHTML = ""; // Czyści listę przed ponownym renderowaniem
        notes.forEach(note => {
            const li = document.createElement("li");
            li.textContent = note.title; // Wyświetla tytuł notatki
            li.classList.add("list-group-item", "list-group-item-action"); // Dodanie klas Bootstrap dla wyglądu
            li.addEventListener("click", () => loadNoteForEdit(note.id)); // Po kliknięciu ładujemy notatkę do edycji
            notesList.appendChild(li);
        });
    }

    //Obsługuje dodawanie i edytowanie notatek
    noteForm.addEventListener("submit", function (event) {
        event.preventDefault(); // Zapobiega domyślnemu przesyłaniu formularza

        const title = noteTitle.value.trim(); // Pobranie tytułu i usunięcie białych znaków
        const content = noteContent.value.trim(); // Pobranie treści

        if (!title) {
            alert("Title is required.");
            return;
        }

        if (noteId.value) {
            // Edycja istniejącej notatki
            const noteIndex = notes.findIndex(note => note.id === noteId.value);
            if (noteIndex !== -1) {
                notes[noteIndex].title = title;
                notes[noteIndex].content = content;
            }
        } else {
            // Tworzenie nowej notatki
            const newNote = {
                id: Date.now().toString(), // Unikalne ID na podstawie aktualnego czasu
                title,
                content
            };
            notes.push(newNote);
        }

        saveNotes();
        renderNotes();
        clearForm();
    });

    //Wczytuje notatkę do formularza, aby można ją było edytować
    function loadNoteForEdit(id) {
        const note = notes.find(note => note.id === id);
        if (note) {
            noteId.value = note.id; // Przypisanie ID notatki do ukrytego pola
            noteTitle.value = note.title; // Wstawienie tytułu do pola
            noteContent.value = note.content; // Wstawienie treści do pola
        }
    }

    //Czyści formularz po dodaniu/edytowaniu notatki
    function clearForm() {
        noteId.value = "";
        noteTitle.value = "";
        noteContent.value = "";
    }

    //Eksportuje notatki do pliku JSON
    exportBtn.addEventListener("click", function () {
        const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(notes));
        const downloadAnchor = document.createElement("a"); // Tworzymy ukryty link do pobrania pliku
        downloadAnchor.setAttribute("href", dataStr);
        downloadAnchor.setAttribute("download", "notes.json"); // Ustawienie nazwy pliku
        document.body.appendChild(downloadAnchor);
        downloadAnchor.click();
        document.body.removeChild(downloadAnchor); // Usunięcie linku po pobraniu
    });

    //Importuje notatki z pliku JSON
    importFile.addEventListener("change", function (event) {
        const file = event.target.files[0]; // Pobranie wybranego pliku
        if (!file) return;

        const reader = new FileReader();
        reader.onload = function (e) {
            try {
                const importedNotes = JSON.parse(e.target.result);
                if (Array.isArray(importedNotes)) {
                    notes = importedNotes; // Nadpisanie aktualnych notatek importowanymi
                    saveNotes();
                    renderNotes();
                } else {
                    alert("Invalid JSON format."); // Komunikat, jeśli format pliku jest błędny
                }
            } catch (error) {
                alert("Error loading JSON file."); // Obsługa błędów parsowania JSON
            }
        };
        reader.readAsText(file); // Odczytanie pliku jako tekst
    });

    // Inicjalizacja aplikacji - wczytanie zapisanych notatek po uruchomieniu strony
    loadNotes();
});
