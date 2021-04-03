//名   字：余本江
//加油！每天你都是最棒的！
//微   信：sink75
package 我的源码;

import java.io.*;
/*
这个可以拷贝整个目录到指定目录下
 */
public class success01 {
        public static void main(String[] args) {
            File begin = new File("D:\\Resources\\练习素材");//拷贝源
            File end = new File("D:\\img\\练习素材");//拷贝目标

            copyDir(begin,end);
        }

        private static void copyDir(File begin, File end) {
            if(begin.isFile()){
                FileInputStream in = null;
                FileOutputStream out = null;
                try {
                    in = new FileInputStream(begin);
                    String path = (end.getAbsolutePath().endsWith("\\") ?
                            end.getAbsolutePath() : end.getAbsolutePath() + "\\")
                            + begin.getAbsolutePath().substring(3);
                    out = new FileOutputStream(path);
                    byte[] bytes = new byte[1024 * 1024];
                    int readCount = 0;
                    while ((readCount = in.read(bytes)) != -1){
                        out.write(bytes,0,readCount);
                    }
                    out.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return;//如果begin是一个文件的话，递归结束
            }

            //获取源下面的子目录
            File[] files = begin.listFiles();
            for(File file : files){
                if(file.isDirectory()){//如果file是一个目录的话，就新建一个对应的目录
                    String beginDir = file.getAbsolutePath();
                    String endDir = (end.getAbsolutePath().endsWith("\\") ?
                            end.getAbsolutePath() : end.getAbsolutePath() + "\\")
                            + beginDir.substring(3);
                    File newFile = new File(endDir);
                    if(!newFile.exists()){
                        newFile.mkdirs();
                    }
                }
                copyDir(file,end);//使用递归的方法调用
            }
        }
}