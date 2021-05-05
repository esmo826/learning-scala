trait BinaryTree[A] {
    def insert[A](data: A)(implicit ord: Ordering[A]): BinaryTree[A]
    def left: BinaryTree[A]
    def right: BinaryTree[A]
    def data: A
}

object BinaryTree {

    private final case class EmptyTree[A]() extends BinaryTree[A] {
        def insert[A](data: A)(implicit ord: Ordering[A]): BinaryTree[A] = BTree(data, EmptyTree[A], EmptyTree[A])
        def left = EmptyTree[A]
        def right = EmptyTree[A]
        def data = ???
    }
    private final case class BTree[A](data: A, left: BinaryTree[A], right: BinaryTree[A]) extends BinaryTree[A] {
        def insert[A](data: A)(implicit ord: Ordering[A]): BinaryTree[A] = {
            import ord.mkOrderingOps
            this match {
                case t: BTree[A] if (data < t.data) => BTree(t.data, left = left.insert(data), t.right)
                case t: BTree[A] if (data >= t.data) => BTree(t.data, left = t.left, right = right.insert(data))
            }
        }
    }

    def empty[A]: BinaryTree[A] = EmptyTree[A]()

    def traverse[A](tree: BinaryTree[A], f: A => Unit): Unit = {
        tree match {
            case EmptyTree() => ()
            case t => {
                traverse(t.left, f)
                f(t.data)
                traverse(t.right, f)
            }
        }
    }

    def main(args: Array[String]): Unit = {
        println("BinaryTree Example")

        val tree : BinaryTree[Int] = Vector(15, 50, 20, 12, 9, 7, 66, 79).foldLeft(empty[Int]) {
            (tree, data) => {
                tree.insert[Int](data)
            }
        }

        traverse[Int](tree, println)

    }
}