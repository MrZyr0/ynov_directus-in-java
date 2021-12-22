package com.j2ee.tdspring.services;

import com.j2ee.tdspring.entities.Model;
import com.j2ee.tdspring.entities.Property;
import com.j2ee.tdspring.enums.PropertyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.StringJoiner;

@Service
public class TableGeneratorService {
    private final SessionFactory sessionFactory;

    public TableGeneratorService(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }

        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    void createTable(Model model) {
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        String sql = String.format("CREATE TABLE %s (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT)", model.getName());
        session.createNativeQuery(sql).executeUpdate();
        txn.commit();
    }

    void dropTable(Model model) {
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        String sql = String.format("DROP TABLE %s", model.getName());
        session.createNativeQuery(sql).executeUpdate();
        txn.commit();
    }

    void addColumnsToTable(Model model, List<Property> properties) {
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();

        StringJoiner s = new StringJoiner(" ");
        s.add(String.format("ALTER TABLE %s", model.getName()));

        properties.forEach(property -> {
            String type = sqlTypeFromPropertyType(property.getType());
            s.add(String.format("ADD %s %s", property.getName(), type));
        });

        session.createNativeQuery(s.toString()).executeUpdate();
        txn.commit();
    }

    void removeColumnsToTable(Model model, List<Property> properties) {
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();

        StringJoiner s = new StringJoiner(" ");
        s.add(String.format("ALTER TABLE %s", model.getName()));

        properties.forEach(property -> {
            s.add(String.format("DROP %s", property.getName()));
        });

        session.createNativeQuery(s.toString()).executeUpdate();
        txn.commit();
    }

    private String sqlTypeFromPropertyType(PropertyType type) {
        switch (type) {
            case DATE: return "DATE";
            case DOUBLE: return "DOUBLE";
            case INTEGER: return "INTEGER";
            case STRING: return "VARCHAR(255)";
            case BOOLEAN: return "BOOL";
            case FLOAT: return "FLOAT";
            default: return "";
        }
    }
}
