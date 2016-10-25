/**************************************
* Henrik Schulz - 2016                *
* DM803 - Advanced Datastructures     *
**************************************/

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Program {
	
	public static void main(String[] args){
		if (args.length == 0) {
			System.out.println("Missing input file");
			System.exit(0);
		}
		if (args.length > 1) {
			System.out.println("Too many arguments");
			System.exit(0);
		}
		List<String> lines = new ArrayList<String>();
		try{
			lines = Files.readAllLines(Paths.get(args[0]), Charset.defaultCharset());
		}
		catch (IOException e){
			System.out.println("Something went wrong");
		}	
		
		int size = lines.size();
		ScapegoatTree tree = new ScapegoatTree(0.5);

		try {

			File file = new File("output.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i = 0; i < size; i++) {
			char operation = lines.get(i).charAt(0);
			int number = Integer.parseInt(lines.get(i).substring(2));
			switch (operation) {
				case 'I':	if (tree.insert(number)) {
								bw.write("S\n");
							}
							else {
								bw.write("F\n");
							}
							break;
				case 'D': 	if (tree.delete(number)) {
								bw.write("S\n");
							}
							else {
								bw.write("F\n");
							}
							break;
				case 'S': 	if (tree.delete(number)) {
								bw.write("S\n");
							}
							else {
								bw.write("F\n");
							}
							break;
				default:	System.out.println("unknown operation");		
			}
		}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}