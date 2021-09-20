# waterCloud
###该项目主要用途是一个能够进行权限控制的手脚架，主要是自己日常开发感觉好用的技术和好用的点，把这些整合起来方便以后开发别的项目时快速搭建。
项目当前整合的技术有:
- springboot
- mybatisplus
- shiro
- jwt
- redis
- mysql

后面计划整合的技术：
- springcloud
- rabbitmq
- quartz(进行了一部分)
- ....

项目结构：
- waterCloud是主pom，主要设置一些依赖 
- cloud-webmagic是核心内容，叫webmagic只是觉得名称好听并不是爬虫哦！（虽然我也在里面写了一点webmagic这个框架的东西，没啥意义只是写了一点），由于后面计划整合springcloud的东西，这个模块
后面将作成一个公共的模块，是springcloud其他模块都要依赖的。当前cloud-webmagic实现了接口级别的权限控制，只需要在你写的接口上面加一个 @RequiresPermissions("user:add")注解，“user:add”
是权限，需要在sys_permission表中设置，每新写一个接口都需要在sys_permission表中新增一条，同时添加一条sys_role_permission记录。sys_role表中是角色，每个用户都拥有一个或多个角色，
可以自行设置。

项目要点：
- 发请求时接口都需要在header中设置“token”，没有@RequiresRoles注解的接口无需，“/sys_user/login”是获取token的接口也就是登录
- mybatisplus自动生成代码默认设置的代码覆盖，生成代码时注意别把写好的代码覆盖掉了，CodeGenerator类运行起来输入表明就可以自动生成代码了
- mybatisplus设置了自动填充，表中create_time、create_by、update_time、update_by（字段明一定要叫这个不然不会填充）字段都会自动填充，其中create_by、update_by字段只有在请求中存在token时才会自动填充否则填充null，
- 在controller层使用@AutoLogAnnotation注解，会记录从日志sys_log，注意其他层使用会有一些问题，后面会完善
- 项目需要redis，所以没有redis的话接口会认证通过不了
- SysUserController中有一些示例，可以参考

由于本人是搞后端的对前端不太熟悉，所以目前只有后端程序，接口权限配置都是直接在表中直接操作的，后面会把开源的ant-design-vue拿来做成可以在前端配置权限的项目。后面项目就像网上那些开源的管理系统一样，我自己搞一遍熟悉技术练手，有好的意见还请多多指教！

