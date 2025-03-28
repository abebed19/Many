package com.abbayllc;

import com.abbayllc.entities.Group;
import com.abbayllc.entities.User;
import com.abbayllc.persistence.CustomPersistenceProviderInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true");

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceProviderInfo(),props);
        EntityManager manager = emf.createEntityManager();

        try{
            manager.getTransaction().begin();
            User user = new User();
            user.setName("Abebe Demis");
            User user2 = new User();
            user2.setName("Noah Abebe");

            Group group = new Group();
            group.setName("Group 1");

            Group group2 = new Group();
            group2.setName("Group 2");

            group.setUsers(List.of(user,user2));
            group2.setUsers(List.of(user2));

            manager.persist(user);
            manager.persist(user2);

            manager.persist(group);
            manager.persist(group2);






            manager.getTransaction().commit();

        }finally {
            manager.close();
        }

    }
}