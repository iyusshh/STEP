package com.company.extended;

import com.company.security.AccessModifierDemo;

public class ExtendedDemo extends AccessModifierDemo {

    // Constructor that calls parent constructor
    public ExtendedDemo(int p, String d, double pr, boolean pub) {
        super(p, d, pr, pub);
    }

    public void testInheritedAccess() {
        System.out.println("\n=== Subclass (Cross-Package) Access Test ===");

        // ❌ privateField not inherited
        // System.out.println(privateField);

        // ❌ defaultField not accessible (different package, not subclass)
        // System.out.println(defaultField);

        // ✅ protectedField accessible (inherited in subclass)
        System.out.println("protectedField = " + protectedField);

        // ✅ publicField always accessible
        System.out.println("publicField = " + publicField);

        // ❌ privateMethod not accessible
        // privateMethod();

        // ❌ defaultMethod not accessible
        // defaultMethod();

        // ✅ protectedMethod accessible via inheritance
        protectedMethod();

        // ✅ publicMethod always accessible
        publicMethod();
    }

    // Overriding a protected method from parent
    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method called in ExtendedDemo");
    }

    public static void main(String[] args) {
        ExtendedDemo child = new ExtendedDemo(5, "SubClass", 9.9, false);
        child.testInheritedAccess();

        // Comparing parent vs child
        AccessModifierDemo parent = new AccessModifierDemo(10, "Parent", 11.1, true);
        System.out.println("\nComparing parent vs child:");
        parent.publicMethod();
        child.publicMethod();
    }
}
