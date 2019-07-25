# 第1章 Redis初识

## Redis的特性

+ 速度快:10W OPS,原因如下：
  + 数据存储在内存中
  + C语言编写
  + 单线程
+ 持久化：Redis所有数据保存在内存中，对数据的更新将异步地保存到磁盘上
+ 多种数据库结构
  + String/Blobs/Bitmaps
  + Hash Tables
  + Linked Lists
  + Sets
  + Sorted Sets
  + HyperLogLog:超小内存唯一值计数
  + GEO：地理信息定位
+ 支持多种编程语言的客户端
+ 功能丰富
  + 发布订阅
  + Lua脚本
  + 事务
  + pipeline
+ 简单
+ 主从复制
+ 高可用、分布式