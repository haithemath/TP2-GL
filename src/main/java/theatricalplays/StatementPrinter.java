package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  public StringBuffer print(Invoice invoice, Map<String, Play> plays) {
    invoice.calculerTotal(plays);
    float totalAmount = invoice.totalAmount;
    int volumeCredits = invoice.volumeCredits;
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for(Order order : invoice.detailsOrder) {
        result.append(String.format("  %s: %s (%s seats)\n", order.playName, frmt.format(order.amount / 100), order.audience));

    }
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount )));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result;
  }




}

