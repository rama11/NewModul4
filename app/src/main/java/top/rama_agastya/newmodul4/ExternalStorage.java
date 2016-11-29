package top.rama_agastya.newmodul4;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExternalStorage extends AppCompatActivity {

    private static final int READ_BLOCK_SIZE = 100;
    @BindView(R.id.edtText)
    EditText editText;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.btnLoad)
    Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        ButterKnife.bind(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/Filesss");
                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    File file = new File(dir, "Text.txt");
                    FileOutputStream fOut = new FileOutputStream(file);

                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                    osw.write(editText.getText().toString());

                    osw.flush();
                    osw.close();

                    Toast.makeText(getBaseContext(), "File Saved Successfully", Toast.LENGTH_SHORT).show();

                    editText.setText("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Toast.makeText(getBaseContext(), "You haven't External Storage", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/Filesss");
                    File file = new File(dir, "Text.txt");

                    FileInputStream fIn = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fIn);
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    String s = "";
                    int charRead;
                    while ((charRead = isr.read(inputBuffer)) > 0) {
                        s += String.copyValueOf(inputBuffer, 0, charRead);
                        inputBuffer = new char[READ_BLOCK_SIZE];
                    }
                    editText.setText(s);
                    Toast.makeText(getBaseContext(), "File loaded successfully", Toast.LENGTH_SHORT).show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
    }
}
