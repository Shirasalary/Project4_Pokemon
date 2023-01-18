import java.util.Random;
import java.util.Scanner;

public class Salandit extends PokemonFire {

    Random random = new Random();
    private static final int SALANDIT = 1;

    private static final String SALANDIT_NAME = "Salandit";
    private static final int MAX_LIVE_POINTS_OF_SALANDIT = 100;
    private static final int MAX_ATTACK_POINTS_OF_SALANDIT = 60;
    private static final int SALAZZLE = 2;

    private static final String SALAZZLE_NAME = "Salazzle";
    private static final int MAX_LIVE_POINTS_OF_SALAZZLE = 160;
    private static final int MAX_ATTACK_POINTS_OF_SALAZZLE = 80;

    private static final int LIVE_COAL_ATTACK_COST = 10;

    private static final int MIN_RIVAL_DAMAGE_LIVE_COAL_ATTACK_COST = 0;
    private static final int MAX_RIVAL_DAMAGE_LIVE_COAL_ATTACK_COST = 25;

    private static final int FIRE_CLAWS_ATTACK_COST = 25;

    private static final int MIN_RIVAL_DAMAGE_FIRE_CLAWS_ATTACK_COST = 0;
    private static final int MAX_RIVAL_DAMAGE_FIRE_CLAWS_ATTACK_COST = 50;
    private static final int LIVE_COAL_ATTACK = 2;
    private static final int FIRE_CLAWS_ATTACK = 3;

    private static final int MAX_DEVELOP_LEVEL = 2;

    public Salandit()
    {
        super(MAX_LIVE_POINTS_OF_SALANDIT,MAX_ATTACK_POINTS_OF_SALANDIT,SALANDIT_NAME,MAX_DEVELOP_LEVEL);
    }

    private boolean liveCoalAttack(Pokemon pokemonRival)
    {
        return super.preformSingleAttackWithRandom(pokemonRival,LIVE_COAL_ATTACK_COST
                ,MIN_RIVAL_DAMAGE_LIVE_COAL_ATTACK_COST , MAX_RIVAL_DAMAGE_LIVE_COAL_ATTACK_COST);
    }


    private boolean fireClawsAttack(Pokemon pokemonRival)
    {
        boolean isfireClawsAttackDone = false;
        if ( !this.isEqualLevel(SALANDIT)) {
            isfireClawsAttackDone = super.preformSingleAttackWithRandom(pokemonRival,FIRE_CLAWS_ATTACK_COST
                    ,MIN_RIVAL_DAMAGE_FIRE_CLAWS_ATTACK_COST , MAX_RIVAL_DAMAGE_FIRE_CLAWS_ATTACK_COST);
        }
        return isfireClawsAttackDone;
    }

    protected boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            this.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == LIVE_COAL_ATTACK) {
            isMakeAttackDone = liveCoalAttack(pokemonRival);
        } else {
            isMakeAttackDone = fireClawsAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }


    protected void printAttackOptions(int level)
    {
        System.out.println("Please choose attack");
        System.out.println("Press 1 for KICK attack");
        System.out.println("Press 2 for LIVE COAL attack");
        if (level == SALAZZLE) {
            System.out.println("Press 3 for FIRE CLAWS attack");
        }
    }


    public boolean toDevelop()
    {
        boolean isDevelopSuccessfully = false;
        if (this.isEqualLevel(SALANDIT))
        {
            isDevelopSuccessfully = super.checkAndSetDevelop(MAX_LIVE_POINTS_OF_SALAZZLE,MAX_ATTACK_POINTS_OF_SALAZZLE,SALAZZLE,SALAZZLE_NAME);
        }

        return isDevelopSuccessfully;
    }

    protected int lotteryOneAttack()
    {
        int numAttack;
        if (this.isEqualLevel(SALANDIT))
        {
            numAttack = random.nextInt(KICK_ATTACK,LIVE_COAL_ATTACK+1);
        } else  {
            numAttack = random.nextInt(KICK_ATTACK,FIRE_CLAWS_ATTACK+1);
        }
        return numAttack;
    }


}
