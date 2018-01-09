package com.meituan.leo

import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.Node
import groovy.util.slurpersupport.NodeChildren

////查看list each 源码，发现正常写法为下面这样写的
//def list = [1, 2, 3, 4, 5]
//
//list2 = list.each({
//        //有（）,each的参数为Closure，简写省略了（）
//    item -> println(item)
//})
//
//list.each {
//        //省略（）
//    item -> println(item)
//}
//
//println(list2)
//println("------------------------------------------------------")

//集合 补充  除了  list map 还有 range
//def range = 1..5
//range.each {
//    println("range:" + it)  //或者这样简写
//}
//
//println("------------------------------------------------------")
//
//rangeFindAll = range.findAll({
//    it % 2 != 0     //过滤
//})
//
//println(rangeFindAll)
//println("------------------------------------------------------")

//x = 1
//def printx() {
//    println x
//}
//
//printx()
//
//def y = 1
//def printy() {
//    println y
//}
//
//printy()

//println("------------------------------------------------------")
// groovy io 操作

//读文件
//def file = new File("/Users/leo/java/groovy_study_leo_demo/a/leo.txt")
//println(file.getAbsoluteFile())
//try{
//    file.eachLine {  //有兴趣的可以查看源码，使用了while循环进行了读取
//    String content ->
//        println content
//    }
//
//    def bytes = file.getBytes()
//    String content = new String(bytes)
//    println(content)
//
//    def inputStream = file.newInputStream()
//    inputStream.eachLine {
//        String oneLine ->
//            println(oneLine)
//    }
//    inputStream.close()
//
//    println("------------------------------------------------------")
//
//    file.withInputStream {
//        ism ->
//            ism.eachLine {
//                String oneLine ->
//                    println(oneLine)
//            }
//    }
//}catch (FileNotFoundException e){
//    e.printStackTrace()
//}

////写文件
//def file = new File("/Users/leo/java/groovy_study_leo_demo/a/write.txt")
//
//
//try {
//    def stream = file.newOutputStream()
//    String content = "leo"
//    stream.write(content.getBytes())
//    stream.close()
//
//    stream.withWriter {
//        writer->
//            writer.write(content)
//    }
//    file.withWriter {
//        writer->
//            writer.write("宋鹏飞")
//    }
//    file.withOutputStream {
//        os->
//            os.withWriter {
//                writer->
//                    writer.write("leo，hello")
//            }
//    }
//
//} catch (FileNotFoundException e) {
//    e.printStackTrace()
//}

//xml操作

//def xml = new XmlSlurper()
//def file = new File("/Users/leo/java/groovy_study_leo_demo/src/main/resources/leo.xml")
//GPathResult pathResult = xml.parse(file)
//def name = pathResult.books.book[1].name
//println(name)


//Gradle中，每一个待编译的工程都叫一个Project。每一个Project在构建的时候都包含一系列的Task。比如一个Android APK的编译可能包含：Java源码编译Task、资源编译Task、JNI编译Task、lint检查Task、打包生成APK的Task、签名Task等。
//一个Project到底包含多少个Task，其实是由编译脚本指定的插件决定。插件是什么呢？插件就是用来定义Task，并具体执行这些Task的东西。
//gradle projects 查看项目下有几个子project
//gradle task 查看项目下任务
















