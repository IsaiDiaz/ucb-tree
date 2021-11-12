package bo.edu.ucb.est;

public class Tree<D extends Comparable<D>> {
    private Node root;

    public Tree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void add(D data) {
        Node<D> newNode = new Node<>(data);
        if ( root == null) { // arbol vacio
            root = newNode;
        } else {
            Node<D> current = root;
            while(current != null ) {
                if (current.getData().compareTo(data) > 0) {
                    if (current.getLeft() == null) { // Tengo el espacio vacio para agregar el nodo
                        current.setLeft(newNode);
                        break; // Terminamos el recorrido
                    } else { // continuo bajando por la rama izquierda
                        current = current.getLeft();
                    }
                } else if (current.getData().compareTo(data) < 0) {
                    if (current.getRight() == null) { // Tengo el espacio vacio para agregar el nodo
                        current.setRight(newNode);
                        break; // Terminamos el recorrido
                    } else { // continuo bajando por la rama derecha
                        current = current.getRight();
                    }
                } else { // igual a cero
                    // Entonces el elemento ya existe.
                    throw new RuntimeException("No se puede agregar elementos duplicados a un ABB");
                }

            }
        }
    }

    public void addRecursive(D data) {
        Node<D> newNode = new Node<>(data);
        if ( root == null) { // arbol vacio
            root = newNode;
        } else {
            addDataToNode(root, newNode);
        }
    }

    private void addDataToNode(Node<D> current, Node<D> newNode) {
        // Comparo si newNode debe ir izquierda o derecha
        // Si debe ir izquierda o derecha verifico si la rama esta libre
        //     - Si esta libre lo agrego a la rama correspondiente.
        //     - Si no esta libre recorro la rama recursivamente.
        if (current != null) {
            if (current.getData().compareTo(newNode.getData()) > 0) {
                if ( current.getLeft() == null) { //vacio
                    current.setLeft(newNode);
                } else {
                    addDataToNode(current.getLeft(), newNode);
                }
            } else if (current.getData().compareTo(newNode.getData()) < 0) {
                if ( current.getRight() == null) { //vacio
                    current.setRight(newNode);
                } else {
                    addDataToNode(current.getRight(), newNode);
                }
            } else {
                throw new RuntimeException("No se puede agregar elementos duplicados a un ABB");
            }

        }
    }


    /**
     *      Hasta que todos los nodos hayan sido atravesados −
     *      Paso 1 − Recorre recursivamente el subarbol izquierdo.
     *      Paso 2 − Visitamos el nodo raíz.
     *      Paso 3 − Recorre recursivamente el subarbol derecho.
     */

    public static void printInOrder(Node<?> root) {
        if (root != null) {
            printInOrder(root.getLeft());
            System.out.println(root.getData());
            printInOrder(root.getRight());
        }
    }


    /**
     * 1. Crear un Stack vacio S.
     * 2. Inicializar el nodo current como root.
     * 3. Insertar el nodo current a S y asignar current = current.getLeft(), hasta que el actual sea NULL.
     * 4. Si current es NULL y S no esta vacio, entonces:
     *      4.1) Hacemos pop de S.
     *      4.2) Imprimos el elemento que hicimos pop y asignamos current = pop_node.getRight();
     *      4.3) Ir al paso 3.
     * 5. Si current es NULL, y S esta vacio entocnes hemos terminado.
     * @param root nodo a partir del cual ser comienza a realizar in order recursiva
     */
    public static void printInOrderNonRecursive(Node<?> root) {

    }

    /**
     * Until all nodes are traversed −
     * Step 1 − Visit root node.
     * Step 2 − Recursively traverse left subtree.
     * Step 3 − Recursively traverse right subtree.
     * @param root
     */
    public static void printPreOrder(Node<?> root) {
        if (root != null) {
            System.out.println(root.getData());
            printPreOrder(root.getLeft());
            printPreOrder(root.getRight());
        }
    }

    /**
     * Until all nodes are traversed −
     * Step 1 − Recursively traverse left subtree.
     * Step 2 − Recursively traverse right subtree.
     * Step 3 − Visit root node.
     * @param root
     */
    public static void printPostOrder(Node<?> root) {
        if (root != null) {
            printPostOrder(root.getLeft());
            printPostOrder(root.getRight());
            System.out.println(root.getData());
        }
    }
}
