package ch.bbw.cge.jokemon;

import ch.bbw.cge.jokemon.move.*;

import java.util.*;
import java.util.Map;

public class Battle {
    private final Trainer attacker;
    private final Trainer defender;

    protected Trainer winner;
    protected Trainer loser;

    private Jokemon currentAttackerJokemon;
    private Jokemon currentDefenderJokemon;

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    Map<String, Move> attackMap = new HashMap<>();



    public Battle(Trainer attacker, Trainer defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void fight() {

        attackMap.put("Absorb", new Absorb());
        attackMap.put("Bubble", new Bubble());
        attackMap.put("Bubble Beam", new BubbleBeam());
        attackMap.put("Crabhammer", new Crabhammer());
        attackMap.put("Ember", new Ember());
        attackMap.put("Fire Blast", new FireBlast());
        attackMap.put("Fire Punch", new FirePunch());
        attackMap.put("Fire Spin", new FireSpin());
        attackMap.put("Flamethrower", new Flamethrower());
        attackMap.put("Hydro Pump", new HydroPump());
        attackMap.put("Mega Drain", new MegaDrain());
        attackMap.put("Petal Dance", new PetalDance());
        attackMap.put("Razor Leaf", new RazorLeaf());
        attackMap.put("Scratch", new Scratch());
        attackMap.put("Solar Beam", new SolarBeam());
        attackMap.put("Tackle", new Tackle());
        attackMap.put("Waterfall", new Waterfall());
        attackMap.put("Water Gun", new WaterGun());

        System.out.println("Encounter with Opponent:");
        System.out.println(attacker.getName() + " has " + attacker.getMyJokemons().size() + " Jokemons and is level " + attacker.getLevel());
        System.out.println(defender.getName() + " has " + defender.getMyJokemons().size() + " Jokemons and is level " + defender.getLevel());
        System.out.println("----------------------");

        updateCurrentJokemon();

        while (doBothHaveAliveJokemons()) {
            updateStatus();
            handleAttack(attacker, currentAttackerJokemon, currentDefenderJokemon);

            if (!doBothHaveAliveJokemons()) break;

            handleAttack(defender, currentDefenderJokemon, currentAttackerJokemon);
        }
        updateStatus();

        System.out.println("The battle has ended!");
        if (winner != null && loser != null) {
            endBattle(winner, loser);
        } else {
            System.out.println("An error occurred: Could not determine the winner or loser.");
        }
    }

    private void handleAttack(Trainer trainer, Jokemon attackingJokemon, Jokemon defendingJokemon) {
        if (attackingJokemon != null) {
            String trainerType = trainer.getStatus() == Trainer.Status.USER ? "USER" : "BOT";
            System.out.println(trainerType + " attack");

            if (trainer.getStatus() == Trainer.Status.USER) {
                doUserAttack(attackingJokemon, defendingJokemon);
            } else if (trainer.getStatus() == Trainer.Status.BOT) {
                doBotAttack(attackingJokemon, defendingJokemon);
            }
        }
    }

    void endBattle(Trainer winner, Trainer loser){
        System.out.println(loser.getName() + " has no alive Jokemons!\n" + winner.getName() + " wins!");
        System.out.println("----------------");
        updateStatus();
        addXpToWinner();
        if (attacker.getStatus() == Trainer.Status.BOT) attacker.regenerateAllJokemons();
        if (defender.getStatus() == Trainer.Status.BOT) defender.regenerateAllJokemons();
    }

    void addXpToWinner() {
        int winnerLevel = winner.getLevel();
        int loserLevel = loser.getLevel();
        int difference = winnerLevel - loserLevel;

        int baseXp;

        if (difference < 0) {
            baseXp = Math.abs(difference) * 2;
            baseXp += 2;
            System.out.println("Bonus of 2XP given for winning against a higher-level opponent!");
        } else if (difference > 0) {
            baseXp = Math.max(18, difference-5);
            System.out.println("Reduced XP due to winning against a lower-level opponent.");
        } else {
            baseXp = 15;
            System.out.println("Same level opponents, awarding base XP.");
        }

        System.out.println("Added " + baseXp + "XP to " + winner.getName() + " and their Jokemons!");
        winner.addXp(baseXp);
        winner.addXpToAllJokemons(baseXp);
    }


    void updateStatus() {
        System.out.println(attacker.getName() + " Jokemons:");
        for (Jokemon i : attacker.getMyJokemons()){
            System.out.println(i.getName() + " has " + i.hp + " HP and is " + i.getStatus());
        }
        System.out.println(defender.getName() + " Jokemons:");
        for (Jokemon i : defender.getMyJokemons()){
            System.out.println(i.getName() + " has " + i.hp + " HP and is " + i.getStatus());
        }
    }
    void doBotAttack(Jokemon currentAttackerJokemon, Jokemon currentDefenderJokemon) {
        System.out.println("It's their turn!");
        System.out.println("They attack with their " + currentAttackerJokemon.getName() + " against your " + currentDefenderJokemon.getName());

        List<String> moves = currentAttackerJokemon.getMoves();
        if (moves.isEmpty()) {
            System.out.println(currentAttackerJokemon.getName() + " has no moves to attack!");
            return;
        }

        String chosenAttack = moves.get(random.nextInt(moves.size()));
        Move attack = attackMap.get(chosenAttack);

        if (attack != null) {
            currentAttackerJokemon.attack(currentDefenderJokemon, attack);
        } else {
            System.out.println("Invalid attack selected!");
        }

        updateCurrentJokemon();
    }

    void doUserAttack(Jokemon currentAttackerJokemon, Jokemon currentDefenderJokemon) {
        System.out.println("It's your turn!");
        System.out.println("You attack with your " + currentAttackerJokemon.getName() + " against their " + currentDefenderJokemon.getName());

        List<String> moves = currentAttackerJokemon.getMoves();

        if (moves.isEmpty()) {
            System.out.println(currentAttackerJokemon.getName() + " has no moves available!");
            return;
        }

        System.out.print("Attack with ");
        System.out.println(String.join(" or ", moves) + "?");

        boolean validAttack = false;
        while (!validAttack) {
            String chosenAttack = scanner.nextLine();

            if (moves.contains(chosenAttack)) {
                Move attack = attackMap.get(chosenAttack);
                if (attack != null) {
                    currentAttackerJokemon.attack(currentDefenderJokemon, attack);
                    validAttack = true;
                }
            } else {
                System.out.println("Not a valid attack! Please try again:");
            }
        }

        updateCurrentJokemon();
    }


    void updateCurrentJokemon(){
        currentAttackerJokemon = findFirstAliveJokemon(attacker);
        currentDefenderJokemon = findFirstAliveJokemon(defender);
    }
    boolean doBothHaveAliveJokemons(){
        Jokemon aliveAttackerJokemon = findFirstAliveJokemon(attacker);
        Jokemon aliveDefenderJokemon = findFirstAliveJokemon(defender);
        if (aliveDefenderJokemon == null && aliveAttackerJokemon != null) {
            winner = attacker;
            loser = defender;
            return false;
        }
        if (aliveDefenderJokemon != null && aliveAttackerJokemon == null) {
            winner = defender;
            loser = attacker;
            return false;
        }
        if (aliveDefenderJokemon == null && aliveAttackerJokemon == null) {
            System.out.println("An error has occured: Both Trainers have none alive Jokemon");
            return false;
        }
        else return true;
    }
    Jokemon findFirstAliveJokemon(Trainer trainer) {
        for (int i = 0; i < trainer.getMyJokemons().size(); i++) {
            if (trainer.getMyJokemons().get(i).getStatus() == Jokemon.Status.ALIVE) {
                return trainer.getMyJokemons().get(i);
            }
        }
        return null;
    }
}
