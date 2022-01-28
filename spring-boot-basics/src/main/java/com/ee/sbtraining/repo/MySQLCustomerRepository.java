package com.ee.sbtraining.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Qualifier("mysql")
//@Profile("mysql")
//@ConditionalOnProperty(name = "dbType", havingValue = "mysql", matchIfMissing = true)
@Primary
public class MySQLCustomerRepository implements ICustomerRepository{
    @Override
    public List<String> getCustomerNames() {
        return List.of("Mysql-1", "mysql-2");
    }
}
