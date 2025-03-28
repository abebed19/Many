package com.abbayllc;

import com.abbayllc.entities.Course;
import com.abbayllc.entities.Group;
import com.abbayllc.entities.Student;
import com.abbayllc.entities.User;
import com.abbayllc.persistence.CustomPersistenceProviderInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.*;

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
            //creating instances
            Student student = new Student();
            student.setName("Abebe Demis");

            Course course1 = new Course();
            course1.setTitle("Object oriented programming");
            Course course2 = new Course();
            course2.setTitle("Javascript in depth");

            Course course3 = new Course();
            course3.setTitle("Spring framework in depth");

            student.setCourses(List.of(course1,course2,course3));



            manager.persist(student);
            manager.getTransaction().commit();


            Student stu= manager.find(Student.class,2l);
            List<Course> abeCourses = stu.getCourses();
            for(Course c : abeCourses){
                System.out.println(c.getTitle());
            }

        }finally {
            manager.close();
        }

    }
}