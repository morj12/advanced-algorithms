package principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

// Classe per homogeneitzar els errors.

public class ErrorWriter {

    static public void writeError(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        String slogs = writer.toString();
        System.err.println("Se ha producido un error. El error es:\n" + slogs);
        try {
            FileWriter fr = new FileWriter("logs.txt", true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write("********************** Error date: " + (new Date()));
            br.newLine();
            br.write(slogs);
            br.write("********************** End error report");
            br.newLine();
            br.newLine();
            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("Error a la manipulació de l'arxiu de logs.");
            e.printStackTrace();
        }
    }
}
