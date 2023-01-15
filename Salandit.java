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

    private static final int KICK_ATTACK = 1;
    private static final int LIVE_COAL_ATTACK = 2;
    private static final int FIRE_CLAWS_ATTACK = 3;

    public Salandit()
    {
        super(MAX_LIVE_POINTS_OF_SALANDIT,MAX_ATTACK_POINTS_OF_SALANDIT,SALANDIT_NAME);
    }

    private boolean liveCoalAttack(Pokemon pokemonRival)
    {
        boolean isliveCoalAttackDone = false;
        if (this.isEqualOrBiggerAttackPoints(LIVE_COAL_ATTACK_COST)) {
            this.downloadAttackPoints(LIVE_COAL_ATTACK_COST);
            int pokemonRivalHarmLifePoints = random.nextInt(MIN_RIVAL_DAMAGE_LIVE_COAL_ATTACK_COST,
                    MAX_RIVAL_DAMAGE_LIVE_COAL_ATTACK_COST + 1);
            pokemonRival.downloadLifePoints(pokemonRivalHarmLifePoints);
            isliveCoalAttackDone = true;
        }
        return isliveCoalAttackDone;
    }


    private boolean fireClawsAttack(Pokemon pokemonRival)
    {
        boolean isfireClawsAttackDone = false;
        if (this.isEqualOrBiggerAttackPoints(FIRE_CLAWS_ATTACK_COST) && !this.isEqualLevel(SALANDIT)) {
            this.downloadAttackPoints(FIRE_CLAWS_ATTACK_COST);
            int pokemonRivalHarmLifePoints = random.nextInt(MIN_RIVAL_DAMAGE_FIRE_CLAWS_ATTACK_COST,
                    MAX_RIVAL_DAMAGE_FIRE_CLAWS_ATTACK_COST + 1);
            pokemonRival.downloadLifePoints(pokemonRivalHarmLifePoints);
            isfireClawsAttackDone = true;
        }
        return isfireClawsAttackDone;
    }

    public boolean performAttack(Pokemon pokemonRival)
    {
        Scanner scanner = new Scanner(System.in);
        boolean isMakeAttackDone = false;
        int playerSelection;
        if (this.isEqualLevel(SALANDIT)) {
            printAttackOptions(SALANDIT);
            playerSelection = scanner.nextInt();
            while (conditionPerformAttack(SALANDIT,playerSelection) ) {
                System.out.println("Please choose number between 1 to " + LIVE_COAL_ATTACK);
                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        } else if (this.isEqualLevel(SALAZZLE) ) {
            printAttackOptions(SALAZZLE);
            playerSelection = scanner.nextInt();
            while (conditionPerformAttack(SALAZZLE, playerSelection)) {
                System.out.println("Please choose number between 1 to " + FIRE_CLAWS_ATTACK);
                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection, pokemonRival);
        }

        return isMakeAttackDone;
    }

    private boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            super.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == LIVE_COAL_ATTACK) {
            isMakeAttackDone = liveCoalAttack(pokemonRival);
        } else if (selectedAttack == FIRE_CLAWS_ATTACK) {
            isMakeAttackDone = fireClawsAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }

    private boolean conditionPerformAttack(int level, int playerSelection)
    {
        boolean conditionResult = false;
        if (level == SALANDIT)
        {
            conditionResult = playerSelection != LIVE_COAL_ATTACK && playerSelection != KICK_ATTACK;

        } else if (level == SALAZZLE) {

            conditionResult = playerSelection != LIVE_COAL_ATTACK && playerSelection != KICK_ATTACK
                    && playerSelection != FIRE_CLAWS_ATTACK;

        }
        return conditionResult;
    }

    private void printAttackOptions(int level)
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
        if (this.isEqualLevel(SALANDIT) && this.isEnoughPointsForLevel2())
        {
            this.finePointsForLevel2();
            this.setPokemonForNextLevel(MAX_LIVE_POINTS_OF_SALAZZLE,MAX_ATTACK_POINTS_OF_SALAZZLE,SALAZZLE,SALAZZLE_NAME);
            isDevelopSuccessfully = true;
        }

        return isDevelopSuccessfully;
    }

}
