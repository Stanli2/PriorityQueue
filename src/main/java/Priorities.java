import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Priorities {
    static class StudentComparator implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            if (s1.cgpa < s2.cgpa) {
                return 1;
            } else if(s1.cgpa > s2.cgpa) {
                return -1;
            } else {
                int compareName = s1.name.compareTo(s2.name);
                if (compareName != 0) {
                    return compareName;
                } else {
                    return s1.id - s2.id;
                }
            }
        }
    }


    public static List<String> getStudents(List<String> events) {
        List empty = new ArrayList(List.of("EMPTY"));
        StudentComparator comparator = new StudentComparator();
        PriorityQueue<Student> pr = new PriorityQueue<>(Integer.parseInt(events.get(0)), comparator);

        events.remove(0);

        for (int i = 0; i < events.size(); i++) {
            if(events.get(i).startsWith("ENTER")) {
                String[] event = events.get(i).split(" ");
                pr.add(new Student(Integer.parseInt(event[3]), event[1], Double.parseDouble(event[2])));
            } else if(events.get(i).startsWith("SERVED")) {
                pr.poll();
            }

        }

        return pr.stream().map(Student::getName).collect(Collectors.toCollection(ArrayList::new));

    }
}
