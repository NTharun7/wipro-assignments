package assignment;

public class GradeA {
	
	    public static void main(String[] args) {
	        int totalStudents = 90;

	        int gradeAStudents = totalStudents / 2; // 50% of total = 45
	        int boysWithA = 20;

	        int girlsWithA = gradeAStudents - boysWithA;
	        System.out.println("Total number of girls who got grade A: " + girlsWithA);
	    }
	}

