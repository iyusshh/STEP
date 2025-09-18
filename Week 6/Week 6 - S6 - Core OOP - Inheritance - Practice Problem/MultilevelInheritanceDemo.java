// File: MultilevelInheritanceDemo.java

class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }

    public void move() {
        System.out.println("Animal is moving");
    }

    public String getAnimalInfo() {
        return String.format("Species: %s, Habitat: %s, Lifespan: %d years, Wildlife: %b",
                species, habitat, lifespan, isWildlife);
    }
}

class Mammal extends Animal {
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod;

    public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                  String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.gestationPeriod = gestationPeriod;
        this.hasWarmBlood = true;
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Mammal is walking/running");
    }

    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }

    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;

    public Dog() {
        super("Dog", "Domestic", 13, false, "Brown", 60);
        this.breed = "Mixed";
        this.isDomesticated = true;
        this.loyaltyLevel = 7;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod, String breed,
               boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod, other.breed,
             other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Loyalty Level: " + loyaltyLevel + "/10");
    }

    public void demonstrateInheritance() {
        System.out.println(getAnimalInfo());
        move();
        eat();
        sleep();
        nurse();
        regulateTemperature();
        bark();
        fetch();
        showLoyalty();
    }

    public static void main(String[] args) {
        System.out.println("=== Test 1: Basic Dog Constructor ===");
        Dog dog1 = new Dog();
        dog1.demonstrateInheritance();

        System.out.println("\n=== Test 2: Detailed Dog Constructor ===");
        Dog dog2 = new Dog("Dog", "Domestic", 15, false, "Black", 65,
                "Labrador", true, 9, "Swimming");
        dog2.demonstrateInheritance();

        System.out.println("\n=== Test 3: Copy Constructor ===");
        Dog dog3 = new Dog(dog2);
        dog3.demonstrateInheritance();

        System.out.println("\n=== Test 4: instanceof checks ===");
        System.out.println("dog1 instanceof Dog: " + (dog1 instanceof Dog));
        System.out.println("dog1 instanceof Mammal: " + (dog1 instanceof Mammal));
        System.out.println("dog1 instanceof Animal: " + (dog1 instanceof Animal));
    }
}
