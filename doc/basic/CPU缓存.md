# cup的缓存

## CPU多级缓存

1. cup缓存 速度从上到下依次递减 ，大小依次递增，因为离CPU越近的数据，CPU获取的速度越快

<div align="center"> <img src="https://github.com/gitXugx/hotchpotch/blob/master/doc/images/basic/cpuCache.png"  /> </div><br>

2. 一级缓存速度接近与cup工作速度，但是缓存是需要同步的，每当同步下一层数据时都会
锁住当前缓存块，占时无法访问。
3. 每级缓存命中率都在80%以上。缓存的带宽只由cpu使用
4. cup访问内存数据时，首先内存需要查看带宽是否有空闲(因为内存还跟其他硬件通信)，如果没有需要等待(或者说处理数据速度，比拿数据速度快)，所以才有了cpu缓存
## CPU缓存的设计
1. cache在所难免会有miss 但是大部分还是会命中
2. 时间局部性:如果该数据被访问过，在不久的将来可能还会被访问呢
3. 空间局部性:如果改数据被访问，那么他相邻的数据很有可能也会被访问
4. 多核情况下第三级缓存一下都是是共享的

## 缓存一致性

1. 缓存数据的4种状态

   - **修改 :** 数据在该缓存中修改，其他cache中的数据改为无效，以当前cache中的数据为准
   - **独享:** 数据只在该缓存中，其他中没有，与主存中数据一致
   - **共享:** 数据在所有缓存中都有，与主存中数据一致
   - **失效:** 数据失效，改数据应该从主存中去取

2. cache操作

   - **local read:** 读取本地缓存中的数据
   - **local write:** 写入本地缓存数据
   - **remote read:** 读取主存中的数据
   - **remote write:** 写入主存中数据

16种转换情况讨论

**修改:**

LR 当前数据是最新的直接从cache中读取 状态不变

LW 直接修改local cache的数据 拥有最新数据，状态不变

RR local cache 拥有最新数据所以不需要RR，一定是其他cache在进行

RR，需要把数据更新到主存修改状态为S(共享)

RW 当cache控制监听到RW，会把数据回写到主存，因为随后会被其他
cache RW，所以local cache line 修改为I(无效)

**独享:**

LR 该数据独享 所以状态不变

LW local cache 写入 拥有最新数据， 状态变为M

RR 其他cache 远程读 ，该数据与内存中一致 ，改变状态为S(共享)

RW other cache 进行RR ，local cache line 改为I(失效)

**共享:**

LR 数据共享，与主存一致 ，状态不变

LW local cache 写 数据发生变化，回写主存数据，修改状态为M

RR other cache RR 数据与主存中一致 ，状态不变

RW local cache line 修改状态为I(失效)

**失效:**

LR local cache line 无效 所以会发生RR 操作

A: 如果只有该local cache 拥有 该为E

B: 如果other cache 有数据状态为E和S 修改状态为s

C: 如果other cache 数据为M ，则需要先RW 然后修改为S ，所以该状态修改为S

LW local cache 无效 所以写的是本地cache

A: 如果other cache 中没有该数据 ，则修改状态为M

B: 如果other cache 数据状态为S和E ，通知other cache 为I ，该状态修改为M

C: 如果other cache 数据为M ，数据先写会主存然后修改为I ，该数据状态修改为M