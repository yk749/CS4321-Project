package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import operators.ScanOperater;
import util.Catalog;
import util.myTable;

public class ScanTest {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Catalog.getInstance();
		try {
			CCJSqlParser parser = new CCJSqlParser(Catalog.getQueryFiles());
			Statement statement;
			int count = 1;
			while ((statement = parser.Statement()) != null) {
				System.out.println("Read statement: " + statement);
				Select select = (Select) statement;
				PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
				ScanOperater s = new ScanOperater(new myTable(plainSelect.getFromItem()));
				
//				System.out.println(s.getNextTuple());
//				System.out.println(s.getNextTuple());
//				System.out.println(s.getNextTuple());
//				s.reset();
//				System.out.println(s.getNextTuple());
//				System.out.println(s.getNextTuple());
//				s.reset();
				
				System.out.println("\nStart dumping...");
				PrintStream ps = new PrintStream(new File("output" + String.valueOf(count)));
				s.dump(ps);
				System.out.println("Dumping finished !");
				count++;
			}
		} catch (Exception e) {
			System.err.println("Exception occurred during parsing");
			e.printStackTrace();
		}
	}

}
