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
		ScapegoatTree tree = new ScapegoatTree(0.5, false);
		for(int i = 0; i < size; i++) {
			char operation = lines.get(i).charAt(0);
			int number = Integer.parseInt(lines.get(i).substring(2));
			switch (operation) {
				case 'I':	System.out.println(tree.insert(number));
							break;
				case 'D': 	System.out.println(tree.delete(number));
							break;
				case 'S': 	System.out.println(tree.search(number));
							break;
				default:	System.out.println("unknown operation");		
			}
		}

	}
}