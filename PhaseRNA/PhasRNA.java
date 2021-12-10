package PhasRNA;

/***
 * @ Author Dot4diw
 * @ Function
 */
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;


public class PhasRNA {

    public static void main(String[] args) {

        final String strInputFile = "/home/am4ture/Code/Java/PhasRNA/Phas-loci.txt";
        final String strOutputFile = "/home/am4ture/Code/Java/PhasRNA/Phas-loci-Result.txt";
        BufferedReader bufferReader = null;
        PrintWriter printWriter = null;
    
        try {
            // BufferedReader br = new BufferedReader(new InputStreamReader(new
            // FileInputStream(new File(strInputFile)), "UTF-8"));
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new
            // FileOutputStream(new File(strOutputFile))));
            bufferReader = new BufferedReader(new FileReader(strInputFile));
            File outputFile = new File(strOutputFile);
            if(!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(strOutputFile,true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
           
            printWriter = new PrintWriter(bufferWriter);
        

            String strLine = null;
            while ((strLine = bufferReader.readLine()) != null) {
                String[] strCols = strLine.split("\t");
                String phasLociID = strCols[0];
                String phasGffID = strCols[1];
                int phasMinPos = Integer.parseInt(strCols[2]);
                int phasMaxPos = Integer.parseInt(strCols[3]);
                String gffFileName = "/home/am4ture/Code/Java/PhasRNA/newgff/".concat(phasGffID).concat(".gff");
                
                System.out.println("Computed gff file: " + phasGffID);
                
                BufferedReader gffReader = null;

                try {
                    gffReader = new BufferedReader(new FileReader(gffFileName));
                    String gffStrLine = null;
        
                    while((gffStrLine = gffReader.readLine()) != null) {
                        String [] gffCols = gffStrLine.split("\t");
                        int gffMinPos = Integer.parseInt(gffCols[3]);
                        int gffMaxPos = Integer.parseInt(gffCols[4]);
                        String gffAnnotation = gffCols[8];
                        if(((gffMinPos < phasMinPos) && (phasMinPos < gffMaxPos)) || ((gffMinPos < phasMaxPos) && (phasMaxPos< gffMaxPos))) {
                            String resultPhasAnnotation =String.format("%s\t%s\t%s\t%s\t%s", phasLociID, phasGffID, phasMinPos, phasMaxPos, gffAnnotation);
                    
                            System.out.println(resultPhasAnnotation);
                            printWriter.println(resultPhasAnnotation);
                        }
                        else{
                            ;
                        }
                    }
                }catch(FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                }catch(IOException ioe) {
                    ioe.printStackTrace();
                }finally 
                {
                    try {
                        if ( gffReader != null)
                        {
                            gffReader.close();
                        }
                    }
                    catch (IOException ioe) 
                    {
                        System.out.println("Error in closing the BufferedReader");
                    }
                }

            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally 
        {
            try {
                if (bufferReader != null)
                {
                    bufferReader.close();
                    printWriter.close();
                }
            }catch (IOException ioe) 
            {
                System.out.println("Error in closing the BufferedReader: " + ioe);
            }
        // finally 
        // {
        //     try {
        //         if (bufferReader != null && printWritter != null)
        //         {
        //             bufferReader.close();
        //             printWriter.close();
        //         }
        //     }catch (IOException ioe) 
        //     {
        //         System.out.println("Error in closing the BufferedReader: " + ioe);
        //     }
        }
    }


    // public static void compareToGffFile(String fileName) {
    //     try {
    //         BufferedReader gffReader = new BufferedReader(new FileReader(fileName));
    //         String gffStrLine = null;

    //         while((gffStrLine = gffReader.readLine()) != null) {
    //             String [] gffCols = gffStrLine.split("\t");
    //             int gffMinPos = Integer.parseInt(gffCols[3]);
    //             int gffMaxPos = Integer.parseInt(gffCols[4]);
    //             String gffAnnotation = gffCols[8];
    //             if(((gffMinPos < phasMinPos) && (phasMinPos < gffMaxPos)) || ((gffMinPos < phasMaxPos) && (phasMaxPos< gffMaxPos))) {
    //                 String resultPhasAnnotation =String.format("%s\t%s\t%s\t%s\t%s\n", phasLociID, phasGffID, phasMinPos, phasMaxPos, gffAnnotation);
            
    //                 System.out.println(resultPhasAnnotation);
    //                 //printWriter.println(resultPhasAnnotation);
    //             }
    //             else{
    //                 ;
    //             }
    //         }
    //     }
    //     catch(FileNotFoundException fnfe) {
    //         fnfe.printStackTrace();
    //     }
    //     catch(IOException ioe) {
    //         ioe.printStackTrace();
    //     }
    //     finally 
    //     {
    //         try {
    //             if (gffReader != null)
    //             {
    //                 gffReader.close();
    //             }
    //         }
    //         catch (IOException ioe) 
    //         {
    //             System.out.println("Error in closing the BufferedReader");
    //         }
    //     }
        
	//}



}
