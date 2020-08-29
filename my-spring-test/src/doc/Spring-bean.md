

ResourceLoader 资源加载器, 接口

加载资源的一种方式，正是策略模式的实现

new ClassPathXmlApplicationContext(); 
实际做了 3 件事

1. 一直调用到 AbstractApplicationContext 的构造函数，构造函数内部设置了一个当前 Context 的
ResourcePatternResolver 为 PathMatchingResourcePatternResolver(支持 Ant 风格的路径解析) 如果没有进行方法重写的话

2. 把我们传递进去的配置文件的路径存放到了 AbstractRefreshableConfigApplicationContext 的 configLocations
数组中

3. 调用 AbstractApplicationContext 的 refresh 方法，进行刷新

```java
```