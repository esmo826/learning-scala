
final case class BsTree[A](data: A, left: Option[BsTree[A]], right: Option[BsTree[A]]) {

}

object BsTree {

    def traverse[A](tree: Option[BsTree[A]], f: A => Unit): Unit = {
        if (tree.isEmpty) ()
        else {
            val t = tree.get
            traverse(t.left, f)
            f(t.data)
            traverse(t.right, f)
        }
    }

    def insert[A](data: A, tree: Option[BsTree[A]])(implicit ord: Ordering[A]): BsTree[A] =
    {
        import ord.mkOrderingOps
        if (tree.isEmpty) BsTree[A](data, None, None)
        else {
            val t = tree.get
            if (data < t.data) t.copy(left = Some(insert(data, t.left)))
            else t.copy(right = Some(insert(data, t.right)))
        }

    }

    def main(args: Array[String]): Unit = {
        println("BsTree Example")

        val tree: Option[BsTree[String]] = Vector("a", "g", "b", "z", "f", "o", "p").foldLeft[Option[BsTree[String]]](None) {
            (tree, data) => {
                //println(s"Inserting $data")
                Some(insert[String](data, tree))
            }

        }

        traverse[String](tree, println)

    }
}