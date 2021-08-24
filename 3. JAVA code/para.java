package pgl;
import pgl.infra.utils.IOUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class para {
    public static void main ( String[] args ) {
        int[] chrom = new int[100];
        for (int i = 0; i < 100; i++) {
            chrom[i] = i+1;
        }
       Para1(chrom, "/Users/song/Documents/1. Lulab/arabidopsis/100samples/taxaBamMap.txt", "/Users/song/Documents/1. Lulab/arabidopsis/100samples/taxa");
    }
    public static void Para1( int chr[], String infileS, String outfileS ) {
        try {
            BufferedWriter bw=null;
            String temp="";
            int chrA[]= chr;
            for (int i=0; i < chrA.length; i++) {
                BufferedReader br=IOUtils.getTextReader ("/Users/song/Documents/1. Lulab/arabidopsis/100samples/taxaBamMap.txt");
                int line=1;
                bw=IOUtils.getTextWriter ("/Users/song/Documents/1. Lulab/arabidopsis/100samples/taxa/taxaBamMap_" + chrA[i] +".txt");
                while ((temp=br.readLine ( )) != null) {
                    if (line == 2) {
//                        bw.write (Integer.toString (chrA[i]));
                        bw.write ("sample"+chrA[i]+".bam"+"\t"+"10"+"\t"+"/data1/home/xinyue/100X_single/bam/true10X_bam/taxa"+chrA[i]+"/sample"+chrA[i]+".bam");
                        bw.newLine ( );
                    }
//                    else if (line == 14) {
//                        bw.write (Integer.toString (chrA[i]));
////                        bw.write("/data1/home/xinyue/ref/byChr/chr0"+chrA[i]+".fa.gz");
//                        bw.newLine ( );
//                    }
//                     else if (line == 20) {
//                        bw.write ("/data1/home/xinyue/100X_single/2_fastcall/taxa"+chrA[i]+"/Lib/1_1_30427672.lib.gz");
//                        bw.newLine ( );
//                    }
//                    else if (line == 35) {
//                        bw.write ("/data1/home/xinyue/100X_single/2_fastcall/taxa"+chrA[i]+"/vcf");
//                        bw.newLine ( );
//                    }
                    else {
                        bw.write (temp);
                        bw.newLine ( );
                    }
                    line++;
                }
                bw.flush ( );
                bw.close ( );
                br.close ( );
            }
//            }
        } catch (Exception e) {
            e.printStackTrace ( );
        }
    }
}
