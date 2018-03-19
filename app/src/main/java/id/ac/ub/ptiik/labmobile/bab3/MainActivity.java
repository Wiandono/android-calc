package id.ac.ub.ptiik.labmobile.bab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> nilai = new ArrayList<String>();
    ArrayList<String> operator = new ArrayList<String>();

    int stack_counter = 0;
    int current_stack = 0;
    int comma_counter = 0;
    int hasilInt = 0;
    double hasilDouble = 0;
    TextView textScreen;
    TextView textScreenTop;
    String text_screen = "";
    boolean calculate = false;
    boolean op = false;
    boolean pertama = true;
    boolean operasi = false;
    boolean comma = false;
    boolean angka = false;
    boolean last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi view
        textScreen = (TextView) this.findViewById(R.id.textViewScreen2);
        textScreenTop = (TextView) this.findViewById(R.id.textViewScreen1);

        nilai.add("");
        operator.add("");

        Button button_0 = (Button) this.findViewById(R.id.button0);
        Button button_1 = (Button) this.findViewById(R.id.button1);
        Button button_2 = (Button) this.findViewById(R.id.button2);
        Button button_3 = (Button) this.findViewById(R.id.button3);
        Button button_4 = (Button) this.findViewById(R.id.button4);
        Button button_5 = (Button) this.findViewById(R.id.button5);
        Button button_6 = (Button) this.findViewById(R.id.button6);
        Button button_7 = (Button) this.findViewById(R.id.button7);
        Button button_8 = (Button) this.findViewById(R.id.button8);
        Button button_9 = (Button) this.findViewById(R.id.button9);
        Button buttonSamaDengan = (Button) this.findViewById(R.id.buttonSamaDengan);
        Button buttonKoma = (Button) this.findViewById(R.id.buttonKoma);
        Button buttonTambah = (Button) this.findViewById(R.id.buttonTambah);
        Button buttonKurang = (Button) this.findViewById(R.id.buttonKurang);
        Button buttonKali = (Button) this.findViewById(R.id.buttonKali);
        Button buttonBagi = (Button) this.findViewById(R.id.buttonBagi);
        Button buttonDel = (Button) this.findViewById(R.id.buttonDel);
        Button buttonPersen = (Button) this.findViewById(R.id.buttonPersen);
        Button buttonC = (Button) this.findViewById(R.id.buttonC);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        buttonSamaDengan.setOnClickListener(this);
        buttonKoma.setOnClickListener(this);
        buttonTambah.setOnClickListener(this);
        buttonKurang.setOnClickListener(this);
        buttonKali.setOnClickListener(this);
        buttonBagi.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        buttonPersen.setOnClickListener(this);
        buttonC.setOnClickListener(this);
    }

    public void setOp() {
        for (int x = current_stack; x < nilai.size(); x++) {
            text_screen += operator.get(x);
        }

        textScreen.setText(text_screen);
        op = false;
    }

    public void setCalculate() {
        for (int x = current_stack; x < nilai.size(); x++) {
            text_screen += operator.get(x);
            for (int y = x + 1; y < nilai.size(); y++) {
                text_screen += nilai.get(y);
                break;
            }
        }

        textScreen.setText(text_screen);
        calculate = false;
    }

    public void setScreen() {

        if (pertama == true) {
            text_screen = "";

            for (int x = 0; x < nilai.size(); x++) {
                text_screen += nilai.get(x);
                if (operator.size() < nilai.size()) {
                    text_screen += operator.get(operator.size() - 1);
                    break;
                } else {
                    text_screen += operator.get(x);
                }

            }
            textScreen.setText(text_screen);
        } else {
            if (comma == false) {
                if (op == true) {
                    text_screen = String.valueOf(hasilInt);
                    setOp();
                } else {
                    text_screen = String.valueOf(hasilInt);
                    setCalculate();
                }
            } else {
                if (op == true) {
                    text_screen = String.valueOf(hasilDouble);
                    setOp();
                } else {
                    text_screen = String.valueOf(hasilDouble);
                    setCalculate();
                }
            }
        }
    }

    public void setResult() {
        current_stack = stack_counter;
        calculate = true;
        op = true;
        pertama = false;
        angka = true;

        if (nilai.get(0) == "") {
            textScreenTop.setText("");
            textScreen.setText("");
        } else {
            for (int x = 0; x < nilai.size(); x++) {
                if (String.valueOf(nilai.get(x)).contains(".")) {
                    comma = true;
                    break;
                } else {
                    comma = false;
                }
            }

            if (comma) {
                for (int x = 0; x < nilai.size(); x++) {
                    if (x > 0) {
                        if (operator.get(x - 1).equals("+")) {
                            hasilDouble = hasilDouble + Double.parseDouble(nilai.get(x));
                        } else if (operator.get(x - 1).equals("-")) {
                            hasilDouble = hasilDouble - Double.parseDouble(nilai.get(x));
                        } else if (operator.get(x - 1).equals("x")) {
                            hasilDouble = hasilDouble * Double.parseDouble(nilai.get(x));
                        } else if (operator.get(x - 1).equals(":")) {
                            hasilDouble = hasilDouble / Double.parseDouble(nilai.get(x));
                        } else if (operator.get(x - 1).equals("%")) {
                            hasilDouble = hasilDouble % Double.parseDouble(nilai.get(x));
                        }
                    } else {
                        hasilDouble = Double.parseDouble(nilai.get(x));
                    }
                }

                textScreenTop.setText(text_screen);
                textScreen.setText(String.valueOf(hasilDouble));
                text_screen = String.valueOf(hasilDouble);
            } else {
                for (int x = 0; x < nilai.size(); x++) {
                    if (x > 0) {
                        if (operator.get(x - 1).equals("+")) {
                            hasilInt = hasilInt + Integer.parseInt(nilai.get(x));
                        } else if (operator.get(x - 1).equals("-")) {
                            hasilInt = hasilInt - Integer.parseInt(nilai.get(x));
                        } else if (operator.get(x - 1).equals("x")) {
                            hasilInt = hasilInt * Integer.parseInt(nilai.get(x));
                        } else if (operator.get(x - 1).equals(":")) {
                            hasilInt = hasilInt / Integer.parseInt(nilai.get(x));
                        } else if (operator.get(x - 1).equals("%")) {
                            hasilInt = hasilInt % Integer.parseInt(nilai.get(x));
                        }
                    } else {
                        hasilInt = Integer.parseInt(nilai.get(x));
                    }
                }

                textScreenTop.setText(text_screen);
                textScreen.setText(String.valueOf(hasilInt));
                text_screen = String.valueOf(hasilInt);
            }
        }
    }

    public void reStart() {
        for (int j = nilai.size() - 1; j >= 1; j--) {
            nilai.remove(j);
        }

        for (int j = operator.size() - 1; j >= 1; j--) {
            operator.remove(j);
        }

        angka = false;
        calculate = false;
        op = false;
        last = false;
        pertama = true;
        hasilInt = 0;
        hasilDouble = 0;
        comma_counter = 0;
        text_screen = "";
        textScreenTop.setText(text_screen);
        textScreen.setText("0");
        stack_counter = 0;
        nilai.set(0, "");
        operator.set(0, "");
    }

    public void setAngka(String nomor) {
        if (angka == false) {
            nilai.set(stack_counter, nilai.get(stack_counter) + nomor);
            operasi = false;
            last = true;
            setScreen();
        } else {
            reStart();
            nilai.set(stack_counter, nilai.get(stack_counter) + nomor);
            operasi = false;
            last = true;
            setScreen();
        }
    }

    public void setOperasi(String simbol) {
        if (operasi == false) {
            operator.set(stack_counter, simbol);
            nilai.add("");
            operator.add("");
            stack_counter++;
            setScreen();
            operasi = true;
            angka = false;
            last = false;
            comma_counter = 0;
        } else {
            stack_counter--;
            nilai.remove(nilai.size() - 1);
            operator.remove(operator.size() - 1);
            operator.set(stack_counter, simbol);
            nilai.add("");
            operator.add("");
            stack_counter++;
            setScreen();
            operasi = true;
            angka = false;
            last = false;
            comma_counter = 0;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button0) {
            setAngka("0");
        } else if (v.getId() == R.id.button1) {
            setAngka("1");
        } else if (v.getId() == R.id.button2) {
            setAngka("2");
        } else if (v.getId() == R.id.button3) {
            setAngka("3");
        } else if (v.getId() == R.id.button4) {
            setAngka("4");
        } else if (v.getId() == R.id.button5) {
            setAngka("5");
        } else if (v.getId() == R.id.button6) {
            setAngka("6");
        } else if (v.getId() == R.id.button7) {
            setAngka("7");
        } else if (v.getId() == R.id.button8) {
            setAngka("8");
        } else if (v.getId() == R.id.button9) {
            setAngka("9");
        } else if (v.getId() == R.id.buttonSamaDengan) {
            operator.set(stack_counter, "");
            operasi = false;
            setResult();
        } else if (v.getId() == R.id.buttonKoma) {
            if (comma_counter == 0) {
                nilai.set(stack_counter, nilai.get(stack_counter) + ".");
                comma_counter = 1;
                setScreen();
            }
        } else if (v.getId() == R.id.buttonTambah) {
            setOperasi("+");
        } else if (v.getId() == R.id.buttonKurang) {
            setOperasi("-");
        } else if (v.getId() == R.id.buttonKali) {
            setOperasi("x");
        } else if (v.getId() == R.id.buttonBagi) {
            setOperasi(":");
        } else if (v.getId() == R.id.buttonPersen) {
            setOperasi("%");
        } else if (v.getId() == R.id.buttonDel) {
            if (nilai.get(0).equals("")) {
                reStart();
            } else {
                if (last == true) {
                    String subString = nilai.get(stack_counter);
                    subString = subString.substring(0, subString.length() - 1);
                    nilai.set(stack_counter, subString);
                    setScreen();
                    if (subString.equals("")) {
                        last = false;
                    }
                } else {
                    operator.remove(stack_counter);
                    operator.set(0, "");
                    stack_counter--;
                    setScreen();
                    last = true;
                }
            }
        } else if (v.getId() == R.id.buttonC) {
            reStart();
        }
    }
}
