import java.io.*;
import java.util.*;
import java.awt.*;

public class Depurador {
   public static void main (String args[]) {
      if (depuracion()) {
         abrirArch();
      }
   }

   public static boolean depuracion() {
        BufferedWriter bw;
        FileWriter fw = null;
        Scanner scan;
        String cad;
        int cont = 0;
        boolean ban = false;
        ArrayList<String> archivo = new ArrayList<String>();
        try {
            scan = new Scanner(new File("ProgramaNoDep.magtm"));
            while (scan.hasNextLine()) {
               cad = scan.nextLine();
               cad = cad.replaceAll("\\t","");
               for(int x=0; x<cad.length(); x++) {
                  int codAsci = cad.charAt(x);
                  if((codAsci>=32 && codAsci<=34)||(codAsci>=37 && codAsci<=62)||(codAsci>=65 && codAsci<=95)||(codAsci>=97 && codAsci<=126)) {
                     if(cad.charAt(x)=='/') {
                        cad = cad.substring(0, x);
                     }
                  }
               }
               cont++;
               if(cad.isEmpty()) continue;
               else {
                  cad = String.valueOf(cont-1)+" "+cad;
                  archivo.add(cad);
               }
            }
            scan.close();
            fw = new FileWriter("ProgramaDepurado.dep");
            bw = new BufferedWriter(fw);
            for(String x: archivo) {
               bw.write(x+"\n");
               System.out.println(x);
            }
            bw.close();
        } catch (Exception e) {
            System.out.print("EROR! El archivo no pudo generarse");
        } finally {
            if (fw != null) {
               try {
                  System.out.print("El archivo se ha depurado correctamente");
                  fw.close();
                  ban = true;
               } catch (Exception e) {
                  System.out.print("ERROR! No se pudo generar el archivo");
               }
            }
        }
        return ban;
   }
   
   public static void abrirArch() {
      File f = new File ("ProgramaDepurado.dep");
      try {
         Desktop.getDesktop().open(f);
      } catch (Exception e) {
         System.out.print("ERROR! No se pudo abrir el archivo");
      }
   }
}      