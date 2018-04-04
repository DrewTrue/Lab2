package humanResources;

public class Department {
    private String name;
    private int size;
    private Employee[] employees;
    private final static int DEFAULT_SIZE = 8;

    public Department(String name) {
        this(name, DEFAULT_SIZE);
    }

    public Department(String name, int size) {
        this(name, new Employee[size]);
    }

    public Department(String name, Employee[] employees) {
        this.size = 0;//employees.length;
        this.name = name;
        this.employees = employees;
    }

    public String getDepartmentName() {
        return name;
    }

    //todo: use system.arraycopy
    public Employee[] getEmployees() {
        Employee[] employees = new Employee[size];
        //for (int i = 0; i < this.employees.length; i++){
        //if (this.employees[i] != null) {
        //employees[counter] = this.employees[i];
        //counter++;
        System.arraycopy(this.employees,0,employees,0,size);
        //  }
        return employees;
    }

    public Employee[] getEmployees(String jobTitle) {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (employees[i] != null && employees[i].getJobTitle().equals(jobTitle))
                counter++;
        }
        Employee[] employee = new Employee[counter];
        counter = 0;
        for (int i = 0; i < size; i++) {
            if(employees[i] != null){
                if (employees[i].getJobTitle().equals(jobTitle)) {
                    //employee[counter] = employees[i];
                    System.arraycopy(employees, i, employee, counter, 1);
                    counter++;
                }
            }
        }
        return employee;
    }

    public int getSize() {
        /*int size = 0;
        for(int i = 0; i < employees.length; i++){
            if(employees[i] != null)
                size++;
        }
        return size;*/
        return size;
    }

    public Employee[] getEmployeesSortedBySalary() {
        Employee[] employees = this.employees;
        if(size > 1) {
            quickSort(employees, 0, size - 1);
            return employees;
        }
        return employees;
    }

    public void swapEmployee(Employee[] employees, int i, int j) {
        Employee template = employees[i];
        employees[i] = employees[j];
        employees[j] = template;
    }

    public void quickSort(Employee[] employees, int begin, int end) {
        int i = begin, j = end, pivot = employees[(begin + end) / 2].getSalary();
        do {
            while (employees[i].getSalary() > pivot && i < end) i++;
            while (employees[j].getSalary() < pivot && j > begin) j--;
            if (i <= j) {
                swapEmployee(employees, i, j);
                i++;
                j--;
            }
        }
        while (i <= j);
        if (begin < j) quickSort(employees, begin, j);
        if (i < end) quickSort(employees, i, end);
    }

    public boolean removeEmployee(String firstName, String secondName) {
        for (int i = 0; i < size; i++) {
            //todo use system.arraycopy
            if (employees[i].getFirstName().equals(firstName) & employees[i].getSecondName().equals(secondName)) {
                //for (int j = i; j < getSize() - 1; j++)
                //employees[j] = employees[j + 1];
                if (i < employees.length - 1)
                    System.arraycopy(employees, i + 1, employees, i, size - i - 1);
                employees[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    //todo system.arraycopy
    public void addEmployee(Employee employee) {
        if(employee == null)
            return;
        //if (getDepartmentSize() == size - 1) {
        if (size == employees.length) {
            Employee[] employees = new Employee[this.employees.length * 2];
            //for (int i = 0; i < getSize(); i++) {
            //employees[i] = this.employees[i];
            System.arraycopy(this.employees,0,employees,0,size);
            //}
            this.employees = employees;
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                size++;
                break;
            }
        }
    }

    //ДОП ЗАДАНИЕ

    public String getEmployeeInformation(String firstName, int newSalary){
        // имя отдел старая зарплата новая зарплата изменения зарплаты
        int oldSalary = 1;
        double difference = 0;
        for(int i = 0; i < size; i++){
            if(employees[i].getFirstName().equals(firstName)) {
                if(employees[i].getSalary() != 0)
                    oldSalary = employees[i].getSalary();
                employees[i].setSalary(newSalary);
                difference = (100.0 - ((double)newSalary / oldSalary)* 100);
                return (this.name + " " + oldSalary + " " + newSalary + " " + difference + "%");
            }
        }
        return null;
    }
}