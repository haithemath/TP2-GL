package theatricalplays;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;

public class Invoice {

  public String customer;
  public List<Performance> performances;
  float totalAmount = 0;
  int volumeCredits = 0;
  List<Order> detailsOrder = new ArrayList<>();



  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }


  public void calculerTotal(Map<String, Play> plays) {
    for(Performance perf : this.performances) {
      Order order;
      Play play = plays.get(perf.playID);
      float thisAmount = 0;


      if(play.type == PieceType.tragedy) {
          thisAmount = 40000;
          if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
          }
      }
      else if(play.type == PieceType.comdey) {
          thisAmount = 30000;
          if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
          }
          thisAmount += 300 * perf.audience;
      }

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if (PieceType.comdey==play.type) volumeCredits += Math.floor(perf.audience / 5);
        order = new Order(play.name, thisAmount, perf.audience);
      this.detailsOrder.add(order);

      totalAmount += thisAmount;
    }
    this.totalAmount /=100;

  }

}

