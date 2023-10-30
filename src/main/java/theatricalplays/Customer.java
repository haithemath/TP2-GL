package theatricalplays;

public class Customer {
  private String nom;
  private int num_c;
  private int points_fidelites;

  public Customer() {

  }
    public Customer(String nom, int num_c, int points_fidelites) {
      this.nom = nom;
      this.num_c = num_c;
      this.points_fidelites = points_fidelites;
  }

  public String getNom() {
    return this.nom;
  }
   public int geNum() {
    return this.num_c;
  }
   public int getPointsFidelites() {
    return this.points_fidelites;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }
  public void setPoints(int solde) {
    this.points_fidelites = solde;
  }
   public void setNumc(int num_c) {
    this.num_c = num_c;
  }

}
