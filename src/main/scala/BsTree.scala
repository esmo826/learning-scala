

final case class BsTree(data: Int, left: Option[BsTree], right: Option[BsTree]) {

}

object BsTree {

    def traverse(tree: Option[BsTree], f: Int => Unit): Unit = {
        if (tree.isEmpty) ()
        else {
            val t = tree.get
            traverse(t.left, f)
            f(t.data)
            traverse(t.right, f)
        }
    }

    def insert(data: Int, tree: Option[BsTree]): BsTree =
    {
        //import ord.mkOrderingOps
        if (tree.isEmpty) BsTree(data, None, None)
        else {
            val t = tree.get
            if (data < t.data) t.copy(left = Some(insert(data, t.left)))
            else t.copy(right = Some(insert(data, t.right)))
        }

    }

    def main(args: Array[String]): Unit = {
        println("BsTree Example")

        val tree: Option[BsTree] = Vector(15, 10, 20, 8, 12, 16, 25).foldLeft[Option[BsTree]](None) {
            (tree, data) => {
                //println(s"Inserting $data")
                Some(insert(data, tree))
            }

        }

        traverse(tree, println)

    }
}