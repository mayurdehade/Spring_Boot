package com.edigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//use annotation to make it as entity class
@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

    //represents primary key
    @Id
    private ObjectId id; //it stores the id value of object id data type
    private String title;
    private String content;
    private LocalDateTime date; // java 8
}
