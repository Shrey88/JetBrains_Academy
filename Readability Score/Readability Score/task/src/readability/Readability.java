package readability;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Readability {

    private double sentCount;
    private double wordCount;
    private double letterCount;
    private double syllableCount;
    private double polysyllableCount;
    private final String fileName;

    /**
     * Provide the file name from where the text needs to be read.
     * @param fileName file name with absolute or relative path.
     */
    public Readability(String fileName) {
        this.sentCount = 0;
        this.wordCount = 0;
        this.letterCount = 0;
        this.syllableCount = 0;
        this.polysyllableCount = 0;
        this.fileName = fileName;
    }

    public void count() {
        count_Sent_Word_Letter(fileName);
    }

    public void score() {
        System.out.println("Words: " + (int) this.wordCount);
        System.out.println("Sentences: " + (int) this.sentCount);
        System.out.println("Characters: " + (int) this.letterCount);
        System.out.println("Syllables: " + (int) this.syllableCount);
        System.out.println("Polysyllables: " + (int) this.polysyllableCount);
    }

    public void calculate() {
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner scanner = new Scanner(System.in);
        String calculate = scanner.nextLine();
        System.out.println();
        switch(calculate) {
            case "ARI":
                Automated_Readability_Index();
                break;
            case "FK":
                Flesch_Kincaid();
                break;
            case "SMOG":
                SMOG();
                break;
            case "CL":
                Coleman_Liau();
                break;
            case "all":
                double sum = Automated_Readability_Index() + Flesch_Kincaid() + SMOG() + Coleman_Liau();
                int end_value = String.valueOf(sum / 4).lastIndexOf('.') +
                        (String.valueOf(sum / 4).length() - String.valueOf(sum / 4).lastIndexOf('.'));
                System.out.println("This text should be understood in average by " +
                        String.valueOf(sum / 4).substring(0, end_value) + "-year-olds.");
                break;
            default:
                break;
        }
    }

    private int score_table(double score) {
        double diff_value = score - Math.floor(score);
        int final_value;

        if (diff_value < 0.6) {
            final_value = (int) Math.floor(score);
        } else {
            final_value = (int) Math.ceil(score);
        }

        switch(final_value) {
            case 1:
                System.out.print("6-year-olds).\n");
                return 6;
            case 2:
                System.out.print("7-year-olds).\n");
                return 7;
            case 3:
                System.out.print("9-year-olds).\n");
                return 9;
            case 4:
                System.out.print("10-year-olds).\n");
                return 10;
            case 5:
                System.out.print("11-year-olds).\n");
                return 11;
            case 6:
                System.out.print("12-year-olds).\n");
                return 12;
            case 7:
                System.out.print("13-year-olds).\n");
                return 13;
            case 8:
                System.out.print("14-year-olds).\n");
                return 14;
            case 9:
                System.out.print("15-year-olds).\n");
                return 15;
            case 10:
                System.out.print("16-year-olds).\n");
                return 16;
            case 11:
                System.out.print("17-year-olds).\n");
                return 17;
            case 12:
                System.out.print("18-year-olds).\n");
                return 18;
            case 13:
                System.out.print("24-year-olds).\n");
                return 24;
            case 14:
                System.out.print("25-year-olds).\n");
                return 25;
            default:
                break;
        }

        return 0;
    }
    private int Automated_Readability_Index() {
        double arIndex = (4.71 * (letterCount / wordCount)) + (0.5 * (wordCount / sentCount)) - 21.43;
        System.out.print("Automated Readability Index: " +
                String.valueOf(arIndex).substring(0, String.valueOf(arIndex).lastIndexOf('.') + 3) + " (about ");

        return score_table(arIndex);
    }

    private int Flesch_Kincaid() {
        double fkIndex = (0.39 * (wordCount / sentCount)) + (11.8 * (syllableCount / wordCount)) - 15.59;

        System.out.print("Flesch–Kincaid readability tests: " +
                String.valueOf(fkIndex).substring(0, String.valueOf(fkIndex).lastIndexOf('.') + 3) + " (about ");

        return score_table(fkIndex);
    }

    private int SMOG() {
        double smogIndex = (1.043 * Math.sqrt(polysyllableCount * (30 / sentCount))) + 3.1291;

        System.out.print("Simple Measure of Gobbledygook: " +
                String.valueOf(smogIndex).substring(0, String.valueOf(smogIndex).lastIndexOf('.') + 3) + " (about ");

        return score_table(smogIndex);
    }

    private int Coleman_Liau() {
        double l = (letterCount / wordCount) * 100;
        double s = (sentCount / wordCount) * 100;
        double clIndex = (0.0588 * l) - (0.296 * s) - 15.8;

        System.out.print("Coleman–Liau index: " +
                String.valueOf(clIndex).substring(0, String.valueOf(clIndex).lastIndexOf('.') + 3) + " (about ");

        return score_table(clIndex);
    }

    private void count_Sent_Word_Letter(String inputFile) {
        double vowelCount;
        boolean dualVowel;
        boolean prevFound;
//        boolean prevPrevNotFound = false;
        boolean nextFound;
        char[] letters;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(inputFile))) {

            String sentences = buffReader.readLine();
            sentCount = sentences.split("[!.?]").length;

            for (String sentence : sentences.split("\\.[0]")) {
                String[] words = sentence.split("\\s+");
                wordCount += words.length;
                for (String word : words) {
//                    System.out.print(word);
                    letterCount += word.toCharArray().length;
                    letters= word.split("[!.?]")[0].toCharArray();

                    //reset the values
                    vowelCount = 0;
                    dualVowel = false;

                    /*
                     *  check for the vowel characters and follow the below rules:
                     *      Count the number of vowels in the word.
                     *      Do not count double-vowels (for example, "rain" has 2 vowels but only 1 syllable).
                     *      If the last letter in the word is 'e' do not count it as a vowel (for example, "side" has 1 syllable).
                     *      If at the end it turns out that the word contains 0 vowels, then consider this word as a 1-syllable one.
                     */
                    {
                        prevFound = false;
//                        prevPrevNotFound = false;

                        for (int i = 0; i < letters.length; i++) {
                            boolean vowelsFound = String.valueOf(letters[i]).matches("[AEIOUYaeiouy]");
                            if (i + 1 == letters.length && letters.length > 1) {
                                prevFound = String.valueOf(letters[i - 1]).matches("[aiouyAIOUY]");
                                //prevPrevNotFound = String.valueOf(letters[i - 2]).matches("[^aiouyAIOUY]");
                            }

                            if (vowelsFound) {
                                if (i + 1 != letters.length) {
                                    if (i != 0){
                                        prevFound = String.valueOf(letters[i - 1]).matches("[aeiouy]");
                                    }
                                    nextFound = String.valueOf(letters[i + 1]).matches("[aeiouy]");

                                    if (!prevFound && !nextFound) {
                                        ++vowelCount;
                                    } else if (!prevFound && nextFound) {
                                        dualVowel = true;
                                    } else if (prevFound && !nextFound) {
                                        if (dualVowel) {
                                            // temp comment
                                            //++syllableCount;
                                            ++vowelCount;
                                            dualVowel = false;
                                            if (vowelCount == 0) {
                                                vowelCount = -1;
                                            }
                                        }
                                    }
                                } else if (i + 1 == letters.length && letters.length > 1) {
                                    if (vowelsFound) {
                                        if (dualVowel) {
                                            // temp comment
                                            //++syllableCount;
                                            ++vowelCount;

                                            dualVowel = false;
                                            if (vowelCount == 0) {
                                                vowelCount = -1;
                                            }
                                        } else if (!prevFound && String.valueOf(letters[i]).matches("[^e]")){
                                            ++vowelCount;
                                        }
                                    }
                                }
                            } else if (i + 1 == letters.length && letters.length > 1) {
                                if(!prevFound) {
                                    if (vowelsFound) {
                                        ++vowelCount;
                                    }
                                }
                            }
                        }

                        if (vowelCount == 0) {
                            ++this.syllableCount;
                        } else if (vowelCount > 0){
                            this.syllableCount += vowelCount;
                            if (vowelCount >= 3) {
                                ++this.polysyllableCount;
                            }
                        }
                    }
//                    System.out.print("\t" + vowelCount + "\t" + (vowelCount == 0 ? 1 : vowelCount) + "\t" +
//                            syllableCount + "\t" + polysyllableCount + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Exception Occurred : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception Occurred : " + e.getMessage());
        }
    }


}
