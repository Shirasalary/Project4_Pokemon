import java.util.Random;

public class Electabuzz extends PokemonElectric{

    Random random = new Random();
    private static final int ELECTABUZZ_LEVEL = 1;
    private static final String ELECTABUZZ_NAME = "Electabuzz";
    private static final int MAX_LIVE_POINTS_OF_ELECTABUZZ = 30;
    private static final int MAX_ATTACK_POINTS_OF_ELECTABUZZ = 100;
    private static final int THUNDER_COST = 60;
    private static final int MIN_RIVAL_DAMAGE_THUNDER_COST = 40;
    private static final int MAX_RIVAL_DAMAGE_THUNDER_COST = 50;
    private static final int ELECTIVIRE_LEVEL = 2;
    private static final String ELECTIVIRE_NAME = "Electivire";
    private static final int MAX_LIVE_POINTS_OF_ELECTIVIRE = 35;
    private static final int MAX_ATTACK_POINTS_OF_ELECTIVIRE = 120;
    private static final int THUNDER_PUNCH_COST = 80;
    private static final int MIN_RIVAL_DAMAGE_THUNDER_PUNCH_COST = 50;

    private static final int MAX_RIVAL_DAMAGE_THUNDER_PUNCH_COST = 120;
    private static final int MAX_DEVELOP_LEVEL = 2;
    private static final int THUNDER_ATTACK = 2;
    private static final int THUNDER_PUNCH_ATTACK = 3;

    public Electabuzz()
    {
        super(MAX_LIVE_POINTS_OF_ELECTABUZZ,MAX_ATTACK_POINTS_OF_ELECTABUZZ,ELECTABUZZ_NAME,MAX_DEVELOP_LEVEL);
    }
    private boolean thunderAttack(Pokemon pokemonRival)
    {
        return this.preformSingleAttackWithRandom(pokemonRival,THUNDER_COST,MIN_RIVAL_DAMAGE_THUNDER_COST,MAX_RIVAL_DAMAGE_THUNDER_COST);
    }
    private boolean thunderPunchAttack(Pokemon pokemonRival)
    {
        boolean isThunderPunch = false;
        if (!this.isEqualLevel(ELECTABUZZ_LEVEL))
        {
            isThunderPunch =
                    this.preformSingleAttackWithRandom(pokemonRival,THUNDER_PUNCH_COST
                            ,MIN_RIVAL_DAMAGE_THUNDER_PUNCH_COST,MAX_RIVAL_DAMAGE_THUNDER_PUNCH_COST);
        }
        return isThunderPunch;
    }

    protected boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            this.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == THUNDER_ATTACK) {
            isMakeAttackDone = thunderAttack(pokemonRival);
        } else if (selectedAttack == THUNDER_PUNCH_ATTACK){
            isMakeAttackDone = thunderPunchAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }

    protected void printAttackOptions(int level)
    {
        System.out.println("Please choose attack");
        System.out.println("Press 1 for KICK attack");
        System.out.println("Press 2 for THUNDER attack");
        if (level == ELECTIVIRE_LEVEL) {
            System.out.println("Press 3 for THUNDER PUNCH attack");
        }
    }
    public boolean toDevelop() {
        boolean isDevelopSuccessfully = false;
        if (this.isEqualLevel(ELECTABUZZ_LEVEL))
        {
           isDevelopSuccessfully = this.checkAndSetDevelop(MAX_LIVE_POINTS_OF_ELECTIVIRE,MAX_ATTACK_POINTS_OF_ELECTIVIRE,ELECTIVIRE_LEVEL,ELECTIVIRE_NAME);

        }
        return isDevelopSuccessfully;
    }
}
