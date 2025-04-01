
public class FireMonster extends Monster {
    
    public FireMonster(String name) {
        super(name, 110, 20, 10, 1, "Flame Burst", 0.8, 1.2, AbilityType.ATTACK);
    }

    @Override
    public int basicAttack(Monster target) {
        int damage = super.basicAttack(target);
        System.out.println(getName() + " attacks with Fire Blast, dealing " + damage + " damage!");
        return damage;
    }

    @Override
    public int specialAttack(Monster target) {
        int damage = (int) (getAttackPower() * (1.5 + (getLevel() * 0.1)));
        target.takeDamage(damage);
        System.out.println(getName() + " uses Fireball special attack, dealing " + damage + " damage!");
        return damage;
    }

    @Override
    public int counterAttack(Monster target) {
        System.out.println(getName() + " uses Fire Wall to counter attack!");
        return target.takeDamage(getAttackPower() / 2);
    }
}
