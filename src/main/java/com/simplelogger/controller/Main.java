package com.simplelogger.controller;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.simplelogger.core.Entry;
import com.simplelogger.repository.FileRepository;

public class Main {
    
    public static void run() throws IOException{

        Path dataPath = Paths.get("data", "entries.json");
        
        FileRepository repo =  new FileRepository(dataPath.toString());

        List<Entry> entries = repo.loadAll();

        Flow flow = new Flow(entries, repo);

        flow.mainControl();
        
    }

    public static void main(String[] args){
        try{ 
            run();
        } catch(IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
    


}
