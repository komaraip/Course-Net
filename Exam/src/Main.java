import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;

class Stakeholder {
    private String ID;
    private String name;
    private int age;

    public Stakeholder(String name, int age) {
        this.ID = generateId();
        this.name = name;
        this.age = age;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }
}

class Lecturer extends Stakeholder {
    private double salary;
    private int totalClass;
    private String faculty;

    public Lecturer(String name, int age, double salary, int totalClass, String faculty) {
        super(name, age);
        this.salary = salary;
        this.totalClass = totalClass;
        this.faculty = faculty;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getTotalClass() {
        return totalClass;
    }

    public void setTotalClass(int totalClass) {
        this.totalClass = totalClass;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

class Student extends Stakeholder {
    private double GPA;
    private String major;

    public Student(String name, int age, double GPA, String major) {
        super(name, age);
        this.GPA = GPA;
        this.major = major;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

public class Main {
    private static ArrayList<Stakeholder> stakeholders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int lecturerCount = 0;
    private static int studentCount = 0;

    private static void displayMainMenu() {
        System.out.println("Course-Net Stakeholder");
        System.out.println("======================");
        System.out.println("1. Add Stakeholder");
        System.out.println("2. Update Stakeholder");
        System.out.println("3. View Summary");
        System.out.println("4. View Detail");
        System.out.println("5. Exit");
        System.out.print("Choice [1..5] : ");
    }

    private static int getValidMenuChoice() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 5) {
                    break;
                } else {
                    System.out.print("Choice [1..5] : ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Choice [1..5] : ");
            }
        }
        return choice;
    }

    private static int getValidNumberInputINT(int min, int max) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number >= min && number <= max) {
                    break;
                } else {
                    System.out.print("Invalid input. Enter a valid number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number between " + min + " and " + max + ": ");
            }
        }
        return number;
    }

    private static double getValidNumberInputDOUBLE(double min, double max) {
        double number;
        while (true) {
            try {
                number = Double.parseDouble(scanner.nextLine());
                if (number >= min && number <= max) {
                    break;
                } else {
                    System.out.print("Invalid input. Enter a valid number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a valid number between " + min + " and " + max + ": ");
            }
        }
        return number;
    }
    
    private static String generateLecturerId() {
    	lecturerCount++;
        return "L" + String.format("%03d", lecturerCount + studentCount);
    }

    private static String generateStudentId() {
    	studentCount++;
        return "S" + String.format("%03d", studentCount + lecturerCount);
    }
    
    private static String generateFormattedId(Stakeholder stakeholder, int index) {
        String prefix = stakeholder instanceof Lecturer ? "L" : "S";
        return prefix + String.format("%03d", index + 1);
    }

    private static void displayStakeholderDetail(Stakeholder stakeholder) {
    	String formattedId = generateFormattedId(stakeholder, stakeholders.indexOf(stakeholder));
        System.out.println("ID               : " + formattedId);
        System.out.println("Name             : " + stakeholder.getName());
        System.out.println("Age              : " + stakeholder.getAge());
        
        if (stakeholder instanceof Lecturer) {
            Lecturer lecturer = (Lecturer) stakeholder;
            System.out.println("Salary           : " + lecturer.getSalary());
            System.out.println("Total Class      : " + lecturer.getTotalClass());
            System.out.println("Faculty          : " + lecturer.getFaculty());
        } else if (stakeholder instanceof Student) {
            Student student = (Student) stakeholder;
            System.out.println("GPA              : " + student.getGPA());
            System.out.println("Major            : " + student.getMajor());
        }
        
        System.out.println("\n");
    }
	
	private static Stakeholder findStakeholderById(String id) {
	    for (Stakeholder stakeholder : stakeholders) {
	        String formattedId = generateFormattedId(stakeholder, stakeholders.indexOf(stakeholder));
	        if (formattedId.equals(id)) {
	            return stakeholder;
	        }
	    }
	    return null;
	}

    private static void addStakeholder() {
        String role;
        do {
            System.out.print("Input your role [Lecturer | Student] : ");
            role = scanner.nextLine();
        } while (!role.equalsIgnoreCase("Lecturer") && !role.equalsIgnoreCase("Student"));
        
        System.out.print("Input name [5..50] : ");
        String name = scanner.nextLine();
        while (name.length() < 5 || name.length() > 50) {
            System.out.print("Invalid input. Enter a name between 5 and 50 characters: ");
            name = scanner.nextLine();
        }

        System.out.print("Input age [18..50] : ");
        int age = getValidNumberInputINT(18, 50);

        String id;
        if (role.equalsIgnoreCase("Lecturer")) {
            System.out.print("Input salary [5000000..50000000] : ");
            double salary = getValidNumberInputDOUBLE(5000000, 50000000);

            System.out.print("Total class [1..5] : ");
            int totalClass = getValidNumberInputINT(1, 5);

            System.out.print("Faculty [Computer Science | Design | Accounting] : ");
            String faculty;
            do {
                faculty = scanner.nextLine();
            } while (!faculty.equalsIgnoreCase("Computer Science")
                    && !faculty.equalsIgnoreCase("Design")
                    && !faculty.equalsIgnoreCase("Accounting"));

            id = generateLecturerId();
            stakeholders.add(new Lecturer(name, age, salary, totalClass, faculty));
        } else {
            System.out.print("Input GPA [0..4] : ");
            double gpa = getValidNumberInputDOUBLE(0, 4);

            System.out.print("Major [Mobile Technology | Cyber Security | Games Technology] : ");
            String major;
            do {
                major = scanner.nextLine();
            } while (!major.equalsIgnoreCase("Mobile Technology")
                    && !major.equalsIgnoreCase("Cyber Security")
                    && !major.equalsIgnoreCase("Games Technology"));

            id = generateStudentId();
            stakeholders.add(new Student(name, age, gpa, major));
        }
        
        System.out.println("Stakeholder ID : " + id);
    }

    private static void updateStakeholder() {
    	if (stakeholders.isEmpty()) {
            System.out.println("No stakeholders to update.\n");
            return;
        }
    	
    	System.out.println("List Stakeholder");
        System.out.println("| No | ID            | Name                  | Role      |");
        System.out.println("==========================================================");
        
        for (int i = 0; i < stakeholders.size(); i++) {
            Stakeholder stakeholder = stakeholders.get(i);
            String role = stakeholder instanceof Lecturer ? "Lecturer" : "Student";
            String id = generateFormattedId(stakeholder, i);
            System.out.printf("| %2d | %-13s | %-21s | %-9s |\n\n", i + 1, id, stakeholder.getName(), role);
        }

        System.out.print("Update number [1.." + stakeholders.size() + "] : ");
        int updateNumber = getValidNumberInputINT(1, stakeholders.size());

        Stakeholder selectedStakeholder = stakeholders.get(updateNumber - 1);

        System.out.print("Input name [5..50] : ");
        String newName = scanner.nextLine();
        while (newName.length() < 5 || newName.length() > 50) {
            System.out.print("Invalid input. Enter a name between 5 and 50 characters: ");
            newName = scanner.nextLine();
        }
        selectedStakeholder.setName(newName);

        System.out.print("Input age [18..50] : ");
        int newAge = getValidNumberInputINT(18, 50);
        selectedStakeholder.setAge(newAge);

        if (selectedStakeholder instanceof Lecturer) {
            Lecturer lecturer = (Lecturer) selectedStakeholder;

            System.out.print("Input salary [5000000..50000000] : ");
            double newSalary = getValidNumberInputDOUBLE(5000000, 50000000);
            lecturer.setSalary(newSalary);

            System.out.print("Total class [1..5] : ");
            int newTotalClass = getValidNumberInputINT(1, 5);
            lecturer.setTotalClass(newTotalClass);

            System.out.print("Faculty [Computer Science | Design | Accounting] : ");
            String newFaculty;
            do {
                newFaculty = scanner.nextLine();
            } while (!newFaculty.equalsIgnoreCase("Computer Science")
                    && !newFaculty.equalsIgnoreCase("Design")
                    && !newFaculty.equalsIgnoreCase("Accounting"));
            lecturer.setFaculty(newFaculty);
        } else if (selectedStakeholder instanceof Student) {
            Student student = (Student) selectedStakeholder;

            System.out.print("Input GPA [0..4] : ");
            double newGPA = getValidNumberInputDOUBLE(0, 4);
            student.setGPA(newGPA);

            System.out.print("Major [Mobile Technology | Cyber Security | Games Technology] : ");
            String newMajor;
            do {
                newMajor = scanner.nextLine();
            } while (!newMajor.equalsIgnoreCase("Mobile Technology")
                    && !newMajor.equalsIgnoreCase("Cyber Security")
                    && !newMajor.equalsIgnoreCase("Games Technology"));
            student.setMajor(newMajor);
        }

        System.out.println("Stakeholder updated.\n");
    }
  
    private static void viewSummary() {
        if (stakeholders.isEmpty()) {
            System.out.println("No stakeholders to display.\n");
            return;
        }

        System.out.println("List Stakeholder");
        System.out.println("| No | ID            | Name                  | Role      |");
        System.out.println("==========================================================");
        
        for (int i = 0; i < stakeholders.size(); i++) {
            Stakeholder stakeholder = stakeholders.get(i);
            String role = stakeholder instanceof Lecturer ? "Lecturer" : "Student";
            String id = generateFormattedId(stakeholder, i);
            System.out.printf("| %2d | %-13s | %-21s | %-9s |\n\n", i + 1, id, stakeholder.getName(), role);
        }
    }


 
    private static void viewDetail() {
    	if (stakeholders.isEmpty()) {
            System.out.println("No stakeholders to display.\n");
            return;
        }

        System.out.println("List Stakeholder");
        System.out.println("| No | ID            | Name                  | Role      |");
        System.out.println("==========================================================");
        
        for (int i = 0; i < stakeholders.size(); i++) {
            Stakeholder stakeholder = stakeholders.get(i);
            String role = stakeholder instanceof Lecturer ? "Lecturer" : "Student";
            String id = generateFormattedId(stakeholder, i);
            System.out.printf("| %2d | %-13s | %-21s | %-9s |\n\n", i + 1, id, stakeholder.getName(), role);
        }
        
        System.out.print("ID to view detail : ");
        String detailId = scanner.nextLine();
        
        Stakeholder selectedStakeholder = findStakeholderById(detailId);
        if (selectedStakeholder != null) {
            displayStakeholderDetail(selectedStakeholder);
        } else {
            System.out.println("Stakeholder with ID " + detailId + " not found.\n");
        }
    }
    
    public static void main(String[] args) {
        int choice;
        do {
            displayMainMenu();
            choice = getValidMenuChoice();
            switch (choice) {
                case 1:
                    addStakeholder();
                    break;
                case 2:
                    updateStakeholder();
                    break;
                case 3:
                    viewSummary();
                    break;
                case 4:
                    viewDetail();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 5);
    }
}

               









