package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project inside the company.
 */
public class Course {
    /**
     * Name the course
     */
    private String name;

    /**
     * Start date of the course
     */
    private Date startDate;

    /**
     * End date of the course
     */
    private Date dueDate;

    /**
     * Estimated duration of the course in months
     */
    private int estimatedDuration;

    /**
     * Students allocated to the course
     */
    private List<Student> enrolled = new ArrayList<Student>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public List<Student> getEnrollments() {
        return enrolled;
    }

    
    public boolean removeEnrolledStudent(Student student) {
    
    	return false;
    	   
    }


    /**
     * Verifies the course has valid dates.
     * @return true if dates are valid, false otherwise.
     */
    public boolean isValidDates(){
       return false;
    }

  

    /**
     * Add a new student to the course
     * @param Student: to enroll  to student in a course 
     * @return true if the student is successfully enrolled, false otherwise
     * @throws IllegalStudentEnrollException
     */

    public boolean addStudentCourse(Student student) throws IllegalStudentEnrollException{
    	int numCourses = student.getNumberOfCourses();
        List<Student> enrollment = getEnrollments();
        boolean validEnrollment = false;
        boolean returnStatement = false;
        
        if (numCourses < 5){
            
                for (int i = 0; i < enrollment.size(); i++) {
                    Student enrolledStudent = enrollment.get(i);
                    if (student.getName() == enrolledStudent.getName() && student.getId() == enrolledStudent.getId()){
                        validEnrollment = false;
                        throw new IllegalStudentEnrollException("This student is already enrolled in this course.");
                    }
                }
                validEnrollment = true;
                if (enrollment.size() > 100) {
                    validEnrollment = false;
                    throw new IllegalStudentEnrollException("This course is currently full.");
                }
                else {
                    validEnrollment = true;
                }

                if (validEnrollment) {
                    returnStatement = enrollment.add(student);
                }
                else {
                    returnStatement = false;
                }

            


        }
        return returnStatement;
   
    }




}
