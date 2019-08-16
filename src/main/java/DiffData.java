import com.google.code.externalsorting.ExternalSort;

import java.io.*;

public class DiffData {
    private static String inputfile1 = "D:\\1.txt";
    private static String inputfile2 = "D:\\2.txt";
    private static String outputfile1 = "D:\\9.txt";
    private static String outputfile2 = "D:\\10.txt";

    public static void main(String[] args) throws IOException {
        Long a = System.currentTimeMillis();
        try {
            ExternalSort.mergeSortedFiles(ExternalSort.sortInBatch(new File(inputfile1)), new File(outputfile1));
            ExternalSort.mergeSortedFiles(ExternalSort.sortInBatch(new File(inputfile2)), new File(outputfile2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file1 = new File(outputfile1);
        File file2 = new File(outputfile2);
        System.out.println("start");
        InputStreamReader read1 = new InputStreamReader(new FileInputStream(file1));
        InputStreamReader read2 = new InputStreamReader(new FileInputStream(file2));
        BufferedReader bufferedReader1 = new BufferedReader(read1);
        BufferedReader bufferedReader2 = new BufferedReader(read2);
        String lineTxt1 = bufferedReader1.readLine();
        String lineTxt2 = bufferedReader2.readLine();

        while (lineTxt1 != null && lineTxt2 != null){
            if (lineTxt1.compareTo(lineTxt2) == 0){
                lineTxt1 = bufferedReader1.readLine();
                lineTxt2 = bufferedReader2.readLine();
                continue;
            }else if (lineTxt1.compareTo(lineTxt2) >0 ){
                System.out.println("add"+lineTxt2);
                lineTxt2 = bufferedReader2.readLine();
            }else if (lineTxt1.compareTo(lineTxt2)<0){
                System.out.println("less"+lineTxt1);
                lineTxt1 = bufferedReader1.readLine();
            }
        }
        if (lineTxt1 == null && lineTxt2 != null){
            while(lineTxt2 != null){
                System.out.println("add"+lineTxt2);
                lineTxt2 = bufferedReader2.readLine();
            }
        }else if (lineTxt1 != null && lineTxt2 == null){
            while (lineTxt1 != null){
                System.out.println("less"+lineTxt1);
                lineTxt1 = bufferedReader1.readLine();
            }
        }

        bufferedReader1.close();
        bufferedReader2.close();
        read1.close();
        read2.close();
        System.out.println((System.currentTimeMillis()-a)/1000);
    }
}
