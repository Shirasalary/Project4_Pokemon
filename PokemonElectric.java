public  abstract class PokemonElectric extends Pokemon {

    private float powerPercent;

    private boolean isUsedSpacialOption;

    private static final float POWER_CHARGE_PERCENT_PER_QUEUE =  (float) 0.05;
    private static final float MAX_POWER_PERCENT = 1;

    private static final int MIN_POWER_PERCENT = 0;

    //O(1)
    protected PokemonElectric(int maxLivePoints,int maxAttackPoints,String name,int maxDevelopLevel)
    {
       super(maxLivePoints, maxAttackPoints,name,maxDevelopLevel);
       this.powerPercent = MIN_POWER_PERCENT;
       this.isUsedSpacialOption = false;
    }

    //O(1)
    public void addPower()
    {
        if ( MAX_POWER_PERCENT - this.powerPercent >POWER_CHARGE_PERCENT_PER_QUEUE)
        {
            this.powerPercent += POWER_CHARGE_PERCENT_PER_QUEUE;
        }
    }

    //O(1)
    public boolean spacialOption()
    {
        boolean isSpacialOptionSuccessfullyDone = false;
        if (!this.isUsedSpacialOption)
        {
          this.increaseAttackPointsToMax();
          this.increaseLifePointsToMax();
          this.isUsedSpacialOption = true;
          isSpacialOptionSuccessfullyDone = true;
        }
        return isSpacialOptionSuccessfullyDone;
    }

    //O(1)
    protected void kickAttack(Pokemon pokemonRival)
    {
        super.kickAttack(pokemonRival);
        if(super.isLifePointsUnder20PercentFromMax())
        {
            this.powerPercent = 0;
        }

    }

    //O(1)
    protected boolean preformSingleAttackWithRandom(Pokemon pokemonRival, int attackCost
            , int minAttackCostForRival , int maxAttackCostForRival )
    {
        boolean isSingleAttackDone = false;
        if (this.isEqualOrBiggerAttackPoints(attackCost)) {
            float powerAttack = 1 + this.powerPercent;
            this.decreaseAttackPoints(attackCost);
            int pokemonRivalHarmLifePoints = random.nextInt(minAttackCostForRival,
                    maxAttackCostForRival + 1);
            if (this.isHasTripleAttack())
            {
                powerAttack *=3;
                this.setHasTripleAttack(DOESNT_HAS_TRIPLE_ATTACK);
            }
            pokemonRivalHarmLifePoints =(int) (pokemonRivalHarmLifePoints * (powerAttack));
            pokemonRival.decreaseLifePoints(pokemonRivalHarmLifePoints );
            System.out.println("Damage to the rival: " + pokemonRivalHarmLifePoints);
            isSingleAttackDone = true;
        }
        return isSingleAttackDone;
    }

    //O(1)
    protected boolean preformSingleAttack(Pokemon pokemonRival, int attackCost , int attackCostForRival )
    {
        float powerAttack = 1 + this.powerPercent;
        if (this.isHasTripleAttack())
        {
            powerAttack *=3;
            this.setHasTripleAttack(DOESNT_HAS_TRIPLE_ATTACK);
        }
        int pokemonRivalHarmLifePoints = (int) (attackCostForRival * (powerAttack));
        return super.preformSingleAttack(pokemonRival,attackCost,pokemonRivalHarmLifePoints);
    }

    //O(1)
    protected void decreaseLifePoints(int decreaseLifePoints)
    {
        super.decreaseLifePoints(decreaseLifePoints);
        if(super.isLifePointsUnder20PercentFromMax())
        {
            this.powerPercent = 0;
        }

    }

    //O(1)
    public void chargePowerForEachTurn()
    {
        if (this.powerPercent < 1)
        {
            this.powerPercent+=POWER_CHARGE_PERCENT_PER_QUEUE;
        }
    }

    //O(1)
    public String toString()
    {
        String output = super.toString();
        output+="\n";
        output+="Power: "+ ( (int)(this.powerPercent *100))+ "%" ;
        return output;
    }

}
