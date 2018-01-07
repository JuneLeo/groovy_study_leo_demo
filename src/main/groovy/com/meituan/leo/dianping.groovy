package com.meituan.leo

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
def file = new File("/Users/leo/java/gradle-study-leo-demo/a/leo.txt")

file.eachLine {
    String content ->
        println content
}

def bytes = file.getBytes()
String content = new String(bytes)
println(content)


