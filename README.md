### Логика программы

Этот проект реализует функции, аналогичные Queue/Deque в стандартной библиотеке, и использует дженерики Java для хранения различных типов данных:

- `LinkedList` реализует функции, аналогичные `LinkedList` в стандартной библиотеке, и используется для хранения данных для `Queue/Deque`.
- `Queue/Deque` предоставляет интерфейс, аналогичный `java.util.Queue/java.util.Deque`.
- `ListQueue/ListDeque` использует `LinkedList` в качестве базовой структуры данных для реализации методов, указанных в интерфейсе `Queue/Deque`.

### Код для реализации

У `Queue/Deque` есть две реализации с `Array/LinkedList` в стандартной библиотеке. Здесь я решил реализовать `LinkedList` в качестве базовой структуры данных.

Схема структуры классов выглядит следующим образом:

![uml](https://github.com/guiqiqi/java-lab2-queue/raw/main/uml.png?raw=true)

`LinkedList` поддерживает двусвязный список. Узлы связанного списка определяются следующим образом:

```java
private static class Node<E> {
    public Node<E> prev;
    public Node<E> next;
    public E data;
}
```

В двусвязном списке сохраняются два указателя (`head/last`) и переменная `leength` для хранения длины текущего связанного списка:

```java
public class LinkedList<E> implements Iterable<E> {
    private Node<E> _head;
    private Node<E> _last;
    private int _length;
}
```

Возможные состояния двух указателей следующие:

- Когда связанный список пуст (данные не сохранены): оба указателя равны `null`.
- Когда связанный список имеет только один элемент: два указателя указывают на один и тот же объект узла `Node`.
- Если связанный список содержит более одного элемента: укажит соответственно на головной и хвостовой объекты `Node` двусвязного списка.

Когда вам нужно работать со связанным списком (например, вставлять, удалять, запрашивать), сначала найдите узел, к которому необходимо получить доступ, и реализует его с помощью метода `indexNode`:

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

Этот метод обеспечивает временную сложность $O(N)$ и гарантированно возвращает объект узла, с которым необходимо работать в рамках поиска $\frac{N}{2}$:

- Когда узел, к которому необходимо получить доступ, находится ближе к головному узлу, он будет искать назад от головного узла.
- Когда узел, к которому необходимо получить доступ, находится ближе к хвостовому узлу, он будет искать вперед от хвостового узла.

Используя `LinkedList`, реализованы интерфейса `Queue/Deque`:

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

Для конкретной реализации обратитесь к исходному коду `ListQueue/ListDeque`, который при необходимости вставляет/запрашивает/удаляет данные в начале/конце очереди.

В то же время `Queue/Deque` использует метод `iterator`, реализованный `LinkedList`, который возвращает экземпляр объекта NodeIterator для реализации поддержки обхода `forEach`.

### Тест

Я написал несколько тестов для этого проекта, которые проверяют нормальную функциональность, которую должна реализовать программа:

- Класс `LinkedListTest` содержит тестовые примеры для `LinkedList`. Он включает в себя функции тестирования, такие как добавление, удаление, сброс, итераторы и включение в связанные списки.
- Класс `ListQueueTest` содержит тестовые примеры для `ListQueue`. Он включает в себя тестирование добавления, удаления, доступа и других функций очередей при различных обстоятельствах. Среди них `ListQueueTest.testBehaviourWithStandardQueue` использует `java.util.Queue` для определения того, ведет ли очередь такое же поведение, как очередь стандартной библиотеки.
- Класс `ListDequeTest` содержит тестовые примеры для `ListDeque`. Он включает в себя тестирование таких функций, как добавление, удаление и доступ к двунаправленным очередям при различных обстоятельствах. Среди них `ListDequeTest.testBehaviourWithStandardDeque` использует `java.util.Deque`, чтобы определить, ведет ли очередь такое же поведение, как двунаправленная очередь стандартной библиотеки.

Я очищаю, компилирую и тестирую проект, используя следующие команды:

```bash
mvn clean
mvn compile
mvn test
```

Пройдены все тесты:

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