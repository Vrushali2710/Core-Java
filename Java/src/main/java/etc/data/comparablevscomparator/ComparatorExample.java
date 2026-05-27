package etc.data.comparablevscomparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Movie2 {
 private String name;
 private double rating;
 private int year;


}

class Rating implements Comparator<Movie2> {

    @Override
    public int compare(Movie2 o1, Movie2 o2) {
        return Double.compare(o1.getRating(), o2.getRating());
    }
}
class NameCompare implements Comparator<Movie2> {

    @Override
    public int compare(Movie2 o1, Movie2 o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

public class ComparatorExample {
    public static void main(String[] args) {
        ArrayList<Movie2> m = new ArrayList<>();
        m.add(new Movie2("Force Awakens", 8.3, 2015));
        m.add(new Movie2("Star Wars", 8.7, 1977));
        m.add(new Movie2("Empire Strikes Back", 8.8, 1980));

        // Sort movies by rating and display all
        Collections.sort(m, new Rating());
        System.out.println("Movies sorted by rating:");
        for (Movie2 m1 : m) {
            System.out.println(m1.getRating() + " " + m1.getName() + " " + m1.getYear());
        }
        Collections.sort(m, new NameCompare());
        System.out.println("\nMovies sorted by name:");
        for (Movie2 m1 : m) {
            System.out.println(m1.getName() + " " + m1.getRating() + " " + m1.getYear());
        }
    }

    }

