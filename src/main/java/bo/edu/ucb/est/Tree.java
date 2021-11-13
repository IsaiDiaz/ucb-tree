package bo.edu.ucb.est;

import java.util.Stack;

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

    public static void printInOrderMio(Node<?> root) {
//        Hasta que todos los nodos hayan sido atravesados −
//        Paso 1 − Recorre recursivamente el subarbol izquierdo.
//                Paso 2 − Visitamos el nodo raíz.
//        Paso 3 − Recorre recursivamente el subarbol derecho.
        if (root.getLeft() == null) {
            //Verificamos si el nodo tiene un hijo a la izquierda
            //Si no es asi, el nodo es un valor minimo y se imprime
            System.out.print(root.getData() + ",");
            if (root.getRight() != null) {
                //verificamos si ese nodo tiene un hijo a la derecha
                //si es así recorremos el subarbol derecho recursivamente
                printInOrderMio(root.getRight());
            }
        } else {
            //Si tiene un hijo a la izquierda  se recorre el subarbol izquierdo recursivamente
            printInOrderMio(root.getLeft());
            //Al terminar de recorrer el subarbol derecho se imprime el nodo root
            System.out.print(root.getData() + ",");
            if (root.getRight() != null) {
                //verificamos si existe un subarbol a la derecha
                //si es así, recorremos el subarbol derecho recursivamente
                printInOrderMio(root.getRight());
            }
            if (root != null) {
                printInOrderMio(root.getLeft());
                System.out.println(root.getData());
                printInOrderMio(root.getRight());
            }
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
    public static void printInOrderNonRecursive(Node <?> root) {
        Stack<Node<?>> pila=new Stack<>();
        Node <?> current=root;
        while(current!=null){
            pila.push(current);
            current=current.getLeft();
        }
        while(current==null & pila.empty()!=true){
            System.out.print(pila.peek().getData()+",");
            current=pila.pop().getRight();
            while(current!=null){
                pila.push(current);
                current=current.getLeft();
            }
        }

    }

    public Node searchPrevNode(D data) {
        Stack<Node<D>> pila=new Stack<>();
        Node <D> current=root;
        while(current!=null){
            if(current.getRight()!=null) {
                if (current.getRight().getData().compareTo(data) == 0) {
                    return current;
                }
            }
            if(current.getLeft()!=null){
                if(current.getLeft().getData().compareTo(data)==0){
                    return current;
                }
            }
            pila.push(current);
            current=current.getLeft();
        }
        while(current==null & pila.empty()!=true){
            current=pila.pop().getRight();
            while(current!=null){
                if(current.getRight()!=null){
                    if(current.getRight().getData().compareTo(data)==0){
                        return current;
                    }
                }
                if(current.getLeft()!=null){
                    if(current.getLeft().getData().compareTo(data)==0){
                        return current;
                    }
                }
                pila.push(current);
                current=current.getLeft();
            }
        }
        current=null;
        return current;
    }

    /**
     * 1. El nodo que se va a eliminar no tiene hijo, es una hoja.
     * Este es el caso más simple; dado que un nodo hoja no tiene hijos, no necesitamos preocuparnos por nada. Podemos reemplazar el nodo hoja con NULL y liberar el espacio asignado a este nodo.
     *
     * 2. El nodo que se va a eliminar tiene un solo hijo (hijo izquierdo o derecho).
     * En este caso, almacenamos el hijo del nodo y eliminamos el nodo de su posición original. Luego, el nodo hijo se inserta en la posición original del nodo eliminado.
     *
     * 3. El nodo que se va a eliminar tiene hijos, hijo izquierdo y derecho.
     * Este es el caso más complicado porque aquí, no podemos simplemente eliminar o reemplazar el nodo con su hijo. En este caso, encontramos el nodo más pequeño en el subárbol derecho del nodo minnode. Reemplace el valor del nodo que se eliminará con el valor de minnode y llame de forma recursiva a delete en este nodo.
     * @param data
     */
    public void remove(D data) {
        Node<D> prev=searchPrevNode(data);
        Node<D> current=null;
        boolean left= false;
        boolean right= false;
        if(prev!=null){
            if(prev.getRight()!=null){
                if(prev.getRight().getData().compareTo(data)==0){
                    current=prev.getRight();
                    right=true;
                }
            }
            if(prev.getLeft()!=null){
                if(prev.getLeft().getData().compareTo(data)==0){
                    current=prev.getLeft();
                    left=true;
                }
            }
            if(current.getRight()==null & current.getLeft()==null){
                if(right){
                    prev.setRight(null);
                }else if(left){
                    prev.setLeft(null);
                }
            }else if(current.getLeft()!=null ^ current.getRight()!=null){
                Node<D> son = null;
                if(current.getRight()!=null){
                    son=current.getRight();
                }else if(current.getLeft()!=null){
                    son=current.getLeft();
                }
                if (right){
                    prev.setRight(son);
                }else if(left){
                    prev.setLeft(son);
                }
            }else if(current.getLeft()!=null && current.getRight()!=null){
                Node<D>minnode=current.getRight();
                Node<D>prevMinNode=current;
                while (minnode.getLeft()!=null){
                    prevMinNode=minnode;
                    minnode=minnode.getLeft();
                }
                if(current.getRight().getData().compareTo(minnode.getData())!=0){
                    minnode.setRight(current.getRight());
                }
                if(current.getLeft().getData().compareTo(minnode.getData())!=0){
                    minnode.setLeft(current.getLeft());
                }
                prevMinNode.setLeft(null);
                if(right){
                    prev.setRight(minnode);
                }else if(left){
                    prev.setLeft(minnode);
                }
            }
        }else if(root!=null){
            if(root.getData().compareTo(data)==0) {
                if(root.getRight()==null & root.getLeft()==null){
                   root=null;
                }else if(root.getLeft()!=null ^ root.getRight()!=null){
                    if(root.getRight()!=null){
                        root=root.getRight();
                    }else if(root.getLeft()!=null){
                        root=root.getLeft();
                    }
                }else if(root.getLeft()!=null && root.getRight()!=null){
                    Node<D>minnode=root.getRight();
                    Node<D>prevMinNode=root;
                    while (minnode.getLeft()!=null){
                        prevMinNode=minnode;
                        minnode=minnode.getLeft();
                    }
                    if(root.getRight().getData().compareTo(minnode.getData())!=0){
                        minnode.setRight(root.getLeft());
                    }
                    if(root.getLeft().getData().compareTo(minnode.getData())!=0){
                            minnode.setLeft(root.getLeft());
                    }
                    prevMinNode.setLeft(null);
                    root=minnode;
                }
            }
        }
    }

    /**
     * Until all nodes are traversed −
     * Step 1 − Visit root node.
     * Step 2 − Recursively traverse left subtree.
     * Step 3 − Recursively traverse right subtree.
     * @param root
     */
    public static void printInOrder(Node<?> root){
        if(root!=null){
            printInOrder(root.getLeft());
            System.out.println(root.getData());
            printInOrder(root.getRight());
        }
    }
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
