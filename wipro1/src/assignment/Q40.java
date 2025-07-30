package assignment;

import java.util.*;

class CD implements Comparable<CD> {
    String title;
    String singer;

    CD(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }

    @Override
    public int compareTo(CD other) {
        return this.singer.compareToIgnoreCase(other.singer);
    }

    public String toString() {
        return "Title: " + title + ", Singer: " + singer;
    }
}

public class Q40 {
    public static void main(String[] args) {
        List<CD> cds = new ArrayList<>();
        cds.add(new CD("Hits of 90s", "Asha"));
        cds.add(new CD("Rock Album", "Zayn"));
        cds.add(new CD("Melody", "Arijit"));

        Collections.sort(cds);

        System.out.println(" CDs Sorted by Singer Name:");
        for (CD cd : cds) {
            System.out.println(cd);
        }
    }
}
/*
 CDs Sorted by Singer Name:
Title: Melody, Singer: Arijit
Title: Hits of 90s, Singer: Asha
Title: Rock Album, Singer: Zayn

 
 */
