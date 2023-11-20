### 项目逻辑

该项目实现了与标准库中 `Queue/Deque` 类似的功能，并且使用了 Java 泛型提供对不同类型数据的存储。其中：

- `LinkedList` 实现了与标准库中 `LinkedList` 相似的功能，用来为 `Queue/Deque` 存储数据。
- `Queue/Deque` 提供了与 `java.util.Queue/java.util.Deque` 类似的接口。
- `ListQueue/ListDeque` 使用 `LinkedList` 作为底层数据结构实现了 `Queue/Deque` 接口中规定的方法。

### 工作原理

`Queue/Deque` 在标准库中有借助 `Array/LinkedList` 的两种实现，我在这里选择实现 `LinkedList` 作为其底层数据结构。

类结构图如下：

![uml](https://github.com/guiqiqi/java-lab2-queue/raw/main/uml.png?raw=true)

`LinkedList` 维护了一个双链表，链表的节点定义如下：

```java
private static class Node<E> {
    public Node<E> prev;
    public Node<E> next;
    public E data;
}
```

在双链表内维护了两个指针（`head/last`）以及一个 `length` 变量，用来存储当前链表的长度：

```java
public class LinkedList<E> implements Iterable<E> {
    private Node<E> _head;
    private Node<E> _last;
    private int _length;
}
```

两个指针的可能状态如下：

- 当链表为空（没有已经存储的数据时）：两个指针均为 `null`。
- 当链表仅有一个元素时：两个指针指向相同的节点 `Node` 对象。
- 当链表拥有超过一个元素时：分别指向双链表的头部/尾部 `Node` 对象。

当需要对链表进行操作时（例如插入、删除、查询），首先找到需要访问的节点，通过 `indexNode` 方法实现：

```java
private Node<E> indexNode(int target) {
    Node<E> current;
    if (target < this._length / 2) {
        int index = 0;
        current = this._head;
        while (index++ < target)
            current = current.next;
    } else {
        int index = this._length - 1;
        current = this._last;
        while (index-- > target)
            current = current.prev;
    }
    return current;
}
```

该方法提供 $O(N)$ 的时间复杂度，保证在 $\frac{N}{2}$ 次查找内返回需要操作的节点对象：

- 当需要访问的节点距离头部节点更近时，会从头部节点向后查找
- 当需要访问的节点距离尾部节点更近时，会从尾部节点向前查找

使用 `LinkedList`，可以对 `Queue/Deque` 接口进行实现：

```java
public interface Queue<E> extends Iterable<E> {
    E head();
    E tail();
    E get();
    boolean put(E data);
    int size();
    boolean contains(E data);
    int maxsize();
}

public interface Deque<E> extends Queue<E> {
    E getTail();
    boolean putHead(E data);
}
```

具体的实现可以参考 `ListQueue/ListDeque` 的源代码。

同时，`Queue/Deque` 使用 `LinkedList` 实现的 `iterator` 方法，该方法将返回一个 `NodeIterator` 对象实例，用于实现对 `forEach` 遍历的支持。

### 测试

我为该项目编写了一些测试，它们测试了程序应该实现的正常功能：

- `LinkedListTest` 类包含对 `LinkedList` 的测试用例。它包括了对链表的增加、删除、重置、迭代器、包含等功能的测试。
- `ListQueueTest` 类包含对 `ListQueue` 的测试用例。它包括了在各种情况下对队列的增加、删除、访问等功能的测试。其中 `ListQueueTest.testBehaviourWithStandardQueue` 使用了 `java.util.Queue` 用来检测队列是否有与标准库队列相同的行为。
- `ListDequeTest` 类包含对 `ListDeque` 的测试用例。它包括了在各种情况下对双向队列的增加、删除、访问等功能的测试。其中 `ListDequeTest.testBehaviourWithStandardDeque` 使用了 `java.util.Deque` 用来检测队列是否有与标准库双向队列相同的行为。

通过使用下列的命令对项目进行清理、编译、测试：

```bash
mvn clean
mvn compile
mvn test
```

测试结果为全部通过：

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ru.spbstu.telematics.java.ListDequeTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.018 s -- in ru.spbstu.telematics.java.ListDequeTest
[INFO] Running ru.spbstu.telematics.java.ListQueueTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s -- in ru.spbstu.telematics.java.ListQueueTest
[INFO] Running ru.spbstu.telematics.java.LinkedListTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in ru.spbstu.telematics.java.LinkedListTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.586 s
[INFO] Finished at: 2023-11-20T07:33:05+03:00
[INFO] ------------------------------------------------------------------------
```

