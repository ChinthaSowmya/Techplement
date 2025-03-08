import java.util.*;

public class QuizGenerator {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<Question>> quizzes = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nQuiz Generator Menu:");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add questions to a quiz");
            System.out.println("3. View available quizzes");
            System.out.println("4. Take a quiz");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createQuiz();
                case 2 -> addQuestions();
                case 3 -> viewQuizzes();
                case 4 -> takeQuiz();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void createQuiz() {
        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();
        if (quizzes.containsKey(quizName)) {
            System.out.println("Quiz already exists!");
            return;
        }
        quizzes.put(quizName, new ArrayList<>());
        System.out.println("Quiz \"" + quizName + "\" created successfully.");
    }

    private static void addQuestions() {
        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();
        if (!quizzes.containsKey(quizName)) {
            System.out.println("Quiz not found.");
            return;
        }
        
        System.out.print("Enter question: ");
        String question = scanner.nextLine();
        List<String> options = new ArrayList<>();
        
        for (int i = 1; i <= 4; i++) {
            System.out.print("Option " + i + ": ");
            options.add(scanner.nextLine());
        }

        System.out.print("Enter correct option number (1-4): ");
        int correctOption = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        quizzes.get(quizName).add(new Question(question, options, correctOption));
        System.out.println("Question added successfully.");
    }

    private static void viewQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }
        System.out.println("Available quizzes:");
        for (String quizName : quizzes.keySet()) {
            System.out.println("- " + quizName);
        }
    }

    private static void takeQuiz() {
        System.out.print("Enter quiz name: ");
        String quizName = scanner.nextLine();
        if (!quizzes.containsKey(quizName)) {
            System.out.println("Quiz not found.");
            return;
        }
        
        List<Question> questions = quizzes.get(quizName);
        if (questions.isEmpty()) {
            System.out.println("No questions available in this quiz.");
            return;
        }

        int score = 0;
        System.out.println("\nTaking the " + quizName + " quiz:");
        for (Question q : questions) {
            System.out.println("\n" + q.question);
            for (int i = 0; i < q.options.size(); i++) {
                System.out.println((i + 1) + ". " + q.options.get(i));
            }
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (answer == q.correctOption) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer: " + q.correctOption);
            }
        }
        System.out.println("\nQuiz completed. Your score: " + score + "/" + questions.size());
    }
}