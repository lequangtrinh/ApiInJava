package com.ApiMongo.Reponsi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ApiMongo.Models.Login;
@Repository
public interface ResponsiLogin extends MongoRepository<Login, String> {

}
