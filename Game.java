import java.util.Random;
import javax.sound.sampled.*;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayIntroduction();
        Monster p1 = monsterCreation(1,scanner);
        preBattleComments(p1.getName());
        Monster p2 = monsterCreation(2,scanner);
        preBattleComments(p2.getName());
        System.out.println("Battle begins!");
        playSound("ost.wav");
        int turn =1;
        while (p1.getHealth() > 0 && p2.getHealth() > 0) {
            startTurn(p1, p2);

            executeAction(p1, p2,scanner);
            p1.checkAndLevelUp(turn);
            System.out.println(p1.getLevel());
            if (lost(p2)) break;

            executeAction(p2, p1,scanner);
            p2.checkAndLevelUp(turn);
            System.out.println(p2.getLevel());
            if (lost(p1)) break;
            
            turn++;
        }
        System.out.println("\nGame Over!");
        scanner.close();
    }

    
    public static void executeAction(Monster p1, Monster p2,Scanner scanner) {
        System.out.println("\n" + p1.getName() + "'s turn!");
        System.out.println("1. Basic Attack");
        System.out.println("2. Special Attack");
        System.out.println("3. Use Ability");
        System.out.print("Choose an action (1/2/3): ");
        int fireChoice = scanner.nextInt();
        switch (fireChoice) {
            case 1:
                p1.basicAttack(p2);
                break;
            case 2:
                p1.specialAttack(p2);
                break;
            case 3:
                p1.useAbility(p2);
                break;
            default:
                System.out.println("Invalid choice. Please select a valid action.");
        }   
    }
    public static Monster monsterCreation(int i,Scanner scanner){
        System.out.println("Player "+i+" selects his Monster");
        System.out.println("1. Fire Monster");
        System.out.println("2. Water Monster");
        System.out.println("3. Earth Monster");
        System.out.println("4. Electron Monster");
        int choice=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Player "+i+" selects his Monster name");
        String name=scanner.nextLine();
        switch (choice) {
            case 1:
                return new FireMonster(name);
            case 2:
                return new WaterMonster(name);
            case 3:
                return new EarthMonster(name);
            case 4:
                return new ElectronMonster(name);
            default:
                return new FireMonster(name);
        }
    }

    public static void displayIntroduction() {
        System.out.println("****************************************");
        System.out.println("        Welcome to the Monster Battle!        ");
        System.out.println("****************************************");

        String introArt =
            "   _______  _______  _______  _______  _______  _______ \n" +
            "  |  ___  ||  ___  ||  ___  ||  ___  ||  ___  ||  ___  |\n" +
            "  | |   | || |   | || |   | || |   | || |   | || |   | |\n" +
            "  | |___| || |___| || |___| || |___| || |___| || |___| |\n" +
            "  |_______||_______||_______||_______||_______||_______|\n" +
            "\n" +
            "    Choose your monster and fight to the finish!\n";
        
        System.out.println(introArt);

        System.out.println("\nFire Monster:");
        System.out.println("   (\\(\\\n" +
                           "   (o.o)\n" +
                           "  (  )  ");
        
        System.out.println("\nWater Monster:");
        System.out.println("    ~~~~\n" +
                           "   ( o )\n" +
                           "  ( ~~~ )\n" +
                           "   \\   /");
    }

public static void preBattleComments(String monsterName) {
    String[] battleMessages = {
        "\"A wild " + monsterName + " appears! And it's... yawning?\"",
        "\"You enter the arena. The crowd cheers! Just kidding, it's empty.\"",
        "\"Prepare for battle! Your opponent looks confused. So do you.\"",
        monsterName + " is ready! You? Not so much.",
        "\"Well, look at you... walking into your inevitable defeat \"",
        "\"Is that fear in your eyes? Nah, you just look lost.\"",
        "\"Ah, so this is the mighty hero I'm supposed to be scared of? How adorable.\"",
        "\"Don't worry, you'll feel better after the fight... well, unless you lose consciousness.\""
    };

    Random rand = new Random();
    System.out.println(monsterName+" enters the arena");
    System.out.println(battleMessages[rand.nextInt(battleMessages.length)]);
}


public static void playSound(String soundFilePath) {
    try {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(Game.class.getResource(soundFilePath));
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
        clip.start();
    } catch (Exception e) {
        System.out.println("Error playing sound: " + e.getMessage());
    }
}


    public static boolean lost(Monster m){
        if (m.getHealth() <= 0) {
            System.out.println(m.getName() + " has been defeated!");
            return true;
        }
        return false;
    }
    public static void startTurn(Monster p1,Monster p2){
        System.out.println("\n");
        System.out.println("\n----- Turn -----");
        System.out.println(p1.getName() + " Health: " + p1.getHealth());
        System.out.println(p2.getName() + " Health: " + p2.getHealth());
    }
    public static int endTurn(int turn){
        return 0;
    }
}