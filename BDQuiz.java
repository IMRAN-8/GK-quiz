import java.util.*;
import java.io.*;

public class BDQuiz {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Player name
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        int score = 0;

        try {
            File file = new File("bd_quiz_questions.txt");
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();

                if (line.isEmpty()) continue;

                // Format: Question|Option1|Option2|Option3|Option4|Answer
                String[] parts = line.split("\\|");

                if (parts.length < 6) continue;

                String question = parts[0];
                String op1 = parts[1];
                String op2 = parts[2];
                String op3 = parts[3];
                String op4 = parts[4];
                String correct = parts[5].toLowerCase();

                System.out.println("\n" + question);
                System.out.println("a) " + op1);
                System.out.println("b) " + op2);
                System.out.println("c) " + op3);
                System.out.println("d) " + op4);

                System.out.print("Your answer (a/b/c/d): ");
                String answer = sc.nextLine().toLowerCase();

                if (answer.equals(correct)) {
                    System.out.println("Correct!");
                    score += 10;
                } else {
                    System.out.println("Wrong! Correct answer: " + correct);
                }
            }

            fileReader.close();

        } catch (Exception e) {
            System.out.println("Could not read the questions file.");
        }

        // Final score
        System.out.println("\n" + name + ", your total score is: " + score);

        // Save score
        try (PrintWriter out = new PrintWriter(new FileWriter("quiz_scores.txt", true))) {
            out.println(name + " - Score: " + score);
            System.out.println("Score saved to quiz_scores.txt");
        } catch (IOException e) {
            System.out.println("Error saving score.");
        }

        sc.close();
    }
}
