
package com.simplelogger.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.simplelogger.core.Entry;

public class FileRepository {
    private final ObjectMapper mapper;
    private final File file;
    
    public FileRepository(String filePath){
        this.file = new File(filePath);

        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    public List<Entry> loadAll(){
        if(!file.exists() || file.length() == 0L){
            return new ArrayList<>();
        }

        try{
            return mapper.readValue(file, new TypeReference<List<Entry>>() {});
        }

        catch(IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveAll(List<Entry> entries){
        try{
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            mapper.writerFor(new TypeReference<List<Entry>>() {})
                .withDefaultPrettyPrinter()
                .writeValue(file, entries);
            } 
        catch (IOException e){
            e.printStackTrace();
        }
    }


}
