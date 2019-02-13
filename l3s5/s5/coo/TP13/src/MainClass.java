
 import java.io.File;
  import java.io.FilenameFilter;
  
  /*
  　　需求： 判断E盘目录下是否有后缀名为.jpg的文件，如果有，就输出此文件名称
     分析：
   两种方式：
  A:先获取所有的，然后遍历的时候，依次判断，如果满足条件就输出。
          a:封装h判断目录
    b:获取该目录下所有文件或者文件夹的File数组
           c:遍历该File数组，得到每一个File对象，然后判断
           d:是否是文件
             是：继续判断是否以.jpg结尾
                   是：就输出该文件名称
                   否：不搭理它
               否：不搭理它
               
       
  B: 获取的时候就已经是满足条件的了，然后输出即可。
          要想实现这个效果，就必须学习一个接口：文件名称过滤器
         public String[] list(FilenameFilter filter)
         public File[] listFiles(FilenameFilter filter)        
  */
 public class MainClass {
 
     public static void main(String[] args) {
         
         //封装h判断目录
         File file = new File("Documents/");
                  //获取该目录下所有文件或者文件夹的File数组
         File[] file1 = file.listFiles();

         //遍历该File数组，得到每一个File对象，然后判断
         for(File f : file1){
             //是否是文件
             if(f.isFile()){                 //是否以.jpg结尾
                 if(f.getName().endsWith(".jpg")){
                     System.out.println(f.getName());
                     //艾斯.jpg
                 }
             }

      }
     }
 }