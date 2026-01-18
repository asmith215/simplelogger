package com.simplelogger.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import com.simplelogger.factory.EntryFactory;
import com.simplelogger.core.Type;
import com.simplelogger.core.Entry;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UI {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public void welcome(){System.out.println("Welcome!\nWhat would you like to do today?");}

    public void goodbye(){System.out.println("Goodbye!");}

    public void entryCreated(){System.out.println("Entry Created!");}

    public void editSaved(){System.out.println("Edit Saved!");}

    public void emptyList(){System.out.println("No entries found.");}

    public void entryDeleted(){System.out.println("Entry deleted.");}

    public void emptyLoadByNameInput(){System.out.println("Search text is empty. Showing no results.");}


    public int startMenu() throws IOException{
        return promptIntRange("1) Create Entry\n2) Load Entries\n3) Edit Entries \n4) Delete Entry\n5) Quit", 1,5);
    }

    public int promptEntries() throws IOException{
        return promptIntRange("What type of entry would you like to create?\n1) Movie\n2) Show\n3) Book\n4) Other\n5) Back", 1,5);
    }

    public Entry promptEntryCreation(Type type) throws IOException{

        String name = getString("Name: ");

        LocalDate date = readDate("Date Finished: (MM-dd-yyyy)");
        
        double rating = promptDoubleRange("Rating (0-10):", 0,10);

        String comments = getString("Comments: ");

        return EntryFactory.createEntry(type,name,date,rating,comments);
    }

    public int promptLoadEntryByType() throws IOException{
        return promptIntRange("What type of entry would you like to load?\n1) All\n2) Movie\n3) Show\n4) Book\n5) Other\n6) Back",1,6);
    }

    public int promptLoadEntries() throws IOException{
        return promptIntRange("Loading by:\n1) Type\n2) Name\n3) Minimum Rating\n4) Back",1,4);
    }

    public String getEntryName() throws IOException{
        return getString("Enter the entry name (or part of it):");
    }

    public double getEntryRating() throws IOException{
        return promptDoubleRange("What is the minimum rating? \n(0-10):", 0,10);
    }

    public int promptEntrySelection(List<Entry> candidates) throws IOException{
        for(int i = 0; i<candidates.size(); i++){
            Entry current = candidates.get(i);
            System.out.printf("%d) [%s] Name: %s | Date: %s | Rating: %.1f %nComments: %s %n%n",
                i+1, 
                current.getType(), 
                current.getName(), 
                FORMATTER.format(current.getDate()), 
                current.getRating(), 
                current.getComment()
            );
        }
        
        return promptIntRange("Select an entry by number: ",1,candidates.size());
    }

    public int promptMediaTypeToChoose() throws IOException{
        return promptIntRange("What type of media do you want to choose?\n1) All\n2) Movie\n3) Show\n4) Book\n5) Other\n6) Back",1,6);
    }

    public int promptEditFieldSelection() throws IOException{
        return promptIntRange("""
                              What would you like to do?
                              1) Edit Name
                              2) Edit Date
                              3) Edit Rating
                              4) Edit Comments
                              5) Finish (Save Changes)
                              6) Cancel (discard changes)""",1,6);
    }

    public boolean confirmDeletion(Entry entry) throws IOException{
        printEntry(entry);
        int input = promptIntRange("Are you sure you want to delete this entry? \n1) Yes\n2) No", 1, 2);
        return input == 1;
    }

    public void showCurrentEdit(Entry entry){
        System.out.println("Currently editing: " + entry.getName());
        printEntry(entry);
    }


    public int promptIntRange(String msg, int min, int max) throws IOException{
        System.out.println(msg);
        while(true){
            try{
                int input = Integer.parseInt(reader.readLine());
                if(input < min || input > max){
                    System.out.println("Invalid input. Please enter within range: " + min + " - " + max);
                    continue;
                } 
                return input;
            } 
            catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter within range: " + min + " - " + max);
            }

        }
    }


    public double promptDoubleRange(String msg, double min, double max) throws IOException{
        System.out.println(msg);
        while(true){
            try{
                double input = Double.parseDouble(reader.readLine());
                if(input < min || input > max){
                    System.out.println("Invalid input. Please enter within range: " + min + " - " + max);
                    continue;
                } 
                return input;
            } 
            catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter within range: " + min + " - " + max);
            }

        }
    }

    public String getString(String msg) throws IOException{
        System.out.println(msg);
        return reader.readLine();
    }

    public LocalDate readDate(String msg) throws IOException{
        while(true){
            System.out.println(msg);
            String input = reader.readLine().trim();
            try{
                return LocalDate.parse(input,FORMATTER);
            } catch(DateTimeParseException e){
                System.out.println("Invalid format. Please enter with this format: MM-dd-yyyy");
            }


        }
    }


    public void printEntry(Entry entry){
        System.out.printf("%n[%s] Name: %s | Date: %s | Rating: %.1f %nComments: %s %n%n",
            entry.getType(), 
            entry.getName(), 
            FORMATTER.format(entry.getDate()), 
            entry.getRating(), 
            entry.getComment()
        );
    }

    public void printEntries(List<Entry> entries){
        if(entries.isEmpty()){
            System.out.println("No entries found.");
            return;
        }
        for(Entry entry: entries){
            printEntry(entry);
        }
    }

}
