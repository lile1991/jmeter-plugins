#jmeter-plugins
====

### 包含功能如下:
````
1. Redis插件, 需要依赖: jedis.jar
2. MD5函数插件
3. 加解密函数插件
4. HTTP接口自动签名前置处理器（接口参数按业务组装并MD5加密， 自动设置到消息头中）
````

## 安装方法
1. mvn package  
2. 将jar包复制到JMeter_Home/lib/ext目录下

#### MD5函数示例：
![MD5函数](https://git.oschina.net/uploads/images/2017/0824/145918_2bcdb271_467163.png "MD5.png")"# jmeter-plugins" 

#### Redis配置示例:
![增加Redis配置](https://git.oschina.net/uploads/images/2017/0824/145903_9c81ea50_467163.png "AddRedis.png")
![设置参数](https://git.oschina.net/uploads/images/2017/0824/145911_2f15b300_467163.png "Redis.png")


