package humanResources;

public class Employee{
    private String firstName;
    private String secondName;
    private String jobTitle;
    private int salary;
    private final static int DEFAULT_SALARY = 0;

    public Employee(){
        this("First Name","Second Name","Job Title", DEFAULT_SALARY);
    }
    public Employee(String firstName, String secondName, String jobTitle, int salary){
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    //todo second constructor

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String value){
        firstName = value;
    }
    public String getSecondName(){
        return secondName;
    }
    public void setSecondName(String value){
        secondName = value;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(String value){
        jobTitle = value;
    }
    public int getSalary(){
        return salary;
    }
    public void setSalary(int value){
        salary = value;
    }
}
