package GameLogic.Commands.PlayersAction;

import GameLogic.Receptors.Player;
import GameLogic.Invocator.Card.Card;
import GameLogic.Invocator.Card.CardType;
import GameLogic.Commands.CommandName;
import GameLogic.Commands.ConcreteCommand;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public abstract class PlayersAction extends ConcreteCommand {
    // TODO : utiliser un servant worker plutôt qu'un player --> demander une action au bon client
    protected Player player;

    public PlayersAction(CommandName name) {
        super(name);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static PlayersAction askAction(Player player) {

        Scanner in = new Scanner(System.in);
        String action;

        // Todo changer les system.in en lecture de JSON
        do {
            System.out.println(player.getName() + " what do you want to do ?");
            action = in.nextLine();
        } while (CommandName.getCommandName(action) == null) ;

        PlayersAction playersAction = null;
        if (CommandName.getCommandName(action).isPlayerAction()) {
            playersAction = (PlayersAction) CommandName.getCommandName(action).getCommand();
            playersAction.setPlayer(player);

            if (playersAction.getName() == CommandName.PLAY_CARD) {
                System.out.println("What card do you want to play ?");
                //action = in.nextLine();
                // TODO change the following line to the chosen card
                ((PlayCard)playersAction).setCardToPlay(new Card(1, "1", CardType.SPELL, 1));
            }
        }


        return playersAction;
    }

    @Override
    public JSONObject toJson() {
        JSONObject playersAction = super.toJson();
        try {
            playersAction.put("player", player.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return playersAction;
    }
}
