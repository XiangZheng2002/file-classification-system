import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.time.ZoneId;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;



public class setANDread {
    // 设置特定时间
    static boolean setTimeZero(String filePath, String contents[]) throws Exception{
        Path file = Path.of(filePath);
        int temp[] = new int[6];
        for(int i=0; i<6; i++){
            temp[i] = Integer.parseInt(contents[i]);
        }
        // 创建 FileTime 对象并设置为文件的最后修改时间
        // LocalDateTime specificDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        LocalDateTime specificDateTime = LocalDateTime.of(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
        Date date = Date.from(specificDateTime.atZone(ZoneId.systemDefault()).toInstant());
        long timestamp = date.getTime() / 1000;
        FileTime time = FileTime.from(timestamp, java.util.concurrent.TimeUnit.SECONDS);

        // 获取文件的基本属性视图
        BasicFileAttributeView view = Files.getFileAttributeView(file, BasicFileAttributeView.class);

        // 获取文件的基本属性
        BasicFileAttributes attrs = view.readAttributes();
        System.out.println("Information in attrs");
        System.out.println(attrs);

        // 输出文件的创建时间、最后修改时间、最后访问时间
        System.out.println("Creation Time: " + attrs.creationTime());
        System.out.println("Last Modified Time: " + attrs.lastModifiedTime());
        System.out.println("Last Accessed Time: " + attrs.lastAccessTime());

        // 设置时间为特定时间(更改creation time)
        view.setTimes(time, null, time);
        System.out.println("Updated Time: " + attrs.creationTime());
        return true;
    }

    // 设置默认时间(当前时间)
    static boolean setTimeZero(String filePath) throws Exception{
        Path file = Path.of(filePath);
        // 获取文件的基本属性视图
        BasicFileAttributeView view = Files.getFileAttributeView(file, BasicFileAttributeView.class);

        // 获取文件的基本属性
        BasicFileAttributes attrs = view.readAttributes();
        System.out.println("Information in attrs");
        System.out.println(attrs);

        // 输出文件的创建时间、最后修改时间、最后访问时间
        System.out.println("Creation Time: " + attrs.creationTime());
        System.out.println("Last Modified Time: " + attrs.lastModifiedTime());
        System.out.println("Last Accessed Time: " + attrs.lastAccessTime());

        // 设置文件的最后修改时间为当前时间
        FileTime now = FileTime.from(Instant.now());
        // 三个参数分别为最后修改时间、最后访问时间和创建时间
        view.setTimes(now, now, now);
        return true;
    }

    // // 读取时间属性
    // static String getTime(String filePath) {
    //     try {
    //         Path file = Path.of(filePath);
    //         BasicFileAttributeView view = Files.getFileAttributeView(file, BasicFileAttributeView.class);
    //         BasicFileAttributes attrs = view.readAttributes();
    //         System.out.println("Creation Time: " + attrs.creationTime());
    //         System.out.println("Last Modified Time: " + attrs.lastModifiedTime());
    //         System.out.println("Last Accessed Time: " + attrs.lastAccessTime());
    //         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //         System.out.println("Time");
    //         String formattedDate = dateFormat.format(attrs.creationTime());
    //         System.out.println("formattedDate");
    //         System.out.println(formattedDate);
    //         return formattedDate;
    //     } catch (Exception e) {
    //         // 处理其他异常
    //         e.printStackTrace();
    //         return "Error occurred";
    //     }
    // }

    // 设置用户自定义名称
    static boolean setTime(String filePath, String time) throws Exception{
        Path file = Path.of(filePath);
        // 创建UserDefinedFileAttributeView对象
        UserDefinedFileAttributeView view = Files.getFileAttributeView(
                file, UserDefinedFileAttributeView.class);

        // 设置人物属性，将"person"标识符和"Tom"值写入文件属性
        String attributeName = "Time";
        String attributeValue = time;
        ByteBuffer buf = StandardCharsets.UTF_8.encode(attributeValue);
        int written = view.write(attributeName, buf);
        System.out.println(written + " bytes written to " + attributeName);
        return true;
    }

    // 读取用户自定义标签
    static String getTime(String filePath) {
        try {
            Path file = Path.of(filePath);
            UserDefinedFileAttributeView view = Files.getFileAttributeView(
                    file, UserDefinedFileAttributeView.class);
            String attributeName = "Time";

            if (view.list().contains(attributeName)) {
                ByteBuffer buffer = ByteBuffer.allocate(view.size(attributeName));
                view.read(attributeName, buffer);
                buffer.flip();
                return StandardCharsets.UTF_8.decode(buffer).toString();
            } else {
                // 属性不存在时返回默认值
                return "Default Time";
            }
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // 设置用户自定义名称
    static boolean setName(String filePath, String name) throws Exception{
        Path file = Path.of(filePath);
        // 创建UserDefinedFileAttributeView对象
        UserDefinedFileAttributeView view = Files.getFileAttributeView(
                file, UserDefinedFileAttributeView.class);

        // 设置人物属性，将"person"标识符和"Tom"值写入文件属性
        String attributeName = "person";
        String attributeValue = name;
        ByteBuffer buf = StandardCharsets.UTF_8.encode(attributeValue);
        int written = view.write(attributeName, buf);
        System.out.println(written + " bytes written to " + attributeName);
        return true;
    }

    // 读取用户自定义标签
    static String getLabel(String filePath) {
        try {
            Path file = Path.of(filePath);
            UserDefinedFileAttributeView view = Files.getFileAttributeView(
                    file, UserDefinedFileAttributeView.class);
            String attributeName = "Label";

            if (view.list().contains(attributeName)) {
                ByteBuffer buffer = ByteBuffer.allocate(view.size(attributeName));
                view.read(attributeName, buffer);
                buffer.flip();
                return StandardCharsets.UTF_8.decode(buffer).toString();
            } else {
                // 属性不存在时返回默认值
                return "Default label";
            }
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            return "Error occurred";
        }
    }


    // 设置用户自定义标签
    static boolean setLabel(String fileAddress, String label) throws Exception{
        // 创建UserDefinedFileAttributeView对象
        Path file = Path.of(fileAddress);
        UserDefinedFileAttributeView view = Files.getFileAttributeView(
                file, UserDefinedFileAttributeView.class);

        // 设置人物属性，将"person"标识符和"Tom"值写入文件属性
        String attributeName = "Label";
        String attributeValue = label;
        ByteBuffer buf = StandardCharsets.UTF_8.encode(attributeValue);
        int written = view.write(attributeName, buf);
        System.out.println(written + " bytes written to " + attributeName);
        return true;
    }

    // 读取用户自定义地点
    static String getPlace(String filePath) {
        try {
            Path file = Path.of(filePath);
            UserDefinedFileAttributeView view = Files.getFileAttributeView(
                    file, UserDefinedFileAttributeView.class);
            String attributeName = "Place";

            if (view.list().contains(attributeName)) {
                ByteBuffer buffer = ByteBuffer.allocate(view.size(attributeName));
                view.read(attributeName, buffer);
                buffer.flip();
                return StandardCharsets.UTF_8.decode(buffer).toString();
            } else {
                // 属性不存在时返回默认值
                return "Default place";
            }
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // 设置用户自定义标签
    static boolean setPlace(String filePath, String place) throws Exception{
        Path file = Path.of(filePath);
        // 创建UserDefinedFileAttributeView对象
        UserDefinedFileAttributeView view = Files.getFileAttributeView(
                file, UserDefinedFileAttributeView.class);

        // 设置地址属性
        String attributeName = "Place";
        String attributeValue = place;
        ByteBuffer buf = StandardCharsets.UTF_8.encode(attributeValue);
        int written = view.write(attributeName, buf);
        System.out.println(written + " bytes written to " + attributeName);
        return true;
    }


    // 读取author
    static String getName(String filePath) {
        try {
            Path file = Path.of(filePath);
            UserDefinedFileAttributeView view = Files.getFileAttributeView(
                    file, UserDefinedFileAttributeView.class);
            String attributeName = "person";

            if (view.list().contains(attributeName)) {
                ByteBuffer buffer = ByteBuffer.allocate(view.size(attributeName));
                view.read(attributeName, buffer);
                buffer.flip();
                return StandardCharsets.UTF_8.decode(buffer).toString();
            } else {
                // 属性不存在时返回默认值
                return "Default value";
            }
        } catch (Exception e) {
            // 处理其他异常
            e.printStackTrace();
            return "Error occurred";
        }
    }


    // 打印file的基本信息
    static void display(String filePath) throws Exception{
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File Name: " + file.getName());
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Label : " + file.canWrite());
            System.out.println("Is Readable: " + file.canRead());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Is File: " + file.isFile());
            System.out.println("File Size in Bytes: " + file.length());
        } else {
            System.out.println("File does not exist.");
        }
    }

    // 调用设置函数
    static boolean set(String path, String attr, String content) throws Exception{
        try {
            boolean flag = false;
            if(attr.equals("标签")){
                flag = setLabel(path, content);
                if(flag == true){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(attr.equals("地点")){
                flag = setPlace(path, content);
                if(flag == true){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(attr.equals("人物")){
                flag = setName(path, content);
                if(flag == true){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(attr.equals("时间")){
                if(!content.equals("")){
                    // String[] arrOfStr = content.split(",");
                    // flag = setTimeZero(path, arrOfStr);
                    flag = setTime(path, content);
                    if(flag == true){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else{
                    flag = setTimeZero(path);
                    if(flag == true){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
            // 处理返回值...
        } catch (Exception ex) {
            // 处理异常...
        }

        return false;
    }

    // 调用查看函数
    static Map check(String path) throws Exception{
        // String author = getName(path);
        // System.out.println("AUTHOR!!!!!!!!!!!!!");
        // System.out.println(author);
        Map<String, String> attrs = new HashMap<>();
        // try{
        System.out.println("HAS GO INTO THE MAP FUNCTION");
        // 查看标签
        String label = getLabel(path);
        if(!label.equals("")){
            attrs.put("标签", label);
        }
        System.out.println("biaoqianDOWN");
        // 查看时间
        String time = getTime(path);
        System.out.println("time");
        System.out.println(time);
        if(!time.equals("")){
            attrs.put("时间", time);
        }
        System.out.println("TIMEDOWN");
        // 查看地点
        String place = getPlace(path);
        if(!place.equals("")){
            attrs.put("地点", place);
        }
        System.out.println("PLACEDOWN");
        // 查看人物
        String author = getName(path);
        if(!author.equals("")){
            attrs.put("作者", author);
        }
        System.out.println("AUTHORDOWN");
        attrs.forEach((key, value) -> System.out.println(key + " : " + value));
        // }catch (Exception ex) {
        //     // 处理异常...
        // }
        return attrs;
    }
}

