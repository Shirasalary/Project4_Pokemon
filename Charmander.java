import java.util.Random;
import java.util.Scanner;

public class Charmander extends PokemonFire {

    Random random = new Random();
    private static final int CHARMANDER = 1;

    private static final String CHARMANDER_NAME = "Charmander";
    private static final int MAX_LIVE_POINTS_OF_CHARMANDER = 80;
    private static final int MAX_ATTACK_POINTS_OF_CHARMANDER = 40;
    private static final int CHARMELEON = 2;

    private static final String CHARMELEON_NAME = "Charmeleon";
    private static final int MAX_LIVE_POINTS_OF_CHARMELEON = 90;
    private static final int MAX_ATTACK_POINTS_OF_CHARMELEON = 60;
    private static final int CHARIZARD = 3;

    private static final String CHARIZARD_NAME = "Charizard";
    private static final int MAX_LIVE_POINTS_OF_CHARIZARD = 130;
    private static final int MAX_ATTACK_POINTS_OF_CHARIZARD = 80;
    private static final int SCRATCH_ATTACK_COST = 15;

    private static final int MIN_RIVAL_DAMAGE_SCRATCH_ATTACK_COST = 25;
    private static final int MAX_RIVAL_DAMAGE_SCRATCH_ATTACK_COST = 30;
    private static final int FLAME_TAIL_ATTACK_COST = 40;
    private static final int MIN_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST = 30;
    private static final int MAX_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST = 50;
    private static final int FIERY_BLAST_ATTACK_COST = 50;
    private static final int RIVAL_DAMAGE_FIERY_BLAST_ATTACK_COST = 50;

    private static final int KICK_ATTACK = 1;
    private static final int SCRATCH_ATTACK = 2;
    private static final int FLAME_TAIL_ATTACK = 3;
    private static final int FIERY_BLAST_ATTACK = 4;

    public Charmander() {
        super(MAX_LIVE_POINTS_OF_CHARMANDER,MAX_ATTACK_POINTS_OF_CHARMANDER,CHARMANDER_NAME);

    }

    private boolean scratchAttack(Pokemon pokemonRival) {
        boolean isScratchAttackDone = false;
        if (this.isEqualOrBiggerAttackPoints(SCRATCH_ATTACK_COST)) {
            this.downloadAttackPoints(SCRATCH_ATTACK_COST);
            int pokemonRivalHarmLifePoints = random.nextInt(MIN_RIVAL_DAMAGE_SCRATCH_ATTACK_COST,
                    MAX_RIVAL_DAMAGE_SCRATCH_ATTACK_COST + 1);
            pokemonRival.downloadLifePoints(pokemonRivalHarmLifePoints);
            isScratchAttackDone = true;
        }
        return isScratchAttackDone;
    }

    private boolean flameTailAttack(Pokemon pokemonRival) {
        boolean isFlameTailAttackDone = false;
        if (this.isEqualOrBiggerAttackPoints(FLAME_TAIL_ATTACK_COST) && !this.isEqualLevel(CHARMANDER)) {
            this.downloadAttackPoints(FLAME_TAIL_ATTACK_COST);
            int pokemonRivalHarmLifePoints = random.nextInt(MIN_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST,
                    MAX_RIVAL_DAMAGE_FLAME_TAIL_ATTACK_COST + 1);
            pokemonRival.downloadLifePoints(pokemonRivalHarmLifePoints);
            isFlameTailAttackDone = true;
        }
        return isFlameTailAttackDone;
    }

    private boolean fieryBlastAttack(Pokemon pokemonRival) {
        boolean isFieryBlastAttackDone = false;
        if ( this.isEqualOrBiggerAttackPoints(FIERY_BLAST_ATTACK_COST) &&!this.isEqualLevel(CHARMANDER) && !this.isEqualLevel(CHARMELEON)) {
            this.downloadAttackPoints(FIERY_BLAST_ATTACK_COST);
            int pokemonRivalHarmLifePoints = RIVAL_DAMAGE_FIERY_BLAST_ATTACK_COST;
            pokemonRival.downloadLifePoints(pokemonRivalHarmLifePoints);
            isFieryBlastAttackDone = true;
        }
        return isFieryBlastAttackDone;
    }

    public boolean performAttack(Pokemon pokemonRival) {
        Scanner scanner = new Scanner(System.in);
        boolean isMakeAttackDone = false;
        int playerSelection;
        if (this.isEqualLevel(CHARMANDER)) {
           printAttackOptions(CHARMANDER);
            playerSelection = scanner.nextInt();
            while (conditionPerformAttack(CHARMANDER,playerSelection) ) {
                System.out.println("Please choose number between 1 to " + SCRATCH_ATTACK);
                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        } else if (this.isEqualLevel(CHARMELEON) ) {
            printAttackOptions(CHARMELEON);
            playerSelection = scanner.nextInt();
            while (conditionPerformAttack(CHARMELEON,playerSelection)) {
                System.out.println("Please choose number between 1 to " + FLAME_TAIL_ATTACK);
                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        } else{
            printAttackOptions(CHARIZARD);
            playerSelection = scanner.nextInt();
            while (conditionPerformAttack(CHARIZARD,playerSelection)) {
                System.out.println("Please choose number between 1 to " + FIERY_BLAST_ATTACK);
                playerSelection = scanner.nextInt();
            }
            isMakeAttackDone = performSelectedAttack(playerSelection,pokemonRival);
        }
       return isMakeAttackDone;
    }

    public boolean toDevelop() {
        boolean isDevelopSuccessfully = false;
        if (this.isEqualLevel(CHARMANDER) && this.isEnoughPointsForLevel2())
        {
            this.finePointsForLevel2();
            this.setPokemonForNextLevel(MAX_LIVE_POINTS_OF_CHARMELEON,MAX_ATTACK_POINTS_OF_CHARMELEON,CHARMELEON,CHARMELEON_NAME);
            isDevelopSuccessfully = true;
        }else if (this.isEqualLevel(CHARMELEON) && this.isEnoughPointsForLevel3())
        {
            this.finePointsForLevel3();
            this.setPokemonForNextLevel(MAX_LIVE_POINTS_OF_CHARIZARD,MAX_ATTACK_POINTS_OF_CHARIZARD,CHARIZARD,CHARIZARD_NAME);
            isDevelopSuccessfully = true;
        }
        return isDevelopSuccessfully;
    }



    private boolean conditionPerformAttack(int level, int playerSelection)
    {
        boolean conditionResult = false;
        if (level == CHARMANDER)
        {
            conditionResult = playerSelection != SCRATCH_ATTACK && playerSelection != KICK_ATTACK;

        } else if (level == CHARMELEON) {

            conditionResult = playerSelection != SCRATCH_ATTACK && playerSelection != FLAME_TAIL_ATTACK
                    && playerSelection != KICK_ATTACK;

        }else{
           conditionResult = playerSelection != SCRATCH_ATTACK && playerSelection != FLAME_TAIL_ATTACK
                   && playerSelection != KICK_ATTACK && playerSelection != FIERY_BLAST_ATTACK;
        }
        return conditionResult;
    }

    private void printAttackOptions(int level)
    {

            System.out.println("Please choose attack");
            System.out.println("Press 1 for KICK attack");
            System.out.println("Press 2 for SCRATCH attack");
        if (level == CHARMELEON) {
            System.out.println("Press 3 for FLAME TAIL attack");
        }else if (level == CHARIZARD){
            System.out.println("Press 3 for FLAME TAIL attack");
            System.out.println("Press 4 for FIERY BLAST attack");
        }
    }


    private boolean performSelectedAttack(int selectedAttack, Pokemon pokemonRival)
    {
        boolean isMakeAttackDone = false;
        if (selectedAttack == KICK_ATTACK)
        {
            super.kickAttack(pokemonRival);
            isMakeAttackDone = true;
        } else if (selectedAttack == SCRATCH_ATTACK) {
            isMakeAttackDone = scratchAttack(pokemonRival);
        } else if (selectedAttack == FLAME_TAIL_ATTACK) {
            isMakeAttackDone = flameTailAttack(pokemonRival);
        } else if (selectedAttack == FIERY_BLAST_ATTACK) {
            isMakeAttackDone = fieryBlastAttack(pokemonRival);
        }
        return isMakeAttackDone;
    }

}
