# .gradle 文件目录

本文转自[地址](https://zhuanlan.zhihu.com/p/26473930)，[地址2](https://zhuanlan.zhihu.com/p/26019083)
       
* .gradle顶级目录
  * caches gradle缓存目录
  * daemon gradle日志目录
  * native gradle平台相关目录
  * wrapper gradle-wrapper下载目录
* caches 缓存目录
  * 目录
    * 2.14.1 | gradle程序的脚本（gradle程序版本）
    * 3.2.1 | gradle程序的脚本（gradle程序版本）    
    * jars-1 | ?    
    * jars-2 | ?    
    * modules-2 | 下载缓存目录  
  * caches/modules-2目录
    * files-2.1 | gradle下载的jar/aar目录
    * metadata-2.16 | gradle-2.14.1的描述文件？
    * metadata-2.23 | gradle-3.2.1的描述文件？
  * files-2.1的目录组织（jar/aar都下载到这里）
    * ${org}/${package}/${version}/${shanum1}/${package-version}.pom
    * ${org}/${package}/${version}/${shanum2}/${package-version}.jar
    ```
        #jcenter上jar的路径
        https://jcenter.bintray.com/com/android/tools/lint/lint-api/25.1.3/lint-api-25.1.3.jar
        #对应的缓存目录为
        .gradle/caches/modules-2/files-2.1/com.android.tools.lint/lint-api/25.1.3/${shasum1}/lint-api-25.1.3.jar
        
        #看下这个目录下有什么？
        ~ find .gradle/caches/modules-2/files-2.1/com.android.tools.lint/lint-api/25.1.3/ -type f
        #除了jar包还有pom，用于描述jar。
        .gradle/caches/modules-2/files-2.1/com.android.tools.lint/lint-api/25.1.3/14c6a94811fb8114a61b8f3ab29214f9466b5c59/lint-api-25.1.3.jar
        .gradle/caches/modules-2/files-2.1/com.android.tools.lint/lint-api/25.1.3/c4844e26d84dd1f450f90d89d7e2d2d09f52760/lint-api-25.1.3.pom
        
        #看下jar的shasum
        ~ shasum .gradle/caches/modules-2/files-2.1/com.android.tools.lint/lint-api/25.1.3/14c6a94811fb8114a61b8f3ab29214f9466b5c59/lint-api-25.1.3.jar
        
        14c6a94811fb8114a61b8f3ab29214f9466b5c59  .gradle/caches/modules-2/files-2.1/com.android.tools.lint/lint-api/25.1.3/14c6a94811fb8114a61b8f3ab29214f9466b5c59/lint-api-25.1.3.jar
        #即目录名。
      ```
      注意：创建jcenter时，对于jar包，可以没有pom，但是如果使用aar，则必须有pom，所以最好是每个版本都有个pom。因为pom中也描述了依赖关系。
* deamon 目录
  * 用于存放gradle daemon的运行日志。按gradle程序版本存放。
  * 2.14.1 | gradle-2.14.1运行的日志
  * 3.2.1 | gradle-3.2.1运行的日志
* native 目录
  * 用于存放平台相关（Win/Linux/Mac）的库。
  * 19 | gradle-2.14.1对应的lib目录，按平台存放，如osx-amd64
  * 21 | gradle-3.2.1对应的lib目录，按平台存放，如osx-amd64
  * jansi | ? 
* wrapper目录
  * 用于存放gradle-wrapper下载gradle的zip包和解压后的文件夹
  * wrapper的目录规则是
    * wrapper/dists/gradle-2.14.1-all/${base36}/gradle-2.14.1-all.zip 
    * wrapper/dists/gradle-2.14.1-all/${base36}/gradle-2.14.1-all.zip.lck
    * wrapper/dists/gradle-2.14.1-all/${base36}/gradle-2.14.1-all.zip.ok  
  * 其中base36的规则为：
    * 从gradle/wrapper/gradle-wrapper.properties中得到distributionUrl，即https://services.gradle.org/distributions/gradle-2.14.1-all.zip，注意文件中的\不算。
    * 对distributionUrl计算md5。例如printf “https://services.gradle.org/distributions/gradle-2.14.1-all.zip” | md5 
      得到8c9a3200746e2de49722587c1108fe87。
    * 利用0x8c9a3200746e2de49722587c1108fe87构造一个uint 128位整数。
    * 将整数利用base36得到base36的值（取小写）。
  * 从gradle-wrapper的jar包中提取的Java代码如下：
    ```java
       import java.math.BigInteger;
       import java.security.MessageDigest;
       
       public class Hash {
       
           public static void main(String[] args) {
               try {
                   MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                   byte[] bytes = args[0].getBytes();
                   messageDigest.update(bytes);
                   String str = new BigInteger(1, messageDigest.digest()).toString(36);
                   System.out.println(str);
               } catch (Exception e) {
                   throw new RuntimeException("Could not hash input string.", e);
               }
           }
       }
    ``` 
* gradle 下载加速
  *  很多人用Android Studio，最不爽的就是下载包依赖非常的缓慢。这是因为国内bintray网站访问速度很慢。谢谢阿里云，给我们提供了bintray jcenter mirror。如果想用阿里云的jcenter加速，请在用户目录/.gradle里放上下面的配置文件init.gradle，即可自动将依赖改为从阿里云下载。
  ```groovy
     gradle.projectsLoaded {
         rootProject.allprojects {
             buildscript {
                 repositories {
                     def REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
                     all { ArtifactRepository repo ->
                         if (repo instanceof MavenArtifactRepository) {
                             def url = repo.url.toString()
                             if (url.startsWith('https://repo1.maven.org/maven2')
                                 || url.startsWith('https://jcenter.bintray.com/')) {
                                 project.logger.lifecycle "Repository ${repo.url} replaced by $REPOSITORY_URL."
                                 println("buildscript ${repo.url} replaced by $REPOSITORY_URL.")
                                 remove repo
                             }
                         }
                     }
                     jcenter {
                         url REPOSITORY_URL
                     }
                 }
             }
             repositories {
                 def REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
                 all { ArtifactRepository repo ->
                     if (repo instanceof MavenArtifactRepository) {
                         def url = repo.url.toString()
                         if (url.startsWith('https://repo1.maven.org/maven2')
                             || url.startsWith('https://jcenter.bintray.com/')) {
                             project.logger.lifecycle "Repository ${repo.url} replaced by $REPOSITORY_URL."
                             println("allprojects ${repo.url} replaced by $REPOSITORY_URL.")
                             remove repo
                         }
                     }
                 }
                 jcenter {
                     url REPOSITORY_URL
                 }
             }
         }
     }
  ```  
  上方的代码其实可以放在根目录的 build.gradle中          