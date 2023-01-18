import java.util.Random;

public class Blitzle extends PokemonElectric {

    Random random = new Random();
    private static final int BLITZLE_LEVEL = 1;
    private static final String BLITZLE_NAME = "Blitzle";
    private static final int MAX_LIVE_POINTS_OF_BLITZLE = 90;
    private static final int MAX_ATTACK_POINTS_OF_BLITZLE = 35;
    private static final int FLOP_COST = 20;
    private static final int MIN_RIVAL_DAMAGE_FLOP_COST = 20;
    private static final int MAX_RIVAL_DAMAGE_FLOP_COST = 25;
    private static final int ZEBSTRIKA_LEVEL = 2;
    private static final String ZEBSTRIKA_NAME = "Zebstrika";
    private static final int MAX_LIVE_POINTS_OF_ZEBSTRIKA = 100;
    private static final int MAX_ATTACK_POINTS_OF_ZEBSTRIKA = 50;
    private static final int ZAP_KICK_COST = 30;
    private static final int MIN_RIVAL_DAMAGE_ZAP_KICK_COST = 30;
    private static final int MAX_RIVAL_DAMAGE_ZAP_KICK_COST = 35;
    private static final int FLOP_ATTACK = 2;

    private static final int ZAP_KICK_ATTACK = 3;

    private static final int MAX_DEVELOP_LEVEL = 2;
    public Blitzle()
    {
        super(MAX_LIVE_POINTS_OF_BLITZLE,MAX_ATTACK_POINTS_OF_BLITZLE,BLITZLE_NAME,MAX_DEVELOP_LEVEL);
    }
    private boolean flopAttack(Pokemon pokemonRival)
    {
        return this.preformSingleAttackWithRandom(pokemonRival,FLOP_COST,MIN_RIVAL_DAMAGE_FLOP_COST,MAX_RIVAL_DAMAGE_FLOP_COST);
    }
    private boolean zapKickAttack(Pokemon pokemonRival)
    {
        boolean isZapKick = false;
        if (!this.isEqualLevel(BLITZLE_LEVEL))
        {
            isZapKick = this.preformSingleAttackWithRandom(pokemonRival,ZAP_KICK_COST
                            ,MIN_RIVAL_DAMAGE_ZAP_KICK_COST,MAX_RIVAL_DAMAGE_ZAP_KICK_COST);
        }

        return isZapKick;
    }

    protected void printAttackOptions(int level)
    {
        System.out.println("Please choose attack");
        System.out.println("Press 1 for KICK attack");
        System.out.println("Press 2 for FLOP attack");
        if (level == ZEBSTRIKA_LEVEL) {
            System.out.println("Press 3 for ZAP KICK attack");
        }
    }

    protected boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            this.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == FLOP_ATTACK) {
            isMakeAttackDone = flopAttack(pokemonRival);
        } else if (selectedAttack == ZAP_KICK_ATTACK){
            isMakeAttackDone = zapKickAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }

    public boolean toDevelop() {
        boolean isDevelopSuccessfully = false;
        if (this.isEqualLevel(BLITZLE_LEVEL))
        {
            isDevelopSuccessfully = checkAndSetDevelop(MAX_LIVE_POINTS_OF_ZEBSTRIKA,MAX_ATTACK_POINTS_OF_ZEBSTRIKA,ZEBSTRIKA_LEVEL,ZEBSTRIKA_NAME);
        }
        return isDevelopSuccessfully;
    }

}
