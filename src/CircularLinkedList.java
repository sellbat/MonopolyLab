public class CircularLinkedList {
    private Link first;
    private Link last;
    public CircularLinkedList(){
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return first==null&&last==null;
    }
    public void insertFirst(Object newData){
        Link newLink = new Link(newData);
        if (isEmpty()){
            first=newLink;
            last=newLink;
        }
        last.nextLink=newLink;
        newLink.nextLink=first;
        first=newLink;
    }
    public int find(Object key){
        Link current = first;
        if (current.data.equals(key)){
            return 0;
        }
        current = current.nextLink;
        int counter = 1;
        while (!current.data.equals(first.data)){
            if (current.data.equals(key)){
                return counter;
            }
            counter++;
            current=current.nextLink;
        }
        return 0;
    }
}
