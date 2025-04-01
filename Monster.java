public abstract class Monster {
    private String name;
    private int health;
    private int attackPower;
    private int defense;
    private int level;
    private String ability;
    private double mana;
    private double levelUpDifficulty;
    private AbilityType abilityType;

    public Monster(String name, int health, int attackPower, int defense, int level, String ability, double mana, double levelUpDifficulty, AbilityType abilityType) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = level;
        this.ability = ability;
        this.mana = mana;
        this.levelUpDifficulty = levelUpDifficulty;
        this.abilityType = abilityType;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = Math.max(0, health); }
    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int pow) { this.attackPower = pow; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }
    public double getMana() { return mana; }
    public int getLevel() { return level; }
    public double getLevelUpDifficulty() { return levelUpDifficulty; }
    public void setLevel(int level) { this.level = level; }
    public String getAbility() { return ability; }
    public String getAbilityType() { return abilityType.toString(); }

    public int takeDamage(int damage) {
        int effectiveDefense = (int) (getDefense() * (1 + getLevel() * 0.02));
        int effectiveDamage = Math.max(0, damage - effectiveDefense);
        health -= effectiveDamage;
        return effectiveDamage;
    }

    public int basicAttack(Monster target) {
        int damage = (int) (getAttackPower() * (1 + Math.log(getLevel() + 1) * 0.3));
        return target.takeDamage(damage);
    }

    public abstract int specialAttack(Monster target);
    public abstract int counterAttack(Monster target);

    public void levelUp() {
        setLevel(getLevel() + 1);
        System.out.println(getName() + " just leveled up to level " + getLevel() + "!");
        double growthFactor = 1.1; 
    
        setHealth((int) Math.round(getHealth() * growthFactor));
        setAttackPower((int) Math.round(getAttackPower() * growthFactor));
        setDefense((int) Math.round(getDefense() * growthFactor));
    
        if (getHealth() > 300) setHealth(300);
        if (getAttackPower() > 100) setAttackPower(100);
        if (getDefense() > 80) setDefense(80);
    }

    public void checkAndLevelUp(int turn) {
        double requiredToLevelUp = getLevel() * getLevelUpDifficulty();
        if (turn >= requiredToLevelUp) {
            levelUp();
        }
    }

    public void useAbility(Monster target) {
        System.out.println(name + " used " + ability + "!");

        switch (abilityType) {
            case ATTACK:
                int abilityDamage = (int) (getAttackPower() * 1.15 + getLevel() * 4);
                System.out.println(name + " deals " + abilityDamage + " damage!");
                target.takeDamage(abilityDamage);
                break;

            case HEAL:
                int healAmount = (int) (getHealth() * 0.15 + getLevel() * 4);
                System.out.println(name + " heals for " + healAmount + " HP!");
                setHealth(getHealth() + healAmount);
                break;

            case BUFF:
                int buffAmount = (int) (getAttackPower() * 0.15);
                System.out.println(name + "'s attack power increased by " + buffAmount + "!");
                setAttackPower(getAttackPower() + buffAmount);
                break;

            case NERF:
                int nerfAmount = (int) (target.getAttackPower() * 0.25); 
                System.out.println(target.getName() + "'s attack power reduced by " + nerfAmount + "!");
                target.setAttackPower(target.getAttackPower() - nerfAmount);
                break;

            case SHIELD:
                System.out.println(name + " gains a temporary shield!");
                setDefense(getDefense() + 15);
                break;

            default:
                System.out.println("Ability has no effect!");
                break;
        }
    }
}
