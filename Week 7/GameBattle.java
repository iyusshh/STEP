// File: GameBattle.java
import java.util.Arrays;

public class GameBattle {

    public void attack(int damage) {
        System.out.println("Basic attack for " + damage + " points!");
    }

    public void attack(int damage, String weapon) {
        System.out.println("Attacking with " + weapon + " for " + damage + " points!");
    }

    public void attack(int damage, String weapon, boolean isCritical) {
        if (isCritical) {
            System.out.println("CRITICAL HIT! " + weapon + " deals " + (damage * 2) + " points!");
        } else {
            attack(damage, weapon);
        }
    }

    public void attack(int damage, String[] teammates) {
        String team = Arrays.toString(teammates);
        int totalDamage = damage * teammates.length;
        System.out.println("Team attack with " + team + " for " + totalDamage + " total damage!");
    }

    public static void main(String[] args) {
        GameBattle battle = new GameBattle();

        System.out.println("--- Gaming Battle Simulation ---");
        
        battle.attack(50);
        battle.attack(75, "Sword");
        battle.attack(60, "Bow", true);
        battle.attack(60, "Bow", false);

        String[] team = {"Alice", "Bob"};
        battle.attack(40, team);
    }
}