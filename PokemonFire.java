import java.util.Random;

public abstract class PokemonFire extends Pokemon {

    private static final int SELF_HARM = 1;

    private static final int NUM_ATTACK_OF_SPECIAL_OPTION = 2;
    private static final int MIN_SELF_DAMAGE_ATTACK = 3;
    private static final int MAX_SELF_DAMAGE_ATTACK = 10;

    protected PokemonFire (int maxLivePoints,int maxAttackPoints, String name,int maxDevelopLevel)
    {
        super(maxLivePoints,maxAttackPoints,name,maxDevelopLevel);
    }

    protected boolean preformSingleAttack(Pokemon pokemonRival, int attackCost , int attackCostForRival )
    {
        boolean isSingleAttackDone = super.preformSingleAttack(pokemonRival,attackCost,attackCostForRival);
        this.selfHarm();
        return isSingleAttackDone;
    }

    protected boolean preformSingleAttackWithRandom(Pokemon pokemonRival, int attackCost
            , int minAttackCostForRival , int maxAttackCostForRival )
    {
        boolean isSingleAttackDone = super.preformSingleAttackWithRandom(pokemonRival,attackCost
        ,minAttackCostForRival, maxAttackCostForRival );
        this.selfHarm();
        return isSingleAttackDone;
    }

    private void selfHarm()
    {
        //לעשות קבוע לסבירות של 25%?
        Random random = new Random();
        int isSelfHarm = random.nextInt(1,5);
        if (isSelfHarm == SELF_HARM)
        {
            int selfHarmLifePoints = random.nextInt(MIN_SELF_DAMAGE_ATTACK,MAX_SELF_DAMAGE_ATTACK+1);
            super.decreaseLifePoints(selfHarmLifePoints);
            System.out.println("Self Harm: " + selfHarmLifePoints);
        }
    }

    public boolean spacialOption(Pokemon pokemonRival)
    {
        boolean isSpacialAttackDone = false;
        int numAttack;
        for (int i = 1; i<=NUM_ATTACK_OF_SPECIAL_OPTION; i++)
        {
            numAttack = this.lotteryOneAttack();
            isSpacialAttackDone= this.performSelectedAttack(numAttack,pokemonRival);
            if (!isSpacialAttackDone)
            {
                break;
            }
        }
        this.pokemonFireFineForSpacialAttack();
        return isSpacialAttackDone;
    }



    protected abstract int lotteryOneAttack();

}
