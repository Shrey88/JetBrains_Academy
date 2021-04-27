import java.util.Scanner;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        // write your code here
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        // write your code here
        return this.algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {
    private final int step;

    public TakePersonsWithStepAlgorithm(int step) {
        // write your code here
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        if (persons.length > 0) {
            double size = (double) persons.length / (double) step;
            int arrSize = (int) Math.ceil(size);

//            if (size - Math.floor(size) < 0.6) {
//                arrSize = (int) size != 0 ? (int) size : 1;
//            } else {
//                arrSize = (int) Math.ceil(size);
//            }

            Person[] personArr = new Person[arrSize];

            if (step == 1) {
                return persons;
            } else {
                int index1 = 0;
                int index2 = 0;
                for (int i = 0; i < arrSize; i++) {
                    personArr[index1] = persons[index2];
                    ++index1;
                    index2 += step;
                }
            }
            return personArr;
        }

        return new Person[0];
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {
    private final int count;

    public TakeLastPersonsAlgorithm(int count) {
        // write your code here
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        if (persons.length > 0) {
            Person[] personArr = new Person[count];

            if (count == 1) {
                personArr[0] = persons[persons.length - 1];
            } else {
                int index = personArr.length - 1;
                for (int i = 0; i < count; i++) {
                    personArr[index] = persons[persons.length - 1 - i];
                    --index;
                }
            }
            return personArr;
        }

        return new Person[0];
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");
        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}