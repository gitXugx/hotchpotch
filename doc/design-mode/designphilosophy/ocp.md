# 开闭原则 

> 开闭原则(Open-Closed Principle, OCP)：一个软件实体应当对扩展开放，对修改关闭。即软件实体应尽量在不修改原有代码的情况下进行扩展。

- [x] 定义
- [x] 使用目的
- [ ] 使用场景

## 定义
遵循开闭原则设计出的模块具有两个主要特征：
- 对于扩展是开放的（Open for extension）。这意味着模块的行为是可以扩展的。当应用的需求改变时，我们可以对模块进行扩展，使其具有满足那些改变的新行为。也就是说，我们可以改变模块的功能。
- 对于修改是关闭的（Closed for modification）。对模块行为进行扩展时，不必改动模块的源代码或者二进制代码。

**主要目的**
+ 对修改关闭，只要是不影响之前的功能，提高稳定性和可维护性
+ 对扩展开放，主要是让系统具有可扩展性和灵活性，对测试比较友好，只需要测试扩展的代码就行了


## 使用开闭原则的目的

开闭原则是面向对象程序设计的终极目标，它使软件实体拥有一定的适应性和灵活性的同时具备稳定性和延续性。具体来说，其作用如下。
1. 对软件测试的影响
软件遵守开闭原则的话，软件测试时只需要对扩展的代码进行测试就可以了，因为原有的测试代码仍然能够正常运行。
2. 可以提高代码的可复用性
粒度越小，被复用的可能性就越大；在面向对象的程序设计中，根据原子和抽象编程可以提高代码的可复用性。
3. 可以提高软件的可维护性
遵守开闭原则的软件，其稳定性高和延续性强，从而易于扩展和维护。




