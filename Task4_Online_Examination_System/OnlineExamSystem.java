import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class OnlineExamSystem extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private User user;
    private Exam exam;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel loginMessage;

    private JTextField displayNameField;
    private JPasswordField newPasswordField;

    private JLabel questionNumberLabel;
    private JLabel questionLabel;
    private JLabel timerLabel;

    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;

    private ButtonGroup optionGroup;

    private JButton previousButton;
    private JButton nextButton;

    private int currentQuestion = 0;

    private Timer timer;

    private int totalSeconds = 30 * 60;
    private int elapsedSeconds = 0;

    private boolean examStarted = false;
    private boolean examSubmitted = false;

    private JLabel resultScoreLabel;
    private JLabel resultTimeLabel;
    private JLabel resultCorrectLabel;
    private JLabel resultIncorrectLabel;

    private JTextArea breakdownArea;

    public OnlineExamSystem() {

        user = new User(
                "student",
                "1234",
                "Student"
        );

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        setTitle("Online Examination System");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        createLoginPanel();
        createProfilePanel();
        createExamPanel();
        createResultPanel();

        add(mainPanel);

        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {

                        handleWindowClosing();
                    }
                }
        );

        cardLayout.show(
                mainPanel,
                "LOGIN"
        );
    }

    // ================= LOGIN =================

    private void createLoginPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(new Color(245, 247, 250));

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel title =
                new JLabel("ONLINE EXAMINATION SYSTEM");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitle =
                new JLabel("Student Login");

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        usernameField =
                new JTextField(18);

        passwordField =
                new JPasswordField(18);

        JButton loginButton =
                new JButton("Login");

        loginMessage =
                new JLabel(" ");

        loginMessage.setForeground(Color.RED);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc);

        gbc.gridy++;

        panel.add(subtitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(
                new JLabel("Username:"),
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                usernameField,
                gbc
        );

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(
                new JLabel("Password:"),
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                passwordField,
                gbc
        );

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        panel.add(
                loginButton,
                gbc
        );

        gbc.gridy++;

        panel.add(
                loginMessage,
                gbc
        );

        loginButton.addActionListener(
                e -> login()
        );

        mainPanel.add(
                panel,
                "LOGIN"
        );
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.equals(user.getUsername())
                && user.checkPassword(password)) {

            loginMessage.setText("");

            showProfileScreen();

        } else {

            loginMessage.setText(
                    "Invalid username or password."
            );
        }
    }

    // ================= PROFILE =================

    private void createProfilePanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(
                new Color(245, 247, 250)
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        JLabel title =
                new JLabel(
                        "UPDATE PROFILE"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        displayNameField =
                new JTextField(20);

        newPasswordField =
                new JPasswordField(20);

        JButton startExamButton =
                new JButton(
                        "Start Exam"
                );

        JButton backButton =
                new JButton("Logout");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        panel.add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(
                new JLabel("Display Name:"),
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                displayNameField,
                gbc
        );

        gbc.gridy++;
        gbc.gridx = 0;

        panel.add(
                new JLabel("New Password:"),
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                newPasswordField,
                gbc
        );

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        panel.add(
                startExamButton,
                gbc
        );

        gbc.gridy++;

        panel.add(
                backButton,
                gbc
        );

        startExamButton.addActionListener(
                e -> updateProfileAndStartExam()
        );

        backButton.addActionListener(
                e -> logout()
        );

        mainPanel.add(
                panel,
                "PROFILE"
        );
    }

    private void showProfileScreen() {

        displayNameField.setText(
                user.getDisplayName()
        );

        newPasswordField.setText("");

        cardLayout.show(
                mainPanel,
                "PROFILE"
        );
    }

    private void updateProfileAndStartExam() {

        String displayName =
                displayNameField
                        .getText()
                        .trim();

        String newPassword =
                new String(
                        newPasswordField.getPassword()
                );

        if (displayName.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Display name cannot be empty.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!newPassword.isEmpty()) {

            if (newPassword.length() < 4) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password must contain at least 4 characters.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            user.setPassword(newPassword);
        }

        user.setDisplayName(displayName);

        startExam();
    }

    // ================= EXAM =================

    private void createExamPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        questionNumberLabel =
                new JLabel(
                        "Question 1"
                );

        questionNumberLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        timerLabel =
                new JLabel(
                        "Time: 30:00"
                );

        timerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        topPanel.add(
                questionNumberLabel,
                BorderLayout.WEST
        );

        topPanel.add(
                timerLabel,
                BorderLayout.EAST
        );

        panel.add(
                topPanel,
                BorderLayout.NORTH
        );

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        questionLabel =
                new JLabel(
                        "Question"
                );

        questionLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        questionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        centerPanel.add(
                questionLabel
        );

        centerPanel.add(
                Box.createVerticalStrut(25)
        );

        option1 =
                new JRadioButton();

        option2 =
                new JRadioButton();

        option3 =
                new JRadioButton();

        option4 =
                new JRadioButton();

        optionGroup =
                new ButtonGroup();

        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        centerPanel.add(option1);
        centerPanel.add(option2);
        centerPanel.add(option3);
        centerPanel.add(option4);

        panel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        previousButton =
                new JButton("Previous");

        nextButton =
                new JButton("Next");

        JButton submitButton =
                new JButton("Submit Exam");

        bottomPanel.add(
                previousButton
        );

        bottomPanel.add(
                nextButton
        );

        bottomPanel.add(
                submitButton
        );

        panel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        previousButton.addActionListener(
                e -> previousQuestion()
        );

        nextButton.addActionListener(
                e -> nextQuestion()
        );

        submitButton.addActionListener(
                e -> confirmSubmission()
        );

        mainPanel.add(
                panel,
                "EXAM"
        );
    }

    private void startExam() {

        exam = new Exam();

        currentQuestion = 0;

        totalSeconds = 30 * 60;

        elapsedSeconds = 0;

        examStarted = true;

        examSubmitted = false;

        loadQuestion();

        cardLayout.show(
                mainPanel,
                "EXAM"
        );

        startTimer();
    }

    private void loadQuestion() {

        ArrayList<Question> questions =
                exam.getQuestions();

        Question question =
                questions.get(currentQuestion);

        questionNumberLabel.setText(
                "Question "
                        + (currentQuestion + 1)
                        + " / "
                        + questions.size()
        );

        questionLabel.setText(
                "<html><div style='width:700px'>"
                        + question.getQuestionText()
                        + "</div></html>"
        );

        String[] options =
                question.getOptions();

        option1.setText(
                "A. " + options[0]
        );

        option2.setText(
                "B. " + options[1]
        );

        option3.setText(
                "C. " + options[2]
        );

        option4.setText(
                "D. " + options[3]
        );

        optionGroup.clearSelection();

        int savedAnswer =
                exam.getAnswer(
                        currentQuestion
                );

        if (savedAnswer == 0) {
            option1.setSelected(true);
        } else if (savedAnswer == 1) {
            option2.setSelected(true);
        } else if (savedAnswer == 2) {
            option3.setSelected(true);
        } else if (savedAnswer == 3) {
            option4.setSelected(true);
        }

        previousButton.setEnabled(
                currentQuestion > 0
        );

        nextButton.setEnabled(
                currentQuestion
                        < questions.size() - 1
        );
    }

    private void saveCurrentAnswer() {

        int answer = -1;

        if (option1.isSelected()) {
            answer = 0;
        } else if (option2.isSelected()) {
            answer = 1;
        } else if (option3.isSelected()) {
            answer = 2;
        } else if (option4.isSelected()) {
            answer = 3;
        }

        exam.setAnswer(
                currentQuestion,
                answer
        );
    }

    private void nextQuestion() {

        saveCurrentAnswer();

        if (currentQuestion
                < exam.getTotalQuestions() - 1) {

            currentQuestion++;

            loadQuestion();
        }
    }

    private void previousQuestion() {

        saveCurrentAnswer();

        if (currentQuestion > 0) {

            currentQuestion--;

            loadQuestion();
        }
    }

    // ================= TIMER =================

    private void startTimer() {

        timerLabel.setText(
                "Time: 30:00"
        );

        timer =
                new Timer(
                        1000,
                        e -> updateTimer()
                );

        timer.start();
    }

    private void updateTimer() {

        totalSeconds--;

        elapsedSeconds++;

        int minutes =
                totalSeconds / 60;

        int seconds =
                totalSeconds % 60;

        timerLabel.setText(
                String.format(
                        "Time: %02d:%02d",
                        minutes,
                        seconds
                )
        );

        if (totalSeconds <= 0) {

            timer.stop();

            autoSubmitExam();
        }
    }

    // ================= SUBMIT =================

    private void confirmSubmission() {

        saveCurrentAnswer();

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to submit the exam?",
                        "Confirm Submission",
                        JOptionPane.YES_NO_OPTION
                );

        if (result == JOptionPane.YES_OPTION) {

            submitExam();
        }
    }

    private void autoSubmitExam() {

        saveCurrentAnswer();

        JOptionPane.showMessageDialog(
                this,
                "Time is up! Your exam has been submitted automatically.",
                "Time Up",
                JOptionPane.INFORMATION_MESSAGE
        );

        submitExam();
    }

    private void submitExam() {

        if (examSubmitted) {
            return;
        }

        examSubmitted = true;

        examStarted = false;

        if (timer != null) {
            timer.stop();
        }

        showResult();
    }

    // ================= RESULT =================

    private void createResultPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(15, 15)
                );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "EXAM RESULT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel resultPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                10,
                                10
                        )
                );

        resultScoreLabel =
                new JLabel(
                        "Score: "
                );

        resultTimeLabel =
                new JLabel(
                        "Time Taken: "
                );

        resultCorrectLabel =
                new JLabel(
                        "Correct: "
                );

        resultIncorrectLabel =
                new JLabel(
                        "Incorrect: "
                );

        resultScoreLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        resultPanel.add(
                resultScoreLabel
        );

        resultPanel.add(
                resultTimeLabel
        );

        resultPanel.add(
                resultCorrectLabel
        );

        resultPanel.add(
                resultIncorrectLabel
        );

        panel.add(
                resultPanel,
                BorderLayout.WEST
        );

        breakdownArea =
                new JTextArea();

        breakdownArea.setEditable(false);

        breakdownArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        breakdownArea
                );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel();

        JButton logoutButton =
                new JButton(
                        "Logout"
                );

        bottomPanel.add(
                logoutButton
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        panel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                panel,
                "RESULT"
        );
    }

    private void showResult() {

        int score =
                exam.calculateScore();

        int correct =
                exam.getCorrectCount();

        int incorrect =
                exam.getIncorrectCount();

        int minutes =
                elapsedSeconds / 60;

        int seconds =
                elapsedSeconds % 60;

        resultScoreLabel.setText(
                "Score: "
                        + score
                        + " out of "
                        + exam.getTotalQuestions()
        );

        resultTimeLabel.setText(
                String.format(
                        "Time Taken: %02d minutes %02d seconds",
                        minutes,
                        seconds
                )
        );

        resultCorrectLabel.setText(
                "Correct Answers: "
                        + correct
        );

        resultIncorrectLabel.setText(
                "Incorrect Answers: "
                        + incorrect
        );

        StringBuilder breakdown =
                new StringBuilder();

        breakdown.append(
                "QUESTION BREAKDOWN\n"
        );

        breakdown.append(
                "========================================\n\n"
        );

        ArrayList<Question> questions =
                exam.getQuestions();

        for (int i = 0;
             i < questions.size();
             i++) {

            Question question =
                    questions.get(i);

            int selected =
                    exam.getAnswer(i);

            int correctAnswer =
                    question.getCorrectAnswer();

            breakdown.append(
                    "Question "
                            + (i + 1)
                            + ": "
            );

            if (selected == correctAnswer) {

                breakdown.append(
                        "CORRECT\n"
                );

            } else {

                breakdown.append(
                        "INCORRECT\n"
                );
            }

            breakdown.append(
                    "Correct Option: "
                            + getOptionLetter(
                                    correctAnswer
                            )
                            + "\n"
            );

            if (selected == -1) {

                breakdown.append(
                        "Your Answer: Not Answered\n"
                );

            } else {

                breakdown.append(
                        "Your Answer: "
                                + getOptionLetter(
                                        selected
                                )
                                + "\n"
                );
            }

            breakdown.append(
                    "----------------------------------------\n"
            );
        }

        breakdownArea.setText(
                breakdown.toString()
        );

        cardLayout.show(
                mainPanel,
                "RESULT"
        );
    }

    private String getOptionLetter(int answer) {

        switch (answer) {

            case 0:
                return "A";

            case 1:
                return "B";

            case 2:
                return "C";

            case 3:
                return "D";

            default:
                return "-";
        }
    }

    // ================= LOGOUT =================

    private void logout() {

        if (timer != null) {
            timer.stop();
        }

        examStarted = false;

        examSubmitted = false;

        currentQuestion = 0;

        usernameField.setText("");

        passwordField.setText("");

        loginMessage.setText("");

        cardLayout.show(
                mainPanel,
                "LOGIN"
        );
    }

    // ================= CLOSE WINDOW =================

    private void handleWindowClosing() {

        if (examStarted
                && !examSubmitted) {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to quit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice
                    == JOptionPane.YES_OPTION) {

                if (timer != null) {
                    timer.stop();
                }

                dispose();
            }

        } else {

            dispose();
        }
    }
}


    

