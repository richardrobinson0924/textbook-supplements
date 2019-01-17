public class LinkedPositionalList<E> implements PositionalList<E> {

    /**
     * A static inner class to implement a position based on a doubly linked list node. Each node has a data element, and a reference to its previous and next nodes.
     */
    private static class Node<E> implements Position<E> {
        /**
         * The main data element of this node
         */
        private E element;

        /**
         * The previous and next nodes of this node
         */
        private Node<E> prev, next;

        /**
         * The sole constructor. Assigns a data element, as well as the previous and next nodes to the node being created
         * @param e the data element of this node
         * @param p the node that is before this node
         * @param n the node that is after this node
         */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e; prev = p; next = n;
        }

        /**
         * Retrieves the data element from this node, and throws an exception if the next node is null
         * @return the data element of this node
         * @throws Exception if the next node is null
         */
        public E getElement() throws Exception {
            if (next == null) throw new Exception();
            return element;
        }

        /**
         * Returns the previous node
         * @return the previous node
         */
        public Node<E> getPrev() { return prev; }

        /**
         * Returns the next node
         * @return the next node
         */
        public Node<E> getNext() { return next; }

        /**
         * Sets the data element of this node to e
         * @param e the new data element of the node
         */
        public void setElement(E e) { element = e; }

        /**
         * Sets the previous node to p
         * @param p the previous node to set to
         */
        public void setPrev(Node<E> p) { prev = p; }

        /**
         * Sets the next node to n
         * @param n the next node to set to
         */
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> header, trailer;
    private int size = 0;

    /**
     * The sole constructor, which constructs an empty linked positional list. Creates a fully null header, and a trailer whose previous node is `header`. As well, sets the next node of `header` to `trailer`
     */
    public LinkedPositionalList( ) {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * Validates a position p as an instance of `Node`, and casts & converts it to a node
     * @param  p         the position to which to validate
     * @return           the position p as a node
     * @throws Exception if p is not a Node or if its next node is null
     */
    private Node<E> validate(Position<E> p) throws Exception {
        if (!(p instanceof Node)) throw new Exception();
        Node<E> node = (Node<E>) p;

        if (node.getNext() == null) throw new Exception();
        return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) return null;
        return node;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    public Position<E> first() { return position(header.getNext()); }
    public Position<E> last() { return position(trailer.getPrev()); }

    public Position<E> before(Position<E> p) throws Exception {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) throws Exception {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ);
        pred.setNext(newest);
        succ.setPrev(newest);

        size++;
        return newest;
    }

    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) throws Exception {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) throws Exception {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    public E set(Position<E> p, E e) throws Exception {
        Node<E> node = validate(p);
        E elem = node.getElement();
        node.setElement(e);
        return elem;
    }

    public E remove(Position<E> p) throws Exception {
        Node<E> node = validate(p);
        Node<E> p = node.getPrev(), n = node.getNext();

        p.setNext(n);
        n.setPrev(p);
        size--;

        E elem = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);

        return elem;
    }

}
