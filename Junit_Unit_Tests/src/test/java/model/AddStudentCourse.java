package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Date;

/**
 *  Implement and test {Course.addStudentCourse } that respects the following:
 *
 *  <ul>
 *
 *      <li> Get the number of courses a student is enrolled in </li>
 *      <li> A student cannot be enrolled in more than 4 courses</li>
 *      <li>Throws an exception if a student is already enrolled in a course</li>
 *      <li>Get the enrollments of the course</li>
 *      <li>A course cannot have more than 100 students</li>
 *      <li>If the above constraints are met then enroll a student in a course i.e. add a new student to the course and return true. If not  then return false</li>
 *  </ul>
 *
 */
public class AddStudentCourse {
    
	Course course;
	
    @BeforeEach
    void setUp(){
        course = new Course();
        System.out.println("course created");
    }

    // This tests if the return statement of AddStudentCourse is True when a valid student is added
    // valid means that the student follows all requirements set by AddStudentCourse:
    // - Number of courses the student is enrolled is has to be less than 4
    // - Number of students in the course the student is trying to enrol cannot be greater than a 100 (inclusive)
    // - The student must not already be enrolled in the course they are trying to enrol in 
    @Test
    void AddStudentCourse_True_ValidStudent(){
        Student student = new Student("James", 001);
        try {
            assertEquals(true,course.addStudentCourse(student));
        }catch (IllegalStudentEnrollException e) {}
    }

    // This tests that when the same student tries to enrol in a course they are already enrolled, AddStudentCourse returns a False
    // showing that the student was not successfully enrolled into that course
    @Test
    void AddStudentCourse_False_AddingExistingStudent(){
        Student student = new Student("James", 001);
        
        try {
            course.addStudentCourse(student);
            assertEquals(false,course.addStudentCourse(student));
        }catch (IllegalStudentEnrollException e) {}
    }

    // This tests that when the course is full (over a 100 students) and another student tries to enrol in the course
    // , AddStudent returns a False showing that the student was not successfully enrolled into the course 
    @Test
    void AddStudentCourse_False_FullCourse(){
        try {
            for (int i = 1; i < 201; i++) {
                String name = "James" + Integer.toString(i);
                Student student = new Student(name, i);
                course.addStudentCourse(student);
            }
            

            Student student_number_101 = new Student("NewStudentJames",202);
            assertEquals(false,course.addStudentCourse(student_number_101));
        }catch (IllegalStudentEnrollException e) {}
        
    }

    // This tests that the IllegalStudentEnrollExpection thrown by the AddStudentCourse function is 
    // the exception thrown when the course is full and the student was unsuccesfully enrolled
    // , this was achieved by check that the message of the thrown exception is the message for an already full course.
    @Test
    void AddStudentCourse_True_FullCourseExceptionMessage(){
        try {
            for (int i = 1; i < 201; i++) {
                String name = "James" + Integer.toString(i);
                Student student = new Student(name, i);
                course.addStudentCourse(student);
            }
            

            Student student_number_101 = new Student("NewStudentJames",202);
            course.addStudentCourse(student_number_101);
        }catch (IllegalStudentEnrollException e) {
            assertEquals("This course is currently full.",e.getMessage());
        }
        
    }
    // This tests that the IllegalStudentEnrollExpection thrown by the AddStudentCourse function is 
    // the exception thrown when the student is already enrolled in the course that they are trying to enrol in
    // and the student was unsuccesfully enrolled, this was achieved by check that the message of the thrown exception
    // is the message for an already student enrolling again.
    @Test
    void AddStudentCourse_False_AddingExistingStudentExceptionMessage(){
        Student student = new Student("James", 001);
        
        try {
            course.addStudentCourse(student);
            course.addStudentCourse(student);
        }catch (IllegalStudentEnrollException e) {
            assertEquals("This student is already enrolled in this course.",e.getMessage());
        }
    }

    // This tests that the AddStudentCourse function throws an expection of class
    // IllegalStudentEnrollException when a new student tries to enrol when the course is full
    @Test
    void AddStudentCourse_IllegalStudentEnrollException_FullCourse_Exception() throws IllegalStudentEnrollException{

        for (int i = 1; i < 102; i++) {
            String name = "James" + Integer.toString(i);
            Student student = new Student(name, i);
            course.addStudentCourse(student);
        }
        

        Student student_number_101 = new Student("NewStudentJames",202);
        //assertEquals(false,course.addStudentCourse(student_number_101));
        assertThrows(Exception.class,()-> course.addStudentCourse(student_number_101));
        
    }

    // This tests that the AddStudentCourse function throws an expection of class
    // IllegalStudentEnrollException when a student trying to enrol is already enrolled in the course.
    @Test
    void AddStudentCourse_IllegalStudentEnrollException_Duplicate_Students_Exception() throws IllegalStudentEnrollException{

        Student student = new Student("James", 001);
        course.addStudentCourse(student);
        //assertEquals(false,course.addStudentCourse(student_number_101));
        assertThrows(Exception.class,()-> course.addStudentCourse(student));
        
    }

    // This tests that when the student trying to enrol in a course already has more that 4 course then the return statement
    // of AddStudentCourse is false.
    @Test
    void AddStudentCourse_False_NumCoursesGreaterThanFive(){
        
        Student student_number_101 = new Student("NewStudentJames",202);
        student_number_101.setNumberOfCourses(5);
        try {
            assertEquals(false,course.addStudentCourse(student_number_101));
        }catch (IllegalStudentEnrollException e) {}
        
    }

}