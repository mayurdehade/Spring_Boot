package com.edigest.journalApp.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

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
    private LocalDateTime date;  //java 8


}
