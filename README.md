# IMProject

学习一个即时通讯项目

在开发版本仅容器化mysql和redis以解决环境问题, Java的容器化考虑在中后期并入, 

目前的compose.yaml只使用了mysql和redis相应的镜像,作为开发环境.

同时,持久化保存使用的是bind volume的方式挂载以便于日常开发,后期考虑使用volume的方式进行挂载.
