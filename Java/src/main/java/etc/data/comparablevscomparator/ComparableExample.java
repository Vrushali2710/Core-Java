package etc.data.comparablevscomparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
 class Movie implements Comparable<Movie> {

    private String name;
    private  double rating;
    private int year;




    @Override
    public int compareTo(Movie other) {
        return this.year - other.year;
    }

}
public class ComparableExample {
    public static void main(String[] args) {
        ArrayList<Movie> l = new ArrayList<>();
        l.add(new Movie("Star Wars", 8.7, 1999));
        l.add(new Movie("Empire Strikes Back", 8.8, 1980));
        l.add(new Movie("Return of the Jedi", 8.4, 1983));
        Collections.sort(l);
        System.out.println("Movies after sorting by year:");
        for (Movie m : l) {
            System.out.println(m.getName() + " " + m.getRating() + " " + m.getYear());
        }

    }
   

}
