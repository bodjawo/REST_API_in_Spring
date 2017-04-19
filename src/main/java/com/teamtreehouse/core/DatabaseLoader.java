package com.teamtreehouse.core;

import com.teamtreehouse.course.Course;
import com.teamtreehouse.course.CourseRepository;
import com.teamtreehouse.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;
    
    @Autowired
    public DatabaseLoader(CourseRepository courses) {
        this.courses = courses;
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics", "https://teamtreehouse.com/library/java-basics");
        course.addReview(new Review(3, "My new comment for description"));
        courses.save(course);
    
        String[] templates = {
            "Up and Running with %s",
            "%s Basics",
            "%s for Beginners",
            "%s for Neckheards",
            "Under the hood: %s"
        };
    
        String[] buzzwords = {
            "Spring REST Data",
            "Java 9",
            "Scala",
            "Groovy",
            "Hibernate",
            "Spring HATEOAS"
        };
    
        List<Course> courseList = new ArrayList<>();
        IntStream.range(0, 100)
            .forEach(i -> {
                String template = templates[i % templates.length];
                String buzzword = buzzwords[i % buzzwords.length];
                String title = String.format(template, buzzword);
                Course course1 = new Course(title, "http://example.com");
                course1.addReview(new Review(i % 5, String.format("More %s please", buzzword)));
                courseList.add(course1);
            });
        courses.save(courseList);
    }
}
