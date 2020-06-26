package Task2;

import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E> > extends ArrayList<E> {
    /**
     * This method is to sort element in the SortedArrayList
     * @param element the element wished to be added into SortedArrayList
     */
    public void insertion(E element){
        if (this.isEmpty()){
            this.add(element);
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (element.compareTo(this.get(i)) < 0)
                    ++this.modCount; // an imitation of ArrayList's source code
                    this.add(i+1, element);
                break; // Once find the target location, stop the loop; if unsure, please try to execute.
            }
        }
    }

}
