package com.company.security;

public class AccessModifierDemo {
    private int privateField;
    String defaultField;
    protected double protectedField;
    public boolean publicField;

    public AccessModifierDemo(int p, String d, double pr, boolean pub) {
        this.privateField = p;
        this.defaultField = d;
        this.protectedField = pr;
        this.publicField = pub;
        System.out.println("AccessModifierDemo constructor called");
    }

    private void privateMethod() { System.out.println("Private method called"); }
    void defaultMethod() { System.out.println("Default method called"); }
    protected void protectedMethod() { System.out.println("Protected method called"); }
    public void publicMethod() { System.out.println("Public method called"); }

    public void testInternalAccess() {
        System.out.println("Inside AccessModifierDemo:");
        System.out.println("privateField = " + privateField);
        System.out.println("defaultField = " + defaultField);
        System.out.println("protectedField = " + protectedField);
        System.out.println("publicField = " + publicField);

        privateMethod(); defaultMethod(); protectedMethod(); publicMethod();
    }

    public static void main(String[] args) {
        AccessModifierDemo obj = new AccessModifierDemo(10, "Hello", 20.5, true);

        System.out.println("obj.publicField = " + obj.publicField);
        obj.publicMethod();

        System.out.println("obj.defaultField = " + obj.defaultField);
        obj.defaultMethod();

        System.out.println("obj.protectedField = " + obj.protectedField);
        obj.protectedMethod();

        obj.testInternalAccess();

        SamePackageTest.testAccess();
    }
}
