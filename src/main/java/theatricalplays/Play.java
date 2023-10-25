package theatricalplays;

public class Play {

  public String name;
  public PieceType type;

  public Play(String name, PieceType type) {
    if(type == PieceType.comdey || type == PieceType.tragedy) {
  this.name = name;
  this.type = type;
    }

  }
}
