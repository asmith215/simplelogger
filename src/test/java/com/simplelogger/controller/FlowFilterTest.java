package com.simplelogger.controller;

import com.simplelogger.core.Entry;
import com.simplelogger.factory.EntryFactory;
import com.simplelogger.core.Type;
import com.simplelogger.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlowFilterTest {

    @TempDir
    Path tempDir;

    private Flow newFlowWithEntries(List<Entry> entries) {
        Path file = tempDir.resolve("entries.json");
        FileRepository repo = new FileRepository(file.toString());
        return new Flow(new ArrayList<>(entries), repo);
    }

    @Test
    void filterByNameContains_caseInsensitive() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        Entry e1 = EntryFactory.createEntry(Type.MOVIE, "Inception", date, 9.0, "");
        Entry e2 = EntryFactory.createEntry(Type.MOVIE, "Inside Out", date, 8.0, "");
        Entry e3 = EntryFactory.createEntry(Type.BOOK, "Dune", date, 8.5, "");

        Flow flow = newFlowWithEntries(List.of(e1, e2, e3));

        List<Entry> result = flow.filterByNameContains("in"); 

        assertEquals(2, result.size());
        List<String> names = result.stream().map(Entry::getName).toList();
        assertTrue(names.contains("Inception"));
        assertTrue(names.contains("Inside Out"));
    }

    @Test
    void filterByMinRating_inclusiveThreshold() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        Entry low = EntryFactory.createEntry(Type.MOVIE, "Low", date, 4.0, "");
        Entry mid = EntryFactory.createEntry(Type.MOVIE, "Mid", date, 7.0, "");
        Entry high = EntryFactory.createEntry(Type.MOVIE, "High", date, 9.5, "");

        Flow flow = newFlowWithEntries(List.of(low, mid, high));

        List<Entry> result = flow.filterByMinRating(7.0);

        assertEquals(2, result.size());
        List<String> names = result.stream().map(Entry::getName).toList();
        assertTrue(names.contains("Mid"));
        assertTrue(names.contains("High"));
    }

    @Test
    void filterEntryByType_returnsOnlySelectedType() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        Entry m = EntryFactory.createEntry(Type.MOVIE, "Mov", date, 7.0, "");
        Entry b = EntryFactory.createEntry(Type.BOOK, "Bk", date, 8.0, "");

        Flow flow = newFlowWithEntries(List.of(m, b));

        List<Entry> movies = flow.filterEntryByType(Type.MOVIE);

        assertEquals(1, movies.size());
        assertEquals("Mov", movies.get(0).getName());
    }
}
