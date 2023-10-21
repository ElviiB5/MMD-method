import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MMDRun {
    private JFrame win;
    private JButton btn;
    private JPanel panel, txtPanel;
    private JTextField[] txt;
    private JLabel[] lbl;
    private JScrollPane scroll;
    private JTextArea area;

    int d, h, c, m, k, t, numbersAmount;
    long[] n;
    double[] x, results;
    double a;
    long infLimit, supLimit;

    PrimitiveRoot primitiveRoot;
    Odd odd;

    public MMDRun() {
        win = new JFrame("MMD");
        btn = new JButton("CALCULAR");
        panel = new JPanel();
        txtPanel = new JPanel();
        txt = new JTextField[9];
        lbl = new JLabel[9];
        area = new JTextArea();
        scroll = new JScrollPane(area);
        n = new long[(int) Math.pow(10, 6)];
        x = new double[(int) Math.pow(10, 6)];
        results = new double[(int) Math.pow(10, 6)];

        primitiveRoot = new PrimitiveRoot();
        odd = new Odd();

        for (int i = 0; i < 9; i++) {
            txt[i] = new JTextField("");
            txt[i].setPreferredSize(new Dimension(70, 20));
        }

        lbl[0] = new JLabel("N");
        lbl[1] = new JLabel("Desde");
        lbl[2] = new JLabel("Hasta");
        lbl[3] = new JLabel("d");
        lbl[4] = new JLabel("h");
        lbl[5] = new JLabel("m");
        lbl[6] = new JLabel("a");
        lbl[7] = new JLabel("n");
        lbl[8] = new JLabel("c");

        properties();
        create();
        listen();
        show();
    }

    public void properties() {
        win.setSize(1000, 600);
        win.setLocationRelativeTo(null);
        win.setLayout(new BorderLayout());
        panel.setBackground(new Color(211, 188, 226));
    }

    public void create() {
        for (int i = 0; i < 9; i++) {
            panel.add(lbl[i]);
            panel.add(txt[i]);
        }
        txtPanel.setLayout(new BorderLayout());
        txtPanel.setBackground(Color.black);
        panel.add(btn);
        txtPanel.add(scroll, BorderLayout.CENTER);
        win.add(panel, BorderLayout.NORTH);
        win.add(txtPanel, BorderLayout.CENTER);
    }

    public void listen() {
        btn.addMouseListener(new ClickListener());
    }

    public void show() {
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }

    class ClickListener extends MouseAdapter {
        public void mouseClicked(MouseEvent me) {
            if (me.getComponent().equals(btn)) {
                numbersAmount = Integer.parseInt(txt[0].getText().trim());
                infLimit = Long.parseLong(txt[1].getText().trim());
                supLimit = Long.parseLong(txt[2].getText().trim());
                calculate();
                print();
            }
        }
    }

    public void calculate() {
        if (numbersAmount < 500) {
            d = 4;
        } else {
            d = 1 + (int) (Math.log10(2 * numbersAmount) + 1 - Math.pow(10, -6));
        }
        h = (int) (5 * (Math.pow(10, (d - 2))));
        m = (int) Math.pow(10, d);
        c = 0;

        a = primitiveRoot.getRoot(m);
        n[0] = odd.getOdd(m);
        while (n[0] % 5 == 0) {
            n[0] = odd.getOdd(m);
        }
        calculateNi();
    }

    public void calculateNi() {
        for (int i = 1; i < numbersAmount; i++) {
            n[i] = (long) ((a * n[i - 1] + c) % m);
        }
        System.out.println(infLimit + " " + supLimit);
        for (int i = 0; i < numbersAmount; i++) {
            x[i] = (double) n[i] / m;
            results[i] = infLimit + (supLimit - infLimit) * x[i];
        }
    }

    public void print() {
        String resultText = "";

        txt[3].setText(String.valueOf(d));
        txt[4].setText(String.valueOf(h));
        txt[5].setText(String.valueOf(m));
        txt[6].setText(String.valueOf(a));
        txt[7].setText(String.valueOf(n[0]));
        txt[8].setText(String.valueOf(c));

        for (int i = 0; i < numbersAmount; i++) {
            resultText += (i + 1 + ":      ni = " + n[i] + "    xi = " + x[i] + "    Ri = " + results[i] + "\n");
        }

        area.setText(resultText + "\n");
    }
}
