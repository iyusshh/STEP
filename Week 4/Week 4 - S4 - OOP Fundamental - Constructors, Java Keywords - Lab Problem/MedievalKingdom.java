import java.util.*;

abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    public MagicalStructure() {
        this("Unknown Structure", 0, "Unknown", true);
    }

    public MagicalStructure(String structureName) {
        this(structureName, 0, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    public abstract String castMagicSpell();
}

class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    public WizardTower() {
        this("Wizard Tower", 100, "Hilltop", true, 5, new String[]{"Fireball"});
    }

    public WizardTower(String[] spells) {
        this("Wizard Tower", 150, "Mountain", true, spells.length, spells);
    }

    public WizardTower(String structureName, int magicPower, String location, boolean isActive, int spellCapacity, String[] knownSpells) {
        super(structureName, magicPower, location, isActive);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    public String castMagicSpell() {
        return structureName + " casts " + (knownSpells.length > 0 ? knownSpells[0] : "a mysterious spell");
    }

    public int getSpellCapacity() {
        return spellCapacity;
    }

    public void doubleSpellCapacity() {
        this.spellCapacity *= 2;
    }
}

class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle() {
        this("Simple Fort", 50, "Valley", true, 100, false);
    }

    public EnchantedCastle(String name, boolean royal) {
        this(name, royal ? 200 : 100, "Plains", true, royal ? 300 : 150, true);
    }

    public EnchantedCastle(String structureName, int magicPower, String location, boolean isActive, int defenseRating, boolean hasDrawbridge) {
        super(structureName, magicPower, location, isActive);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    public String castMagicSpell() {
        return structureName + " radiates protective wards.";
    }

    public void tripleDefense() {
        this.defenseRating *= 3;
    }

    public int getDefenseRating() {
        return defenseRating;
    }
}

class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary() {
        this("Small Library", 30, "Village", true, 100, "Latin");
    }

    public MysticLibrary(int books) {
        this("Grand Library", 120, "Capital", true, books, "Sanskrit");
    }

    public MysticLibrary(String structureName, int magicPower, String location, boolean isActive, int bookCount, String ancientLanguage) {
        super(structureName, magicPower, location, isActive);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    public String castMagicSpell() {
        return structureName + " whispers knowledge in " + ancientLanguage;
    }
}

class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair() {
        this("Dragon Lair", 200, "Cave", true, "Fire Dragon", 5000);
    }

    public DragonLair(String dragonType) {
        this("Dragon Lair", 300, "Mountain Cave", true, dragonType, 8000);
    }

    public DragonLair(String structureName, int magicPower, String location, boolean isActive, String dragonType, int treasureValue) {
        super(structureName, magicPower, location, isActive);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    public String castMagicSpell() {
        return dragonType + " roars and unleashes fiery breath.";
    }
}

class StructureInteractions {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return !(s1 instanceof DragonLair && s2 instanceof MysticLibrary);
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower) {
            return attacker.structureName + " defeats " + defender.structureName;
        } else if (attacker.magicPower < defender.magicPower) {
            return defender.structureName + " withstands " + attacker.structureName;
        } else {
            return "Both structures are evenly matched";
        }
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) {
            total += s.magicPower;
        }
        return total;
    }

    public static void applySpecialEffects(MagicalStructure[] structures) {
        for (MagicalStructure s1 : structures) {
            for (MagicalStructure s2 : structures) {
                if (s1 instanceof WizardTower && s2 instanceof MysticLibrary) {
                    ((WizardTower) s1).doubleSpellCapacity();
                }
                if (s1 instanceof EnchantedCastle && s2 instanceof DragonLair) {
                    ((EnchantedCastle) s1).tripleDefense();
                }
            }
        }
    }
}

class KingdomManager {
    public static void categorizeStructures(MagicalStructure[] structures) {
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) System.out.println(s.structureName + " is a WizardTower");
            else if (s instanceof EnchantedCastle) System.out.println(s.structureName + " is a Castle");
            else if (s instanceof MysticLibrary) System.out.println(s.structureName + " is a Library");
            else if (s instanceof DragonLair) System.out.println(s.structureName + " is a DragonLair");
        }
    }

    public static void calculateTax(MagicalStructure[] structures) {
        for (MagicalStructure s : structures) {
            int tax;
            if (s instanceof WizardTower) tax = 100;
            else if (s instanceof EnchantedCastle) tax = 200;
            else if (s instanceof MysticLibrary) tax = 50;
            else if (s instanceof DragonLair) tax = 500;
            else tax = 0;
            System.out.println(s.structureName + " pays tax: " + tax);
        }
    }

    public static void determineSpecialization(MagicalStructure[] structures) {
        int magic = 0, defense = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower || s instanceof MysticLibrary) magic++;
            if (s instanceof EnchantedCastle || s instanceof DragonLair) defense++;
        }
        if (magic > defense) System.out.println("Kingdom is Magic-focused");
        else if (defense > magic) System.out.println("Kingdom is Defense-focused");
        else System.out.println("Kingdom is Balanced");
    }
}

public class MedievalKingdom {
    public static void main(String[] args) {
        MagicalStructure[] structures = {
            new WizardTower(),
            new EnchantedCastle(),
            new MysticLibrary(),
            new DragonLair()
        };

        KingdomManager.categorizeStructures(structures);
        KingdomManager.calculateTax(structures);
        StructureInteractions.applySpecialEffects(structures);
        System.out.println("Total Magic Power: " + StructureInteractions.calculateKingdomMagicPower(structures));
        KingdomManager.determineSpecialization(structures);
        System.out.println(StructureInteractions.performMagicBattle(structures[0], structures[1]));
    }
}
