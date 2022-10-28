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
        else{
            newLink.nextLink = first;
            first = newLink;
            last.nextLink = first;
        }
    }
    public Link<T> find(Link<T> key){
        Link<T> current = first;
        if (current.data.equals(key.data)){
            return current;
        }
        current = current.nextLink;
        while (!current.data.equals(first.data)){
            if (current.data.equals(key.data)){
                return current;
            }
            current=current.nextLink;
        }
        return current;
    }
    public void delete(T data){
        Link<T> current = first;
        if (current.data.equals(data)){
            first = first.nextLink;
            last.nextLink=first;
        }
        else {
            boolean deleted = true;
            while (deleted) {
                if (current.nextLink.data.equals(data)) {
                    current.nextLink = current.nextLink.nextLink;
                    deleted=false;
                }
                if (current.nextLink.equals(first)){
                    last=current;
                }
                current = current.nextLink;
            }
        }
    }
    public int size(){
        Link<T> current = first;
        int counter = 0;
        if(first==last){
            return 1;
        }
        else {
            while (current.nextLink!=first){
                counter++;
                current=current.nextLink;
            }
        }
        return counter+1;
    }
}
