package com.ee.sbtraining.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("mongo")
//@Profile("mongo")
//@ConditionalOnProperty(name = "dbType", havingValue = "mongo")
public class MongoDBCustomerRepository implements ICustomerRepository{
    @Override
    public List<String> getCustomerNames() {
        return List.of("Mongo-1", "Mongo-2");
    }
}
