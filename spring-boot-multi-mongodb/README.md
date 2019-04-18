#MongoDB的适合对大量或者无固定格式的数据进行存储，比如：日志、缓存等。对事物支持较弱，不适用复杂的多文档（多表）的级联查询
#Lombok使用方法：
添加依赖后，找到maven下的jar包并执行：
1.安装完成之后，请确认eclipse安装路径下是否多了一个lombok.jar包，并且其
2. 配置文件eclipse.ini中是否 添加了如下内容:
       -javaagent:lombok.jar
       -Xbootclasspath/a:lombok.jar
3.如果上面的答案均为true，那么恭喜你已经安装成功
4.重启eclipse或myeclipse