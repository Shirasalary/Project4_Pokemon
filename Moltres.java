import java.util.Random;
import java.util.Scanner;

public class Moltres extends PokemonFire{

    Random random = new Random();
    private static final String MOLTRES_NAME = "Moltres";
    private static final int MAX_LIVE_POINTS_OF_MOLTRES = 120;
    private static final int MAX_ATTACK_POINTS_OF_MOLTRES = 60;

    private static final int ASSISTING_HEATER_ATTACK_COST = 30;

    private static final int MIN_RIVAL_DAMAGE_ASSISTING_HEATER_ATTACK_COST = 10;
    private static final int MAX_RIVAL_DAMAGE_ASSISTING_HEATER_ATTACK_COST = 60;

    private static final int FIRE_WING_ATTACK_COST = 30;

    private static final int RIVAL_DAMAGE_FIRE_WING_ATTACK_COST = 30;
    private static final int ASSISTING_HEATER_ATTACK = 2;
    private static final int FIRE_WING_ATTACK = 3;

    private static final int MAX_DEVELOP_LEVEL = 1;

    private static final boolean NO_OPTION_TO_DEVELOP = false;

    public Moltres()
    {
        super(MAX_LIVE_POINTS_OF_MOLTRES,MAX_ATTACK_POINTS_OF_MOLTRES,MOLTRES_NAME,MAX_DEVELOP_LEVEL);
    }

    private boolean assistingHeaterAttack(Pokemon pokemonRival)
    {
        return super.preformSingleAttackWithRandom(pokemonRival
                ,ASSISTING_HEATER_ATTACK_COST,MIN_RIVAL_DAMAGE_ASSISTING_HEATER_ATTACK_COST
                , MAX_RIVAL_DAMAGE_ASSISTING_HEATER_ATTACK_COST );
    }

    private boolean fireWingAttack(Pokemon pokemonRival)
    {
        return super.preformSingleAttack(pokemonRival,FIRE_WING_ATTACK_COST
                ,RIVAL_DAMAGE_FIRE_WING_ATTACK_COST);
    }

    protected boolean conditionPerformAttack(int playerSelection)
    {
        boolean conditionResult =  playerSelection >= KICK_ATTACK && playerSelection <= FIRE_WING_ATTACK ;
        if (!conditionResult)
        {
            System.out.println("Please choose number between 1 to " + FIRE_WING_ATTACK);
        }
        return conditionResult;
    }

    protected void printAttackOptions(int level)
    {
        System.out.println("Please choose attack");
        System.out.println("Press 1 for KICK attack");
        System.out.println("Press 2 for ASSISTING HEATER attack");
        System.out.println("Press 3 for FIRE WING attack");
    }

    protected boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            this.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == ASSISTING_HEATER_ATTACK) {
            isMakeAttackDone = assistingHeaterAttack(pokemonRival);
        } else  {
            isMakeAttackDone = fireWingAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }

    protected int lotteryOneAttack()
    {
        int numAttack = random.nextInt(KICK_ATTACK,FIRE_WING_ATTACK+1);

        return numAttack;
    }

    public boolean toDevelop()
    {
        return NO_OPTION_TO_DEVELOP;
    }
}
