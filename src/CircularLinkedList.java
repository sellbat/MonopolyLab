public class CircularLinkedList<T> {


    private Link<T> first;
    private Link<T> last;

    public Link<T> getFirst() {
        return first;
    }

    public void setFirst(Link<T> first) {
        this.first = first;
    }

    public Link<T> getLast() {
        return last;
    }

    public void setLast(Link<T> last) {
        this.last = last;
    }
    public CircularLinkedList(){
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return first==null&&last==null;
    }
    public void insertFirst(T newData){
        Link<T> newLink = new Link<T>(newData);
        if (isEmpty()){
            first=newLink;
            last=newLink;
        }
        last.nextLink=newLink;
        newLink.nextLink=first;
        first=newLink;
    }
    public Link<T> find(Link<T> key){
        Link<T> current = first;
        if (current.data.equals(key.data)){
            return key;
        }
        current = current.nextLink;
        while (!current.data.equals(first.data)){
            if (current.data.equals(key.data)){
                return current;
            }
            current=current.nextLink;
        }
        return key;
    }
}
