package gui.board;

import gameLogic.receptors.Chest;
import gameLogic.receptors.Creature;
import gameLogic.receptors.Player;
import gameLogic.receptors.Receptor;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedList;

/** Cette classe permet d'instancier les lignes composant le "plateau" de jeu. */
public class GUILine {

  // Le nombre de cases dans une ligne
  private final int NB_SPOTS = 12;

  // Le numéro de la ligne
  private int noLine;

  //Les cases composant la ligne
  private LinkedList<GUISpot> GUISpots;

  //La liste de creatures
  private LinkedList<Receptor> receptors;

  //La liste de trésors
  private LinkedList<Chest> chests;

  private VBox vBox;
  private GridPane gridPane;

  // le groupe d'îlots qu'on affichera par la suite
  Group root;


  public GUILine(int noLine) {
    this.noLine = noLine;
  }

  /**
   * Constructeur de la classe GUILine
   *
   * @param noLine : le numéro de la ligne
   */
  public GUILine(int noLine, GridPane gridPane, VBox vbox, Player player1, Player player2) throws IOException {
    this.noLine = noLine;
    receptors = new LinkedList<>(); // on initialise la liste de créatures.
    GUISpots = new LinkedList<>(); // on initialise la liste de GUISpots.
    chests = new LinkedList<>(); //on initialise la liste de chess.

    this.vBox = vbox;
    this.gridPane = gridPane;

    int indexCreature = 0;
    for (int spot = 0; spot < NB_SPOTS; ++spot) {
      vbox = new VBox(); // On créé une box verticale pour aligne la créature à l'île.
      GUISpots.add(new GUISpot());
      if(spot == 0)
      {
        chests.add(new Chest("",player1));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren()
                .addAll((chests.get(0).getImageView()), (GUISpots.get(spot).getImageView()));
        gridPane.add(vbox, spot, noLine);
      }
      else if(spot == 11) {
        chests.add(new Chest("",player2));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren()
                .addAll((chests.get(1).getImageView()), (GUISpots.get(spot).getImageView()));
        gridPane.add(vbox, spot, noLine);
      }
      else {
        receptors.add(new Creature("unknown", 0, 0, 0));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren()
                .addAll((receptors.get(indexCreature++).getImageView()), (GUISpots.get(spot).getImageView()));
        gridPane.add(vbox, spot, noLine);
      }

    }
  }

  public void setReceptor(Receptor receptor, int spot) {
    receptors.get(spot).setTo(receptor);

    ObservableList<Node> childrens = gridPane.getChildren();
    for (Node node : childrens) {
      if(gridPane.getRowIndex(node) == noLine && gridPane.getColumnIndex(node) == spot) {
        ((VBox)node).getChildren().set(0, receptors.get(spot).getImageView());
        break;
      }
    }
  }

  /**
   * Permet de récupérer le nombre de cases.
   *
   * @return le nombre de cases de la ligne.
   */
  public int getNB_SPOTS() {
    return NB_SPOTS;
  }

  public GUISpot getSpot(int index) {
    if (GUISpots == null)
      return null;
    return GUISpots.get(index);
  }

  /**
   * Permet de récupérer la liste des cases de la ligne.
   *
   * @return la liste des cases de la ligne.
   */
  public LinkedList<GUISpot> getGUISpots() {
    return GUISpots;
  }

  /**
   * Permet de récupérer le numéro de la ligne.
   *
   * @return le numéro de la ligne.
   */
  public int getNoLine() {
    return noLine;
  }
}
