# Groovy学习笔记2

### list补充
* 查看list each 源码，发现正常写法为下面这样写的
```Groovy
def list = [1, 2, 3, 4, 5]

list2 = list.each({
        //有（）,each的参数为Closure，简写省略了（）
    item -> println(item)
})

list.each {
        //省略（）
    item -> println(item)
}

println(list2)
```
```
1
2
3
4
5
1
2
3
4
5
[1, 2, 3, 4, 5]
```

* range集合
```Groovy
def range = 1..5
range.each {
    println("range:" + it)  //或者这样简写
}

println("------------------------------------------------------")

rangeFindAll = range.findAll({
    it % 2 != 0     //过滤
})

println(rangeFindAll)
```
```
range:1
range:2
range:3
range:4
range:5
------------------------------------------------------
[1, 3, 5]  //过滤结果
```
### Closure
```Groovy
x = 1
def printx() {
    println x
}

printx()

def y = 1
def printy() {
    println y   // y获取不到
}

printy()
```
```
1
Caught: groovy.lang.MissingPropertyException: No such property: y for class: com.meituan.leo.groovy.dianping
groovy.lang.MissingPropertyException: No such property: y for class: com.meituan.leo.groovy.dianping
	at com.meituan.leo.groovy.dianping.printy(dianping.groovy:43)
	at com.meituan.leo.groovy.dianping.run(dianping.groovy:46)
```

### 文件I/O操作
* io read操作
```groovy
def file = new File("/Users/leo/java/gradle-study-leo-demo/a/leo.txt")

file.eachLine {
    String content ->
        println content
}

def bytes = file.getBytes()
String content = new String(bytes)
println(content)
```

```
我是leo
我是leo
```



