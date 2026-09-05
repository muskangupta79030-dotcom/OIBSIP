import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    OnlineExamSystem examSystem =
                            new OnlineExamSystem();

                    examSystem.setVisible(true);
                }
        );
    }
}

