package com.company.javabeans;

import java.lang.reflect.Method;

/**
 * Utility class to demonstrate JavaBean introspection via reflection.
 */
public class JavaBeanProcessor {

    // Print all properties by calling getters
    public static void printAllProperties(EmployeeBean emp) {
        try {
            Method[] methods = EmployeeBean.class.getMethods();
            for (Method m : methods) {
                if ((m.getName().startsWith("get") || m.getName().startsWith("is"))
                        && m.getParameterCount() == 0) {
                    Object value = m.invoke(emp);
                    String property = m.getName().replaceFirst("get|is", "");
                    System.out.println(property + ": " + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Copy all properties from one EmployeeBean to another
    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            Method[] methods = EmployeeBean.class.getMethods();
            for (Method m : methods) {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
                    Object value = m.invoke(source);
                    String setterName = "set" + m.getName().substring(3);
                    try {
                        Method setter = EmployeeBean.class.getMethod(setterName, m.getReturnType());
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {
                        // skip if setter doesn't exist (e.g., read-only)
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test main
    public static void main(String[] args) {
        EmployeeBean emp1 = new EmployeeBean("E003", "Charlie", "Brown", 50000,
                "IT", java.time.LocalDate.of(2019, 8, 20), true);
        EmployeeBean emp2 = new EmployeeBean();

        System.out.println("Properties of emp1:");
        printAllProperties(emp1);

        System.out.println("\nCopying properties from emp1 to emp2...");
        copyProperties(emp1, emp2);
        System.out.println("emp2 after copy: " + emp2);
    }
}
