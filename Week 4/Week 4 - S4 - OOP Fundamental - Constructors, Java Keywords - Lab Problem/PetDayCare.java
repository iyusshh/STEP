import java.util.*;

class VirtualPet {
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String currentStage;
    private boolean isGhost;

    static final String[] EVOLUTION_STAGES = {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    static int totalPetsCreated = 0;

    public VirtualPet() {
        this("Unknown", getRandomSpecies(), 0, 50, 50, EVOLUTION_STAGES[0]);
    }

    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 0, 60, 60, EVOLUTION_STAGES[1]);
    }

    public VirtualPet(String petName, String species) {
        this(petName, species, 1, 70, 70, EVOLUTION_STAGES[2]);
    }

    public VirtualPet(String petName, String species, int age, int happiness, int health, String stage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.currentStage = stage;
        this.isGhost = false;
        totalPetsCreated++;
    }

    public static String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private static String getRandomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Dog", "Phoenix", "Slime"};
        return speciesList[new Random().nextInt(speciesList.length)];
    }

    public void feedPet() {
        if (!isGhost) {
            health = Math.min(100, health + 10);
            happiness += 5;
        }
    }

    public void playWithPet() {
        if (!isGhost) {
            happiness = Math.min(100, happiness + 10);
            health -= 5;
        }
    }

    public void healPet() {
        if (!isGhost) {
            health = Math.min(100, health + 15);
        }
    }

    public void evolvePet() {
        if (isGhost) return;
        int stageIndex = Arrays.asList(EVOLUTION_STAGES).indexOf(currentStage);
        if (age > 2 && stageIndex < EVOLUTION_STAGES.length - 1) {
            currentStage = EVOLUTION_STAGES[stageIndex + 1];
        }
    }

    public void simulateDay() {
        if (isGhost) return;
        age++;
        happiness -= new Random().nextInt(6);
        health -= new Random().nextInt(6);
        if (health <= 0) {
            currentStage = "Ghost";
            isGhost = true;
        } else {
            evolvePet();
        }
    }

    public String getPetStatus() {
        return String.format("[%s] %s (%s) - Age: %d, Health: %d, Happiness: %d, Stage: %s",
                petId.substring(0, 8), petName, species, age, health, happiness, currentStage);
    }
}

public class PetDayCare {
    public static void main(String[] args) {
        List<VirtualPet> pets = new ArrayList<>();
        pets.add(new VirtualPet());
        pets.add(new VirtualPet("Fluffy"));
        pets.add(new VirtualPet("Rex", "Dragon"));
        pets.add(new VirtualPet("Mimi", "Cat", 5, 80, 90, "Teen"));

        for (int day = 1; day <= 5; day++) {
            System.out.println("\n--- Day " + day + " ---");
            for (VirtualPet pet : pets) {
                pet.simulateDay();
                System.out.println(pet.getPetStatus());
            }
        }

        System.out.println("\nTotal pets created: " + VirtualPet.totalPetsCreated);
    }
}
