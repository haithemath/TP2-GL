package theatricalplays;


import java.util.Map;

public class StatementPrinter {

  public StringBuffer print(Invoice invoice, Map<String, Play> plays, String type) {
    StringBuffer result = new StringBuffer("");
    if("text".equals(type.toLowerCase())) {
    result =  invoice.toText(plays);
    }
    else if("html".equals(type.toLowerCase())) {
      result = invoice.toHtml(plays);
    }
    return result;
  }



}

