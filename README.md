# JavaTutorials
* mongodb module compile by gradle v6.6
* http://jsonhandle.sinaapp.com/ chrome JSON extension
* `MacOS 下快捷键 ⌘⌥L` 格式化代码 ` ⌥代表 option`
* `idea 激活码`[链接](http://idea.medeming.com/jets)`http://idea.medeming.com/jets`
* `https://developers.google.com/photos/library/guides/get-started-java`[链接](https://github.com/google/java-photoslibrary) Google photo RESTFul api
# MacOS下的快捷键
* `win + alt + l` 格式化代码
* `ctrl + alt + o` 更新导入的依赖包
* 整合easy excel 
* 整合 xxl-job
* 整合 solr和shiro
* 整合https://hacpai.com/article/1587991687126 typeHandler
* 整合https://github.com/huage2580/leeks 在Idea里面查看基金走势
* add typehandler 
* add webLogAspect
* custom class loader
* jvmti enctypt jar
* git pull difference git pull --rebase 
* threadLocal
* [dingtalk download](https://github.com/codewindy/JavaTutorials/blob/master/common/src/main/java/com/codewindy/common/utils/Test.java)
* 面向切面编程时，常用的API拦截方式有Fliter，Interceptor，ControllerAdvice以及Aspect，它们的拦截顺序为 Fliter -> Interceptor -> ControllerAdvice -> Aspect -> controller。这里我们使用Aspect来实现。
 ```markdown
spring aop就是一个同心圆，要执行的方法为圆心，最外层的order最小。从最外层按照AOP1、AOP2的顺序依次执行doAround方法，doBefore方法。然后执行method方法，最后按照AOP2、AOP1的顺序依次执行doAfter、doAfterReturn方法。也就是说对多个AOP来说，先before的，一定后after。

对于上面的例子就是，先外层的就是对所有controller的切面，内层就是自定义注解的。 那不同的切面，顺序怎么决定呢，尤其是同格式的切面处理，譬如两个execution的情况，那spring就是随机决定哪个在外哪个在内了。

所以大部分情况下，我们需要指定顺序，最简单的方式就是在Aspect切面类上加上@Order(1)注解即可，order越小最先执行，也就是位于最外层。像一些全局处理的就可以把order设小一点，具体到某个细节的就设大一点。
```
* [spring log aspect](https://github.com/techial1042/Blog/issues/64)
* [springboot与cloud对应版本](https://start.spring.io/actuator/info)
* docker pull rabbitmq
```
docker run -d --name rabbitmq3.8.9 
-p 5672:5672 -p 15672:15672  
--hostname rabbitmq  
-e RABBITMQ_DEFAULT_VHOST=/vhost 
-e RABBITMQ_DEFAULT_USER=admin 
-e RABBITMQ_DEFAULT_PASS=admin

```
* [死信对列](https://my.oschina.net/10000000000/blog/1624963)