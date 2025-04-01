public class EarthMonster extends Monster {

    public EarthMonster(String name) {
        super(name, 150, 14, 16, 1, "Rock Wall", 1.2, 1.4, AbilityType.SHIELD);
    }

    @Override
    public int basicAttack(Monster target) {
        int damage = super.basicAttack(target);
        System.out.println(getName() + " attacks with Rock Throw, dealing " + damage + " damage!");
        return damage;
    }

    @Override
    public int specialAttack(Monster target) {
        int damage = (int) (getAttackPower() * (1.4 + (getLevel() * 0.1)));
        target.takeDamage(damage);
        System.out.println(getName() + " uses Earthquake special attack, dealing " + damage + " damage!");
        return damage;
    }

    @Override
    public int counterAttack(Monster target) {
        System.out.println(getName() + " uses Earth Shield to counter attack!");
        return target.takeDamage(target.getAttackPower() / 2);
    }
}
