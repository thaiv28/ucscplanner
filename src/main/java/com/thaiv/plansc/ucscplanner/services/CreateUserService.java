package com.thaiv.plansc.ucscplanner.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Service;

import com.thaiv.plansc.coursedb.models.Course;
import com.thaiv.plansc.coursedb.repositories.CourseRepository;
import com.thaiv.plansc.ucscplanner.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateUserService {

    private final CourseService courseService;

    public User createUser(File file){
        int mpe = 0;
        ArrayList<Course> perms = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<String> aps = new ArrayList<>();

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()) {
                String courseString = scanner.nextLine().replaceAll("\\s", "");
                System.out.println("createUserService courseString: " + courseString);
                if(courseString.contains("MPE_")){
                    mpe = Integer.parseInt(returnCode(courseString, "(?<=MPE_)\\d+"));
                    continue;
                }
                if(courseString.contains("PERM_")){
                    Course newPermCourse = courseService.getById(
                        returnCode(courseString, "(?<=PERM_\\s).+")).get();
                    perms.add(newPermCourse);
                    continue;
                }
                if(courseString.contains("AP_")){
                    aps.add(returnCode(courseString, "(?<=AP_\\s).+"));
                    continue;
                }
                
                courses.add(courseService.getById(courseString).get());
            }
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
            System.exit(2);
        }

        return new User(courses, mpe, perms, aps);
    }

    public String returnCode (String courseString, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(courseString);

        if(matcher.find()){
            return matcher.group();
        } else {
            return null;
        }
    }

}
