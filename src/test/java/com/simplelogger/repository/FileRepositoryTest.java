package com.simplelogger.repository;

import com.simplelogger.core.Entry;
import com.simplelogger.core.Type;
import com.simplelogger.factory.EntryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileRepositoryTest {

    @TempDir
    Path tempDir;

    @Test
    void loadAll_returnsEmptyList_whenFileDoesNotExist() throws Exception {
        Path file = tempDir.resolve("entries.json");
        Files.deleteIfExists(file);

        FileRepository repo = new FileRepository(file.toString());

        List<Entry> entries = repo.loadAll();

        assertNotNull(entries);
        assertTrue(entries.isEmpty(), "Expected empty list when file missing");
    }

    @Test
    void saveAll_and_loadAll_roundTrip() throws Exception {
        Path file = tempDir.resolve("entries.json");
        FileRepository repo = new FileRepository(file.toString());

        LocalDate date = LocalDate.of(2024, 10, 1);

        Entry movie = EntryFactory.createEntry(Type.MOVIE, "Inception", date, 9.0, "Great");
        Entry book  = EntryFactory.createEntry(Type.BOOK, "Dune", date, 8.5, "Classic");

        List<Entry> original = List.of(movie, book);

        repo.saveAll(original);

        List<Entry> loaded = repo.loadAll();

        assertNotNull(loaded);
        assertEquals(2, loaded.size(), "Expected two entries after round-trip");

        // Find entries by name and verify fields
        Entry loadedMovie = loaded.stream().filter(e -> "Inception".equals(e.getName())).findFirst().orElseThrow();
        assertEquals(Type.MOVIE, loadedMovie.getType());
        assertEquals(9.0, loadedMovie.getRating());
        assertEquals("Great", loadedMovie.getComment());

        Entry loadedBook = loaded.stream().filter(e -> "Dune".equals(e.getName())).findFirst().orElseThrow();
        assertEquals(Type.BOOK, loadedBook.getType());
        assertEquals(8.5, loadedBook.getRating());
        assertEquals("Classic", loadedBook.getComment());
    }

    @Test
    void saveAll_overwritesExistingFile() throws Exception {
        Path file = tempDir.resolve("entries.json");
        FileRepository repo = new FileRepository(file.toString());

        LocalDate date = LocalDate.of(2024, 10, 1);
        Entry a = EntryFactory.createEntry(Type.MOVIE, "A", date, 5.0, "");
        repo.saveAll(List.of(a));

        // overwrite with B
        Entry b = EntryFactory.createEntry(Type.BOOK, "B", date, 6.0, "");
        repo.saveAll(List.of(b));

        List<Entry> loaded = repo.loadAll();
        assertEquals(1, loaded.size());
        assertEquals("B", loaded.get(0).getName());
    }
}
