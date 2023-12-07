import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RecordRW {
    public List records;

    public RecordRW() throws IOException {
        records = new LinkedList();
        this.read();
    }

    public boolean newRecord(int time) throws IOException {
        read();
        records.add(time);
        records.sort(Comparator.naturalOrder());
        write();
        if ((int)records.get(0) == time){
            return true;
        }
        return false;
    }
    private void write() throws IOException {

        String dir = ".\\temp.txt";
        File file = new File(dir);
        if (!file.exists()){
            file.createNewFile();
        }

        FileWriter writer = new FileWriter(file);
        for (Object i : records){
            writer.write(i.toString() + "\n");
        }
        writer.flush();
        writer.close();
    }
    private void read() throws IOException {
        String dir = ".\\temp.txt";
        File file = new File(dir);
        if (!file.exists()){
            file.createNewFile();
        }
        char[] chars = new char[100];
        FileReader reader = new FileReader(file);
        reader.read(chars);
        int time = 0;
        for (char c : chars){
            if (c == '\n'){
                records.add(time);
                time = 0;
            } else if (c == '\0') {
                break;
            } else {
                time *= 10;
                time += c - 48;
            }
        }

        reader.close();
    }
}


