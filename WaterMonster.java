public class WaterMonster extends Monster {
    public WaterMonster(String name) {        
        super(name, 130, 16, 14, 1, "Water Castle", 1.0, 1.3, AbilityType.HEAL);
    }
    @Override
    public int basicAttack(Monster target) {
        int damage = (int) (super.basicAttack(target) * 0.9);
        setDefense( getDefense()+ damage / 4); 
        System.out.println(getName() + " attacks with Water Strike, dealing " + damage + " damage and healing " + (damage / 4) + " HP!");
        
        return damage;
    }
    @Override
    public int specialAttack(Monster target) {
        int damage = (int) (getAttackPower() * (1.5 + (getLevel() * 0.1)));
        target.takeDamage(damage);
        System.out.println(getName() + " uses Waterfall special attack, dealing " + damage + " damage!");
        return damage;
    }
    @Override
    public int counterAttack(Monster target) {
        System.out.println(getName() + " uses Water Cloak to counter attack!");
        return target.takeDamage(getAttackPower() / 2);    
    }
}
