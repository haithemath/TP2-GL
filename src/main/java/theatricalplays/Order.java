package theatricalplays;


/*
 * Cette classe sert à enregistrer une ligne de commande pour chaque pièce de théatre
 * elle contient : Le nom de la pièce
 * le total
 * le nombre de places
 * elle est utilisée pour l'affichage de détails de la pièce dans la facture
 */

public class Order {
  String playName;
  float amount;
  int audience;
  public Order(String playname, float amount, int audience) {
    this.playName = playname;
    this.amount = amount;
    this.audience = audience;
  }

  public String getPlayName() {
    return this.playName;
  }
    public float getAmount() {
    return this.amount;
  }
    public int getAudience() {
    return this.audience;
  }
}
