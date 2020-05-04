import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import br.mia.model.WellFormedFormula;
import br.mia.parser.ParseException;
import br.mia.parser.Parser;
import br.mia.parser.SimpleNode;
import br.mia.parser.WffListBuilder;

public class Main {

	public static void main(String[] args) {
		String fileName = "teste3.txt";
		File file = new File(fileName);
		try {
			InputStream is = new FileInputStream(file);
			Parser parser = new Parser(is);
			SimpleNode root = parser.start();
			WffListBuilder wffListBuilder = new WffListBuilder();
			List<WellFormedFormula> wffList = wffListBuilder.buildWffList(root);
			WellFormedFormula lastWff = wffList.get(wffList.size() - 1);
			
			System.out.println("Original wffs");
			for (WellFormedFormula wff : wffList)
				if (wff != lastWff)
					System.out.println(wff + ",");
			System.out.println(lastWff);
			
			System.out.println();
			System.out.println("Wffs in prenex normal form");
			for (WellFormedFormula wff : wffList)
				if (wff != lastWff)
					System.out.println(wff.toPrenexNormalForm() + ",");
			System.out.println(lastWff.toPrenexNormalForm());
			
			System.out.println();
			System.out.println("Wffs in skolem normal form");
			for (WellFormedFormula wff : wffList)
				if (wff != lastWff)
					System.out.println(wff.toSkolemNormalForm() + ",");
			System.out.println(lastWff.toSkolemNormalForm());
			
			System.out.println();
			System.out.println("Wffs in conjunctive normal form");
			for (WellFormedFormula wff : wffList)
				if (wff != lastWff)
					System.out.println(wff.toConjunctiveNormalForm() + ",");
			System.out.println(lastWff.toConjunctiveNormalForm());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}