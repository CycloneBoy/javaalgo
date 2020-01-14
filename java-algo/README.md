# javaalgo

《Java语言程序设计(进阶篇)》 或《(Introduction to Java Programming (Comprehensive Version Tenth Edition）》

书中的例子和我编写的例子
说明：模块名：java-algo
第23章 排序
---
研究和分析各种排序算法的时间效率 
插入排序、冒泡排序、归并排序、快速排序、堆排序、桶排序、基数排序、外部排序

设计和实现一个二叉堆，并完成堆排序 "1.  "

1. 选择排序、插入排序、冒泡排序和快速排序的最差时间复杂程度为O(n^2)
2. 归并排序的平均情况和最差情况的复杂程度为O（nlogn).快速排序的平均时间也是O(nlogn)
3. 对于设计排序这样的高效算法，堆是一个很有用的数据结构。本章介绍了如何定义和实现一个堆类。以及如何向/从堆中插入和删除元素
4. 堆排序的时间复杂程度为O(nlogn)
5. 桶排序和基数排序都是针对整数键值得特定排序算法。这些算法不是通过比较键值而是使用桶来对键值进行排序，它们会比一般的排序算法效率要高
6. 可以使用归并排序的一种变体 -- 称为外部排序 -- 对外部文件中的大型数据进行排序。

第24章 实现线性表、栈、队列、优先队列 
---

1. 本章学习了如何实现线性表、栈、队列和优先队列
2. 定义一个数据结构本质上是定义一个类，为数据结构定义的类应该使用数据域来进行存储，并提供方法来支持诸如插入和删除等操作
3. 创建一个数据结构是从该类创建一个实例，这样就可以将方法应用在实例上来进行处理数据结构，比如插入一个元素到数据结构中，或者从数据结构中删除一个元素
4. 本章学习了如何采用堆来事先一个优先队列

第25章 二叉查找树 
---
1. 二叉查找树(BST) 是一种分层的数据结构。学习了如何定义和实现BST类，学习了如何从/向BST插入和删除元素。学习了如何使用中序、后序、深度优先搜素以及广度优先搜索来遍历BST.
2. 迭代器是一个提供了遍历像集合、线性表或二叉树这样的容器中的元素的统一方法的对象。学习了如何定义和实现二叉树中元素的迭代器类。
3. 霍夫曼编码是一种压缩数据的方案，它使用较少的比特来编码经常出现的字符。字符的编码是使用二叉树基于它在文本中出现的次数来构建的，该二叉树成为霍夫曼编码树。

第26章 AVL树
---
1. AVL树是高度平衡二叉树。在一棵AVL树中，每个结点的两个子树的高度差为0或者1
2. 在一棵AVL树中插入或者删除元素的过程和在常规的二叉查找树树中是一样的。不同之处在于可能需要在插入或者删除后重新平衡该树。
3. 插入和删除引起的树的不平衡，通过不平衡节点处的子树的旋转重新获得平衡。
4. 一个结点的重新平衡的过程称为旋转。有四种可能的旋转：LL旋转、LR旋转、RR旋转、RL旋转。
5. AVL树的高度为O(logn).因此，search、Insert 以及delete 方法的时间复杂程度为O(logn).

第27章 散列
---
1. 映射表是存储条目的一种数据结构。每个条目包含两个部分：键和值。键也称为搜素键，用于查找相应的值。可以使用散列技术来实现映射表，实现使用O(1)的时间复杂程度来实现查找、获取、插入已经删除。
2. 集合是一种存储元素的数据结构。可以使用散列技术来实现集合，实现使用O(1)的时间复杂程度来实现查找、获取、插入以及删除。
3. 散列是一种无须执行搜索，即可从一个键得到索引获取值得技术。典型的散列函数首先将搜索键转化为一个称之为散列码的整数值，然后将散列码压缩为散列表中的一个索引。
4. 当两个键映射到散列表中的同样的索引上时，冲突发生。通常有两种方式来处理冲突：开放地址法和链地址法。
5. 开放地址法是在发生冲突时，在散列表中找到一个开放位置的过程。开放地址法中有几种变体:线性探测、二次探测以及再哈希。
6. 链地址法将具有同样散列索引的条目放到同一个位置中，而不是寻找新的位置。链地址法中每个位置称为一个桶。桶是容纳多个条目的容器。

