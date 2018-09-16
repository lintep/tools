package tools.util.file;

import tools.util.Directory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed on 6/23/2016.
 */
public class FileTools {

    public static boolean delete(String fileAddress) throws IOException {

        File file = new File(fileAddress);
        if (file.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDirectory(String directoryAddress){
        return new File(directoryAddress).isDirectory();
    }

    public static List<String> getFileAddress(String directory){
        File srcDir = new File(directory);

        if(srcDir.isDirectory()){

            List<String> resultFileAddress=new ArrayList<>();

            for(File dir:srcDir.listFiles()){
                if(dir.isFile()){
                    resultFileAddress.add(dir.getAbsolutePath());
                }
            }

            return resultFileAddress;
        }

        return null;
    }

    public static boolean exist(String fileAddress) {
        return new File(fileAddress).exists();
    }

    public static String getType(String fileAddress) throws IOException {
        BufferedReader bfr = tools.util.File.getBufferedReader(fileAddress);
        String line = bfr.readLine();
        System.out.println("\n\n"+fileAddress+"\n"+line);
        bfr.close();
        if(line.indexOf("moov")>=0){
            return "MP4";
        }
        if(line.indexOf("PNG")>=0){
            return "PNG";
        }
        if(line.indexOf("JFIF")>=0){
            return "JPG";
        }
        if(line.indexOf("PK")>=0){
            return "ZIP";
        }

        return "";
    }

    enum FileType{
        MP4,JPG,MP3,ZIP
    }

    public static void main(String[] args){
        File[] dirs = Directory.ls("P:\\m\\108/");//Muhammad-Bluray_1080.mp4?1522146738
        for (File file : dirs) {
            String type = null;
            try {
                type = getType(file.getAbsolutePath());
            } catch (IOException e) {
//                if(e.getMessage().indexOf("Access")<0){
                    System.out.println(file.getAbsoluteFile());
                    e.getMessage();
//                }
            } catch (NullPointerException e){
//                if(e.getMessage().indexOf("Access")<0){
                    System.out.println(file.getAbsoluteFile());
                    e.getMessage();
//                }
            }
            if(type!=null && type.length()>0) {
                System.out.println(file.getName());
                System.out.println("Type: " + type);
                System.out.println("_________________________");
//                try {
//                    tools.util.File.copy(file.getAbsolutePath(),file.getAbsolutePath()+"."+type.toLowerCase());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}

