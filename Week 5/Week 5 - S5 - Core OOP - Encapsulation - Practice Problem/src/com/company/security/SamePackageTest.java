package com.company.security;

public class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo obj = new AccessModifierDemo(42, "World", 55.5, false);

        System.out.println("\nInside SamePackageTest:");

        System.out.println("publicField = " + obj.publicField);
        obj.publicMethod();

        System.out.println("defaultField = " + obj.defaultField);
        obj.defaultMethod();

        System.out.println("protectedField = " + obj.protectedField);
        obj.protectedMethod();

        // private method/field are NOT accessible here
        // obj.privateMethod(); // would cause compile error
    }
}
