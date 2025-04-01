public class ElectronMonster extends Monster {

    public ElectronMonster(String name) {
        super(name, 105, 22, 9, 1, "Thunder Strike", 1.5, 1.1, AbilityType.NERF);
    }

    @Override
    public int basicAttack(Monster target) {
        int damage = (int) (getAttackPower() * (1 + Math.log(getLevel() + 1)));
        target.takeDamage(damage);
        System.out.println(getName() + " attacks with Electric Pulse, dealing " + damage + " damage!");
        return damage;
    }

    @Override
    public int specialAttack(Monster target) {
        int damage = (int) (getAttackPower() * (1.6 + (getLevel() * 0.1)));
        target.takeDamage(damage);
        System.out.println(getName() + " uses Thunder Strike special attack, dealing " + damage + " damage!");
        return damage;
    }

    @Override
    public int counterAttack(Monster target) {     
    System.out.println(getName() + " uses Quantum Tunnel to phase through reality and avoid damage!");
    int damage = (int) (target.getAttackPower() * 0.75); 
    target.takeDamage(damage); 
    System.out.println(getName() + " reappears and deals " + damage + " damage with a surprise attack!");
    return damage;
    }
}
