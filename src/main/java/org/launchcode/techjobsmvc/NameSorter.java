package org.launchcode.techjobsmvc;

import java.util.Comparator;
// is being used by JobData class to sort objects in this case job data
// in a case-insensitive alphabetical order
public class NameSorter implements Comparator<Object> {

    @Override
    public int compare(Object o1, Object o2) {
        return o1.toString().toLowerCase().compareTo(o2.toString().toLowerCase());
    }

}

