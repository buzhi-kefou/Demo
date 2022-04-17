# 走近Java
## 走近Java
- 概述
- 技术体系
- 发展历史
- 虚拟机家族
> Classic Exact
> HotSpot
> Connected (Limited) Device Configuration
> JRokit J9
> Liquid Azul
> Harmony Dalvik
- 展望未来
- 编译JDK
> 编译参数
> - --with-debug-level=\<level\>
> - --enable-debug
> - --with-native-debug-symbols=\<method\>
> 
> 
# 自动内存管理
## 内存区域与内存溢出异常
### 概述
### 运行时数据区域
- 程序计数器
- 本地方法栈
- 虚拟机栈
- 堆
- 方法区
- 常量池
- 直接内存
### 虚拟机对象探秘
1. 对象创建
- 类加载
- 内存分配
- 初始化
- 构造函数
2. 内存布局
- 对象头
- 实例数据
- 对齐填充
3. 访问定位
- 句柄访问
- 直接指针
### OutOfMemoryError异常

## 垃圾收集器与内存分配策略
### 概述

