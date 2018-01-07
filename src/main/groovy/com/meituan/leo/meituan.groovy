package com.meituan.leo

//println("meituan")

//String str = "hello meituan"
//
//String str2 = ", welcome"
//
//String getString() {
//    "hello leo"
//}
//
//String str3 = '''I
//   am
//       leo
//'''
//
//println(str)
//
//println(getString())
//
//println(str3)
//
//println('${str+str2}')
//
//println("${str + str2}")

//def a = 1, b = 2
//
//println a + b

//a = 1
//b = 2
//println(a + b)

//def int a = 1
//def list = ["android", "gradle", "groovy"]
//
//list.each {
//    item -> println(item)
//}
//println("---------------------------------------------------")
//println(list[1])
//list[1] = "java"
//println(list[1])
//println("---------------------------------------------------")
//def map = ["name": "leo", "age": 23, "habby": "code"]
//
//map.entrySet().each {
//    item ->
//        println(item.key + "," + item.value)
//}
//println("---------------------------------------------------")
//map.each {
//    key, value ->
//        println(key + "," + value)
//}
//println("---------------------------------------------------")
//println(map.get("name"))
//map.name = "song"
//println(map."name")
//map['name'] = "leo"
//println(map['name'])
//class MeiTuan {
//    String name = "Default"
//
//    String getApp() {
//        "App"
//    }
//
//    String getDianPing() {
//        "MeiTuan"
//        "DianPing"
//    }
//
//    String getMeiTuan() {
//        return "MeiTuan"
//        "DianPing"
//    }
//}
//
//def meituan = new MeiTuan()
////
//println(meituan.getApp())
//println(meituan.getMeiTuan())
//
//leo = {
//    new MeiTuan().getDianPing()
//}
//println(leo())
//println(meituan.getName())

//def add = {
//        //闭包
//    int a, int b -> a + b
//}
//println(add(1, 2))
//def del = {
//        //不指定数据类型
//    a, b -> a - b
//}
//println(del.call(1, 2))
//println("---------------------------------------------------")
//def name = {
//    println("leo")
//}
//name()
//
//def noParam = {
//    println("${it}")    //内置隐含参数it
//}
//noParam.call("leo")
//println("---------------------------------------------------")
//def together = {
//    Closure closure, a, b, c  //Closure 为闭包，它可以作为参数传递
//        ->
//        closure(a, b) + c
//}
//println(together(add, 1, 1, 1))   //如果成功，输出结果为3
class Test {
    def x = 30
    def y = 40

    def run() {
        def data = [x: 10, y: 20]
        def cl = {
            y = x + y
        }
        //相当于给函数  设置默认值    delegate 委托数据    resolveStrategy委托策略
        cl.delegate = data
        cl.resolveStrategy = Closure.DELEGATE_FIRST  //优先委托的值,默认将从委托里面取，可以取到空
        println(cl())

    }
}

new Test().run()

class Test2 {
    def x = 30
//    def y = 40

    def run() {
        def data = [x: 10, y: 20]
        def cl = {
            y = x + y
        }
        //相当于给函数  设置默认值    delegate 委托数据    resolveStrategy委托策略
        cl.delegate = data
        cl.resolveStrategy = Closure.OWNER_FIRST  //意思，优先own的值，其次是委托的值
        println(cl())   //优先 x = 30，发现没有y，其次是委托的值

    }
}

new Test2().run()






