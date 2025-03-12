document.addEventListener("DOMContentLoaded", function () {
    const notesList = document.getElementById("notesList");
    const noteForm = document.getElementById("noteForm");
    const noteTitle = document.getElementById("noteTitle");
    const noteContent = document.getElementById("noteContent");
    const noteId = document.getElementById("noteId");
    const exportBtn = document.getElementById("exportBtn");
    const importFile = document.getElementById("importFile");

    let notes = [];

    // Pobranie notatek z localStorage
    function loadNotes() {
        const savedNotes = localStorage.getItem("notes");
        notes = savedNotes ? JSON.parse(savedNotes) : [];
        renderNotes();
    }

    // Zapisanie notatek do localStorage
    function saveNotes() {
        localStorage.setItem("notes", JSON.stringify(notes));
    }

    // Renderowanie listy notatek
    function renderNotes() {
        notesList.innerHTML = "";
        notes.forEach(note => {
            const li = document.createElement("li");
            li.textContent = note.title;
            li.classList.add("list-group-item", "list-group-item-action");
            li.addEventListener("click", () => loadNoteForEdit(note.id));
            notesList.appendChild(li);
        });
    }

    // Obsługa formularza
    noteForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const title = noteTitle.value.trim();
        const content = noteContent.value.trim();

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
                id: Date.now().toString(),
                title,
                content
            };
            notes.push(newNote);
        }

        saveNotes();
        renderNotes();
        clearForm();
    });

    // Wczytywanie notatki do edycji
    function loadNoteForEdit(id) {
        const note = notes.find(note => note.id === id);
        if (note) {
            noteId.value = note.id;
            noteTitle.value = note.title;
            noteContent.value = note.content;
        }
    }

    // Czyszczenie formularza
    function clearForm() {
        noteId.value = "";
        noteTitle.value = "";
        noteContent.value = "";
    }

    // Eksport notatek do pliku JSON
    exportBtn.addEventListener("click", function () {
        const dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(notes));
        const downloadAnchor = document.createElement("a");
        downloadAnchor.setAttribute("href", dataStr);
        downloadAnchor.setAttribute("download", "notes.json");
        document.body.appendChild(downloadAnchor);
        downloadAnchor.click();
        document.body.removeChild(downloadAnchor);
    });

    // Import notatek z pliku JSON
    importFile.addEventListener("change", function (event) {
        const file = event.target.files[0];
        if (!file) return;

        const reader = new FileReader();
        reader.onload = function (e) {
            try {
                const importedNotes = JSON.parse(e.target.result);
                if (Array.isArray(importedNotes)) {
                    notes = importedNotes;
                    saveNotes();
                    renderNotes();
                } else {
                    alert("Invalid JSON format.");
                }
            } catch (error) {
                alert("Error loading JSON file.");
            }
        };
        reader.readAsText(file);
    });

    // Inicjalizacja aplikacji
    loadNotes();
});
