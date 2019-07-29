# 第2章 API的理解和使用

## 通用命令

+ keys:遍历所有的Key,还可以正则遍历。keys一般不在生产环境中使用
+ dbsize：计算key的总数
+ exists key：检查key是否存在，存在返回1，不存在返回0
+ del key [key ...]：删除指定的key-value
+ expire key seconds:设置key在seconds后过期
+ type key：key的类型(string、hash、list、set、zset、none)

