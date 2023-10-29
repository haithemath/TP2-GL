package theatricalplays;

public class Play {

  public String name;
  public PieceType type;

  private Play(String name, PieceType type) {
    this.name = name;
    this.type = type;

   }

   public  static  Play creerPlay(String name, PieceType type) {
    if(type==null) {
      throw new IllegalArgumentException("le type ne doit pas etre null");
    }

    return new Play(name, type);
}
}
