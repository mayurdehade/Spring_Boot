package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.JournalEntry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId; //datatype
import org.springframework.data.mongodb.repository.MongoRepository;

//we only extend the class name as MongoRepository<Entity, IdDataType>
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
