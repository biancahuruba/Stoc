package exemplu.functionalitati.stoc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JTextField;

public class TextFieldModel extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[][] data;
	public static final File fileFields = new File("RowFields.txt");
	private int RowData;
	private int columnNumber;

	public TextFieldModel() {
		super();
		RowData = readRowNumber(fileFields);
		columnNumber = 4;
		data = new String[RowData][columnNumber];
	}

	public TextFieldModel(int newColumnNr) {
		super();
		columnNumber = newColumnNr;
		data = new String[RowData][columnNumber];
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex - 1][columnIndex - 1];
	}

	public void add(String[] list) {
		int row = readRowNumber(fileFields);
		for (int column = 0; column < columnNumber; column++) {
			setValueAt(list[column], row - 1, column);
		}
		row++;
		display(data);
		saveRowNumeber(row, fileFields);

	}

	private void display(String[][] data) {
		for (int row = 0; row < readRowNumber(fileFields); row++) {
			for (int column = 0; column < 4; column++) {
				System.out.print(data[row][column] + " ");
			}
			System.out.println("");
		}

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = (String) aValue;
	}

	private void saveRowNumeber(int row, File file) {
		FileWriter fileWriter;
		try {
			PrintWriter writ = new PrintWriter(file);
			writ.print("");
			writ.close();

			fileWriter = new FileWriter(file);
			fileWriter.write("" + row);
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int readRowNumber(File file) {

		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			StringBuffer stringBuffer = new StringBuffer();
			int numCharsRead;
			char[] charArray = new char[1024];
			while ((numCharsRead = fileReader.read(charArray)) > 0) {
				stringBuffer.append(charArray, 0, numCharsRead);
			}
			fileReader.close();
			return Integer.parseInt(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;

	}

}
