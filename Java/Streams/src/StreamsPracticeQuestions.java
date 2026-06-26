import java.util.*;
import java.util.stream.Collectors;


class Employee {
    String name;
    int salary;
    String department;

    public Employee(String name, int salary,String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
}

public class StreamsPracticeQuestions {

    public static void main(String[] args) {
        List<Employee> empList = Arrays.asList(
                new Employee("John",45000,"Banking"),
                new Employee("Vishwas",56000,"IT"),
                new Employee("Pooja",34000,"Banking"),
                new Employee("Ramesh",89000,"Civil"),
                new Employee("Kevin",34000,"IT")

        );
//        find the employee with highest salary
        Optional<Employee> highestPaid = empList.stream().max(Comparator.comparingDouble(Employee::getSalary));
        highestPaid.ifPresent(employee -> System.out.println("Highest : "+employee.name));

//        find the employee with second highest paid salary
        Optional<Employee> secondHighestPaid = empList.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();
        secondHighestPaid.ifPresent(employee -> System.out.println("Second Highest:"+employee.name));

//        find the employee with lowest paid salary
        Optional<Employee> lowestPaid = empList.stream().min(Comparator.comparingDouble(Employee::getSalary));
        lowestPaid.ifPresent(employee -> System.out.println("Lowest:"+employee.name));

//        find the average salary of employees
        OptionalDouble emp = empList.stream().mapToDouble(Employee::getSalary).average();
        emp.ifPresent(System.out::println);

        // count the total number of employees

        long empCount = empList.stream().mapToInt(Employee::getSalary).count();
        System.out.println(empCount);

        //Group employees by department
        Map<String, List<String>> employeeNamesByDept = empList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                       Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        System.out.println(employeeNamesByDept);

        //Count employees by each Department

        Map<String, Long> countEmployeeByDept = empList.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));

        System.out.println(countEmployeeByDept);

        // highest paid employee in each department


        Map<String, Integer> highestPaidEmpInEachDept = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
                                opt -> opt.map(Employee::getSalary).orElse(0))));
        System.out.println(highestPaidEmpInEachDept);
//





    }
}
