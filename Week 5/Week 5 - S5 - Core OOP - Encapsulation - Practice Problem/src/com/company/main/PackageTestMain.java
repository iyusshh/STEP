package com.company.main;

import com.company.security.AccessModifierDemo;

public class PackageTestMain {
    public static void main(String[] args) {
        AccessModifierDemo obj = new AccessModifierDemo(1, "CrossPkg", 2.2, true);

        System.out.println("=== Cross-Package Access Test ===");

        // ❌ privateField not accessible
        // System.out.println(obj.privateField);

        // ❌ defaultField not accessible (default = package-private)
        // System.out.println(obj.defaultField);

        // ❌ protectedField not accessible (only accessible via subclass, not plain object)
        // System.out.println(obj.protectedField);

        // ✅ publicField accessible everywhere
        System.out.println("publicField = " + obj.publicField);

        // ❌ privateMethod() not accessible
        // obj.privateMethod();

        // ❌ defaultMethod() not accessible
        // obj.defaultMethod();

        // ❌ protectedMethod() not accessible directly
        // obj.protectedMethod();

        // ✅ publicMethod accessible everywhere
        obj.publicMethod();
    }
}
