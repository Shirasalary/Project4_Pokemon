import java.util.Random;

public abstract class PokemonFire extends Pokemon {

    private static final int SELF_HARM = 1;

    private static final int MIN_SELF_DAMAGE_ATTACK = 3;
    private static final int MAX_SELF_DAMAGE_ATTACK = 10;

    protected PokemonFire (int maxLivePoints,int maxAttackPoints, String name)
    {
        super(maxLivePoints,maxAttackPoints,name);
    }



    private void selfHarm()
    {
        //לעשות קבוע לסבירות של 25%?
        Random random = new Random();
        int isSelfHarm = random.nextInt(1,5);
        if (isSelfHarm == SELF_HARM)
        {
            int selfHarmLifePoints = random.nextInt(MIN_SELF_DAMAGE_ATTACK,MAX_SELF_DAMAGE_ATTACK+1);
            super.downloadLifePoints(selfHarmLifePoints);
        }
    }


}
