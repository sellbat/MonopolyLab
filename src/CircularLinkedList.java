public class CircularLinkedList<T> {
    //list thats end leads to the beginning
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
    public CircularLinkedList(){//initializes an empty list
        first = null;
        last = null;
    }

    public boolean isEmpty(){//checks if the list is empty
        return first==null&&last==null;
    }
    public void insertFirst(T newData){//inserts a new link
        Link<T> newLink = new Link<T>(newData);
        if (isEmpty()){//if the link is the only link, makes it both first and last
            first=newLink;
            last=newLink;
        }
        else{//otherwise makes the new link first, the old first the second, and connects the last link to the new first
            newLink.nextLink = first;
            first = newLink;
            last.nextLink = first;
        }
    }
    public Link<T> find(Link<T> key){//locates a link in the list based on data
        Link<T> current = first;
        //returns the first link if the key is first
        if (current.data.equals(key.data)){
            return current;
        }
        current = current.nextLink;
        //checks the remaining links for the key and returns the wanted link
        while (!current.data.equals(first.data)){
            if (current.data.equals(key.data)){
                return current;
            }
            current=current.nextLink;
        }
        return current;
    }
    public void delete(T data){//deletes a link from the list
        Link<T> current = first;
        //if the wanted deletion is the first link, sets the first link as the old second and sets the last links next link to the new first
        //therefor removing the old first
        if (current.data.equals(data)){
            first = first.nextLink;
            last.nextLink=first;
        }
        else {
            boolean deleted = true;
            //checks the remaining links for the data and removes the link with the data
            while (deleted) {
                if (current.nextLink.data.equals(data)) {
                    current.nextLink = current.nextLink.nextLink;
                    deleted=false;
                }
                //if the removed link is the last, sets the second to last link as the new last
                if (current.nextLink.equals(first)){
                    last=current;
                }
                current = current.nextLink;
            }
        }
    }
    public int size(){ //finds the number of links in the list
        Link<T> current = first;
        int counter = 0;
        if(first==last){//if there is only one link, returns one
            return 1;
        }
        else {
            //counts the number of links until the first is reached
            while (current.nextLink!=first){
                counter++;
                current=current.nextLink;
            }
        }
        //returns the counted links plus one because the first link wasn't origonaly counted
        return counter+1;
    }
}
