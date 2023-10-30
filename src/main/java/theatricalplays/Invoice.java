package theatricalplays;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;


public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  float totalAmount = 0;
  int volumeCredits = 0;
  List<Order> detailsOrder = new ArrayList<>();
  float total_after_reduction = 0;



  public Invoice(Customer customer, List<Performance> performances) {
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
    this.customer.setPoints(this.customer.getPointsFidelites()+volumeCredits);
    if(this.customer.getPointsFidelites() >= 150) {
    this.total_after_reduction = this.totalAmount -15;
      this.customer.setPoints(this.customer.getPointsFidelites()-150);
    }

  }

  StringBuffer toText(Map<String, Play> plays) {
    this.calculerTotal(plays);
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", this.customer.getNom()));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for(Order order : this.detailsOrder) {
        result.append(String.format("  %s: %s (%s seats)\n", order.playName, frmt.format(order.amount / 100), order.audience));

    }
    result.append(String.format("Amount owed is %s\n", frmt.format(this.totalAmount )));
    result.append(String.format("You earned %s credits\n", this.volumeCredits));
    return result;
  }

  StringBuffer toHtml(Map<String, Play> plays) {
    this.calculerTotal(plays);

    StringBuffer result = new StringBuffer(String.format(
      "<!DOCTYPE html>\r\n" + //
        "<html>\r\n" +
        "<head>\r\n" +
        "<title>Invoice</title>\r\n" +
        "</head>\r\n" +
        "<body>\r\n" +
        "<h1>Invoice</h1>\r\n" +
        "<ul>\r\n" +
        "<li><strong>Client : </strong>BigCo</li>\r\n" +
        "</ul>\r\n" +
        "<table border=1 style= \"border-spacing: 4px;\">\r\n" +
        "<thead>\r\n" +
        "<tr>\r\n" +
        "<th>Piece</th>\r\n" +
        "<th>Seats sold</th>\r\n" +
        "<th>Price</th>\r\n" +
        "</tr>\r\n" +
        "</thead>\r\n"+
        "<tbody>\r\n"

        ));

      NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
      for(Order order : this.detailsOrder) {
        result.append(String.format("<tr>\r\n"+"<td>%s</td>\r\n"+"<td>%s</td>\r\n"+"<td>%s</td>\r\n"+
        "</tr>\r\n", order.playName,  order.audience,frmt.format(order.amount / 100)));
    }
    result.append(String.format(
        "<tr>\r\n" + //
        "<th colspan=\"2\">Total owed:</th>\r\n" + //
        "<td>%s</td>\r\n" + //
        "</tr>\r\n" + //
        "<tr>\r\n" + //
        "<th colspan=\"2\">Fidelity points earned:</th>\r\n" + //
        "<td>%s</td>\r\n" + //
        "</tr>\r\n",frmt.format(this.totalAmount), this.volumeCredits));

        if(this.total_after_reduction > 0) {
    result.append(String.format(
    "</tbody>\r\n"+
    "</table>\r\n" + //
    "<p>You have more than 150 points, so you benefit from a reduction of 15$</p>\r\n"+
    "<p><strong>New total : </strong>$1,715.00</p>\r\n" + //
    "<p>Payement is required under 30 days. We can break your knees if you don't so.</p>\r\n" + //
    "</body>\r\n" + //
    "</html>"

    ));
        }
        else {
    result.append(String.format(
    "</tbody>\r\n"+
    "</table>\r\n" + //
    "<p>Payement is required under 30 days. We can break your knees if you don't so.</p>\r\n"+
    "</body>\r\n" + //
    "</html>"

    ));
        }
        return result;
  }

}

