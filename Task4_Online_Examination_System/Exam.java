import java.util.ArrayList;

public class Exam {

    private ArrayList<Question> questions;
    private int[] selectedAnswers;

    public Exam() {

        questions = new ArrayList<>();

        questions.add(
                new Question(
                        "Which language is mainly used for Android development?",
                        new String[]{
                                "Java",
                                "HTML",
                                "CSS",
                                "SQL"
                        },
                        0
                )
        );

        questions.add(
                new Question(
                        "Which keyword is used to inherit a class in Java?",
                        new String[]{
                                "implement",
                                "extends",
                                "inherits",
                                "super"
                        },
                        1
                )
        );

        questions.add(
                new Question(
                        "Which collection allows duplicate elements?",
                        new String[]{
                                "Set",
                                "Map",
                                "List",
                                "None"
                        },
                        2
                )
        );

        questions.add(
                new Question(
                        "Which method is the entry point of a Java program?",
                        new String[]{
                                "start()",
                                "run()",
                                "main()",
                                "execute()"
                        },
                        2
                )
        );

        questions.add(
                new Question(
                        "Which concept hides internal implementation details?",
                        new String[]{
                                "Inheritance",
                                "Encapsulation",
                                "Polymorphism",
                                "Abstraction"
                        },
                        3
                )
        );

        questions.add(
                new Question(
                        "Which keyword is used to create an object?",
                        new String[]{
                                "class",
                                "object",
                                "new",
                                "create"
                        },
                        2
                )
        );

        questions.add(
                new Question(
                        "Which Swing component is used for a single-choice option?",
                        new String[]{
                                "JTextField",
                                "JRadioButton",
                                "JLabel",
                                "JPanel"
                        },
                        1
                )
        );

        questions.add(
                new Question(
                        "Which layout is useful for switching between different panels?",
                        new String[]{
                                "FlowLayout",
                                "GridLayout",
                                "CardLayout",
                                "BorderLayout"
                        },
                        2
                )
        );

        questions.add(
                new Question(
                        "Which class is used for a Swing countdown timer?",
                        new String[]{
                                "java.util.Timer",
                                "javax.swing.Timer",
                                "TimerTask",
                                "Clock"
                        },
                        1
                )
        );

        questions.add(
                new Question(
                        "Which keyword is used to handle exceptions?",
                        new String[]{
                                "try",
                                "catch",
                                "throw",
                                "All of these"
                        },
                        3
                )
        );

        selectedAnswers = new int[questions.size()];

        for (int i = 0; i < selectedAnswers.length; i++) {
            selectedAnswers[i] = -1;
        }
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setAnswer(int questionIndex, int answer) {
        selectedAnswers[questionIndex] = answer;
    }

    public int getAnswer(int questionIndex) {
        return selectedAnswers[questionIndex];
    }

    public int calculateScore() {

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {

            if (selectedAnswers[i]
                    == questions.get(i).getCorrectAnswer()) {

                score++;
            }
        }

        return score;
    }

    public int getCorrectCount() {
        return calculateScore();
    }

    public int getIncorrectCount() {
        return questions.size() - calculateScore();
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
