# hsweb-framework 
后台管理基础框架,基于maven,spring-boot,mybatis

### 主要功能
1. 基础dao,service,controller类，增删改查直接继承即可.
2. 通用mybatis配置文件,支持多种条件查询自动生成,支持自动生成insert,update,delete语句 支持和查询相同的各种条件.
3. 实现用户,权限管理;基于aop,注解,精确到按钮的权限控制.
4. 动态表单功能,可在前端设计表单,动态生成数据库表,提供统一的增删改查接口.
5. 数据库支持 mysql,oracle,h2

### 其他组件
1. [hsweb-easy-orm](https://github.com/hs-web/hsweb-easy-orm) :为动态表单设计的orm框架
2. [hsweb-expands-compress](https://github.com/hs-web/hsweb-expands/tree/master/hsweb-expands-compress) :文件压缩，解压操作
3. [hsweb-expands-office](https://github.com/hs-web/hsweb-expands/tree/master/hsweb-expands-office) :office文档操作( excel读写，模板导出，word模板导出)
4. [hsweb-expands-request](https://github.com/hs-web/hsweb-expands/tree/master/hsweb-expands-request): 请求模拟(http,ftp)
5. [hsweb-expands-script](https://github.com/hs-web/hsweb-expands/tree/master/hsweb-expands-script):动态脚本,动态编译执行java,groovy,javascript,spel,ognl....
6. [hsweb-expands-shell](https://github.com/hs-web/hsweb-expands/tree/master/hsweb-expands-shell):shell执行
7. [hsweb-expands-template](https://github.com/hs-web/hsweb-expands/tree/master/hsweb-expands-template):各种模板引擎

# 安装
```bash
    $ git clone https://github.com/hs-web/hsweb-framework.git
    $ cd hsweb-framework
    $ mvn install -DskiptTests
```

# 使用
参照:[hsweb-platform](https://github.com/hs-web/hsweb-platform)
示例:[hsweb.me](http://hsweb.me)测试用户:test (test2,test3,test4....) 密码:123456 

# 许可

[apache2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
