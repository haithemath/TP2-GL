package theatricalplays;



import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.*;

import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementPrinterTests {
     Map<String, Play> plays = Map.of(
                "hamlet",   Play.creerPlay("Hamlet",PieceType.tragedy),
                "as-like",  Play.creerPlay("As You Like It", PieceType.comdey),
                "othello",  Play.creerPlay("Othello", PieceType.tragedy));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));
    @Test
    void exampleStatement() {


        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays,"text");

        verify(result);
    }

    @Test
    void htmlRendu() {
       StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays,"html");

        verify(result);
    }

    // dans notre cas, ce test ne sert à rien puisque on a déjà introduit un nouveau type énumerative qui
    // va protéger le type de théatre en acceptant que les valeurs definies par ce type-là

    // @Test
    // void statementWithNewPlayTypes() {
    //     Map<String, Play> plays = Map.of(
    //             "henry-v",  new Play("Henry V", PieceType.henry),
    //             "as-like", new Play("As You Like It", "pastoral"));

    //     Invoice invoice = new Invoice("BigCo", List.of(
    //             new Performance("henry-v", 53),
    //             new Performance("as-like", 55)));

    //     StatementPrinter statementPrinter = new StatementPrinter();
    //     Assertions.assertThrows(Error.class, () -> {
    //         statementPrinter.print(invoice, plays);
    //     });
    // }



  /*
  * Tests unitaires
  */
      @Test
      void test_total_facture() {

        invoice.calculerTotal(plays);
        assertEquals(1730.00, invoice.totalAmount, 0.0001);
      }
      @Test
      void test_points_fidelites() {

        invoice.calculerTotal(plays);
        assertEquals(47, invoice.volumeCredits);


      }
}
