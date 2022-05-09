import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
public class OCRHelper {
 
    private final String LANG_OPTION = "-l";
    private final String EOL = System.getProperty("line.separator");
 
    private String tessPath = "D://Tesseract";
    //private String tessPath = new File("tesseract").getAbsolutePath();
 
    public String recognizeText(File imageFile) throws Exception {
        
        File outputFile = new File(imageFile.getParentFile(), "output");
 
        StringBuffer strB = new StringBuffer();
        List<String> cmd = new ArrayList<String>();
 
        cmd.add(tessPath + "\\tesseract");
 
        cmd.add("");
        cmd.add(outputFile.getName());
        cmd.add(LANG_OPTION);
        cmd.add("chi_sim");
        //cmd.add("eng");
 
        ProcessBuilder pb = new ProcessBuilder();
        /**
         *Sets this process builder's working directory.
         */
        pb.directory(imageFile.getParentFile());
        cmd.set(1, imageFile.getName());
        pb.command(cmd);
        pb.redirectErrorStream(true);
        long startTime = System.currentTimeMillis();
        System.out.println("StartTime: " + startTime);
        Process process = pb.start();
        // tesseract.exe 1.jpg 1 -l chi_sim
        
        // Runtime.getRuntime().exec("tesseract.exe 1.jpg 1 -l chi_sim");
        /**
         * the exit value of the process. By convention, 0 indicates normal
         * termination.
         */
//      System.out.println(cmd.toString());
        int w = process.waitFor();
        if (w == 0)
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),
                    "UTF-8"));
            String str;
 
            while ((str = in.readLine()) != null) {
                strB.append(str).append(EOL);
            }
            in.close();
 
            long endTime = System.currentTimeMillis();
            System.out.println("EndTime: " + endTime);
            System.out.println("Duration: " + (endTime - startTime) + "ms");
        } else {
            String msg;
            switch (w) {
                case 1:
                    msg = "Errors accessing files. There may be spaces in your image's filename.";
                    break;
                case 29:
                    msg = "Cannot recognize the image or its selected region.";
                    break;
                case 31:
                    msg = "Unsupported image format.";
                    break;
                default:
                    msg = "Errors occurred.";
            }
            throw new RuntimeException(msg);
        }
        new File(outputFile.getAbsolutePath() + ".txt").delete();
        return strB.toString().replaceAll("\\s*", "");
    }
}