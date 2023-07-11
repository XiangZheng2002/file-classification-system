// package Finalpackage;
import javax.swing.JOptionPane;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Operation{
    public Operation(){

    }

    public static boolean copyFile(String fromWhere, String toWhere) throws Exception{
        try {
            Path sourceFile = Paths.get(fromWhere);
            Path targetFolder = Paths.get(toWhere);
            Files.copy(sourceFile, targetFolder.resolve(sourceFile.getFileName()));
            System.out.println("File copied successfully!");
            return true;
        } catch (IOException e) {
            System.err.println("Failed to copy file: " + e.getMessage());
            return false;
        }
    }

    public static void classify(String address, String attr, String content) throws Exception{
        Map<String, String> fromANDto = new HashMap<>();
        List <String> filePaths = getAllFilePaths(address);
        // 修改默认地址位置！
         String defaultDic = "C:/Users/小小木/Documents/homework/大二下/JAVA1/finalProject/file/SORTED/";
//        String defaultDic = "C:/";
        String folder1;
        String toDic = "";
        boolean flag = true;
        if(attr.equals("标签")){
            folder1 = "/label/";
            toDic = defaultDic + GUI.fileClass + folder1 + content;
            for(String filePath: filePaths){
                String current = setANDread.getLabel(filePath);
                if(current.equals(content)){
                    fromANDto.put(filePath, toDic);
                }
            }
        }
        else if(attr.equals("时间")){
            folder1 = "/time/";
            toDic = defaultDic + GUI.fileClass + folder1 + content;
            for(String filePath: filePaths){
                String current = setANDread.getTime(filePath);
                if(current.equals(content)){
                    fromANDto.put(filePath, toDic);
                }
            }
        }
        else if(attr.equals("地点")){
            folder1 = "/place/";
            toDic = defaultDic + GUI.fileClass + folder1 + content;
            for(String filePath: filePaths){
                String current = setANDread.getPlace(filePath);
                if(current.equals(content)){
                    fromANDto.put(filePath, toDic);
                }
            }
        }
        else if(attr.equals("人物")){
            folder1 = "/author/";
            toDic = defaultDic + GUI.fileClass + folder1 + content;
            System.out.print(toDic);
            for(String filePath: filePaths){
                String current = setANDread.getName(filePath);
                if(current.equals(content)){
                    fromANDto.put(filePath, toDic);
                }
            }
        }
        // 创建目录
        File folder = new File(toDic);
        // 检查文件夹是否已存在
        if (folder.exists()) {
            System.out.println("文件夹已存在");
        } else {
            // 创建文件夹
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("文件夹创建成功");
            } else {
                System.out.println("文件夹创建失败");
            }
        }
        for (Map.Entry<String, String> entry : fromANDto.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 使用key和value进行操作
            try{
                flag = copyFile(key, value);
            }
            catch(IOException e){
                flag = false;
                System.out.println("Something wrong in COPYING FILES!");
                JOptionPane.showMessageDialog(null, "Something wrong in COPYING FILES!");
            }

        }
        if(flag == true){
            JOptionPane.showMessageDialog(null, "COPY FILE PROCESS FINISH!");
        }
    }

    public static List<String> getAllFilePaths(String folderPath) {
        List<String> filePaths = new ArrayList<>();
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            getAllFiles(folder, filePaths);
        }
        return filePaths;
    }

    private static void getAllFiles(File folder, List<String> filePaths) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filePaths.add(file.getAbsolutePath());
                } else if (file.isDirectory()) {
                    getAllFiles(file, filePaths);
                }
            }
        }
    }
};
