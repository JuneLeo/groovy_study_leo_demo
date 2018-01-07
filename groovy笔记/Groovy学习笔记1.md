# Groovy学习笔记1

### 配置
* 首先下载Groovy [网址](http://groovy-lang.org/download.html)

* 解压，在 .bash_profile 配置环境变量  
```
#groovy
GROOVY_HOME=$HOME/groovy-2.4.13
export PATH=$PATH:${GROOVY_HOME}/bin
```
* 记得 source .bash_profile,

  * 然后 groovy -v  显示  Groovy Version: 2.4.13 JVM: 1.8.0_25 Vendor: Oracle Corporation OS: Mac OS X

* 使用IJ创建java项目，引入groovy 即可。

### 使用

* 创建与执行

创建 Groovy Script 类型文件

*meituan.groovy*
```Groovy
println("meituan")
```
终端输入 groovy meituan.groovy或者右键 Run
```
leodeMacBook-Pro:groovy leo$ groovy meituan.groovy
meituan
```
* 字符串

特殊：三引号是原样输出支持换行，单引号中$符号不生效，双引号$符号可以输出字符内容
```Groovy
String str = "hello meituan"
String str2 = ", welcome"
String getString() {
    "hello leo"
}
String str3 = '''I
   am
       leo
'''
println(str)
println(getString())
println(str3)
println('${str+str2}')
println("${str + str2}")
```
```
hello meituan
hello leo
I
   am
      leo
 
${str+str2}
hello meituan, welcome
```
* def关键字，和python中的关键字一样的性质，代表任意类型

* 集合
```Groovy
//def int a = 1
def list = ["android", "gradle", "groovy"]
 
list.each {
    item -> println(item)
}
println("---------------------------------------------------")
println(list[1])
list[1] = "java"
println(list[1])
println("---------------------------------------------------")
def map = ["name": "leo", "age": 23, "habby": "code"]
 
map.entrySet().each {
    item ->
        println(item.key + "," + item.value)
}
println("---------------------------------------------------")
map.each {
    key, value ->
        println(key + "," + value)
}
println("---------------------------------------------------")
println(map.get("name"))
map.name = "song"
println(map."name")
map['name'] = "leo"
println(map['name'])
```
```
android
gradle
groovy
---------------------------------------------------
gradle
java
---------------------------------------------------
name,leo
age,23
habby,code
---------------------------------------------------
name,leo
age,23
habby,code
---------------------------------------------------
leo
song
leo
```
* Groovy类
```Groovy
class MeiTuan {
    String name = "Default"
    String getApp() {
        "App"
    }
    String getDianPing() {
        "MeiTuan"
        "DianPing"
    }
    String getMeiTuan() {
        return "MeiTuan"
        "DianPing"
    }
}
 
def meituan = new MeiTuan()
//
println(meituan.getApp())
println(meituan.getMeiTuan())
 
leo = {
    new MeiTuan().getDianPing()
}
println(leo())
println(meituan.getName()) //groovy会自动生成get/set方法
```
```
App
MeiTuan
DianPing
Default
```
* 闭包Closure  闭包就是{}代码块
```Groovy
def add = {
        //闭包
    int a, int b -> a + b
}
println(add(1, 2))
def del = {
        //不指定数据类型
    a, b -> a - b
}
println(del.call(1, 2))
println("---------------------------------------------------")
def name = {
    println("leo")
}
name()
 
def noParam = {
    println("${it}")    //内置隐含参数it
}
noParam.call("leo")
println("---------------------------------------------------")
def together = {
    Closure closure, a, b, c  //Closure 为闭包，它可以作为参数传递
        ->
        closure(a, b) + c
}
println(together(add, 1, 1, 1))   //如果成功，输出结果为3
```
```
3
-1
---------------------------------------------------
leo
---------------------------------------------------
leo
---------------------------------------------------
3
```
* 闭包委托  resolveStrategy 委托策略有多种方式，可以自行查看地址
```Groovy
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
```
```
30
50
```

