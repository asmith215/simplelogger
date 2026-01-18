
package com.simplelogger.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.simplelogger.core.Entry;
import com.simplelogger.core.Type;
import com.simplelogger.repository.FileRepository;
import com.simplelogger.ui.UI;

public class Flow {
    private final UI ui = new UI();
    private final FileRepository repo;
    private final List<Entry> entries;


    public Flow(List<Entry> entries, FileRepository repo){
        this.entries = entries;
        this.repo = repo;
    }


    public void mainControl() throws IOException{
        ui.welcome();
        while(true){
            int choice = ui.startMenu();
            switch (choice) {
            case 1 -> createEntryFlow();
            case 2 -> loadEntryFlow();
            case 3 -> editEntryFlow();
            case 4 -> deleteEntryFlow();
            case 5 -> {
                ui.goodbye();
                return;
            }
            }
        }
    }


    public void createEntryFlow() throws IOException{
        int input = ui.promptEntries();
        switch(input){
            case 1 -> entries.add(ui.promptEntryCreation(Type.MOVIE));
            case 2 -> entries.add(ui.promptEntryCreation(Type.SHOW));
            case 3 -> entries.add(ui.promptEntryCreation(Type.BOOK));
            case 4 -> entries.add(ui.promptEntryCreation(Type.OTHER));
            case 5 -> {return;}
            }
        repo.saveAll(entries);
        ui.entryCreated();
    }

    public void loadEntryFlow() throws IOException{
        while(true){
            int input = ui.promptLoadEntries();
            switch(input){
                case 1 -> {loadEntriesByType();}
                case 2 -> {loadEntriesByName();}
                case 3 -> {loadEntriesByMinRating();}
                case 4 -> {return;}
            }
        }
    }

    private void loadEntriesByType() throws IOException{
        while(true){
            int input = ui.promptLoadEntryByType();
            switch(input){
                case 1 -> { ui.printEntries(entries); }
                case 2 -> { ui.printEntries(filterEntryByType(Type.MOVIE)); }
                case 3 -> { ui.printEntries(filterEntryByType(Type.SHOW)); }
                case 4 -> { ui.printEntries(filterEntryByType(Type.BOOK)); }
                case 5 -> { ui.printEntries(filterEntryByType(Type.OTHER)); }
                case 6 -> {return;}
            } 
        }
    }

    private void loadEntriesByName() throws IOException{
        String name = ui.getEntryName().trim();
        if(name.isEmpty()){
            ui.emptyLoadByNameInput();
            return;
        }
        ui.printEntries(filterByNameContains(name));
    }

    private void loadEntriesByMinRating() throws IOException{
        double rating = ui.getEntryRating();
        ui.printEntries(filterByMinRating(rating));
    }

    public List<Entry> filterByNameContains(String substring){
        String name = substring.toLowerCase(Locale.ROOT);
        List<Entry> result = new ArrayList<>();
        for(Entry entry: entries){
            String entryName = entry.getName();
            if(entryName != null && entryName.toLowerCase(Locale.ROOT).contains(name)){
                result.add(entry);
            }
        }
        return result;
    }

    public List<Entry> filterByMinRating(double minRating){
        List<Entry> result = new ArrayList<>();
        for(Entry entry: entries){
            Double rating = entry.getRating();
            if(rating != null && rating >= minRating){
                result.add(entry);
            }
        }
        return result;
    }
    
    public void editEntryFlow() throws IOException{

        Optional<Entry> selection = selectEntryByMediaType();

        if(selection.isEmpty()){ return; }


        editEntry(selection.get());
    
    }



    private void editEntry(Entry input) throws IOException{
        String originalName = input.getName();
        LocalDate originalDate = input.getDate();
        Double originalRating = input.getRating();
        String originalComment = input.getComment();
        
        
        while(true){
            ui.showCurrentEdit(input);
            int editType = ui.promptEditFieldSelection();
                
                switch(editType){
                    //name
                    case(1) -> {input.setName(ui.getString("Name: "));}
                    //date
                    case(2) -> {input.setDate(ui.readDate("Date Finished: (MM-dd-yyyy)"));}
                    //rating
                    case(3) -> {input.setRating(ui.promptDoubleRange("Rating (0-10):", 0,10));}
                    //comments
                    case(4) -> {input.setComment(ui.getString("Comments: "));}
                    //finished (save)
                    case(5) -> {
                        repo.saveAll(entries);
                        ui.editSaved();
                        return;
                    }
                    //cancel (discard changes)
                    case(6) -> {
                        input.setName(originalName);
                        input.setDate(originalDate);
                        input.setRating(originalRating);
                        input.setComment(originalComment);
                        return;
                    }
                }
        }
    }

    private List<Entry> getEntriesForMediaChoice(int input){
        return switch(input){
                case 1 -> entries;
                case 2 -> filterEntryByType(Type.MOVIE);
                case 3 -> filterEntryByType(Type.SHOW);
                case 4 -> filterEntryByType(Type.BOOK);
                case 5 -> filterEntryByType(Type.OTHER);
                default -> entries;
        };
    }

    public void deleteEntryFlow() throws IOException{
            Optional<Entry> selection = selectEntryByMediaType();

            if(selection.isEmpty()){ return; }

            deleteEntry(selection.get());
    }

    private void deleteEntry(Entry entry) throws IOException{
        if(ui.confirmDeletion(entry)){
            entries.remove(entry);
            repo.saveAll(entries);
            ui.entryDeleted();
        }
    }

    private Optional<Entry> selectEntryByMediaType() throws IOException{
        while(true){
            int mediaChoice = ui.promptMediaTypeToChoose();

            if(mediaChoice == 6){
                return Optional.empty();
            }

            List<Entry> candidates = getEntriesForMediaChoice(mediaChoice);

            if(candidates.isEmpty()){
                ui.emptyList();
                continue;
            }

            int entryInput = ui.promptEntrySelection(candidates);

            return Optional.of(candidates.get(entryInput-1));
        }
    }

    public List<Entry> filterEntryByType(Type type){
        List<Entry> sorted = new ArrayList<>();
        for(Entry entry: entries){
            if(entry.getType() == type){
                sorted.add(entry);
            }
        }
        return sorted;
    }

}