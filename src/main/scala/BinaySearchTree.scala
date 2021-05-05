

case class Node(data: Int, left: Option[Node] = None, right: Option[Node] = None)

object BinaryTree {

    def inorder(root: Option[Node]): Unit = {
        root.fold(()){ n =>
            inorder(n.left)
            println(n.data)
            inorder(n.right)
        }
        // root match {
        //     case None => println("empty")
        //     case Some(n) => {
        //         if (!n.left.isEmpty) {
        //             //println("left isn't empty")
        //             inorder(n.left)
        //         }
        //         println(s"${n.data}")
        //         if (!n.right.isEmpty) {
        //             //println("right isn't empty")
        //             inorder(n.right)
        //         }
        //     }
        // }
    }

    def insert(data: Int, node: Option[Node]) : Node = {

        node match {
            case None => {
                //println("empty, not anymore")
                Node(data, None, None)
            }
            case Some(n) => {
                if (data < n.data) {
                    n.copy(n.data, Some(insert(data, n.left)), n.right)
                } else {
                    n.copy(n.data, n.left, Some(insert(data, n.right)))
                }
            }
        }

        // node.fold(Node(data, None, None)) { n =>
        //     if (data < n.data) {
        //         //println("insert left")
        //         //insert(data, n.left)
        //         n.copy(n.data, Some(insert(data, n.left)), n.right)
        //     } else {
        //         //println("insert right")
        //         //insert(data, n.right)
        //         n.copy(n.data, n.left, Some(insert(data, n.right)))
        //     }
        // }
    }

    def main(args: Array[String]): Unit = {
        println("Binary Tree Example")

        val tree = Vector(15, 10, 20, 8, 12, 16, 25).foldLeft[Option[Node]](None) {
            (node, data) => {
                //println(s"Inserting $data")
                Some(insert(data, node))
            }
        }

        // val tree = Vector(15, 10, 20, 8, 12, 16, 25).foldLeft[Option[Node]](None) {
        //     (node, data) => {
        //         println(s"Inserting $data")
        //         val f = insert(data, node)
        //         Some(f)
        //         //println(s"$foo")
        //         //Some(tree)
        //     }
        // }

        inorder(tree)

    }
}
