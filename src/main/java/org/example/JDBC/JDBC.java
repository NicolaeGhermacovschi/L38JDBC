package org.example.JDBC;

import org.example.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC {
    static String URL = "jdbc:postgresql://localhost:5432/L39DB";
    static String user = "postgres";
    static String password = "admin";

    public void menuSQL(int nr) {


        try (Connection conn = DriverManager.getConnection(URL, user, password);
             Statement statement = conn.createStatement()) {

            if (nr == 1) {
                showDate(conn, statement);
            } else if (nr == 2) {
                addData(conn, statement, new Employee(59, "Anton", "Anton", 58, "Chisinau", 2000, 9));

            } else if (nr == 3) {
                upDateData(conn, statement);
            } else if (nr == 4) {
                deleteData(conn, statement);
            } else if (nr == 5) {
                methodMaxMinAge(conn, statement);
            } else {
                System.out.println("EXIT");
            }





        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showDate(Connection conn, Statement statement) throws SQLException {
        if (conn != null) {
            ResultSet rs = statement.executeQuery("SELECT * FROM employee;");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
                        + " " + rs.getInt(4) + " " + rs.getString(5) + " " + rs.getInt(6) +
                        " " + rs.getInt(7));
            }
        } else {
            System.out.println(" Not Connected from SHOW ");
        }
    }

    private void addData(Connection conn, Statement statement, Employee employee) throws SQLException {
        if (conn != null) {
            boolean rs = statement.execute("INSERT INTO employee VALUES('" + employee.getId() + "','" + employee.getLastName() +
                    "','" + employee.getFirstName() + "','" + employee.getAge() + "','" + employee.getAddress() + "','" +
                    employee.getSalary() + "','" + employee.getIdCompany() + "');");

            if (!rs) System.out.println("Successfully Inserted");
            else System.out.println("Insert Failed");
        } else {
            System.out.println(" Not Connected from ADD ");
        }
    }

    private void deleteData(Connection conn, Statement statement) throws SQLException {
        if (conn != null) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter ID employe :");
            int id = sc.nextInt();
            sc.close();

            boolean rs = false;

                rs = statement.execute("DELETE FROM employee WHERE employee_id = " + id + ";");



            if (!rs) System.out.println("Success Delete");
            else System.out.println("Delete Failed");
        } else {
            System.out.println(" Not Connected from DELETE ");
        }
    }

    private void upDateData(Connection conn, Statement statement) throws SQLException {
        if (conn != null) {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter ID employe :");
            int id = sc.nextInt();
            System.out.println("Enter Adress :");
            String address = sc.next();
            sc.close();

            boolean rs = statement.execute("UPDATE employee SET adress = '" + address + "' WHERE employee_id = " + id);

            if (!rs) System.out.println("Success UPDATE");
            else System.out.println("UPDATE Failed");
        } else {
            System.out.println(" Not Connected FROM update");
        }
    }

    private void methodMaxMinAge(Connection conn, Statement statement) throws SQLException {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();

        if (conn != null) {

            ResultSet rs = statement.executeQuery("SELECT * FROM employee;");
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt(1));
                emp.setFirstName(rs.getString(2));
                emp.setLastName(rs.getString(3));
                emp.setAge(rs.getInt(4));
                emp.setAddress(rs.getString(5));
                emp.setSalary(rs.getInt(6));
                emp.setIdCompany(rs.getInt(7));
                employeeArrayList.add(emp);
            }


            if (!employeeArrayList.isEmpty()) {
                int maxAge = employeeArrayList.get(0).getAge();

                for (Employee employee : employeeArrayList) {
                    if (employee.getAge() > maxAge)
                        maxAge = employee.getAge();
                }
                System.out.println("max :" + maxAge);

                int minAge = employeeArrayList.get(0).getAge();
                for (Employee employee : employeeArrayList) {
                    if (employee.getAge() < minAge)
                        minAge = employee.getAge();
                }
                System.out.println("min : " + minAge);

                for (Employee employee : employeeArrayList) {
                    if (employee.getAge() == maxAge) {
                        System.out.println("Max age from Employee : " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getAge());
                    }
                    if (employee.getAge() == minAge) {
                        System.out.println("Min age from Employee : " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getAge());
                    }
                }
            }
        } else {
            System.out.println(" Not Connected from SHOW MAX and MIN");
        }






    }

}
