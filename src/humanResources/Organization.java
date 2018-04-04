package humanResources;

public class Organization {
    private String name;
    private Department[] departments;
    private int size;
    private final static int DEFAULT_SIZE = 8;

    //todo: use system.systemarraycopy everywhere as possible

    public Organization(String name) {
        this(name, new Department[DEFAULT_SIZE]);
    }

    public Organization(String name, Department[] departments) {
        this.size = 0; //departments.length;
        this.name = name;
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public Department[] getDepartments() {
        Department[] department = new Department[size];
        /*for (int i = 0; i < getDepartmentSize(); i++)
            if (departments[i] != null)
                department[i] = departments[i];*/
        System.arraycopy(departments, 0, department,0, size);
        return department;
    }

    public int getDepartmentSize() {
        /*int Counter = 0;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null)
                Counter++;
            else break;
        }
        return Counter;*/
        return size;
    }

    public Department getDepartments(String name) {
        for (int i = 0; i < size; i++) {
            //if(departments[i] != null) {
            if (departments[i] != null & departments[i].getDepartmentName().equals(name))
                return departments[i];
            //}
        }
        return null;
    }

    public Employee getBestEmployee() {
        int maxSalary = 0, index = 0;
        Employee[] employee;
        for (int i = 0; i < size; i++) {
            if (departments[i] != null) {
                employee = departments[i].getEmployeesSortedBySalary();
                //if(employee[0] != null) {
                if (employee[0] != null & employee[0].getSalary() > maxSalary) {
                    maxSalary = employee[0].getSalary();
                    index = i;
                }
                //}
            }
        }
        employee = departments[index].getEmployeesSortedBySalary();
        return employee[0];
    }

    public Department getEmployeesDepartment(String firstName, String secondName) {
        Employee[] employee;
        for (int i = 0; i < size; i++) {
            if (departments[i] != null) {
                employee = departments[i].getEmployees();
                for(int j = 0; j < employee.length; j++) {
                    //if (employee[j] != null) {
                    if (employee[j] != null & (employee[j].getFirstName().equals(firstName) && employee[j].getSecondName().equals(secondName)))
                        return departments[i];
                    //}
                }
            }
        }
        return null;
    }

    public int getEmployeesQuantity(String jobTitle) {
        int quantity = 0;
        for (int i = 0; i < size; i++) {
            if(departments[i] != null) {
                quantity += departments[i].getEmployees(jobTitle).length;
            }
        }
        return quantity;
    }

    public int getEmployeesQuantity() {
        int quantity = 0;
        for (int i = 0; i < size; i++) {
            if(departments[i] != null) {
                quantity += departments[i].getSize();
            }
        }
        return quantity;
    }

    public void addDepartment(Department department) {
        if(department == null)
            return;
        if (size == departments.length) {
            Department[] departments = new Department[this.departments.length * 2];
            /*for (int i = 0; i < this.departments.length; i++) {
                if (this.departments[i] != null)
                    departments[i] = this.departments[i];
            }*/
            System.arraycopy(this.departments, 0, departments,0, size);
            this.departments = departments;
        }
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] == null) {
                departments[i] = department;
                size++;
                break;
            }
        }
    }

    public boolean removeDepartment(String name) {
        for (int i = 0; i < size; i++) {
            //if(departments[i] != null) {
            if (departments[i] != null & departments[i].getDepartmentName().equals(name)) {
                /*for (int j = i; j < size - 1; j++) {
                    departments[j] = departments[j + 1];
                }*/
                if(i < departments.length - 1)
                    System.arraycopy(departments, i + 1, departments, i, size - i - 1);
                departments[size - 1] = null;
                size--;
                return true;
            }
            //}
        }
        return false;
    }
}
