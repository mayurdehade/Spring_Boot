package com.edigest.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;

    //Indexed fast searching process and unique does not allow duplicate value
    //NonNull check if the field is null then it not save in database
    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    //creating reference of journalEntry in user collection (it works like foreign key)
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
