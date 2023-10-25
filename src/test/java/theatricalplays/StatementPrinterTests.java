package theatricalplays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Play("Hamlet",PieceType.tragedy),
                "as-like", new Play("As You Like It", PieceType.comdey),
                "othello", new Play("Othello", PieceType.tragedy));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

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
}
