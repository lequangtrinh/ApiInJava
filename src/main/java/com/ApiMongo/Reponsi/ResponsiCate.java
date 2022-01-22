package com.ApiMongo.Reponsi;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ApiMongo.Models.Category;

public interface ResponsiCate extends MongoRepository<Category, String> {

}
